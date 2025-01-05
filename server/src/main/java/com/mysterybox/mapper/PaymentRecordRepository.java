package com.mysterybox.mapper;

import com.mysterybox.entity.PaymentRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PaymentRecordRepository extends JpaRepository<PaymentRecord, Long> {
    List<PaymentRecord> findByUserIdOrderByCreateTimeDesc(Long userId);
    List<PaymentRecord> findByOrderId(Long orderId);
} 