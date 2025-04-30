package com.mysterybox.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mysterybox.dto.DrawPreference;
import com.mysterybox.dto.PopularStyle;
import com.mysterybox.dto.Review;
import com.mysterybox.dto.SalesTrend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    @Select("SELECT COUNT(create_time) FROM orders WHERE create_time LIKE #{date}")
    Double gettodaysum(String date);
@Select("SELECT sum(total_amount) FROM orders WHERE create_time LIKE #{date}")
            Double gettoorders(String date);
@Select("SELECT COUNT(create_time) FROM `user` WHERE create_time LIKE #{date}")
    Double getusers(String date);


    /**
    * @author tian
    * @description 针对表【review】的数据库操作Mapper
    * @createDate 2025-04-30 11:41:55
    * @Entity com.mysterybox.dto.Review
    */
    interface ReviewMapper extends BaseMapper<Review> {

    }
}