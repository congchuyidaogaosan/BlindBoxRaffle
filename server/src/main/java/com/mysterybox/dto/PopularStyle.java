package com.mysterybox.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PopularStyle {
    private Long styleId;
    private String styleName;
    private Long drawCount;
} 