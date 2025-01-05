package com.mysterybox.mapper;

import com.mysterybox.entity.Review;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface ReviewMapper {
    List<Review> findAll();
    Review findById(@Param("id") Long id);
    List<Review> findByUserId(@Param("userId") Long userId);

    void insert(Review review);
    void update(Review review);
    void delete(@Param("id") Long id);
} 