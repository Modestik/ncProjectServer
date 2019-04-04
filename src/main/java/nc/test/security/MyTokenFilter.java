package nc.test.security;


import nc.test.model.Users;
import nc.test.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class MyTokenFilter extends GenericFilterBean {

    private UserService userService;
    private String authTokenHeaderName = "x-auth-token";

    public MyTokenFilter(UserService userDetailsService) {
        this.userService = userDetailsService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws ServletException {
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            String authToken = httpServletRequest.getHeader(authTokenHeaderName);

            if (StringUtils.hasText(authToken)) {
                String username = TokenUtil.getUserNameFromToken(authToken);

                Users users = userService.getUserByLogin(username);

                if (TokenUtil.validateToken(authToken, users)) {
                    List<GrantedAuthority> authorities = new ArrayList<>();
                    authorities.add(new SimpleGrantedAuthority(users.getRole()));
                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(users,
                            users.getPassword(), authorities);
                    SecurityContextHolder.getContext().setAuthentication(token);
                }
            }

            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}