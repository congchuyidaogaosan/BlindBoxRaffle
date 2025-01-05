package com.mysterybox.service.impl;

import com.mysterybox.dto.DrawPreference;
import com.mysterybox.dto.PopularStyle;
import com.mysterybox.entity.BoxSeries;
import com.mysterybox.entity.BoxStyle;
import com.mysterybox.mapper.BoxSeriesMapper;
import com.mysterybox.mapper.BoxStyleMapper;
import com.mysterybox.service.BoxSeriesService;
import com.mysterybox.service.BoxStyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoxStyleServiceImpl implements BoxStyleService {

    @Autowired
    private BoxStyleMapper boxSeriesMapper;

    @Override
    public List<BoxStyle> getAllStyles() {

        return boxSeriesMapper.selectList(null);
    }

    @Override
    public BoxStyle getStyleById(Long id) {
        return boxSeriesMapper.findById(id);
    }

    @Override
    public BoxStyle createStyle(BoxStyle series) {
        int insert = boxSeriesMapper.insert(series);

        BoxStyle byId = boxSeriesMapper.findById((long) insert);
        return byId;
    }

    @Override
    public BoxStyle updateStyle(BoxStyle series) {
        int insert = boxSeriesMapper.updateById(series);

        BoxStyle byId = boxSeriesMapper.findById((long) insert);
        return byId;
    }

    @Override
    public void deleteStyle(Long id) {
        boxSeriesMapper.deleteById(id);
    }

    //方法作用看不懂
    @Override
    public List<BoxStyle> getStylesBySeriesId(Long seriesId) {
        return null;
    }

    //方法作用看不懂
    @Override
    public List<DrawPreference> getDrawPreferences() {
        return null;
    }

    //方法作用看不懂
    @Override
    public List<PopularStyle> getPopularStyles() {
        return null;
    }
}
