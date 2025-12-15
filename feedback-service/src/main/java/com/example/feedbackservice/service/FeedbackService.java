package com.example.feedbackservice.service;

import com.example.feedbackservice.client.AnalyticsClient;
import com.example.feedbackservice.client.AnalyticsEventDto;
import com.example.feedbackservice.dto.FeedbackRequest;
import com.example.feedbackservice.dto.FeedbackResponse;
import com.example.feedbackservice.entity.Feedback;
import com.example.feedbackservice.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackService {

    private final FeedbackRepository repository;
    private final AnalyticsClient analyticsClient;

    // Constructor Injection for Repository and Feign Client
    public FeedbackService(FeedbackRepository repository, AnalyticsClient analyticsClient) {
        this.repository = repository;
        this.analyticsClient = analyticsClient;
    }

    // Method to create feedback and send event to Analytics
    public FeedbackResponse createFeedback(String username, FeedbackRequest req, String token) {
        // 1. Save to Database
        Feedback f = new Feedback();
        f.setUsername(username);
        f.setMessage(req.getMessage());
        f.setRating(req.getRating());
        f.setProductId(req.getProductId());

        Feedback saved = repository.save(f);

        // 2. Send Event to Analytics Service (Generic "FEEDBACK_SUBMITTED" event)
        try {
            AnalyticsEventDto event = new AnalyticsEventDto(
                    "FEEDBACK_SUBMITTED",
                    "User " + username + " reviewed Product ID: " + req.getProductId()
            );
            analyticsClient.logEvent(token, event);
            System.out.println("Sent FEEDBACK_SUBMITTED event to Analytics");
        } catch (Exception e) {
            // We catch the error so the feedback is still saved even if Analytics is down
            System.err.println("Failed to send analytics event: " + e.getMessage());
        }

        return mapToDto(saved);
    }

    // Method to get feedback for a specific product
    public List<FeedbackResponse> getFeedbackByProduct(Long productId) {
        return repository.findByProductId(productId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // Method to get all feedback
    public List<FeedbackResponse> getAllFeedback() {
        return repository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // Helper method to convert Entity to DTO
    private FeedbackResponse mapToDto(Feedback f) {
        return new FeedbackResponse(
                f.getId(),
                f.getUsername(),
                f.getMessage(),
                f.getRating(),
                f.getProductId(),
                f.getCreatedAt()
        );
    }
}