package com.api.supermercado.security;

import com.api.supermercado.repositories.PersonRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final PersonRepository personRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String path = request.getServletPath();
        System.out.println("➡️  Incoming Request: " + request.getMethod() + " " + path);

        // 🔥 EXCLUSIÓN DE RUTAS PÚBLICAS
        if (isPublicRoute(request)) {
            System.out.println("🟢 Ruta pública → " + path + " → Saltando filtro JWT.");
            filterChain.doFilter(request, response);
            return;
        }

        // 🔍 Leer header Authorization
        String authHeader = request.getHeader("Authorization");
        System.out.println("🔍 Authorization Header: " + authHeader);

        // Sin token → permitir (solo será autenticado en rutas protegidas)
        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            System.out.println("⚠️  No token presente → continuando sin autenticación.");
            filterChain.doFilter(request, response);
            return;
        }

        // Extraer token
        String token = authHeader.substring(7);
        String username = jwtService.extractUsername(token);
        System.out.println("🧾 Username extraído: " + username);

        // Validar token
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            var person = personRepository.findByUsername(username);

            if (person.isPresent() && jwtService.isTokenValid(token, username)) {

                System.out.println("🔐 Token válido → autenticando...");

                var userDetails = org.springframework.security.core.userdetails.User
                        .withUsername(person.get().getUsername())
                        .password(person.get().getPassword())
                        .roles(person.get().getRoleId().name())  // 🔥 CORREGIDO
                        .build();

                var authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);

            } else {
                System.out.println("❌ Token inválido o usuario no encontrado");
            }
        }

        filterChain.doFilter(request, response);
    }

    // 🔥 FUNCION CORRECTA PARA EXCLUIR TODO LO PUBLICO
    private boolean isPublicRoute(HttpServletRequest request) {
        String path = request.getServletPath();

        return
                "OPTIONS".equalsIgnoreCase(request.getMethod()) ||
                        path.startsWith("/auth") ||
                        path.startsWith("/swagger-ui") ||
                        path.startsWith("/v3/api-docs") ||
                        path.equals("/error");
    }
}
