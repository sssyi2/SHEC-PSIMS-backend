package com.webtab.shecpsims.model.dto.bigdata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitStatsDTO {
    private String day;
    private Integer count;
}