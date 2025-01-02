package com.mysterybox.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrawPreference {
    private Long seriesId;
    private String seriesName;
    private Long drawCount;
} 