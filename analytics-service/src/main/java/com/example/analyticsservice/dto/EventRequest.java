package com.example.analyticsservice.dto;

import jakarta.validation.constraints.NotBlank;

public class EventRequest {

    @NotBlank(message = "Event type is required")
    private String eventType;

    private String metadata;

    // Getters and Setters
    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }

    public String getMetadata() { return metadata; }
    public void setMetadata(String metadata) { this.metadata = metadata; }
}