package com.webtab.shecpsims.model.dto.bigdata;

import lombok.Data;

@Data
public class TrendDTO {
    private String text;
    private String type;  // "up" or "down" or null
}