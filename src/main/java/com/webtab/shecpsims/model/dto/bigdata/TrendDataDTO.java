package com.webtab.shecpsims.model.dto.bigdata;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrendDataDTO {
    private String date;
    private Integer stock;
}
