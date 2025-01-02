package com.mysterybox.mapper;

import com.mysterybox.entity.BoxSeries;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface BoxSeriesMapper {
    List<BoxSeries> findAll();
    BoxSeries findById(@Param("id") Long id);
    List<BoxSeries> findByStatus(@Param("status") Integer status);
    void insert(BoxSeries series);
    void update(BoxSeries series);
    void delete(@Param("id") Long id);
} 