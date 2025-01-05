package com.mysterybox.service.impl;


import com.mysterybox.entity.OrderDetail;
import com.mysterybox.entity.orders;
import com.mysterybox.mapper.OrderMapper;
import com.mysterybox.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<orders> getAllOrders() {
        List<orders> orders = orderMapper.selectList(null);
        return orders;
    }

    @Override
    public orders getOrderById(Long id) {
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
}
