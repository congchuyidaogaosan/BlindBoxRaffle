package com.mysterybox.controller;

import com.mysterybox.common.Result;
import com.mysterybox.dto.DrawPreference;
import com.mysterybox.dto.PopularStyle;
import com.mysterybox.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;


    @GetMapping("/newget")
    public Result newget(){

        HashMap<String, String> getnewsession = statisticsService.getnewsession();
        return Result.success(getnewsession);

    }
    @GetMapping("/overview")
    public Result<Map<String, Object>> getOverview(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime since) {
        Map<String, Object> statistics = new HashMap<>();
        
        // 今日数据
        statistics.put("todayOrders", statisticsService.countOrders(LocalDateTime.now()));
        statistics.put("todaySales", statisticsService.countSales(LocalDateTime.now()));
        statistics.put("newUsers", statisticsService.countNewUsers(LocalDateTime.now()));
        statistics.put("draws", statisticsService.countDraws(LocalDateTime.now()));
        
        // 同比数据
        statistics.put("orderChange", statisticsService.getOrderChange());
        statistics.put("salesChange", statisticsService.getSalesChange());
        statistics.put("userChange", statisticsService.getUserChange());
        statistics.put("drawChange", statisticsService.getDrawChange());
        
        // 销售趋势
        statistics.put("salesTrend", statisticsService.getSalesTrend());
        
        return Result.success(statistics);
    }

    @GetMapping("/preferences")
    public Result<List<DrawPreference>> getDrawPreferences() {
        return Result.success(statisticsService.getDrawPreferences());
    }

    //获取系列销量数据
    @GetMapping("/popular")
    public Result<List<PopularStyle>> getPopularStyles() {
        return Result.success(statisticsService.getPopularStyles());
    }
} 