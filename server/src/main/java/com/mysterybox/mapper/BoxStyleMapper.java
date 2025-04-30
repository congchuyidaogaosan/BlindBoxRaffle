package com.mysterybox.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mysterybox.entity.BoxSeries;
import com.mysterybox.entity.BoxStyle;
import com.mysterybox.dto.DrawPreference;
import com.mysterybox.dto.PopularStyle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BoxStyleMapper extends BaseMapper<BoxStyle> {
    List<BoxStyle> findByNameAndSeriesId(
        @Param("name") String name,
        @Param("seriesId") Long seriesId
    );
    BoxStyle findById(@Param("id") Long id);
    List<BoxStyle> findBySeriesId(@Param("seriesId") Long seriesId);
    List<BoxStyle> findBySeriesIdAndStatus(@Param("seriesId") Long seriesId, @Param("status") Integer status);

   // int insert(BoxStyle style);
    void update(BoxStyle style);
    void delete(@Param("id") Long id);
    List<DrawPreference> findDrawPreferences();
    List<PopularStyle> findPopularStyles();

    @Select("select * from box_style where id=#{id}")
    BoxStyle selectById(@Param("id") Long id);

} 