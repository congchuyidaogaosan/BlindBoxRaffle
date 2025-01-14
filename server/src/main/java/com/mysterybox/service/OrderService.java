package com.mysterybox.service;

import com.mysterybox.entity.OrderDetail;
import com.mysterybox.entity.orders;
import com.mysterybox.dto.OrderDTO;

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
    List<OrderDTO> getOrdersByUser(Long userId, String status);
} 