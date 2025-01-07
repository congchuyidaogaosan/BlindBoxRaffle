package com.mysterybox.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("box_style")
public class BoxStyle {
    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("series_id")
    private Long seriesId;

    @TableField(exist = false)
    private BoxSeries series;
    
    private String name;
    private String description;
    private Integer stock;
    private BigDecimal probability;
    private String imageUrl;
    private Date createTime;
    private Date updateTime;
} 