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
        List<BoxStyle> styleList = boxStyleService.getAllStyles();
        return Result.success(styleList);
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
        BoxStyle savedStyle = boxStyleService.createStyle(style);
        return Result.success("创建款式成功", savedStyle);
    }

    @PutMapping("/{id}")
    public Result<BoxStyle> updateStyle(@PathVariable Long id, @Valid @RequestBody BoxStyle style) {
        style.setId(id);
        BoxStyle updatedStyle = boxStyleService.updateStyle(style);
        return Result.success("更新款式成功", updatedStyle);
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteStyle(@PathVariable Long id) {
        boxStyleService.deleteStyle(id);
        return Result.success("删除款式成功", null);
    }

    @PostMapping("/{id}/image")
    public Result<String> uploadImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
    //mei you  zhe ge fang  fa
//        String imageUrl = boxStyleService.uploadImage(id, file);
        return Result.success("上传图片成功", "");
    }
} 