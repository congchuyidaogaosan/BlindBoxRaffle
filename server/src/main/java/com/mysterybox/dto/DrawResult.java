package com.mysterybox.dto;

import com.mysterybox.entity.BoxStyle;
import com.mysterybox.entity.Order;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrawResult {
    private BoxStyle style;
    private Order order;
} 