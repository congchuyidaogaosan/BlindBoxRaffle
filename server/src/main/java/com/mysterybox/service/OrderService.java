package com.mysterybox.service;


import com.mysterybox.entity.OrderDetail;
import com.mysterybox.entity.orders;

import java.time.LocalDateTime;
import java.util.List;


public interface OrderService {
    List<orders> getAllOrders();
    orders getOrderById(Long id);
    List<orders> getUserOrders(Long userId);
    List<orders> getOrdersByTimeRange(LocalDateTime startTime, LocalDateTime endTime);
    orders createOrder(orders order);
    orders updateOrder(orders order);
    void deleteOrder(Long id);
    void addOrderDetail(Long orderId, OrderDetail detail);
    List<OrderDetail> getOrderDetails(Long orderId);
} 