package com.mysterybox.controller;

import com.mysterybox.common.Result;
import com.mysterybox.dto.LoginRequest;
import com.mysterybox.dto.LoginResponse;
import com.mysterybox.entity.User;
import com.mysterybox.service.UserService;
import com.mysterybox.utils.JwtUtil;
import com.mysterybox.utils.WxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private WxUtil wxUtil;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public Result<LoginResponse> wxLogin(@RequestBody LoginRequest loginRequest) {
        try {
            // 1. 获取openId
            String openId = wxUtil.getOpenId(loginRequest.getCode());
            
            // 2. 查找或创建用户
            User user = userService.findByOpenId(openId);
            if (user == null) {
                // 新用户，创建账号
                user = new User();
                user.setOpenId(openId);
                user.setNickname(loginRequest.getUserInfo().getNickName());
                user.setAvatarUrl(loginRequest.getUserInfo().getAvatarUrl());
                user.setGender(loginRequest.getUserInfo().getGender());
                user.setCountry(loginRequest.getUserInfo().getCountry());
                user.setProvince(loginRequest.getUserInfo().getProvince());
                user.setCity(loginRequest.getUserInfo().getCity());
                user.setLanguage(loginRequest.getUserInfo().getLanguage());
                user.setRole("USER");
                user = userService.createUser(user);
            }

            // 3. 生成token
            String token = jwtUtil.generateToken(user);

            // 4. 构建返回结果
            LoginResponse response = new LoginResponse();
            response.setToken(token);
            response.setUserInfo(user);

            return Result.success(response);
        } catch (Exception e) {
            return Result.error(500, "登录失败: " + e.getMessage());
        }
    }

    @GetMapping("/info")
    public Result<User> getUserInfo() {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            return Result.error(401, "未登录");
        }
        return Result.success(currentUser);
    }

    @PostMapping("/update")
    public Result<User> updateUserInfo(@RequestBody User user) {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            return Result.error(401, "未登录");
        }
        
        // 只允许更新部分字段
        currentUser.setNickname(user.getNickname());
        currentUser.setAvatarUrl(user.getAvatarUrl());
        currentUser.setGender(user.getGender());
        
        User updatedUser = userService.updateUser(currentUser);
        return Result.success(updatedUser);
    }
} 