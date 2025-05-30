package com.webtab.shecpsims.service.bigdata.impl;


import com.webtab.shecpsims.mapper.bigdata.AlertRecordMapper;
import com.webtab.shecpsims.mapper.bigdata.ElderlyProfileMapper;
import com.webtab.shecpsims.mapper.bigdata.FollowupRecordMapper;
import com.webtab.shecpsims.model.dto.bigdata.StatCardDTO;
import com.webtab.shecpsims.model.dto.bigdata.TrendDTO;
import com.webtab.shecpsims.service.bigdata.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final ElderlyProfileMapper elderlyProfileMapper;
    private final AlertRecordMapper alertRecordMapper;
    private final FollowupRecordMapper followupRecordMapper;

    @Override
    public List<StatCardDTO> getStatCards() {
        List<StatCardDTO> cards = new ArrayList<>();

        // 1. 在院患者统计卡片
        StatCardDTO patientCard = new StatCardDTO();
        patientCard.setTitle("在院患者");

        // 获取当前在院患者数
        int currentPatientCount = elderlyProfileMapper.countCurrentPatients();
        patientCard.setValue(String.valueOf(currentPatientCount));

        // 计算患者数量趋势
        Map<String, Object> patientTrend = calculatePatientTrend();
        if (patientTrend != null) {
            TrendDTO trend = new TrendDTO();
            String direction = (String) patientTrend.get("trend_direction");
            double percentage = (double) patientTrend.get("trend_percentage");

            String arrowSymbol = "up".equals(direction) ? "↑" : "↓";
            trend.setText("较昨日 " + arrowSymbol + percentage + "%");
            trend.setType(direction);

            patientCard.setTrend(trend);
        }

        // 2. 预警数量统计卡片
        StatCardDTO alertCard = new StatCardDTO();
        alertCard.setTitle("预警数量");

        // 获取未处理预警数量
        int alertCount = alertRecordMapper.countActiveAlerts();
        alertCard.setValue(String.valueOf(alertCount));

        // 3. 随访完成率统计卡片
        StatCardDTO followupCard = new StatCardDTO();
        followupCard.setTitle("随访完成率");

        // 计算随访完成率
        double completionRate = followupRecordMapper.getFollowupCompletionRate();
        followupCard.setValue(String.format("%.0f%%", completionRate));

        // 添加所有卡片
        cards.add(patientCard);
        cards.add(alertCard);
        cards.add(followupCard);

        return cards;
    }

    @Override
    public Map<String, Object> calculatePatientTrend() {
        // 获取今日和昨日的患者数量
        int todayCount = elderlyProfileMapper.countCurrentPatients();
        int yesterdayCount = elderlyProfileMapper.countYesterdayPatients();

        Map<String, Object> trend = new HashMap<>();

        // 计算趋势比例和方向
        if (yesterdayCount > 0) {
            double trendPercentage = Math.abs((todayCount - yesterdayCount) * 100.0 / yesterdayCount);
            String trendDirection = todayCount >= yesterdayCount ? "up" : "down";

            trend.put("trend_percentage", Math.round(trendPercentage * 100) / 100.0); // 保留2位小数
            trend.put("trend_direction", trendDirection);
        } else {
            trend.put("trend_percentage", 100.0);
            trend.put("trend_direction", "up");
        }

        return trend;
    }
}