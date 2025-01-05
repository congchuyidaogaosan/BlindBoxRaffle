package com.mysterybox.service.impl;

import com.mysterybox.dto.DrawResult;
import com.mysterybox.entity.BoxStyle;

import com.mysterybox.entity.orders;
import com.mysterybox.service.DrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//作用
@Service
public class DrawServiceImpl implements DrawService {

//    @Autowired
//    private Draw

    @Override
    public DrawResult draw(Long userId, Long seriesId) {
        return null;
    }

    @Override
    public List<DrawResult> drawMultiple(Long userId, Long seriesId, int count) {
        return null;
    }

    @Override
    public BoxStyle calculateDrawResult(Long seriesId) {
        return null;
    }

    @Override
    public orders createDrawOrder(Long userId, BoxStyle style) {
        return null;
    }
}
