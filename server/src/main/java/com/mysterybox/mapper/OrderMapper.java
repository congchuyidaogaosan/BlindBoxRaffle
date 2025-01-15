package com.mysterybox.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mysterybox.entity.BoxSeries;
import com.mysterybox.entity.orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<orders> {
    List<orders> findAll();
    orders findById(@Param("id") Long id);
    
    // 合并重复的方法,使用@Select注解,添加关联查询
    @Select("SELECT o.*, bs.name as series_name, bs.description as series_description, " +
           "bst.name as style_name, bst.image_url as style_image_url " +
           "FROM orders o " +
           "LEFT JOIN box_style bst ON o.box_style_id = bst.id " +
           "LEFT JOIN box_series bs ON bst.series_id = bs.id " +
           "WHERE o.user_id = #{userId} " +
           "ORDER BY o.create_time DESC")
    List<orders> findByUserId(@Param("userId") Long userId);
    
    List<orders> findByCreateTimeBetween(@Param("startTime") LocalDateTime startTime,
                                      @Param("endTime") LocalDateTime endTime);

    void update(orders order);
    void delete(@Param("id") Long id);
    int countByCreateTimeAfter(@Param("time") LocalDateTime time);
    
    // 统计用户特定状态的订单数量
    Integer countByUserIdAndStatus(@Param("userId") Long userId, @Param("status") String status);
    
    // 根据用户ID和状态查询订单,添加关联查询
    @Select("SELECT o.*, bs.name as style_name, bs.image_url as style_image_url, " +
           "bsr.id as series_id, bsr.name as series_name, bsr.description as series_description " +
           "FROM orders o " +
           "LEFT JOIN box_style bs ON o.box_style_id = bs.id " +
           "LEFT JOIN box_series bsr ON bs.series_id = bsr.id " +
           "WHERE o.user_id = #{userId} AND o.status = #{status} " +
           "ORDER BY o.create_time DESC")
    List<orders> findByUserIdAndStatus(@Param("userId") Long userId, @Param("status") String status);

    // 统计用户获得的不同款式数量
    Integer countDistinctStylesByUserId(@Param("userId") Long userId);
} 