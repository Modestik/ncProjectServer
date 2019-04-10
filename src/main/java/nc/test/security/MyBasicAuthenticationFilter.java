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
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.Charset;
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
            //Заголовок
            String head = header.startsWith(Sessions.BASIC) ? Sessions.BASIC : Sessions.SESSION;

            String[] tokens = extractAndDecodeHeader(header, head);

            String username = tokens[0];    //login
            String password;                //password
            String id;                      //id session

            if (head.equals(Sessions.BASIC)) {  //создаем сессию в бд
                password = tokens[1];
                HttpSession httpSession = request.getSession();
                id = httpSession.getId();
                sessionService.createSession(id, username);
            } else {                            //просто апдейтим последнее действие
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                id = tokens[1];
                sessionService.updateSession(id);
                Users users = userService.getUserByLogin(username);
                password = users.getPassword();
            }

            if (authenticationIsRequired(username)) {   //контекст всегда очищается(?) и это срабатывает всегда
                //////////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
                Sessions sessions = sessionService.getSession(id);

                UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
                authRequest.setDetails(authenticationDetailsSource.buildDetails(request));

                Authentication authResult = authenticationManager.authenticate(authRequest);
                SecurityContextHolder.getContext().setAuthentication(authResult);

                //потом перепишу со StringBuilder'ом(но это не точно)
                String originalInput = sessions.getUsername() + ":" + sessions.getId();
                String token = Sessions.SESSION + Base64.getEncoder().encodeToString(originalInput.getBytes(Charset.forName("US-ASCII")));
                response.addHeader(HttpHeaders.AUTHORIZATION, token);
            }
        } catch (AuthenticationException failed) {
            SecurityContextHolder.clearContext();
            authenticationEntryPoint.commence(request, response, failed);
            return;
        }
        chain.doFilter(servletRequest, servletResponse);
    }

    private String[] extractAndDecodeHeader(String header, String flag) {

        byte[] base64Token = header.substring(flag.length()).getBytes(Charset.forName("US-ASCII"));
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
