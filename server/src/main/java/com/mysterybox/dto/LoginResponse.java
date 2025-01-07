package com.mysterybox.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private UserInfo userInfo;

    @Data
    public static class UserInfo {
        private Long id;
        private String nickName;
        private String avatarUrl;
        private Integer gender;
    }
} 