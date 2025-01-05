package com.mysterybox.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mysterybox.entity.BoxSeries;
import com.mysterybox.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {
    List<OrderDetail> findByOrderId(@Param("orderId") Long orderId);

    void update(OrderDetail orderDetail);
    void delete(@Param("id") Long id);
} 