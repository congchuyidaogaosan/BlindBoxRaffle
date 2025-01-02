package com.mysterybox.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "box_series")
public class BoxSeries extends BaseEntity {
    private String name;
    private String description;
    private Integer status;
    
    @OneToMany(mappedBy = "series")
    private List<BoxStyle> styles;
} 