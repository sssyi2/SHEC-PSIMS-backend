package com.webtab.shecpsims.model.entity.bigdata;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CostBreakdown {
    private Integer id;
    private Integer financialDataId;
    private String costCategory;
    private BigDecimal costAmount;
    private String description;
    private LocalDateTime createdAt;
}