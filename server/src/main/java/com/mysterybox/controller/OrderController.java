package com.mysterybox.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mysterybox.dto.Review;
import com.mysterybox.service.ReviewService;
import com.mysterybox.common.Result;
import com.mysterybox.dto.OrderDTO;
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

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/list")
    public Result<List<OrderDTO>> getOrders() {
        List<OrderDTO> orders = orderService.getAllOrdersWithDetails();
        return Result.success(orders);
    }

    @GetMapping("/detail/{id}")
    public Result<orders> getOrderById(@PathVariable Long id) {
        orders order = orderService.getOrderById(id);
        if (order == null) {
            return Result.error(404, "订单不存在");
        }
        return Result.success(order);
    }

    @PostMapping("/create")
    public Result<orders> createOrder(@Valid @RequestBody orders order) {
        orders savedOrder = orderService.createOrder(order);
        return Result.success("创建订单成功", savedOrder);
    }

    @PostMapping("/update/{id}")
    public Result<orders> updateOrder(@PathVariable Long id, @Valid @RequestBody orders order) {
        order.setId(id);
        orders updatedOrder = orderService.updateOrder(order);
        return Result.success("更新订单成功", updatedOrder);
    }

    @PostMapping("/delete/{id}")
    public Result<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return Result.success("删除订单成功", null);
    }

    @GetMapping("/getOrdersByUser")
    public Result<List<OrderDTO>> getOrdersByUser(
            @RequestParam Long userId,
            @RequestParam(required = false, defaultValue = "ALL") String status) {
        try {
            List<OrderDTO> orders = orderService.getOrdersByUser(userId, status);

            for (OrderDTO order : orders) {
                Long styleId = order.getStyleId();
                List<Review> list = reviewService.list(new QueryWrapper<Review>().eq("box_style_id",styleId));
                order.setReviews(list);
            }


            System.out.println(orders);
            return Result.success(orders);
        } catch (Exception e) {
            return Result.error("获取订单列表失败: " + e.getMessage());
        }
    }
} 