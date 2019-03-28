package nc.test.config.parent;

import nc.test.security.MyBasicAuthenticationProvider;
import nc.test.security.MyBasicAuthenticationEntryPoint;
import nc.test.security.MyBasicAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity //включает поддержку web security и обеспечивает интеграцию со Spring MVC
@ComponentScan("nc.test.security")
//@Import(DBConfig.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public SecurityConfig() {
        super();
    }

/*    @Autowired
    private DataSource dataSource;*/

    @Autowired
    private MyBasicAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private MyBasicAuthenticationProvider authProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
/*        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, true from users where username=?")
                .authoritiesByUsernameQuery("select username,'ROLE_' || role from users where username=?")
                .passwordEncoder(new BCryptPasswordEncoder());*/
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/auth/*").hasAnyRole("CUSTOMER", "ADMIN", "DRIVER", "OPERATOR")
                .antMatchers("/orders/*").permitAll()
                .antMatchers("/customer/name").permitAll()
                .antMatchers("/car/*").hasAnyRole( "ADMIN")
                .antMatchers("/user/*").hasAnyRole( "ADMIN")
                .antMatchers("/price").permitAll()
                .anyRequest().permitAll()
                .and().httpBasic()
                    .authenticationEntryPoint(authenticationEntryPoint)
                .and().logout()
                    .logoutUrl("/auth/logout")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID");


        //Фильтр
        http.addFilterBefore(
                new MyBasicAuthenticationFilter(), BasicAuthenticationFilter.class);
    }
}
