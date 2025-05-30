package com.webtab.shecpsims.model.dto.bigdata;

import lombok.Data;

@Data
public class StatCardDTO {
    private String title;
    private String value;
    private TrendDTO trend;
}