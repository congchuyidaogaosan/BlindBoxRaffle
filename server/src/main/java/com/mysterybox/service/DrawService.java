package com.mysterybox.service;

import com.mysterybox.entity.BoxStyle;
import com.mysterybox.entity.Order;
import com.mysterybox.dto.DrawResult;
import java.util.List;

public interface DrawService {
    DrawResult draw(Long userId, Long seriesId);
    List<DrawResult> drawMultiple(Long userId, Long seriesId, int count);
    BoxStyle calculateDrawResult(Long seriesId);
    Order createDrawOrder(Long userId, BoxStyle style);
} 