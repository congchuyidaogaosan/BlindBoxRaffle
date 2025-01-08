package com.mysterybox.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@TableName("box_style")
public class BoxStyle {
    @TableId(type = IdType.AUTO)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @TableField("series_id")
    private Long seriesId;

    @TableField(exist = false)
    private String seriesName;
    
    @TableField(exist = false)
    private String seriesDescription;
    
    @TableField(exist = false)
    private BigDecimal seriesPrice;

    private String name;
    private String description;
    private Integer stock;
    private BigDecimal probability;
    private String imageUrl;
    private Date createTime;
    private Date updateTime;
    @TableLogic
    private Integer isDeleted;
} 