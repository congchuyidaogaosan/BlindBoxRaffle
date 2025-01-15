package com.mysterybox.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mysterybox.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    User findByUsername(String username);
    User findByOpenId(@Param("openId") String openId);
    int insertUser(User user);
    int updateUser(User user);

    @Insert("\n" +
            "INSERT INTO user (open_id,sessionkey) VALUE (#{open_id},#{sessionkey})")
    int insertOne(@Param("open_id")String open_id,@Param("sessionkey") String sessionkey);

    /**
     * 查询所有未删除的用户
     */
    List<User> findAll();

    /**
     * 逻辑删除用户
     */
    void logicDelete(@Param("id") Long id);

} 