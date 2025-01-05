package com.mysterybox.controller;

import com.mysterybox.entity.User;
import com.mysterybox.service.UserService;
import com.mysterybox.utils.JwtUtils;
import com.mysterybox.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public Result<?> login(@Valid @RequestBody User loginRequest) {
        try {
            // 验证用户名和密码
            User user = userService.findByUsername(loginRequest.getUsername());
            if (user == null) {
                return Result.error(400, "用户不存在");
            }

            // 使用相同的 encoder 实例进行测试
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            if (!encoder.matches(loginRequest.getPassword(), user.getPassword())) {
                return Result.error(400, "密码错误");
            }
//
////            // 生成JWT token
            String token = jwtUtils.generateToken(user.getUsername());

            // 构建响应
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("type", "Bearer");

            // 移除敏感信息
            user.setPassword(null);
            response.put("userInfo", user);

            return Result.success("登录成功", response);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("登录失败：" + e.getMessage());
        }
    }

    @PostMapping("/register")
    public Result<?> register(@Valid @RequestBody User registerRequest) {
        if (userService.existsByUsername(registerRequest.getUsername())) {
            return Result.error(400, "用户名已存在");
        }

        try {
            // 加密密码
            registerRequest.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

            User user = userService.createUser(registerRequest);
            user.setPassword(null); // 移除密码后返回

            return Result.success("注册成功", user);
        } catch (Exception e) {
            return Result.error("注册失败：" + e.getMessage());
        }
    }
} 