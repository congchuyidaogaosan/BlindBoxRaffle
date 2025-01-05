package com.mysterybox.mapper;

import com.alibaba.druid.support.spring.stat.annotation.Stat;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mysterybox.dto.DrawPreference;
import com.mysterybox.dto.PopularStyle;
import com.mysterybox.dto.SalesTrend;
import com.mysterybox.entity.BoxSeries;
import com.sun.org.glassfish.external.statistics.Statistic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface StatisticsMapper {
    
    Long countOrders(@Param("since") LocalDateTime since);
    
    Double countSales(@Param("since") LocalDateTime since);
    
    int countNewUsers(@Param("since") LocalDateTime since);
    
    Long countDraws(@Param("since") LocalDateTime since);
    
    Double getOrderChange();
    
    Double getSalesChange();
    
    Double getUserChange();
    
    Double getDrawChange();
    
    List<SalesTrend> getSalesTrend();
    
    List<DrawPreference> getDrawPreferences();
    
    List<PopularStyle> getPopularStyles();
} 