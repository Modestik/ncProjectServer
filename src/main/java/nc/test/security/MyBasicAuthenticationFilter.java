package nc.test.security;

import nc.test.model.Sessions;
import nc.test.model.Users;
import nc.test.service.SessionService;
import nc.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

public class MyBasicAuthenticationFilter extends GenericFilterBean {

    private MyBasicAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private UserService userService;

    private AuthenticationManager authenticationManager;

    private AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource = new WebAuthenticationDetailsSource();

    public MyBasicAuthenticationFilter(AuthenticationManager authenticationManager,
                                       MyBasicAuthenticationEntryPoint authenticationEntryPoint,
                                       SessionService sessionService,
                                       UserService userService) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.sessionService = sessionService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String header = request.getHeader("Authorization");

        if (header == null || (!header.startsWith(Sessions.BASIC) && !header.startsWith(Sessions.SESSION))) {
            chain.doFilter(request, response);
            return;
        }
        try {
            String[] tokens = extractAndDecodeHeader(header);

            assert tokens.length == 2;

            String username = tokens[0];

            if (header.startsWith(Sessions.BASIC)) {
                Users users = userService.getUserByLogin(username);
                String passwordFromRequest = tokens[1];
                String passwordFromDB = users.getPassword();
                if (!BCrypt.checkpw(passwordFromRequest, passwordFromDB)) {
                    //хотел кинуть throw AuthenticationException , но что-то пошло не так
                    authenticationEntryPoint.commence(request, response , null);
                    return;
                }

                sessionService.createSession(username);
                Sessions sessions = sessionService.getSession(username);

                //////////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
                UsernamePasswordAuthenticationToken authRequest =
                        new UsernamePasswordAuthenticationToken(username, passwordFromRequest);
                authRequest.setDetails(authenticationDetailsSource.buildDetails(request));

                Authentication authResult = authenticationManager.authenticate(authRequest);
                SecurityContextHolder.getContext().setAuthentication(authResult);

                //потом перепишу со StringBuilder'ом
                String originalInput = sessions.getUsername() + ":" + sessions.getId();
                String token = Sessions.SESSION + " " + java.util.Base64.getEncoder().encodeToString(originalInput.getBytes());
                response.addHeader(HttpHeaders.AUTHORIZATION, token);

            } else {
                Sessions sessions = sessionService.getSession(username);
                sessionService.updateSession(sessions.getId());
            }

            //Authentification
            if (authenticationIsRequired(username)) {
                //по идеи он должен создаваться только при авторизации
                String breakpoint;
               /* UsernamePasswordAuthenticationToken authRequest =
                        new UsernamePasswordAuthenticationToken(username, password);
                authRequest.setDetails(authenticationDetailsSource.buildDetails(request));

                Authentication authResult = authenticationManager.authenticate(authRequest);
                System.out.println("До установления контекста" + request.getRequestURI());
                SecurityContextHolder.getContext().setAuthentication(authResult);
                onSuccessfulAuthentication(request, response, authResult, username, authOption);*/
            }
        } catch (AuthenticationException failed) {
            SecurityContextHolder.clearContext();
            authenticationEntryPoint.commence(request, response, failed);
            return;
        }

        chain.doFilter(servletRequest, servletResponse);
    }

    /**
     * Метод декодирующий Base64
     *
     * @param header - Authentication
     * @return
     * @throws IOException
     */
    private String[] extractAndDecodeHeader(String header) throws IOException {

        byte[] base64Token = header.substring(6).getBytes("UTF-8");
        byte[] decoded;
        try {
            decoded = Base64.getDecoder().decode(base64Token);
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException(
                    "Failed to decode basic authentication token");
        }

        String token = new String(decoded);

        int delim = token.indexOf(":");

        if (delim == -1) {
            throw new BadCredentialsException("Invalid basic authentication token");
        }
        return new String[]{token.substring(0, delim), token.substring(delim + 1)};
    }

    private boolean authenticationIsRequired(String username) {

        Authentication existingAuth = SecurityContextHolder.getContext().getAuthentication();

        if (existingAuth == null || !existingAuth.isAuthenticated()) {
            return true;
        }

        if (existingAuth instanceof UsernamePasswordAuthenticationToken && !existingAuth.getName().equals(username)) {
            return true;
        }

        if (existingAuth instanceof AnonymousAuthenticationToken) {
            return true;
        }

        return false;
    }
}
