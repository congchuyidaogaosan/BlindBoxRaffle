package com.mysterybox.service;

import com.mysterybox.entity.User;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User getUserByUsername(String username);
    User createUser(User user);
    User updateUser(User user);
    void deleteUser(Long id);
    boolean existsByUsername(String username);
    void updateBalance(Long userId, java.math.BigDecimal newBalance);
    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByUsername(String username);
    User getCurrentUser();
} 