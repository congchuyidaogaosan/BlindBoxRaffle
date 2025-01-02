package com.mysterybox.controller;

import com.mysterybox.entity.BoxSeries;
import com.mysterybox.service.BoxSeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/series")
public class BoxSeriesController {
    
    @Autowired
    private BoxSeriesService boxSeriesService;
    
    @GetMapping
    public ResponseEntity<List<BoxSeries>> getAllSeries() {
        return ResponseEntity.ok(boxSeriesService.getAllSeries());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<BoxSeries> getSeriesById(@PathVariable Long id) {
        return ResponseEntity.ok(boxSeriesService.getSeriesById(id));
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BoxSeries> createSeries(@Valid @RequestBody BoxSeries series) {
        return ResponseEntity.ok(boxSeriesService.createSeries(series));
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BoxSeries> updateSeries(@PathVariable Long id, @Valid @RequestBody BoxSeries series) {
        series.setId(id);
        return ResponseEntity.ok(boxSeriesService.updateSeries(series));
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteSeries(@PathVariable Long id) {
        boxSeriesService.deleteSeries(id);
        return ResponseEntity.ok().build();
    }
} 