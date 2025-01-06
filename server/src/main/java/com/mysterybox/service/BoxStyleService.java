package com.mysterybox.service;

import com.mysterybox.entity.BoxStyle;
import com.mysterybox.dto.DrawPreference;
import com.mysterybox.dto.PopularStyle;
import java.util.List;

public interface BoxStyleService {
    List<BoxStyle> getAllStyles();
    BoxStyle getStyleById(Long id);
    List<BoxStyle> getStylesBySeriesId(Long seriesId);
    BoxStyle createStyle(BoxStyle style);
    BoxStyle updateStyle(BoxStyle style);
    void deleteStyle(Long id);
    List<DrawPreference> getDrawPreferences();
    List<PopularStyle> getPopularStyles();
    List<BoxStyle> getHotStyles(int limit);
} 