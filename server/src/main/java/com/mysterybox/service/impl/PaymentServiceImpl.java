package com.mysterybox.service.impl;

import com.mysterybox.entity.Order;
import com.mysterybox.entity.PaymentRecord;
import com.mysterybox.entity.RechargeRecord;
import com.mysterybox.entity.User;
import com.mysterybox.repository.PaymentRecordRepository;
import com.mysterybox.repository.RechargeRecordRepository;
import com.mysterybox.service.PaymentService;
import com.mysterybox.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRecordRepository paymentRecordRepository;
    
    @Autowired
    private RechargeRecordRepository rechargeRecordRepository;
    
    @Autowired
    private UserService userService;

    @Override
    public PaymentRecord pay(Long userId, Long orderId, BigDecimal amount) {
        User user = userService.getUserById(userId);
        if (user.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }
        
        userService.updateBalance(userId, user.getBalance().subtract(amount));
        
        PaymentRecord record = new PaymentRecord();
        User user1 = new User();
        user1.setId(userId);
        record.setUser(user1);

        Order order = new Order();
        order.setId(orderId);

        record.setOrder(order);
        record.setAmount(amount);
        record.setStatus("SUCCESS");
        return paymentRecordRepository.save(record);
    }

    @Override
    public RechargeRecord recharge(Long userId, BigDecimal amount) {
        User user = userService.getUserById(userId);
        userService.updateBalance(userId, user.getBalance().add(amount));
        
        RechargeRecord record = new RechargeRecord();
        User user1 = new User();
        user1.setId(userId);
        record.setUser(user1);
        record.setAmount(amount);
        return rechargeRecordRepository.save(record);
    }

    @Override
    public List<PaymentRecord> getUserPayments(Long userId) {
        return paymentRecordRepository.findByUserIdOrderByCreateTimeDesc(userId);
    }

    @Override
    public List<RechargeRecord> getUserRecharges(Long userId) {
        return rechargeRecordRepository.findByUserIdOrderByCreateTimeDesc(userId);
    }

    @Override
    public List<PaymentRecord> getOrderPayments(Long orderId) {
        return paymentRecordRepository.findByOrderId(orderId);
    }
} 