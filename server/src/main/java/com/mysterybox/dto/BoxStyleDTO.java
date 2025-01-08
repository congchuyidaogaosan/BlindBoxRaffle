package com.mysterybox.dto;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.mysterybox.entity.BoxSeries;
import com.mysterybox.entity.BoxStyle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data

@NoArgsConstructor
public class BoxStyleDTO {


    private Long id;


    private Long seriesId;



    
    private String name;
    private String description;
    private Integer stock;
    private BigDecimal probability;
    private String imageUrl;
    private Date createTime;
    private Date updateTime;
    private BoxSeries series;

    public BoxStyleDTO(BoxStyle boxStyle, BoxSeries boxSeries) {
        this.setId(boxStyle.getId());
        this.setSeriesId(boxStyle.getSeriesId());
        this.setName(boxStyle.getName());
        this.setDescription(boxStyle.getDescription());
        this.setStock(boxStyle.getStock());
        this.setProbability(boxStyle.getProbability());
        this.setImageUrl(boxStyle.getImageUrl());
        this.setCreateTime(boxStyle.getCreateTime());
        this.setUpdateTime(boxStyle.getUpdateTime());

        this.series=boxSeries;
    }


}
