package com.webtab.shecpsims.model.dto.bigdata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsDTO {
    private List<VisitStatsDTO> dailyVisits;
    private List<DepartmentStatsDTO> departmentStats;
    private DashboardStatsDTO stats;
}