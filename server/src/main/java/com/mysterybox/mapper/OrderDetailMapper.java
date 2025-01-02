package com.mysterybox.mapper;

import com.mysterybox.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface OrderDetailMapper {
    List<OrderDetail> findByOrderId(@Param("orderId") Long orderId);
    void insert(OrderDetail orderDetail);
    void update(OrderDetail orderDetail);
    void delete(@Param("id") Long id);
} 