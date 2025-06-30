package com.webtab.shecpsims.model.dto.bigdata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyTrendDTO {
    private String month;
    private BigDecimal revenue;
    private BigDecimal cost;
}