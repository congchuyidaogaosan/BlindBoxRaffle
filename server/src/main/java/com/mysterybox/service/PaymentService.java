package com.mysterybox.service;

import com.mysterybox.entity.PaymentRecord;
import com.mysterybox.entity.RechargeRecord;
import java.math.BigDecimal;
import java.util.List;

public interface PaymentService {
    PaymentRecord pay(Long userId, Long orderId, BigDecimal amount);
    RechargeRecord recharge(Long userId, BigDecimal amount);
    List<PaymentRecord> getUserPayments(Long userId);
    List<RechargeRecord> getUserRecharges(Long userId);
    List<PaymentRecord> getOrderPayments(Long orderId);
} 