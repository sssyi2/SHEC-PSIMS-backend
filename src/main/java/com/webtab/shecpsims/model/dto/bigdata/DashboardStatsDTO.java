package com.webtab.shecpsims.model.dto.bigdata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardStatsDTO {
    private Integer totalPatients;  // 总患者数
    private Integer newPatients;    // 新增患者数
    private Integer totalDoctors;   // 医生总数
    private Integer avgVisits;      // 平均就诊人数
}