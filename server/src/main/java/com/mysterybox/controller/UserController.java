package com.mysterybox.controller;

import com.mysterybox.common.Result;
import com.mysterybox.dto.LoginRequest;
import com.mysterybox.dto.LoginResponse;
import com.mysterybox.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = userService.login(request);
        return Result.success(response);
    }
} 