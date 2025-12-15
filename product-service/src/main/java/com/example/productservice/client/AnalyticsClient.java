package com.example.productservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "analytics-service")
public interface AnalyticsClient {

    @PostMapping("/analytics/events")
    void logEvent(@RequestHeader("Authorization") String token, @RequestBody AnalyticsEventDto event);
}