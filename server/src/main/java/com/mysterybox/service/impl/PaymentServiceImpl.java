package com.mysterybox.service.impl;

import com.mysterybox.entity.PaymentRecord;
import com.mysterybox.entity.RechargeRecord;
import com.mysterybox.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {



    @Override
    public PaymentRecord pay(Long userId, Long orderId, BigDecimal amount) {
        return null;
    }

    @Override
    public RechargeRecord recharge(Long userId, BigDecimal amount) {
        return null;
    }

    @Override
    public List<PaymentRecord> getUserPayments(Long userId) {
        return null;
    }

    @Override
    public List<RechargeRecord> getUserRecharges(Long userId) {
        return null;
    }

    @Override
    public List<PaymentRecord> getOrderPayments(Long orderId) {
        return null;
    }
}
