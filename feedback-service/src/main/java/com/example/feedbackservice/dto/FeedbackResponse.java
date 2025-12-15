package com.example.feedbackservice.dto;

import java.time.LocalDateTime;

public class FeedbackResponse {
    private Long id;
    private String username;
    private String message;
    private Integer rating;
    private LocalDateTime createdAt;

    private Long productId;


    public FeedbackResponse(Long id, String username, String message, Integer rating,Long productId, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.message = message;
        this.rating = rating;
        this.createdAt = createdAt;
        this.productId=productId;
    }

    // Getters
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getMessage() { return message; }
    public Integer getRating() { return rating; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}