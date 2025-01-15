package com.mysterybox.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mysterybox.dto.LoginRequest;
import com.mysterybox.dto.LoginResponse;
import com.mysterybox.entity.User;
import java.util.List;
import java.math.BigDecimal;
import java.util.Map;

public interface UserService extends IService<User> {
    List<User> getAllUsers();
    User getUserById(Long id);
    User getUserByUsername(String username);
    User createUser(User user);
    User updateUser(User user);
    void deleteUser(Long id);
    boolean existsByUsername(String username);
    User findByUsername(String username);
    User getCurrentUser();
    LoginResponse login(LoginRequest request);
    User findByOpenId(String openId);
    int newsave(User kehuEntity);
    
    void updateBalance(Long userId, BigDecimal newBalance);

    /**
     * 获取用户统计数据
     * @param userId 用户ID
     * @return 包含抽盒次数和获得款式数的统计数据
     */
    Map<String, Integer> getUserStats(Long userId);

    /**
     * 获取活跃用户数量（未删除的用户）
     */
    Long countActiveUsers();
} 