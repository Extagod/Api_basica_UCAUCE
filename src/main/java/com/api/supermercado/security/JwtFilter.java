package com.api.supermercado.security;

import com.api.supermercado.enums.RoleEnum;
import com.api.supermercado.repositories.PersonRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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
        System.out.println("➡️ Request: " + request.getMethod() + " " + path);

        // 🔥 EXCLUIR RUTAS PUBLICAS
        if (isPublicRoute(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 🔍 Leer Authorization header
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);
        String username = jwtService.extractUsername(token);

        // Validar token
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            var person = personRepository.findByUsername(username);

            if (person.isPresent() && jwtService.isTokenValid(token, username)) {

                // 🔥 Obtener roles desde el TOKEN
                List<String> roleNames = jwtService.extractClaim(token, c -> (List<String>) c.get("roles"));

                // Convertir roles → Authorities
                var authorities = roleNames.stream()
                        .map(r -> new SimpleGrantedAuthority("ROLE_" + r))
                        .collect(Collectors.toList());

                // Crear userDetails manualmente
                var userDetails = org.springframework.security.core.userdetails.User
                        .withUsername(username)
                        .password(person.get().getPassword())
                        .authorities(authorities)
                        .build();

                // Autenticar usuario
                var authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        authorities
                );

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);

                System.out.println("🔐 Usuario autenticado con roles: " + roleNames);
            }
        }

        filterChain.doFilter(request, response);
    }

    // 🔥 RUTAS PUBLICAS
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
