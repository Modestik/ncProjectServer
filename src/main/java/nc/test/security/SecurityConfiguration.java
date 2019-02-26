package nc.test.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;


@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static String REALM = "MY_TEST_REALM";

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(  "select email, " +
                                        "       password, " +
                                                "true as enabled " +
                                        "from users " +
                                        "where email=?")
                .authoritiesByUsernameQuery("select u.email, " +
                                            "       'ROLE_' || r.name" +
                                            "  from users u," +
                                            "       role r" +
                                            "  where u.id_role = r.id_role and u.email=?")
                .passwordEncoder(getPasswordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

  /*      http.csrf().disable();
        http.authorizeRequests()
               // .antMatchers("/order/**").hasRole("USER")
                .antMatchers("**secured/**").authenticated()
                .antMatchers("/auth").permitAll()
                .anyRequest().permitAll()
                .and()
                .formLogin().permitAll()
                .and().logout().permitAll()
                .and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint());
*/
        //  http.csrf().disable();
        http.authorizeRequests().anyRequest().hasAnyRole("ADMIN", "USER")
                .and()
                .httpBasic()
                .and().logout().permitAll()
        ;//.realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint());
    }

/*    @Bean
    public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint() {
        return new CustomBasicAuthenticationEntryPoint();
    }*/


    private PasswordEncoder getPasswordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return true;
            }
        };
    }
}
