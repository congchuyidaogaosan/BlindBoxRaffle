package com.mysterybox.dto;

import lombok.Data;
import java.time.LocalDate;
import java.math.BigDecimal;

@Data
public class SalesTrend {
    private LocalDate date;
    private BigDecimal amount;
} 