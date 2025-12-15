package com.example.userservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtConfig {

    // You can also set these values in application.yml and inject with @Value
    @Value("${app.jwt.secret:changeit-changeit-changeit-changeit-please-change}")
    private String jwtSecret;

    @Value("${app.jwt.expiration-ms:86400000}") // default 24 hours
    private long jwtExpirationMs;

    public String getJwtSecret() {
        return jwtSecret;
    }

    public long getJwtExpirationMs() {
        return jwtExpirationMs;
    }
}
