package com.webtab.shecpsims.service.bigdata.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webtab.shecpsims.mapper.bigdata.PatientHealthMetricsMapper;
import com.webtab.shecpsims.model.entity.bigdata.PatientHealthMetrics;
import com.webtab.shecpsims.service.bigdata.PatientHealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class PatientHealthServiceImpl implements PatientHealthService {

    @Autowired
    private PatientHealthMetricsMapper metricsMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<PatientHealthMetrics> getLast7DaysMetrics(Integer patientId) {
        return metricsMapper.selectLast7DaysByPatientId(patientId);
    }

    @Override
    public Object callFlaskPredict(Integer patientId, List<PatientHealthMetrics> metrics) {
        List<List<Double>> sequenceData = new ArrayList<>();
        for (PatientHealthMetrics m : metrics) {
            List<Double> row = new ArrayList<>();
            // 假设字段顺序为：年龄、性别(男1女0)、身高、体重、BMI、收缩压、舒张压、心率、血糖、...（补全到20项）
            row.add(m.getAge() != null ? m.getAge().doubleValue() : 0);
            row.add("男".equals(m.getGender()) ? 1.0 : 0.0); // 性别
            // 下面字段请根据你的表结构和other_metrics内容补全
            // 示例：身高、体重、心率等可能在other_metrics里
            Map<String, Object> other = new HashMap<>();
            if (m.getOtherMetrics() != null) {
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    other = mapper.readValue(m.getOtherMetrics(), Map.class);
                } catch (Exception ignored) {}
            }
            row.add(other.getOrDefault("height", 0.0) instanceof Number ? ((Number)other.get("height")).doubleValue() : 0.0); // 身高
            row.add(other.getOrDefault("weight", 0.0) instanceof Number ? ((Number)other.get("weight")).doubleValue() : 0.0); // 体重
            row.add(m.getBmi() != null ? m.getBmi() : 0.0); // BMI
            row.add(m.getSystolicPressure() != null ? m.getSystolicPressure() : 0.0); // 收缩压
            row.add(m.getDiastolicPressure() != null ? m.getDiastolicPressure() : 0.0); // 舒张压
            row.add(other.getOrDefault("heart_rate", 0.0) instanceof Number ? ((Number)other.get("heart_rate")).doubleValue() : 0.0); // 心率
            row.add(m.getBloodSugar() != null ? m.getBloodSugar() : 0.0); // 血糖
            // 继续补全剩余字段，确保每行20个数
            row.add(other.getOrDefault("cholesterol", 0.0) instanceof Number ? ((Number)other.get("cholesterol")).doubleValue() : 0.0);
            //row.add(other.getOrDefault("creatinine", 0.0) instanceof Number ? ((Number)other.get("creatinine")).doubleValue() : 0.0);
            // 其余字段用0填充或按实际业务补全
            while (row.size() < 20) row.add(0.0);
            sequenceData.add(row);
        }
        Map<String, Object> flaskRequest = new HashMap<>();
        flaskRequest.put("sequence_data", sequenceData);

        String flaskUrl = "http://127.0.0.1:5000/api/predict/health";
        try {
            return restTemplate.postForObject(flaskUrl, flaskRequest, Map.class);
        } catch (Exception e) {
            Map<String, Object> err = new HashMap<>();
            err.put("error", "Flask服务调用失败: " + e.getMessage());
            return err;
        }
    }
}