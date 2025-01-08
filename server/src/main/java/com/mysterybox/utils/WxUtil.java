package com.mysterybox.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WxUtil {

    @Value("${wx.miniapp.appid}")
    private String appId;

    @Value("${wx.miniapp.secret}")
    private String appSecret;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String getOpenId(String code) {
        String url = String.format(
            "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code",
            appId, appSecret, code
        );

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        try {
            JsonNode root = objectMapper.readTree(response.getBody());
            if (root.has("errcode") && root.get("errcode").asInt() != 0) {
                throw new RuntimeException("获取openId失败：" + root.get("errmsg").asText());
            }
            return root.get("openid").asText();
        } catch (Exception e) {
            throw new RuntimeException("解析微信返回数据失败", e);
        }
    }
} 