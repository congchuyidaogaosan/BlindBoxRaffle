package com.mysterybox.mapper;

import com.mysterybox.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
    List<Order> findByCreateTimeBetween(LocalDateTime startTime, LocalDateTime endTime);
    int countByCreateTimeAfter(LocalDateTime time);
} 