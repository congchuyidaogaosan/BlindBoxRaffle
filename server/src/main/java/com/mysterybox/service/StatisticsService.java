package com.mysterybox.service;

import com.mysterybox.dto.DrawPreference;
import com.mysterybox.dto.PopularStyle;
import com.mysterybox.dto.SalesTrend;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public interface StatisticsService {
    
    Long countOrders(LocalDateTime since);
    
    Double countSales(LocalDateTime since);
    
    int countNewUsers(LocalDateTime since);
    
    Long countDraws(LocalDateTime since);
    
    Double getOrderChange();
    
    Double getSalesChange();
    
    Double getUserChange();
    
    Double getDrawChange();
    
    List<SalesTrend> getSalesTrend();
    
    List<DrawPreference> getDrawPreferences();
    
    List<PopularStyle> getPopularStyles();

    public HashMap<String,String>  getnewsession();
}