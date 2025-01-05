package com.mysterybox.controller;

import com.mysterybox.common.Result;

import com.mysterybox.entity.orders;
import com.mysterybox.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public Result<List<orders>> getOrders() {
        List<orders> orders = orderService.getAllOrders();
        return Result.success(orders);
    }

    @GetMapping("/{id}")
    public Result<orders> getOrderById(@PathVariable Long id) {
        orders order = orderService.getOrderById(id);
        if (order == null) {
            return Result.error(404, "订单不存在");
        }
        return Result.success(order);
    }

    @PostMapping
    public Result<orders> createOrder(@Valid @RequestBody orders order) {
        orders savedOrder = orderService.createOrder(order);
        return Result.success("创建订单成功", savedOrder);
    }

    @PutMapping("/{id}")
    public Result<orders> updateOrder(@PathVariable Long id, @Valid @RequestBody orders order) {
        order.setId(id);
        orders updatedOrder = orderService.updateOrder(order);
        return Result.success("更新订单成功", updatedOrder);
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return Result.success("删除订单成功", null);
    }
} 