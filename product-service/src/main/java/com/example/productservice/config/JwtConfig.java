package com.example.productservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtConfig {
    @Value("${app.jwt.secret}")
    private String jwtSecret;

    public String getJwtSecret() { return jwtSecret; }
}