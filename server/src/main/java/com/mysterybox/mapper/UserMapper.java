package com.mysterybox.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mysterybox.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    User findByUsername(String username);
    User findByOpenId(@Param("openId") String openId);
} 