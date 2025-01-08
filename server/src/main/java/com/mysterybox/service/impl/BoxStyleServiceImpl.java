package com.mysterybox.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mysterybox.dto.BoxStyleQuery;
import com.mysterybox.entity.BoxStyle;
import com.mysterybox.entity.Review;
import com.mysterybox.mapper.BoxStyleMapper;
import com.mysterybox.mapper.ReviewMapper;
import com.mysterybox.service.BoxStyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BoxStyleServiceImpl implements BoxStyleService {

    @Autowired
    private BoxStyleMapper boxStyleMapper;

    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    public List<BoxStyle> getAllStyles(BoxStyleQuery query) {
        return boxStyleMapper.findByNameAndSeriesId(query.getName(), query.getSeriesId());
    }

    @Override
    public List<BoxStyle> getStylesBySeriesId(Long seriesId) {
        return boxStyleMapper.findBySeriesId(seriesId);
    }

    @Override
    public BoxStyle createStyle(BoxStyle style) {
        boxStyleMapper.insert(style);
        return boxStyleMapper.selectById(style.getId());
    }

    @Override
    public BoxStyle updateStyle(BoxStyle style) {
        boxStyleMapper.updateById(style);
        return boxStyleMapper.selectById(style.getId());
    }

    @Override
    public void deleteStyle(Long id) {

        // 再删除款式数据
        boxStyleMapper.delete(id);
    }

    @Override
    public List<BoxStyle> getHotStyles(int limit) {
        return boxStyleMapper.selectList(new QueryWrapper<BoxStyle>()
            .orderByDesc("sales")
            .last("LIMIT " + limit));
    }
}
