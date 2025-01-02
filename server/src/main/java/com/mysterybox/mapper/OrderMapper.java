package com.mysterybox.mapper;

import com.mysterybox.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface OrderMapper {
    List<Order> findAll();
    Order findById(@Param("id") Long id);
    List<Order> findByUserId(@Param("userId") Long userId);
    List<Order> findByCreateTimeBetween(@Param("startTime") LocalDateTime startTime, 
                                      @Param("endTime") LocalDateTime endTime);
    void insert(Order order);
    void update(Order order);
    void delete(@Param("id") Long id);
    int countByCreateTimeAfter(@Param("time") LocalDateTime time);
} 