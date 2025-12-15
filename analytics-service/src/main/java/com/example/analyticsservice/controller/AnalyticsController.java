package com.example.analyticsservice.controller;

import com.example.analyticsservice.dto.EventRequest;
import com.example.analyticsservice.dto.EventResponse;
import com.example.analyticsservice.dto.StatsResponse;
import com.example.analyticsservice.service.AnalyticsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    private final AnalyticsService service;

    public AnalyticsController(AnalyticsService service) {
        this.service = service;
    }

    @PostMapping("/events")
    public ResponseEntity<EventResponse> logEvent(@Valid @RequestBody EventRequest request,
                                                  Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(service.logEvent(username, request));
    }

    @GetMapping
    public ResponseEntity<List<EventResponse>> getAllEvents() {
        return ResponseEntity.ok(service.getAllEvents());
    }

    @GetMapping("/stats")
    public ResponseEntity<StatsResponse> getStats() {
        return ResponseEntity.ok(service.getStats());
    }
}