package com.mysterybox.mapper;

import com.mysterybox.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface UserMapper {
    List<User> findAll();
    User findById(@Param("id") Long id);
    User findByUsername(@Param("username") String username);
    void insert(User user);
    void update(User user);
    void delete(@Param("id") Long id);
    void updateBalance(@Param("id") Long id, @Param("amount") BigDecimal amount);
    int countByCreateTimeAfter(@Param("time") LocalDateTime time);
} 