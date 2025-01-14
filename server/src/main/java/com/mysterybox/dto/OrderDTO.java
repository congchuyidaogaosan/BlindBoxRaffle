package com.mysterybox.dto;

import com.mysterybox.entity.BoxSeries;
import com.mysterybox.entity.BoxStyle;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderDTO {
    // 订单基本信息
    private Long id;
    private Long userId;
    private String status;
    private BigDecimal totalAmount;
    private Date createTime;
    private Date updateTime;
    
    // 款式信息
    private Long styleId;
    private String styleName;
    private String styleImageUrl;
    
    // 系列信息
    private Long seriesId;
    private String seriesName;
    private String seriesDescription;
} 