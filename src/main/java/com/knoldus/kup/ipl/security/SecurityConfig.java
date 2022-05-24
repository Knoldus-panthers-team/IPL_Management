package com.knoldus.kup.ipl.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(User
                .withUsername("admin")
                .password("$2a$10$2KXy6icbupG38tEnTeSPvuygSO8WykD0hGQ.Kj/yk.Rust7BfhZu6")
                .roles("ADMIN")
                .build());
        return userDetailsManager;
    }
    
    @Bean
    public PasswordEncoder getPassword(){
        return new BCryptPasswordEncoder();
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/ipl").permitAll()
                .antMatchers("/ipl/admin/**")
                .authenticated().antMatchers("/result/**")
                .authenticated().antMatchers("/players/**")
                .authenticated().antMatchers("/teams/**")
                .authenticated().antMatchers("/matches/**")
                .hasRole("ADMIN")
                .and()
                .formLogin()
                .and()
                .httpBasic();
    }
}
