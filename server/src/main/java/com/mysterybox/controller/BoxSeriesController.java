package com.mysterybox.controller;

import com.mysterybox.common.Result;
import com.mysterybox.entity.BoxSeries;
import com.mysterybox.service.BoxSeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/series")
public class BoxSeriesController {
    
    @Autowired
    private BoxSeriesService boxSeriesService;
    
    @GetMapping("/list")
    public Result<List<BoxSeries>> getAllSeries() {
        List<BoxSeries> seriesList = boxSeriesService.getAllSeries();
        return Result.success(seriesList);
    }
    
    @PostMapping("/detail/{id}")
    public Result<BoxSeries> getSeriesById(@PathVariable Long id) {
        BoxSeries series = boxSeriesService.getSeriesById(id);
        if (series == null) {
            return Result.error(404, "系列不存在");
        }
        return Result.success(series);
    }
    
    @PostMapping("/create")
    public Result<BoxSeries> createSeries(@Valid @RequestBody BoxSeries series) {
        BoxSeries savedSeries = boxSeriesService.createSeries(series);
        return Result.success("创建系列成功", savedSeries);
    }
    
    @PostMapping("/update/{id}")
    public Result<BoxSeries> updateSeries(@PathVariable Long id, @Valid @RequestBody BoxSeries series) {
        series.setId(id);
        BoxSeries updatedSeries = boxSeriesService.updateSeries(series);
        return Result.success("更新系列成功", updatedSeries);
    }
    
    @PostMapping("/delete/{id}")
    public Result<Void> deleteSeries(@PathVariable Long id) {
        boxSeriesService.deleteSeries(id);
        return Result.success("删除系列成功", null);
    }
} 