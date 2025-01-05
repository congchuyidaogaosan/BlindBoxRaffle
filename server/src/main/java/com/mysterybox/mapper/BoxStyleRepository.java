package com.mysterybox.mapper;

import com.mysterybox.entity.BoxStyle;
import com.mysterybox.dto.DrawPreference;
import com.mysterybox.dto.PopularStyle;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Mapper
public interface BoxStyleRepository extends JpaRepository<BoxStyle, Long> {
    List<BoxStyle> findBySeriesId(Long seriesId);
    List<BoxStyle> findBySeriesIdAndStatus(Long seriesId, Integer status);
    
    @Query("SELECT new com.mysterybox.dto.DrawPreference(bs.series.id, bs.series.name, COUNT(*)) " +
           "FROM OrderDetail od JOIN od.boxStyle bs GROUP BY bs.series.id, bs.series.name ORDER BY COUNT(*) DESC")
    List<DrawPreference> findDrawPreferences();
    
    @Query("SELECT new com.mysterybox.dto.PopularStyle(bs.id, bs.name, COUNT(*)) " +
           "FROM OrderDetail od JOIN od.boxStyle bs GROUP BY bs.id, bs.name ORDER BY COUNT(*) DESC")
    List<PopularStyle> findPopularStyles();
} 