package com.mysterybox.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mysterybox.dto.BoxStyleQuery;
import com.mysterybox.entity.BoxStyle;
import com.mysterybox.mapper.BoxStyleMapper;
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
        return boxStyleMapper.findById(style.getId());
    }

    @Override
    public BoxStyle updateStyle(BoxStyle style) {
        boxStyleMapper.updateById(style);
        return boxStyleMapper.findById(style.getId());
    }

    @Override
    public void deleteStyle(Long id) {
        boxStyleMapper.deleteById(id);
    }

    @Override
    public List<BoxStyle> getHotStyles(int limit) {
        return boxStyleMapper.selectList(new QueryWrapper<BoxStyle>()
            .orderByDesc("sales")
            .last("LIMIT " + limit));
    }
}
