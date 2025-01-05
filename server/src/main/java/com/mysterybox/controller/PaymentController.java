package com.mysterybox.controller;

import com.mysterybox.common.Result;
import com.mysterybox.entity.PaymentRecord;
import com.mysterybox.entity.RechargeRecord;
import com.mysterybox.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/pay")
    public Result<PaymentRecord> pay(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam Long orderId,
            @RequestParam BigDecimal amount) {
        PaymentRecord record = paymentService.pay(Long.valueOf(userDetails.getUsername()), orderId, amount);
        return Result.success("支付成功", record);
    }

    @PostMapping("/recharge")
    public Result<RechargeRecord> recharge(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam BigDecimal amount) {
        RechargeRecord record = paymentService.recharge(Long.valueOf(userDetails.getUsername()), amount);
        return Result.success("充值成功", record);
    }

    @GetMapping("/user/payments")
    public Result<List<PaymentRecord>> getUserPayments(
            @AuthenticationPrincipal UserDetails userDetails) {
        List<PaymentRecord> records = paymentService.getUserPayments(Long.valueOf(userDetails.getUsername()));
        return Result.success(records);
    }

    @GetMapping("/user/recharges")
    public Result<List<RechargeRecord>> getUserRecharges(
            @AuthenticationPrincipal UserDetails userDetails) {
        List<RechargeRecord> records = paymentService.getUserRecharges(Long.valueOf(userDetails.getUsername()));
        return Result.success(records);
    }

    @GetMapping("/order/{orderId}")
    public Result<List<PaymentRecord>> getOrderPayments(@PathVariable Long orderId) {
        List<PaymentRecord> records = paymentService.getOrderPayments(orderId);
        return Result.success(records);
    }
} 