package com.mysterybox.service.impl;

import com.mysterybox.entity.BoxSeries;
import com.mysterybox.mapper.BoxSeriesMapper;
import com.mysterybox.service.BoxSeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoxSeriesServiceImpl implements BoxSeriesService {

    @Autowired
    private BoxSeriesMapper boxSeriesMapper;

    @Override
    public List<BoxSeries> getAllSeries() {
        List<BoxSeries> boxSeries = boxSeriesMapper.selectList(null);
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
}
