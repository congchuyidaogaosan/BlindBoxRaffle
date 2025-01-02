package com.mysterybox.repository;

import com.mysterybox.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    
    @Query("SELECT COUNT(u) FROM User u WHERE u.createTime > :time")
    int countByCreateTimeAfter(@Param("time") LocalDateTime time);
    
    @Query("SELECT COUNT(DISTINCT u.id) FROM User u JOIN LoginLog l ON u.id = l.user.id WHERE l.loginTime > :time")
    int countActiveUsers(@Param("time") LocalDateTime time);
} 