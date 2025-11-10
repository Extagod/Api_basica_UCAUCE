package com.api.supermercado.security;

import com.api.supermercado.repositories.PersonRepository;
import com.api.supermercado.entities.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final PersonRepository personRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        System.out.println("🔧 Configurando SecurityFilterChain...");

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/auth/**",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        .anyRequest().authenticated()
                )

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        System.out.println("✅ SecurityFilterChain configurado correctamente.\n");
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            System.out.println("🔍 Buscando usuario en la BD → " + username);

            Person p = personRepository.findByUsername(username)
                    .orElseThrow(() -> {
                        System.out.println("❌ Usuario no encontrado → " + username);
                        return new UsernameNotFoundException("Usuario no encontrado");
                    });

            System.out.println("✅ Usuario encontrado → " + username + " | role=" + p.getRole());

            return User.withUsername(p.getUsername())
                    .password(p.getPassword())
                    .roles(p.getRole().toString())
                    .build();
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        System.out.println("🔐 Creando AuthenticationProvider (DaoAuthenticationProvider)...");
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        System.out.println("✅ AuthenticationProvider listo.\n");
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        System.out.println("🚀 Obteniendo AuthenticationManager desde configuración.\n");
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        System.out.println("🧂 Registrando PasswordEncoder BCrypt.\n");
        return new BCryptPasswordEncoder();
    }
}
