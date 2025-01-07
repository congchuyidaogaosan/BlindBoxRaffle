package com.mysterybox.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WxUtil {

    @Value("${wx.appId}")
    private String appId;

    @Value("${wx.appSecret}")
    private String appSecret;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String getOpenId(String code) {
        String url = String.format(
            "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code",
            appId, appSecret, code
        );

        try {
            String response = restTemplate.getForObject(url, String.class);
            JsonNode jsonNode = objectMapper.readTree(response);
            return jsonNode.get("openid").asText();
        } catch (Exception e) {
            throw new RuntimeException("获取openId失败", e);
        }
    }
} 