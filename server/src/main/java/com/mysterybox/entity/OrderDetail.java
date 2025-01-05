package com.mysterybox.entity;

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "order_detail")
public class OrderDetail extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private orders order;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "box_style_id")
    private BoxStyle boxStyle;
    
    private Integer quantity;
    private BigDecimal price;
} 