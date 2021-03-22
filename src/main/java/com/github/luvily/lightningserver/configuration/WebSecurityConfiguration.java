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

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // Disabling cors and csrf
                .cors().and().csrf().disable()
                // Setting authentication method to http basic
                .httpBasic().and()
                // Authorizing requests
                .authorizeRequests()
                .antMatchers(
                        "/users/**"
                ).hasRole("ADMIN")
                .antMatchers(
                        "/users"
                ).hasRole("ADMIN")
                .antMatchers(
                        "/capes/texture/**"
                ).hasRole("ADMIN")
                .antMatchers(
                        "/capes/**"
                ).permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = passwordEncoder();

        auth.inMemoryAuthentication()
                .withUser("shitzuu")
                .password(passwordEncoder.encode("hello-world"))
                .roles("ADMIN");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
