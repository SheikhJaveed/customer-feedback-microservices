package com.example.userservice.security;

import com.example.userservice.config.JwtConfig;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private final JwtConfig jwtConfig;
    private final Key key;

    public JwtUtil(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
        this.key = Keys.hmacShaKeyFor(jwtConfig.getJwtSecret().getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String subject, Set<String> roles) {
        long now = System.currentTimeMillis();
        Date issuedAt = new Date(now);
        Date expiry = new Date(now + jwtConfig.getJwtExpirationMs());

        String rolesCsv = String.join(",", roles);

        return Jwts.builder()
                .setSubject(subject)
                .claim("roles", rolesCsv)
                .setIssuedAt(issuedAt)
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
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
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public Set<String> extractRoles(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody();
        Object rolesObj = claims.get("roles");
        if (rolesObj == null) return Set.of();
        String csv = rolesObj.toString();
        return Set.of(csv.split(","));
    }
}
