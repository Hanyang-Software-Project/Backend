package com.ziggs.ziggs_backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        return http.build();
    }
}




/*
@Configuration
@EnableWebSecurity


public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF for APIs
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/users").permitAll()  // Allow POST requests to /api/users without authentication
                        .requestMatchers(HttpMethod.GET, "/users/**").permitAll()  // Allow GET requests to /api/users/userDTO/{id} without authentication
                        .requestMatchers(HttpMethod.DELETE, "/users/*").permitAll()
                        .anyRequest().authenticated()  // Require authentication for all other requests
                )
                .httpBasic(withDefaults());  // Enable HTTP Basic auth with defaults

        return http.build();
    }
}

*/
