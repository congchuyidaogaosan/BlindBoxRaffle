package com.mysterybox.service;

import com.mysterybox.entity.BoxStyle;

import com.mysterybox.dto.DrawResult;
import com.mysterybox.entity.orders;

import java.util.List;

public interface DrawService {
    DrawResult draw(Long userId, Long seriesId);
    List<DrawResult> drawMultiple(Long userId, Long seriesId, int count);
    BoxStyle calculateDrawResult(Long seriesId);
    orders createDrawOrder(Long userId, BoxStyle style);
} 