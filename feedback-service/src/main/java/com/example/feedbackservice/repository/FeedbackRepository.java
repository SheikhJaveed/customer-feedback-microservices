package com.example.feedbackservice.repository;

import com.example.feedbackservice.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    // Finds all feedback where productId = ?
    List<Feedback> findByProductId(Long productId);
}