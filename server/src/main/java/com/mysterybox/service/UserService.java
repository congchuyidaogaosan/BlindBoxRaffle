package com.mysterybox.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mysterybox.dto.LoginRequest;
import com.mysterybox.dto.LoginResponse;
import com.mysterybox.entity.User;
import java.util.List;
import java.math.BigDecimal;

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
} 