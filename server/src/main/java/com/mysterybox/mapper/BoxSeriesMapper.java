package com.mysterybox.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mysterybox.entity.BoxSeries;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface BoxSeriesMapper extends BaseMapper<BoxSeries> {
    List<BoxSeries> findAll();
    BoxSeries findById(@Param("id") Long id);
    List<BoxSeries> findByStatus(@Param("status") Integer status);
   
} 