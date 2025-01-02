package com.mysterybox.controller;

import com.mysterybox.dto.DrawPreference;
import com.mysterybox.dto.PopularStyle;
import com.mysterybox.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/statistics")
@PreAuthorize("hasRole('ADMIN')")
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/overview")
    public ResponseEntity<Map<String, Object>> getOverview(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime since) {
        Map<String, Object> statistics = new HashMap<>();
        statistics.put("newUsers", statisticsService.countNewUsers(since));
        statistics.put("activeUsers", statisticsService.countActiveUsers(since));
        statistics.put("orders", statisticsService.countOrders(since));
        return ResponseEntity.ok(statistics);
    }

    @GetMapping("/preferences")
    public ResponseEntity<List<DrawPreference>> getDrawPreferences() {
        return ResponseEntity.ok(statisticsService.getDrawPreferences());
    }

    @GetMapping("/popular")
    public ResponseEntity<List<PopularStyle>> getPopularStyles() {
        return ResponseEntity.ok(statisticsService.getPopularStyles());
    }
} 