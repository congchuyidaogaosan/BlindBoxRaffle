package com.mysterybox.dto;

import com.mysterybox.entity.User;
import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private User userInfo;
} 