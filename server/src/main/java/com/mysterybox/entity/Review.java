package com.mysterybox.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "review")
public class Review extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "box_style_id")
    private BoxStyle boxStyle;
    
    private String content;
    private Integer rating;
} 