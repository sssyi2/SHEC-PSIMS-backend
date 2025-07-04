package com.webtab.shecpsims.service.bigdata;

import com.webtab.shecpsims.model.dto.bigdata.StatisticsDTO;

public interface StatisticsService {
    /**
     * 获取统计数据
     * @param period 时间周期：week, month, year
     * @return 统计数据DTO
     */
    StatisticsDTO getStatisticsData(String period);
}