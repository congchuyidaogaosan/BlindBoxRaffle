package com.mysterybox.service.impl;

import com.mysterybox.entity.BoxStyle;
import com.mysterybox.mapper.BoxStyleRepository;
import com.mysterybox.service.BoxStyleService;
import com.mysterybox.dto.DrawPreference;
import com.mysterybox.dto.PopularStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class BoxStyleServiceImpl implements BoxStyleService {
    @Autowired
    private BoxStyleRepository boxStyleRepository;

    @Override
    public List<BoxStyle> getAllStyles() {
        return boxStyleRepository.findAll();
    }

    @Override
    public BoxStyle getStyleById(Long id) {
        return boxStyleRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Style not found"));
    }

    @Override
    public List<BoxStyle> getStylesBySeriesId(Long seriesId) {
        return boxStyleRepository.findBySeriesId(seriesId);
    }

    @Override
    public BoxStyle createStyle(BoxStyle style) {
        return boxStyleRepository.save(style);
    }

    @Override
    public BoxStyle updateStyle(BoxStyle style) {
        return boxStyleRepository.save(style);
    }

    @Override
    public void deleteStyle(Long id) {
        boxStyleRepository.deleteById(id);
    }

    @Override
    public List<DrawPreference> getDrawPreferences() {
        return boxStyleRepository.findDrawPreferences();
    }

    @Override
    public List<PopularStyle> getPopularStyles() {
        return boxStyleRepository.findPopularStyles();
    }
} 