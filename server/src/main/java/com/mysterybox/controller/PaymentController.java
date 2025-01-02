package com.mysterybox.controller;

import com.mysterybox.entity.PaymentRecord;
import com.mysterybox.entity.RechargeRecord;
import com.mysterybox.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/pay")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<PaymentRecord> pay(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam Long orderId,
            @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(paymentService.pay(Long.valueOf(userDetails.getUsername()), orderId, amount));
    }

    @PostMapping("/recharge")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<RechargeRecord> recharge(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(paymentService.recharge(Long.valueOf(userDetails.getUsername()), amount));
    }

    @GetMapping("/user/payments")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<PaymentRecord>> getUserPayments(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(paymentService.getUserPayments(Long.valueOf(userDetails.getUsername())));
    }

    @GetMapping("/user/recharges")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<RechargeRecord>> getUserRecharges(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(paymentService.getUserRecharges(Long.valueOf(userDetails.getUsername())));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<PaymentRecord>> getOrderPayments(@PathVariable Long orderId) {
        return ResponseEntity.ok(paymentService.getOrderPayments(orderId));
    }
} 