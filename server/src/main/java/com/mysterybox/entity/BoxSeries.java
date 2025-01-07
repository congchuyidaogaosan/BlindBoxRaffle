package com.mysterybox.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@TableName("box_series")
public class BoxSeries {
    @TableId(type = IdType.AUTO)
    private Long id;
    
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    private Integer status;
    private Date createTime;
    private Date updateTime;

    @TableField(exist = false)
    private List<BoxStyle> styles;
} 