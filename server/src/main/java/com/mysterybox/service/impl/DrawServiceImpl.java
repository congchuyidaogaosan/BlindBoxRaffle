package com.mysterybox.service.impl;

import com.mysterybox.entity.BoxStyle;
import com.mysterybox.entity.Order;
import com.mysterybox.entity.User;
import com.mysterybox.dto.DrawResult;
import com.mysterybox.service.DrawService;
import com.mysterybox.service.UserService;
import com.mysterybox.service.OrderService;
import com.mysterybox.mapper.BoxStyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class DrawServiceImpl implements DrawService {
    @Autowired
    private BoxStyleRepository boxStyleRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    private final Random random = new Random();

    @Override
    public DrawResult draw(Long userId, Long seriesId) {
        User user = userService.getUserById(userId);
        BoxStyle style = calculateDrawResult(seriesId);

        if (user.getBalance().compareTo(style.getPrice()) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        Order order = createDrawOrder(userId, style);
        return new DrawResult(style, order);
    }

    @Override
    public List<DrawResult> drawMultiple(Long userId, Long seriesId, int count) {
        List<DrawResult> results = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            results.add(draw(userId, seriesId));
        }
        return results;
    }

    @Override
    public BoxStyle calculateDrawResult(Long seriesId) {
        List<BoxStyle> styles = boxStyleRepository.findBySeriesIdAndStatus(seriesId, 1);
        double totalProbability = styles.stream()
                .mapToDouble(style -> style.getProbability().doubleValue())
                .sum();

        double randomValue = random.nextDouble() * totalProbability;
        double currentSum = 0;

        for (BoxStyle style : styles) {
            currentSum += style.getProbability().doubleValue();
            if (randomValue <= currentSum) {
                return style;
            }
        }

        return styles.get(0);
    }

    @Override
    public Order createDrawOrder(Long userId, BoxStyle style) {
        Order order = new Order();
        User user = new User();
        user.setId(userId);
        order.setUser(user);
        order.setTotalAmount(style.getPrice());
        order.setStatus("COMPLETED");
        return orderService.createOrder(order);
    }
} 