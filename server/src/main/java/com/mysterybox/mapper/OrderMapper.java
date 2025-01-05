package com.mysterybox.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mysterybox.entity.BoxSeries;

import com.mysterybox.entity.orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<orders> {
    List<orders> findAll();
    orders findById(@Param("id") Long id);
    List<orders> findByUserId(@Param("userId") Long userId);
    List<orders> findByCreateTimeBetween(@Param("startTime") LocalDateTime startTime,
                                      @Param("endTime") LocalDateTime endTime);

    void update(orders order);
    void delete(@Param("id") Long id);
    int countByCreateTimeAfter(@Param("time") LocalDateTime time);
} 