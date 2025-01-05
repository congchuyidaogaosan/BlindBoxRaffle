package com.mysterybox.controller;

import com.mysterybox.common.Result;
import com.mysterybox.entity.User;
import com.mysterybox.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/current")
    public Result<User> getCurrentUser() {
        User user = userService.getCurrentUser();
        user.setPassword(null);
        return Result.success(user);
    }

    @PutMapping("/profile")
    public Result<User> updateProfile(@Valid @RequestBody User user) {
        User updatedUser = userService.updateUser(user);
        updatedUser.setPassword(null);
        return Result.success("更新个人信息成功", updatedUser);
    }

    @PutMapping("/balance")
    public Result<User> updateBalance(@RequestParam Long userId, @RequestParam BigDecimal amount) {
        userService.updateBalance(userId, amount);
        User user = userService.getUserById(userId);
        user.setPassword(null);
        return Result.success("更新余额成功", user);
    }

//    @GetMapping("/counts")
//    public Result<Object> getCounts() {
//        User user = userService.getCurrentUser();
//        return Result.success(userService.getUserCounts(user.getId()));
//    }
} 