package com.mysterybox.service.impl;

import com.mysterybox.dto.DrawResult;
import com.mysterybox.entity.BoxSeries;
import com.mysterybox.entity.BoxStyle;
import com.mysterybox.entity.User;
import com.mysterybox.entity.orders;
import com.mysterybox.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Service
public class DrawServiceImpl implements DrawService {

    @Autowired
    private BoxSeriesService boxSeriesService;
    
    @Autowired
    private BoxStyleService boxStyleService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private OrderService orderService;

    private final Random random = new Random();

    @Override
    @Transactional
    public DrawResult draw(Long userId, Long seriesId) {
        // 1. 检查系列是否存在
        BoxSeries series = boxSeriesService.getSeriesById(seriesId);
        if (series == null) {
            throw new RuntimeException("系列不存在");
        }

        // 2. 检查用户余额
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        if (user.getBalance().compareTo(series.getPrice()) < 0) {
            throw new RuntimeException("余额不足");
        }

        // 3. 随机抽取款式
        BoxStyle style = calculateDrawResult(seriesId);
        if (style == null) {
            throw new RuntimeException("抽奖失败");
        }

        // 4. 扣除用户余额
        BigDecimal newBalance = user.getBalance().subtract(series.getPrice());
        userService.updateBalance(userId, newBalance);

        // 5. 创建订单
        orders order = createDrawOrder(userId, style);

        return new DrawResult(style, order);
    }

    @Override
    public BoxStyle calculateDrawResult(Long seriesId) {
        List<BoxStyle> styles = boxStyleService.getStylesBySeriesId(seriesId);
        if (styles.isEmpty()) {
            return null;
        }

        // 验证概率
        double totalProbability = 0;
        for (BoxStyle style : styles) {
            if (style.getProbability() == null || style.getProbability().doubleValue() < 0) {
                throw new RuntimeException("款式概率设置错误");
            }
            totalProbability += style.getProbability().doubleValue();
        }

        // 如果概率总和不为100%，进行归一化处理
        if (Math.abs(totalProbability - 100.0) > 0.01) {
            for (BoxStyle style : styles) {
                double normalizedProbability = (style.getProbability().doubleValue() / totalProbability) * 100;
                style.setProbability(BigDecimal.valueOf(normalizedProbability));
            }
        }

        // 生成随机数并选择款式
        double randomValue = random.nextDouble() * 100;
        double currentSum = 0;

        for (BoxStyle style : styles) {
            currentSum += style.getProbability().doubleValue();
            if (randomValue <= currentSum) {
                return style;
            }
        }

        // 如果由于浮点数精度问题没有选中任何款式，返回最后一个
        return styles.get(styles.size() - 1);
    }

    @Override
    public orders createDrawOrder(Long userId, BoxStyle style) {
        orders order = new orders();
        order.setUserId(userId);
        order.setBoxStyleId(style.getId());
        order.setTotalAmount(style.getSeriesPrice());
        order.setStatus("COMPLETED");
        
        return orderService.createOrder(order);
    }

    @Override
    public List<DrawResult> drawMultiple(Long userId, Long seriesId, int count) {
        throw new UnsupportedOperationException("暂不支持批量抽奖");
    }
}
