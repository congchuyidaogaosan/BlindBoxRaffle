package com.mysterybox.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysterybox.dto.LoginRequest;
import com.mysterybox.dto.LoginResponse;
import com.mysterybox.entity.User;
import com.mysterybox.mapper.UserMapper;
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

    @Override
    public List<User> getAllUsers() {
        return userMapper.selectList(null);
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
    public void deleteUser(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userMapper.findByUsername(username) != null;
    }

    @Override
    public void updateBalance(Long userId, BigDecimal newBalance) {
        User user = getUserById(userId);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        user.setBalance(newBalance);
        userMapper.updateById(user);
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
}