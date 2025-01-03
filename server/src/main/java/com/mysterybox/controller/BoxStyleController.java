package com.mysterybox.controller;

import com.mysterybox.entity.BoxStyle;
import com.mysterybox.service.BoxStyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/styles")
public class BoxStyleController {
    @Autowired
    private BoxStyleService boxStyleService;

    @GetMapping
    public ResponseEntity<List<BoxStyle>> getAllStyles() {
        return ResponseEntity.ok(boxStyleService.getAllStyles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoxStyle> getStyleById(@PathVariable Long id) {
        return ResponseEntity.ok(boxStyleService.getStyleById(id));
    }

    @GetMapping("/series/{seriesId}")
    public ResponseEntity<List<BoxStyle>> getStylesBySeriesId(@PathVariable Long seriesId) {
        return ResponseEntity.ok(boxStyleService.getStylesBySeriesId(seriesId));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BoxStyle> createStyle(@Valid @RequestBody BoxStyle style) {
        return ResponseEntity.ok(boxStyleService.createStyle(style));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BoxStyle> updateStyle(@PathVariable Long id, @Valid @RequestBody BoxStyle style) {
        style.setId(id);
        return ResponseEntity.ok(boxStyleService.updateStyle(style));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteStyle(@PathVariable Long id) {
        boxStyleService.deleteStyle(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/image")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> uploadImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
//        String imageUrl = boxStyleService.uploadImage(id, file);
        return ResponseEntity.ok(null);
    }
} 