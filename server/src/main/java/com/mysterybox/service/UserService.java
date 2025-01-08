package com.mysterybox.service;

import com.mysterybox.dto.LoginRequest;
import com.mysterybox.dto.LoginResponse;
import com.mysterybox.entity.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User getUserByUsername(String username);
    User createUser(User user);
    User updateUser(User user);
    void deleteUser(Long id);
    boolean existsByUsername(String username);
    void updateBalance(Long userId, java.math.BigDecimal newBalance);
    User findByUsername(String username);
    User getCurrentUser();
    LoginResponse login(LoginRequest request);
    User findByOpenId(String openId);
} 