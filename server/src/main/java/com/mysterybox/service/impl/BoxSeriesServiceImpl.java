package com.mysterybox.service.impl;

import com.mysterybox.entity.BoxSeries;
import com.mysterybox.mapper.BoxSeriesRepository;
import com.mysterybox.service.BoxSeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class BoxSeriesServiceImpl implements BoxSeriesService {
    @Autowired
    private BoxSeriesRepository boxSeriesRepository;

    @Override
    public List<BoxSeries> getAllSeries() {
        return boxSeriesRepository.findAll();
    }

    @Override
    public BoxSeries getSeriesById(Long id) {
        return boxSeriesRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Series not found"));
    }

    @Override
    public BoxSeries createSeries(BoxSeries series) {
        return boxSeriesRepository.save(series);
    }

    @Override
    public BoxSeries updateSeries(BoxSeries series) {
        return boxSeriesRepository.save(series);
    }

    @Override
    public void deleteSeries(Long id) {
        boxSeriesRepository.deleteById(id);
    }
} 