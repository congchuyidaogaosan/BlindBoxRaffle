package com.mysterybox.controller;

import com.mysterybox.common.Result;
import com.mysterybox.dto.BoxStyleDTO;
import com.mysterybox.dto.BoxStyleQuery;
import com.mysterybox.entity.BoxSeries;
import com.mysterybox.entity.BoxStyle;
import com.mysterybox.service.BoxSeriesService;
import com.mysterybox.service.BoxStyleService;
import com.mysterybox.service.impl.BoxSeriesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/styles")
public class BoxStyleController {

    @Autowired
    private BoxStyleService boxStyleService;


    @Autowired
    private BoxSeriesServiceImpl boxSeriesServiceImpl;

    @GetMapping("/list")
    public Result<List<BoxStyleDTO>> getAllStyles(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) Long seriesId
    ) {
        BoxStyleQuery query = new BoxStyleQuery();
        query.setName(name);
        query.setSeriesId(seriesId);
        List<BoxStyle> styleList = boxStyleService.getAllStyles(query);
        ArrayList<BoxStyleDTO> boxStyleDTOS = new ArrayList<>();
        if (styleList.size()>0) {
            for(BoxStyle style : styleList) {


                BoxSeries seriesById = boxSeriesServiceImpl.getSeriesById(style.getSeriesId());
                BoxStyleDTO boxStyleDTO = new BoxStyleDTO(style, seriesById);
                boxStyleDTOS.add(boxStyleDTO);

            }
        }
        return Result.success(boxStyleDTOS);
    }

    @GetMapping("/series/{seriesId}")
    public Result<List<BoxStyle>> getStylesBySeriesId(@PathVariable Long seriesId) {
        List<BoxStyle> styles = boxStyleService.getStylesBySeriesId(seriesId);
        return Result.success(styles);
    }

    @PostMapping("/create")
    public Result<BoxStyle> createStyle(@Valid @RequestBody BoxStyle style) {
        BoxStyle savedStyle = boxStyleService.createStyle(style);
        return Result.success("创建款式成功", savedStyle);
    }

    @PostMapping("/update/{id}")
    public Result<BoxStyle> updateStyle(@PathVariable Long id, @Valid @RequestBody BoxStyle style) {
        style.setId(id);
        BoxStyle updatedStyle = boxStyleService.updateStyle(style);
        return Result.success("更新款式成功", updatedStyle);
    }

    @PostMapping("/delete/{id}")
    public Result<Void> deleteStyle(@PathVariable Long id) {
        boxStyleService.deleteStyle(id);
        return Result.success("删除款式成功", null);
    }

    @GetMapping("/hot")
    public Result<List<BoxStyle>> getHotStyles(@RequestParam(defaultValue = "3") int limit) {
        List<BoxStyle> hotStyles = boxStyleService.getHotStyles(limit);
        return Result.success(hotStyles);
    }
} 