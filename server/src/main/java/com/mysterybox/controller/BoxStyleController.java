package com.mysterybox.controller;

import com.mysterybox.common.Result;
import com.mysterybox.entity.BoxStyle;
import com.mysterybox.service.BoxStyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/styles")
public class BoxStyleController {
    @Autowired
    private BoxStyleService boxStyleService;

    @GetMapping
    public Result<List<BoxStyle>> getAllStyles() {
        return Result.success(boxStyleService.getAllStyles());
    }

    @GetMapping("/{id}")
    public Result<BoxStyle> getStyleById(@PathVariable Long id) {
        BoxStyle style = boxStyleService.getStyleById(id);
        if (style == null) {
            return Result.error(404, "款式不存在");
        }
        return Result.success(style);
    }

    @GetMapping("/series/{seriesId}")
    public Result<List<BoxStyle>> getStylesBySeriesId(@PathVariable Long seriesId) {
        return Result.success(boxStyleService.getStylesBySeriesId(seriesId));
    }

    @PostMapping
    public Result<BoxStyle> createStyle(@Valid @RequestBody BoxStyle style) {
        return Result.success("创建成功", boxStyleService.createStyle(style));
    }

    @PutMapping("/{id}")
    public Result<BoxStyle> updateStyle(@PathVariable Long id, @Valid @RequestBody BoxStyle style) {
        style.setId(id);
        return Result.success("更新成功", boxStyleService.updateStyle(style));
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteStyle(@PathVariable Long id) {
        boxStyleService.deleteStyle(id);
        return Result.success("删除成功", null);
    }

    @PostMapping("/{id}/image")
    public Result<String> uploadImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
//        String imageUrl = boxStyleService.uploadImage(id, file);
        return Result.success("上传成功", null);
    }
} 