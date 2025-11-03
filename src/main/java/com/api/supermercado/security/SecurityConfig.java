package com.api.supermercado.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
@Profile("dev")
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desactiva CSRF (importante para POST)
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()) // Permite TODO
                .formLogin(form -> form.disable()) // Quita login por formulario
                .httpBasic(basic -> basic.disable()); // Quita auth básica

        return http.build();
    }
}
