package com.mysterybox.service;

import com.mysterybox.entity.Order;
import com.mysterybox.entity.OrderDetail;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    List<Order> getUserOrders(Long userId);
    List<Order> getOrdersByTimeRange(LocalDateTime startTime, LocalDateTime endTime);
    Order createOrder(Order order);
    Order updateOrder(Order order);
    void deleteOrder(Long id);
    void addOrderDetail(Long orderId, OrderDetail detail);
    List<OrderDetail> getOrderDetails(Long orderId);
} 