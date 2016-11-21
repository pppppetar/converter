package de.zooplus.converter.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by dragan on 20-Nov-16.
 */
@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"de.zooplus.converter"})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("ConverterUserDetailsService")
    private UserDetailsService converterUserDetailsService;


    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(converterUserDetailsService);
        auth.authenticationProvider(authenticationProvider());
//        auth.inMemoryAuthentication()
//                .withUser("11").password("11").roles("USER");
    }


//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(converterUserDetailsService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/resources/**").permitAll()
//                .anyRequest().authenticated()
////                .antMatchers("/", "/login", "/registration").permitAll()
////                .antMatchers("/home/**").hasAnyAuthority("AUTHENTICATED", "ROLE_AUTHENTICATED")
////                .antMatchers("/home/**").access("hasRole('AUTHENTICATED')")
////                .antMatchers("/", "/login", "/registration").permitAll()
//                .and().formLogin().loginPage("/login").permitAll()
////                .usernameParameter("email").passwordParameter("password")
//                .and().csrf().and().logout().permitAll()
//                .and().exceptionHandling().accessDeniedPage("/accessDenied");
        http
                .authorizeRequests()
                .anyRequest()
                .hasAnyAuthority("USER")
                .and()
                .formLogin();
//                .loginPage("/login")
//                .defaultSuccessUrl("/home")
//                .loginProcessingUrl("/login_process")
//                .failureUrl("/login?error=true");
//                .and()
//                .logout().logoutSuccessUrl("/login");
    }
}
