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

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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

    // 获取钱包余额
    @GetMapping("/wallet/balance")
    public Result<BigDecimal> getWalletBalance(@RequestParam Long userId) {
        try {
            User user = userService.getUserById(userId);
            if (user == null) {
                return Result.error("用户不存在");
            }
            return Result.success(user.getBalance());
        } catch (Exception e) {
            return Result.error("获取余额失败: " + e.getMessage());
        }
    }

    // 充值
    @PostMapping("/wallet/recharge")
    public Result<BigDecimal> recharge(
            @RequestParam Long userId,
            @RequestParam BigDecimal amount) {
        System.out.println(userId+ ""+amount);
        try {
            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                return Result.error("充值金额必须大于0");
            }
            
            User user = userService.getUserById(userId);
            if (user == null) {
                return Result.error("用户不存在");
            }
            
            // 计算新余额
            BigDecimal newBalance = user.getBalance().add(amount);
            
            // 更新用户余额
            userService.updateBalance(userId, newBalance);
            
            return Result.success("充值成功", newBalance);
        } catch (Exception e) {
            return Result.error("充值失败: " + e.getMessage());
        }
    }

    @GetMapping("/stats")
    public Result<Map<String, Integer>> getUserStats(@RequestParam Long userId) {
        try {
            Map<String, Integer> stats = userService.getUserStats(userId);
            return Result.success(stats);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    /**
     * 获取所有用户列表
     */
    @GetMapping("/list")
    public Result<List<User>> getUsers() {
        try {
            List<User> users = userService.getAllUsers();
            return Result.success(users);
        } catch (Exception e) {
            return Result.error("获取用户列表失败: " + e.getMessage());
        }
    }

    /**
     * 逻辑删除用户
     */
    @PostMapping("/delete/{id}")
    public Result<String> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return Result.success("删除用户成功");
        } catch (Exception e) {
            return Result.error("删除用户失败: " + e.getMessage());
        }
    }
} 