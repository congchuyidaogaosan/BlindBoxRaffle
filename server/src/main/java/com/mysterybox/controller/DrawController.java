package com.mysterybox.controller;

import com.mysterybox.common.Result;
import com.mysterybox.dto.DrawResult;
import com.mysterybox.entity.BoxStyle;
import com.mysterybox.service.DrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/draw")
public class DrawController {

    @Autowired
    private DrawService drawService;

    @PostMapping("/{seriesId}")
    public Result<DrawResult> draw(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long seriesId) {
        DrawResult result = drawService.draw(Long.valueOf(userDetails.getUsername()), seriesId);
        return Result.success("抽奖成功", result);
    }

    @PostMapping("/{seriesId}/multiple/{count}")
    public Result<List<DrawResult>> drawMultiple(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long seriesId,
            @PathVariable int count) {
        List<DrawResult> results = drawService.drawMultiple(Long.valueOf(userDetails.getUsername()), seriesId, count);
        return Result.success("抽奖成功", results);
    }

    @GetMapping("/history/{userId}")
    public Result<List<BoxStyle>> getDrawHistory(@PathVariable Long userId) {
//        return Result.success(drawService.getDrawHistory(userId));
        return Result.success(null);
    }
} 