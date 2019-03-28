package nc.test.config.parent;

import nc.test.security.CustomAuthenticationProvider;
import nc.test.security.MyBasicAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

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
    private CustomAuthenticationProvider authProvider;

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
/*        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/auth/role").hasAnyRole("CUSTOMER", "ADMIN", "DRIVER", "OPERATOR")
                .antMatchers("/orders/*").permitAll()
                .antMatchers("/customer/name").permitAll()//Когда решил забить на security
                .antMatchers("/auth/").permitAll()
                // /admin
                .anyRequest().permitAll()
                .and().httpBasic()
                .and().logout();*/

        http
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .httpBasic()
                .authenticationEntryPoint(authenticationEntryPoint);
    }
}
