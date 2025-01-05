package com.mysterybox.service.impl;

import com.mysterybox.dto.DrawPreference;
import com.mysterybox.dto.PopularStyle;
import com.mysterybox.dto.SalesTrend;
import com.mysterybox.mapper.StatisticsMapper;
import com.mysterybox.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    
    @Autowired
    private StatisticsMapper statisticsMapper;

    @Override
    public Long countOrders(LocalDateTime since) {
        return statisticsMapper.countOrders(since);
    }

    @Override
    public Double countSales(LocalDateTime since) {
        return statisticsMapper.countSales(since);
    }

    @Override
    public int countNewUsers(LocalDateTime since) {
        return statisticsMapper.countNewUsers(since);
    }

    @Override
    public Long countDraws(LocalDateTime since) {
        return statisticsMapper.countDraws(since);
    }

    @Override
    public Double getOrderChange() {
        return statisticsMapper.getOrderChange();
    }

    @Override
    public Double getSalesChange() {
        return statisticsMapper.getSalesChange();
    }

    @Override
    public Double getUserChange() {
        return statisticsMapper.getUserChange();
    }

    @Override
    public Double getDrawChange() {
        return statisticsMapper.getDrawChange();
    }

    @Override
    public List<SalesTrend> getSalesTrend() {
        return statisticsMapper.getSalesTrend();
    }

    @Override
    public List<DrawPreference> getDrawPreferences() {
        return statisticsMapper.getDrawPreferences();
    }

    @Override
    public List<PopularStyle> getPopularStyles() {
        return statisticsMapper.getPopularStyles();
    }
} 