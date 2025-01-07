package com.mysterybox.service;

import com.mysterybox.entity.BoxStyle;
import com.mysterybox.dto.BoxStyleQuery;
import com.mysterybox.dto.DrawPreference;
import com.mysterybox.dto.PopularStyle;
import java.util.List;

public interface BoxStyleService {
    List<BoxStyle> getAllStyles(BoxStyleQuery query);
    List<BoxStyle> getStylesBySeriesId(Long seriesId);
    BoxStyle createStyle(BoxStyle style);
    BoxStyle updateStyle(BoxStyle style);
    void deleteStyle(Long id);
    List<BoxStyle> getHotStyles(int limit);
} 