package com.example.feedbackservice.security;

import com.example.feedbackservice.config.JwtConfig;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private final Key key;

    public JwtUtil(JwtConfig jwtConfig) {
        this.key = Keys.hmacShaKeyFor(jwtConfig.getJwtSecret().getBytes(StandardCharsets.UTF_8));
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public List<String> extractRoles(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody();
        String roles = (String) claims.get("roles");
        if (roles == null || roles.isEmpty()) return List.of();
        return List.of(roles.split(","));
    }
}