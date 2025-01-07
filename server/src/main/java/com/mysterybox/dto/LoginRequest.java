package com.mysterybox.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String code;
    private WxUserInfo userInfo;

    @Data
    public static class WxUserInfo {
        private String nickName;
        private String avatarUrl;
        private Integer gender;
        private String country;
        private String province;
        private String city;
        private String language;
    }
} 