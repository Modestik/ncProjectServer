package nc.test.config.parent;

import nc.test.security.*;
import nc.test.service.SessionService;
import nc.test.service.UserService;
import nc.test.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.GenericFilterBean;

@Configuration
@EnableWebSecurity //включает поддержку web security и обеспечивает интеграцию со Spring MVC
@ComponentScan("nc.test.security")
@ComponentScan("nc.test.service")
@PropertySource("classpath:log4j.properties")
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public SecurityConfig() {
        super();
    }

    @Autowired
    private MyBasicAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private MyBasicAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private MyBasicAuthenticationProvider authProvider;

    @Autowired
    private UserService userService;

    @Autowired
    private SessionService sessionService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/auth/*").hasAnyRole("CUSTOMER", "ADMIN", "DRIVER", "OPERATOR")
                .antMatchers("/orders/*").permitAll()
                .antMatchers("/customer/name").permitAll()
                .antMatchers("/car/*").hasAnyRole("ADMIN")
                .antMatchers("/user/employees/*").hasAnyRole("ADMIN")
                .antMatchers("/user/customers").permitAll()
                .antMatchers("/price").permitAll()
                .anyRequest().permitAll()
                //.and()
                //.httpBasic()
                //.authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                .and()
                .logout()
                .logoutUrl("/auth/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");

        http.addFilterBefore(
                new MyBasicAuthenticationFilter(authenticationManager(),
                        authenticationEntryPoint,
                        sessionService,
                        userService),
                BasicAuthenticationFilter.class);

    }
}
