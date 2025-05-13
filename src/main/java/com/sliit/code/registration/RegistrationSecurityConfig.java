package com.sliit.code.registration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class RegistrationSecurityConfig {

    @Bean
    @Order(1)
    public SecurityFilterChain registrationSecurityChain(HttpSecurity http) throws Exception {
        http
                // only apply this chain to /api/register
                .antMatcher("/api/register")
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()     // allow POST /api/register without auth
                );
        return http.build();
    }
}
