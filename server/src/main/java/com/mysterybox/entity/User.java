package com.mysterybox.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    @Column(name = "balance")
    private BigDecimal balance = BigDecimal.ZERO;
    private String openId;
    private String sessionkey;
    private String avatarUrl;
    private Integer gender;
    private String country;
    private String province;
    private String city;
    private String language;
    private Integer status;
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss", timezone ="GMT+8")
    private Date lastLoginTime;
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss", timezone ="GMT+8")
    private Date createTime;
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss", timezone ="GMT+8")
    private Date updateTime;

//    @Column(name = "openid")
//    private String openid;
//    @Column(name = "sessionkey")
//    private String sessionkey;

} 