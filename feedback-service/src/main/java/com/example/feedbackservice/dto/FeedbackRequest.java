package com.example.feedbackservice.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FeedbackRequest {

    @NotBlank(message = "Message cannot be empty")
    private String message;

    @NotNull
    @Min(1) @Max(5)
    private Integer rating;

    // --- NEW FIELD ---
    @NotNull(message = "Product ID is required")
    private Long productId;

    // Getters & Setters
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
}