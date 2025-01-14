package com.mysterybox.controller.wx;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mysterybox.common.Result;
import com.mysterybox.dto.LoginResponse;
import com.mysterybox.entity.User;
import com.mysterybox.service.UserService;
import com.mysterybox.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/WXLogin")
public class LoginController {

    private final String AppId = "wxb38fa1462130b7fd";
    private final String AppSecret = "e7965d960c3a161ce50bff28eaf42b15";


    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService kehuService;

    @RequestMapping("setCode")
    public Result setCode(@RequestParam("code") String code) {

        System.out.println("code" + code);

        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("appid", AppId);
        params.add("secret", AppSecret);
        params.add("js_code", code);
        String url = "https://api.weixin.qq.com/sns/jscode2session";

        System.out.println(url + "?appid=" + AppId + "&secret=" + AppSecret + "&js_code=" + code);
        HttpHeaders headers = new HttpHeaders();
        //将请求头部和参数合成一个请求
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        try {
            System.out.println("response.getStatusCode() :" + response.getStatusCode());

            JSONObject jsonObject = JSON.parseObject(response.getBody());
            String session_key = (String) jsonObject.get("session_key");
            String openid = (String) jsonObject.get("openid");
            //这个注释解开 和这个方法里边的注释解开  可以进行用户自行保存
            User kehuEntity = AddOrUpdate(openid, session_key);


            // 3. 生成token
            String token = jwtUtil.generateToken(kehuEntity);
            //暂时假用
            //String token = jwtUtil.generateToken(session_key, openid);


            // 4. 构建返回结果
            LoginResponse loginresponse = new LoginResponse();
            loginresponse.setToken(token);
            //记得换成用户   kehuEntity
            loginresponse.setUserInfo(kehuEntity);

            return Result.success(loginresponse);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Result.error(205, "微信登入失败");
        }
    }


    private User AddOrUpdate(String openID, String sessionKey) {
        User kehuEntity = new User();
        kehuEntity.setOpenId(openID);
        kehuEntity.setSessionkey(sessionKey);
        User info = kehuService.getOne(new QueryWrapper<User>().eq("open_id", openID));
        if(info == null) {
            kehuService.newsave(kehuEntity);

        }else {
            kehuService.updateById(kehuEntity);
        }

     //   boolean openid = kehuService.saveOrUpdate(kehuEntity, new QueryWrapper<User>().eq("openid", openID));


        return info;
    }

}
