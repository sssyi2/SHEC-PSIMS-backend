package com.webtab.shecpsims.model.entity.bigdata;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class MonthlyFinancialTrends {
    private Integer id;
    private Integer year;
    private Integer month;
    private BigDecimal revenue;
    private BigDecimal cost;
    private BigDecimal profit; // 计算字段
    private BigDecimal roi; // 计算字段
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}