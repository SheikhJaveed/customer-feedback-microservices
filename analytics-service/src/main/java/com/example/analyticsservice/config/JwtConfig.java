package com.example.analyticsservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtConfig {

    // MUST match the secret in user-service
    @Value("${app.jwt.secret:changeit-changeit-changeit-changeit-please-change}")
    private String jwtSecret;

    public String getJwtSecret() {
        return jwtSecret;
    }
}