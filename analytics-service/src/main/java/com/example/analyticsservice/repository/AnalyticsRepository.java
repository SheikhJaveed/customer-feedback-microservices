package com.example.analyticsservice.repository;

import com.example.analyticsservice.entity.AnalyticsEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface AnalyticsRepository extends JpaRepository<AnalyticsEvent, Long> {
    List<AnalyticsEvent> findByEventType(String eventType);

    // Count events grouped by type
    @Query("SELECT e.eventType, COUNT(e) FROM AnalyticsEvent e GROUP BY e.eventType")
    List<Object[]> countEventsByType();
}