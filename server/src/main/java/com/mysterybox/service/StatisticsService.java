package com.mysterybox.service;

import com.mysterybox.dto.DrawPreference;
import com.mysterybox.dto.PopularStyle;
import java.time.LocalDateTime;
import java.util.List;

public interface StatisticsService {
    int countNewUsers(LocalDateTime since);
    int countActiveUsers(LocalDateTime since);
    int countOrders(LocalDateTime since);
    List<DrawPreference> getDrawPreferences();
    List<PopularStyle> getPopularStyles();
} 