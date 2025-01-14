package com.mysterybox.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class orders extends BaseEntity {


    @Id
    @TableId(type = IdType.AUTO)
    private Long id;

    @Column(name = "user_id")

    private Long userId;
    
    @Column(name = "box_style_id")
    private Long boxStyleId;
    
    private BigDecimal totalAmount;
    private String status;  // PENDING, COMPLETED, CANCELLED
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @TableField(exist = false)
    private User user;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @TableField(exist = false)
    private List<OrderDetail> orderDetails;
    
    // 款式关联字段
    @TableField(exist = false)
    private String styleName;
    
    @TableField(exist = false)
    private String styleImageUrl;
    
    // 系列关联字段
    @TableField(exist = false)
    private Long seriesId;
    
    @TableField(exist = false)
    private String seriesName;
    
    @TableField(exist = false)
    private String seriesDescription;
} 