package com.webtab.shecpsims.service.bigdata;

import com.webtab.shecpsims.model.dto.bigdata.StatCardDTO;

import java.util.List;
import java.util.Map;

public interface DashboardService {

    /**
     * 获取统计卡片数据
     * @return 统计卡片列表
     */
    List<StatCardDTO> getStatCards();

    /**
     * 计算患者数量趋势
     * @return 患者趋势数据
     */
    Map<String, Object> calculatePatientTrend();
}