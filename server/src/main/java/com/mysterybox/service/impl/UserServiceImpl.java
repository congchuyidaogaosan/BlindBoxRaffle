package com.mysterybox.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysterybox.dto.LoginRequest;
import com.mysterybox.dto.LoginResponse;
import com.mysterybox.entity.User;
import com.mysterybox.mapper.UserMapper;
import com.mysterybox.mapper.OrderMapper;
import com.mysterybox.service.UserService;
import com.mysterybox.utils.JwtUtil;
import com.mysterybox.utils.WxUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.Authentication;
import java.util.Map;
import java.util.HashMap;

@Service
@Transactional
public class UserServiceImpl  extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private WxUtil wxUtil;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<User> getAllUsers() {
        return userMapper.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return findByUsername(username);
    }

    @Override
    public User createUser(User user) {
        if (existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setBalance(BigDecimal.ZERO);
        userMapper.insert(user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        User existingUser = getUserById(user.getId());
        if (existingUser == null) {
            throw new RuntimeException("User not found");
        }
        
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            user.setPassword(existingUser.getPassword());
        }
        
        userMapper.updateById(user);
        return user;
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        User user = getUserById(id);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        
        // 检查是否是管理员
        if ("ADMIN".equals(user.getRole())) {
            throw new RuntimeException("不能删除管理员用户");
        }
        
        // 使用 status = -1 表示删除
        userMapper.logicDelete(id);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userMapper.findByUsername(username) != null;
    }

    @Override
    @Transactional
    public void updateBalance(Long userId, BigDecimal newBalance) {
        User user = getUserById(userId);
        if (user != null) {
            user.setBalance(newBalance);
            userMapper.updateById(user);
        } else {
            throw new RuntimeException("用户不存在");
        }
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        String openId = authentication.getName();
        return findByOpenId(openId);
    }

    @Override
    @Transactional
    public LoginResponse login(LoginRequest request) {
        // 这个方法可以保留，但不会被使用，因为我们现在使用微信登录
        throw new UnsupportedOperationException("不支持的登录方式");
    }

    @Override
    public User findByOpenId(String openId) {
        return userMapper.findByOpenId(openId);
    }

    @Override
    public int newsave(User kehuEntity) {
        return userMapper.insert(kehuEntity);
    }

    @Override
    public Map<String, Integer> getUserStats(Long userId) {
        Map<String, Integer> stats = new HashMap<>();
        
        // 获取抽盒次数 (完成状态的订单数)
        Integer drawCount = orderMapper.countByUserIdAndStatus(userId, "COMPLETED");
        stats.put("drawCount", drawCount);
        
        // 获取款式数 (不同款式的数量)
        Integer boxCount = orderMapper.countDistinctStylesByUserId(userId);
        stats.put("boxCount", boxCount);
        
        return stats;
    }

    @Override
    public Long countActiveUsers() {
        return lambdaQuery()
            .ne(User::getStatus, -1)
            .count();
    }
}