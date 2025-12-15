package com.example.analyticsservice.dto;

import java.util.Map;

public class StatsResponse {
    private Map<String, Long> eventCounts;

    public StatsResponse(Map<String, Long> eventCounts) {
        this.eventCounts = eventCounts;
    }

    public Map<String, Long> getEventCounts() { return eventCounts; }
}