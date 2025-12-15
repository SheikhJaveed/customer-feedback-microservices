package com.example.userservice.service;

import com.example.userservice.client.AnalyticsClient; // Import
import com.example.userservice.client.AnalyticsEventDto; // Import
import com.example.userservice.config.JwtConfig;
import com.example.userservice.dto.LoginRequest;
import com.example.userservice.dto.SignupRequest;
import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final JwtConfig jwtConfig;
    private final AnalyticsClient analyticsClient; // 1. Add Client

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       JwtUtil jwtUtil,
                       JwtConfig jwtConfig,
                       AnalyticsClient analyticsClient) { // 2. Inject Client
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.jwtConfig = jwtConfig;
        this.analyticsClient = analyticsClient;
    }

    // ... (signup method remains unchanged) ...
    public Map<String, Object> signup(SignupRequest req) {
        if (userRepository.existsByUsername(req.getUsername())) {
            throw new IllegalArgumentException("Username already taken");
        }
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }

        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER");

        User u = new User();
        u.setUsername(req.getUsername());
        u.setEmail(req.getEmail());
        u.setPassword(passwordEncoder.encode(req.getPassword()));
        u.setRoles(roles);

        User saved = userRepository.save(u);
        String token = jwtUtil.generateToken(saved.getUsername(), saved.getRoles());

        return Map.of("token", token, "expiresIn", jwtConfig.getJwtExpirationMs());
    }

    public Map<String, Object> login(LoginRequest req) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
        } catch (AuthenticationException ex) {
            throw new IllegalArgumentException("Invalid username/password");
        }

        User user = userRepository.findByUsername(req.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        String token = jwtUtil.generateToken(user.getUsername(), user.getRoles());

        // 3. Call Analytics Service
        try {
            AnalyticsEventDto event = new AnalyticsEventDto("USER_LOGIN", "User logged in successfully");
            analyticsClient.logEvent("Bearer " + token, event);
            System.out.println("Sent login event to Analytics Service");
        } catch (Exception e) {
            System.err.println("Failed to send analytics event: " + e.getMessage());
            // We catch the error so login doesn't fail just because analytics is down
        }

        return Map.of(
                "token", token,
                "expiresIn", jwtConfig.getJwtExpirationMs()
        );
    }
}