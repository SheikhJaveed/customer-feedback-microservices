package com.example.productservice.client;

public class AnalyticsEventDto {
    private String eventType;
    private String metadata;

    public AnalyticsEventDto(String eventType, String metadata) {
        this.eventType = eventType;
        this.metadata = metadata;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }
}