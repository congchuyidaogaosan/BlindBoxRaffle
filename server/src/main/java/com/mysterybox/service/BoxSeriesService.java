package com.mysterybox.service;

import com.mysterybox.entity.BoxSeries;
import java.util.List;

public interface BoxSeriesService {
    List<BoxSeries> getAllSeries();
    BoxSeries getSeriesById(Long id);
    BoxSeries createSeries(BoxSeries series);
    BoxSeries updateSeries(BoxSeries series);
    void deleteSeries(Long id);
} 