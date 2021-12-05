package com.java.ph3.halinamarket.security_login.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/order/lines").hasAnyRole("ADMIN", "USER")
                .antMatchers("/add/delivery").hasAnyRole("ADMIN", "USER")
                .antMatchers("/add/delivery/address").hasAnyRole("ADMIN", "USER")
                .antMatchers("/orders").hasAnyRole("ADMIN", "USER")
                .antMatchers("/order/view").hasAnyRole("ADMIN", "USER")
                .antMatchers("/order/lines/product/edit/{id}").hasAnyRole("ADMIN", "USER")
                .antMatchers("/order/lines/product/delete/{id}").hasAnyRole("ADMIN", "USER")
                .antMatchers("/category/prod/{id}").hasAnyRole("ADMIN", "USER")
                .antMatchers("/product/details/{id}").hasAnyRole("ADMIN", "USER")
                .antMatchers("/search").hasAnyRole("ADMIN", "USER")
                .antMatchers("/category/sub/{id}").hasAnyRole("ADMIN", "USER")
                .antMatchers("/home/loggedin").hasAnyRole("ADMIN", "USER")
                .antMatchers("/category/add").hasRole("ADMIN")
                .antMatchers("/product/add").hasRole("ADMIN")
                .antMatchers("/home").permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home/loggedin", true)
                .usernameParameter("email")
                .and()
                .httpBasic()
                .and()
                .logout()
                .logoutUrl("/logout")
                .and()
                .headers().frameOptions().disable()
                .and()
                .csrf().disable();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
