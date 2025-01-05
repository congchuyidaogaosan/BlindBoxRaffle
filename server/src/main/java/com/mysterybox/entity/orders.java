package com.mysterybox.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class orders extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @TableField(exist = false)
    private User user;
    
    private BigDecimal totalAmount;
    private String status;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @TableField(exist = false)
    private List<OrderDetail> orderDetails;
} 