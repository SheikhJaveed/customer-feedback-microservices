package com.example.feedbackservice.controller;

import com.example.feedbackservice.dto.FeedbackRequest;
import com.example.feedbackservice.dto.FeedbackResponse;
import com.example.feedbackservice.service.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    private final FeedbackService service;

    public FeedbackController(FeedbackService service) { this.service = service; }

    // --- OLD METHOD REMOVED ---
    // The previous 2-argument method was deleted to prevent conflict.

    // --- CORRECT METHOD ---
    // Captures the Token to send to Analytics
    @PostMapping
    public ResponseEntity<FeedbackResponse> submitFeedback(
            @Valid @RequestBody FeedbackRequest request,
            Authentication authentication,
            @RequestHeader("Authorization") String token // Capture Token
    ) {
        String username = authentication.getName();
        // Pass all 3 arguments: username, request, and token
        return ResponseEntity.ok(service.createFeedback(username, request, token));
    }

    // GET /feedback?productId=1
    @GetMapping
    public ResponseEntity<List<FeedbackResponse>> getFeedback(@RequestParam(required = false) Long productId) {
        if (productId != null) {
            return ResponseEntity.ok(service.getFeedbackByProduct(productId));
        }
        return ResponseEntity.ok(service.getAllFeedback());
    }
}