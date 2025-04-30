package com.mysterybox.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysterybox.dto.Review;
import com.mysterybox.service.ReviewService;
import com.mysterybox.mapper.StatisticsMapper;
import org.springframework.stereotype.Service;

/**
* @author tian
* @description 针对表【review】的数据库操作Service实现
* @createDate 2025-04-30 11:41:55
*/
@Service
public class ReviewServiceImpl extends ServiceImpl<StatisticsMapper.ReviewMapper, Review>
    implements ReviewService{

}




