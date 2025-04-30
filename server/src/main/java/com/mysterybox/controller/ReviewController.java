package com.mysterybox.controller;


import com.mysterybox.dto.Review;
import com.mysterybox.service.ReviewService;
import com.mysterybox.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Review")
public class ReviewController {



    @Autowired
    private ReviewService reviewService;


    /**
     * 获取所有用户列表
     */
    @PostMapping("/add")
    public Result<?> add(@RequestBody Review review) {
        try {
             reviewService.save(review);
            return Result.success("");
        } catch (Exception e) {
            return Result.error("获取用户列表失败: " + e.getMessage());
        }
    }

    /**
     * 获取所有用户列表
     */
    @GetMapping("/list")
    public Result<List<Review>> getUsers() {
        try {
            List<Review> list = reviewService.list();
            return Result.success(list);
        } catch (Exception e) {
            return Result.error("获取用户列表失败: " + e.getMessage());
        }
    }

    /**
     * 逻辑删除用户
     */
    @PostMapping("/delete/{id}")
    public Result<String> deleteUser(@PathVariable Long id) {
        try {
            reviewService.removeById(id);
            return Result.success("删除用户成功");
        } catch (Exception e) {
            return Result.error("删除用户失败: " + e.getMessage());
        }
    }







}
