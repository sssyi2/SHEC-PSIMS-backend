package com.webtab.shecpsims.model.entity.bigdata;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CostCategories {
    private Integer id;
    private String categoryName;
    private Integer displayOrder;
    private String colorCode;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}