package com.mysterybox.controller;

import com.mysterybox.mapper.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewService;

//    @PostMapping
//    public ResponseEntity<Review> submitReview(@RequestBody Review request) {
//        Review review = reviewService.createReview(
//            request.getUser().getId(),
//            request.getBoxStyle().getId(),
//            request.getRating(),
//            request.getContent()
//        );
//        return ResponseEntity.ok(review);
//    }
//
//    @GetMapping("/style/{styleId}")
//    public ResponseEntity<List<Review>> getStyleReviews(@PathVariable Long styleId) {
//        return ResponseEntity.ok(reviewService.getReviewsByStyleId(styleId));
//    }
//
//    @GetMapping("/user/{userId}")
//    public ResponseEntity<List<Review>> getUserReviews(@PathVariable Long userId) {
//        return ResponseEntity.ok(reviewService.getReviewsByUserId(userId));
//    }
} 