package com.github.luvily.lightningserver.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String ADMIN_ROLE = "ADMIN";

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf().disable()
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/users/**").hasRole(ADMIN_ROLE)
                .antMatchers("/users").hasRole(ADMIN_ROLE)
                .antMatchers("/capes/texture/**").hasRole(ADMIN_ROLE)
                .antMatchers("/capes/**").permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = passwordEncoder();

        // Creating a new user "luvily : hello-world"
        auth.inMemoryAuthentication()
                .withUser("luvily")
                .password(passwordEncoder.encode("hello-world"))
                .roles(ADMIN_ROLE);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
