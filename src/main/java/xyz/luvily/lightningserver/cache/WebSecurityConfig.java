package xyz.luvily.lightningserver.cache;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors()
                .and()
                .csrf().disable();

        httpSecurity.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/users/**").hasRole("ADMIN")
                .antMatchers("/capes/texture/**").permitAll()
                .antMatchers("/capes/**").permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManager) throws Exception {
        BCryptPasswordEncoder encoder = getPasswordEncoder();

        // Creating user with username 'shitzuu' and password 'hello-world'
        authenticationManager.inMemoryAuthentication()
                .withUser("shitzuu")
                .password(encoder.encode("hello-world"))
                .roles("ADMIN");
    }

    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
