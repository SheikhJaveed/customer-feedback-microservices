package com.example.analyticsservice.service;

import com.example.analyticsservice.dto.EventRequest;
import com.example.analyticsservice.dto.EventResponse;
import com.example.analyticsservice.dto.StatsResponse;
import com.example.analyticsservice.entity.AnalyticsEvent;
import com.example.analyticsservice.repository.AnalyticsRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnalyticsService {

    private final AnalyticsRepository repository;

    public AnalyticsService(AnalyticsRepository repository) {
        this.repository = repository;
    }

    public EventResponse logEvent(String username, EventRequest req) {
        AnalyticsEvent event = new AnalyticsEvent();
        event.setEventType(req.getEventType());
        event.setUsername(username);
        event.setMetadata(req.getMetadata());

        AnalyticsEvent saved = repository.save(event);
        return mapToDto(saved);
    }

    public List<EventResponse> getAllEvents() {
        return repository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public StatsResponse getStats() {
        List<Object[]> results = repository.countEventsByType();
        Map<String, Long> counts = new HashMap<>();
        for (Object[] result : results) {
            String type = (String) result[0];
            Long count = (Long) result[1];
            counts.put(type, count);
        }
        return new StatsResponse(counts);
    }

    private EventResponse mapToDto(AnalyticsEvent e) {
        return new EventResponse(e.getId(), e.getEventType(), e.getUsername(), e.getMetadata(), e.getTimestamp());
    }
}