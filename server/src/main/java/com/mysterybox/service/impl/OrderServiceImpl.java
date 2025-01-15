package com.mysterybox.service.impl;

import com.mysterybox.entity.OrderDetail;
import com.mysterybox.entity.orders;
import com.mysterybox.entity.BoxStyle;
import com.mysterybox.entity.BoxSeries;
import com.mysterybox.mapper.OrderMapper;
import com.mysterybox.mapper.BoxStyleMapper;
import com.mysterybox.mapper.BoxSeriesMapper;
import com.mysterybox.service.OrderService;
import com.mysterybox.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private BoxStyleMapper boxStyleMapper;

    @Autowired
    private BoxSeriesMapper boxSeriesMapper;

    @Override
    public List<orders> getAllOrders() {
        List<orders> orders = orderMapper.selectList(null);
        return orders;
    }



    @Override
    public orders getOrderById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("订单ID不能为空");
        }
        return orderMapper.findById(id);
    }

    @Override
    public List<orders> getUserOrders(Long userId) {
        return orderMapper.findByUserId(userId);
    }

    @Override
    public List<orders> getOrdersByTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        return orderMapper.findByCreateTimeBetween(startTime, endTime);
    }

    @Override
    public orders createOrder(orders order) {
        long l = System.currentTimeMillis();
        order.setId(l);
        orderMapper.insert(order);

        return orderMapper.findById(order.getId());

    }

    @Override
    public orders updateOrder(orders order) {

        orderMapper.updateById(order);

        return orderMapper.findById(order.getId());
    }

    @Override
    public void deleteOrder(Long id) {
        orderMapper.deleteById(id);
    }

    //?作用 不懂
    @Override
    public void addOrderDetail(Long orderId, OrderDetail detail) {

    }

    //?作用 不懂
    @Override
    public List<OrderDetail> getOrderDetails(Long orderId) {
        return null;
    }

    @Override
    public List<OrderDTO> getOrdersByUser(Long userId, String status) {
        List<orders> ordersList;
        if ("ALL".equals(status)) {
            ordersList = orderMapper.findByUserId(userId);
        } else {
            ordersList = orderMapper.findByUserIdAndStatus(userId, status);
        }
        
        // 转换为 DTO
        return ordersList.stream().map(order -> {
            OrderDTO dto = new OrderDTO();
            dto.setId(order.getId());
            dto.setUserId(order.getUserId());
            dto.setStatus(order.getStatus());
            dto.setTotalAmount(order.getTotalAmount());
            dto.setCreateTime(order.getCreateTime());
            dto.setUpdateTime(order.getUpdateTime());
            
            // 设置款式和系列信息
            dto.setStyleId(order.getBoxStyleId());
            dto.setStyleName(order.getStyleName());
            dto.setStyleImageUrl(order.getStyleImageUrl());
            dto.setSeriesId(order.getSeriesId());
            dto.setSeriesName(order.getSeriesName());
            dto.setSeriesDescription(order.getSeriesDescription());
            
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getAllOrdersWithDetails() {
        List<orders> ordersList = orderMapper.selectList(null);
        
        // 转换为 DTO
        return ordersList.stream().map(order -> {
            OrderDTO dto = new OrderDTO();
            dto.setId(order.getId());
            dto.setUserId(order.getUserId());
            dto.setStatus(order.getStatus());
            dto.setTotalAmount(order.getTotalAmount());
            dto.setCreateTime(order.getCreateTime());
            dto.setUpdateTime(order.getUpdateTime());
            
            // 设置款式和系列信息
            dto.setStyleId(order.getBoxStyleId());
            // 通过 box_style_id 查询款式信息
            BoxStyle boxStyle = boxStyleMapper.selectById(order.getBoxStyleId());
            if (boxStyle != null) {
                dto.setStyleName(boxStyle.getName());
                dto.setStyleImageUrl(boxStyle.getImageUrl());
                
                // 通过 series_id 查询系列信息
                BoxSeries series = boxSeriesMapper.selectById(boxStyle.getSeriesId());
                if (series != null) {
                    dto.setSeriesId(series.getId());
                    dto.setSeriesName(series.getName());
                    dto.setSeriesDescription(series.getDescription());
                }
            }
            
            return dto;
        }).collect(Collectors.toList());
    }
}
