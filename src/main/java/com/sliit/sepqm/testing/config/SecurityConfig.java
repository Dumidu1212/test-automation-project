package com.sliit.sepqm.testing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // LET ALL /api/** THROUGH
                        .requestMatchers("/api/**").permitAll()
                        // (if you have any other MVC endpoints you want secured, you can scope them here)
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}
