package com.example.feedbackservice.client;

public class AnalyticsEventDto {
    private String eventType;
    private String metadata;

    public AnalyticsEventDto(String eventType, String metadata) {
        this.eventType = eventType;
        this.metadata = metadata;
    }
    public String getEventType() { return eventType; }
    public String getMetadata() { return metadata; }
}