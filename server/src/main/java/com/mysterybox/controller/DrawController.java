package com.mysterybox.controller;

import com.mysterybox.dto.DrawResult;
import com.mysterybox.entity.BoxStyle;
import com.mysterybox.service.DrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/draw")
public class DrawController {
    
    @Autowired
    private DrawService drawService;
    
    @PostMapping("/{seriesId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<DrawResult> draw(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long seriesId) {
        return ResponseEntity.ok(drawService.draw(Long.valueOf(userDetails.getUsername()), seriesId));
    }
    
    @PostMapping("/{seriesId}/multiple/{count}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<DrawResult>> drawMultiple(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long seriesId,
            @PathVariable int count) {
        return ResponseEntity.ok(drawService.drawMultiple(Long.valueOf(userDetails.getUsername()), seriesId, count));
    }
    
    @GetMapping("/history/{userId}")
    public ResponseEntity<List<BoxStyle>> getDrawHistory(@PathVariable Long userId) {
        return ResponseEntity.ok(drawService.getUserDrawHistory(userId));
    }
} 