package com.webtab.shecpsims.model.dto.bigdata;

import lombok.Data;

@Data
public class UpdateInventoryDTO {
    private Integer stock;
    private Integer minStock;
    private Double dailyUsage;
}
