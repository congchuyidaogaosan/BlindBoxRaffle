package com.mysterybox.service.impl;

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

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private WxUtil wxUtil;

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
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return findByUsername(username);
    }

    @Override
    @Transactional
    public LoginResponse login(LoginRequest request) {
        // 获取openId
        String openId = wxUtil.getOpenId(request.getCode());
        
        // 查找或创建用户
        User user = userMapper.findByOpenId(openId);
        if (user == null) {
            user = new User();
            user.setOpenId(openId);
            user.setStatus(1);
            user.setCreateTime(new Date());
        }
        
        // 更新用户信息
        LoginRequest.WxUserInfo wxUserInfo = request.getUserInfo();
        BeanUtils.copyProperties(wxUserInfo, user);
        user.setLastLoginTime(new Date());
        user.setUpdateTime(new Date());
        
        if (user.getId() == null) {
            userMapper.insert(user);
        } else {
            userMapper.updateById(user);
        }

        // 生成token
        String token = JwtUtil.generateToken(user.getId());

        // 构建返回数据
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        
        LoginResponse.UserInfo userInfo = new LoginResponse.UserInfo();
        BeanUtils.copyProperties(user, userInfo);
        response.setUserInfo(userInfo);

        return response;
    }
} 