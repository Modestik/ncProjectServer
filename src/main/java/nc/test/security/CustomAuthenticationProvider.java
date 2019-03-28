package nc.test.security;

import nc.test.exception.NotFoundException;
import nc.test.model.Users;
import nc.test.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    public CustomAuthenticationProvider() {
        super();
    }

    @Autowired
    UserService userService;

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        try {
            String name = authentication.getName();
            String password = authentication.getCredentials().toString();

            Users user = userService.getUserByLogin(name);
            String passwordBcrypt = user.getPassword();

            if (BCrypt.checkpw(password, passwordBcrypt)) {
                final List<GrantedAuthority> grantedAuths = new ArrayList<>();
                grantedAuths.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
                final UserDetails principal = new User(name, password, grantedAuths);
                final Authentication auth = new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
                return auth;
            } else {
                return null;
            }
        } catch (NotFoundException e) {
            return null;
        }
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
