package com.example.analyticsservice.dto;

import java.time.LocalDateTime;

public class EventResponse {
    private Long id;
    private String eventType;
    private String username;
    private String metadata;
    private LocalDateTime timestamp;

    public EventResponse(Long id, String eventType, String username, String metadata, LocalDateTime timestamp) {
        this.id = id;
        this.eventType = eventType;
        this.username = username;
        this.metadata = metadata;
        this.timestamp = timestamp;
    }

    // Getters
    public Long getId() { return id; }
    public String getEventType() { return eventType; }
    public String getUsername() { return username; }
    public String getMetadata() { return metadata; }
    public LocalDateTime getTimestamp() { return timestamp; }
}