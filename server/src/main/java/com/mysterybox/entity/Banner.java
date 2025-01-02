package com.mysterybox.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "banner")
public class Banner extends BaseEntity {
    private String imageUrl;
    private String linkUrl;
    private Integer sortOrder;
    private Integer status;
} 