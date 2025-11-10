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

        // ✅ No interceptar login, register, error, y preflight
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())
                || path.startsWith("/auth")
                || path.equals("/error")) {

            System.out.println("🟢 Ruta pública detectada → " + path + " → Saltando filtro JWT.");
            filterChain.doFilter(request, response);
            return;
        }

        // ✅ Obtener header Authorization
        String authHeader = request.getHeader("Authorization");
        System.out.println("🔍 Authorization Header: " + authHeader);

        // ✅ Si no viene token → pasar, no cortar
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("⚠️  No token presente → continuando sin autenticación.");
            filterChain.doFilter(request, response);
            return;
        }

        // ✅ Extraer token y username
        String token = authHeader.substring(7);
        String username = jwtService.extractUsername(token);
        System.out.println("🧾 Username extraído del token: " + username);

        // ✅ Validación y autenticación
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            var person = personRepository.findByUsername(username);

            if (person.isEmpty()) {
                System.out.println("❌ Usuario no encontrado en la BD → " + username);
            } else {
                System.out.println("✅ Usuario encontrado en la BD → " + username);
            }

            if (person.isPresent() && jwtService.isTokenValid(token, username)) {

                System.out.println("🔐 Token válido → Autenticando usuario en el contexto de seguridad");

                var userDetails = org.springframework.security.core.userdetails.User
                        .withUsername(person.get().getUsername())
                        .password(person.get().getPassword())
                        .roles(person.get().getRole().toString())
                        .build();

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);
            } else {
                System.out.println("❌ Token inválido o expirado → usuario *NO autenticado*");
            }
        }

        System.out.println("➡️  Continuando la cadena de filtros...\n");
        filterChain.doFilter(request, response);
    }
}
