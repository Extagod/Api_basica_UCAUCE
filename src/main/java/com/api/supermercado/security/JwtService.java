package com.api.supermercado.security;

import com.api.supermercado.enums.RoleEnum;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;

@Getter
@Service
public class JwtService {

    private static final String SECRET = "d8A$Qw!93Jkd82#@Zx1lC0vP9*Hq73lSd!9@LmWqZ"; // Debe tener 32+ chars

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    // ----------------------- EXTRACT USERNAME -----------------------
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // ----------------------- GENERIC EXTRACT -------------------------
    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return resolver.apply(claims);
    }

    // ---------------------- VALIDATE TOKEN ---------------------------
    public boolean isTokenValid(String token, String expectedUsername) {
        String username = extractUsername(token);
        return username.equals(expectedUsername) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        Date expiration = extractClaim(token, Claims::getExpiration);
        return expiration.before(new Date());
    }

    // ---------------------- GENERATE TOKEN ---------------------------
    public String generateToken(String username, List<RoleEnum> roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles.stream().map(Enum::name).toList());

        return buildToken(claims, username);
    }

    // ---------------------- BUILD TOKEN ------------------------------
    private String buildToken(Map<String, Object> claims, String username) {

        Instant now = Instant.now();
        Instant expiration = now.plus(60, ChronoUnit.MINUTES); // 60 min

        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiration))
                .signWith(getSigningKey())
                .compact();
    }

    public Instant extractExpiration(String token) {
        return extractClaim(token, claims -> claims.getExpiration().toInstant());
    }

}
