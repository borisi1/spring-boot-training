package dev.usdev.spring.boot.training.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable() // not relevant for GET index
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // allow everyone
                );
        return httpSecurity.build();
    }
}
