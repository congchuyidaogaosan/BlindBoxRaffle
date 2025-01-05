package com.mysterybox.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "box_style")
public class BoxStyle extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "series_id")
    @TableField(exist = false)
    private BoxSeries series;
    
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private BigDecimal probability;
    private String imageUrl;
    private Integer status;
} 