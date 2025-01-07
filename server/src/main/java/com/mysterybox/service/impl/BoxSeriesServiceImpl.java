package com.mysterybox.service.impl;

import com.mysterybox.entity.BoxSeries;
import com.mysterybox.mapper.BoxSeriesMapper;
import com.mysterybox.service.BoxSeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import java.util.List;

@Service
public class BoxSeriesServiceImpl implements BoxSeriesService {

    @Autowired
    private BoxSeriesMapper boxSeriesMapper;

    @Override
    public List<BoxSeries> getAllSeries(String name, Integer status) {
        List<BoxSeries> boxSeries = boxSeriesMapper.findByNameAndStatus(name, status);
        return boxSeries;
    }

    @Override
    public BoxSeries getSeriesById(Long id) {

        return boxSeriesMapper.findById(id);
    }

    @Override
    public BoxSeries createSeries(BoxSeries series) {
        int insert = boxSeriesMapper.insert(series);

        return boxSeriesMapper.findById(series.getId());
    }

    @Override
    public BoxSeries updateSeries(BoxSeries series) {
        int i = boxSeriesMapper.updateById(series);

        return boxSeriesMapper.findById(series.getId());
    }

    @Override
    public void deleteSeries(Long id) {
        int i = boxSeriesMapper.deleteById(id);
    }

    @Override
    public List<BoxSeries> getHotSeries(int limit) {
        return boxSeriesMapper.findHotSeries(limit);
    }
}
