package com.mysterybox.controller;

import com.mysterybox.common.Result;
import com.mysterybox.dto.DrawResult;
import com.mysterybox.service.DrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/draw")
public class DrawController {

    @Autowired
    private DrawService drawService;

    @PostMapping("/mystery")
    public Result<DrawResult> drawMystery(@RequestParam Long seriesId, @RequestParam Long userId) {
        try {
            DrawResult result = drawService.draw(userId, seriesId);
            return Result.success(result);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}