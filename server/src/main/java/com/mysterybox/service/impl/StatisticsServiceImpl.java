package com.mysterybox.service.impl;

import com.mysterybox.dto.DrawPreference;
import com.mysterybox.dto.PopularStyle;
import com.mysterybox.repository.UserRepository;
import com.mysterybox.repository.OrderRepository;
import com.mysterybox.repository.BoxStyleRepository;
import com.mysterybox.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private BoxStyleRepository boxStyleRepository;

    @Override
    public int countNewUsers(LocalDateTime since) {
        return userRepository.countByCreateTimeAfter(since);
    }

    @Override
    public int countActiveUsers(LocalDateTime since) {
        return userRepository.countActiveUsers(since);
    }

    @Override
    public int countOrders(LocalDateTime since) {
        return orderRepository.countByCreateTimeAfter(since);
    }

    @Override
    public List<DrawPreference> getDrawPreferences() {
        return boxStyleRepository.findDrawPreferences();
    }

    @Override
    public List<PopularStyle> getPopularStyles() {
        return boxStyleRepository.findPopularStyles();
    }
} 