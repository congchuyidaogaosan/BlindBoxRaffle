package com.mysterybox.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.math.BigDecimal;

@Data
@Entity
@TableName("user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String phone;
    private String role;
    private BigDecimal balance;
    private String openId;

    private String avatarUrl;
    private Integer gender;
    private String country;
    private String province;
    private String city;
    private String language;
    private Integer status;
    private Date lastLoginTime;
    private Date createTime;
    private Date updateTime;

//    @Column(name = "openid")
//    private String openid;
//    @Column(name = "sessionkey")
//    private String sessionkey;

} 