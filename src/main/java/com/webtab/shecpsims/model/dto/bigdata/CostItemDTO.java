package com.webtab.shecpsims.model.dto.bigdata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostItemDTO {
    private String name;
    private BigDecimal value;
    private String colorCode;
}