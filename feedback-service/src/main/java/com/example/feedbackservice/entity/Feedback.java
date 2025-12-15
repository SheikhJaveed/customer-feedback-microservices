package com.example.feedbackservice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String message;
    private Integer rating;

    // --- NEW FIELD ---
    @Column(nullable = false)
    private Long productId; // Links this review to a Product ID

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() { this.createdAt = LocalDateTime.now(); }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
    public Long getProductId() { return productId; } // New Getter
    public void setProductId(Long productId) { this.productId = productId; } // New Setter
    public LocalDateTime getCreatedAt() { return createdAt; }
}