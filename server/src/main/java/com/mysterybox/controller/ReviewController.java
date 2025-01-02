package com.mysterybox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    
    @Autowired
    private ReviewService reviewService;
    
    @PostMapping
    public ResponseEntity<Review> submitReview(@RequestBody ReviewRequest request) {
        Review review = reviewService.createReview(
            request.getUserId(),
            request.getStyleId(),
            request.getRating(),
            request.getContent()
        );
        return ResponseEntity.ok(review);
    }
    
    @GetMapping("/style/{styleId}")
    public ResponseEntity<List<Review>> getStyleReviews(@PathVariable Long styleId) {
        return ResponseEntity.ok(reviewService.getReviewsByStyleId(styleId));
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Review>> getUserReviews(@PathVariable Long userId) {
        return ResponseEntity.ok(reviewService.getReviewsByUserId(userId));
    }
} 