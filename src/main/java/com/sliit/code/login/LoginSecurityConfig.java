package com.sliit.code.login;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class LoginSecurityConfig {

    @Bean
    @Order(2)
    public SecurityFilterChain loginSecurityChain(HttpSecurity http) throws Exception {
        http
                // only apply this chain to /login
                .antMatcher("/login")
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()     // allow POST /login without auth
                )
                .httpBasic();                    // enable basic auth for everything else
        return http.build();
    }
}
