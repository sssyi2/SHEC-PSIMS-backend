package com.webtab.shecpsims.controller.gateway;

import com.webtab.shecpsims.service.integration.FlaskApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * AI数据分析API网关控制器
 * 作为前端与Flask AI服务之间的代理
 */
@RestController
@RequestMapping("/api/ai")
@Slf4j
public class AiGatewayController {

    @Autowired
    private FlaskApiService flaskApiService;

    /**
     * 健康检查
     */
    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> healthCheck() {
        log.info("AI服务健康检查请求");
        return flaskApiService.healthCheck();
    }

    /**
     * 获取患者列表 (用于AI分析)
     */
    @GetMapping("/patients")
    public ResponseEntity<Map<String, Object>> getPatients(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int per_page,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) Integer age_min,
            @RequestParam(required = false) Integer age_max) {
        
        log.info("获取患者列表 - 页码: {}, 每页: {}", page, per_page);
        
        Map<String, Object> params = Map.of(
            "page", page,
            "per_page", per_page,
            "search", search != null ? search : "",
            "gender", gender != null ? gender : "",
            "age_min", age_min != null ? age_min : "",
            "age_max", age_max != null ? age_max : ""
        );
        
        return flaskApiService.getPatients(params);
    }

    /**
     * 获取患者详情 (用于AI分析)
     */
    @GetMapping("/patients/{patientId}")
    public ResponseEntity<Map<String, Object>> getPatientDetail(@PathVariable Long patientId) {
        log.info("获取患者详情 - 患者ID: {}", patientId);
        return flaskApiService.getPatientDetail(patientId);
    }

    /**
     * AI预测分析 (通用接口)
     */
    @PostMapping("/predict")
    public ResponseEntity<Map<String, Object>> aiPredict(@RequestBody Map<String, Object> requestData) {
        log.info("AI预测分析请求 - 患者ID: {}", requestData.get("patient_id"));
        return flaskApiService.aiPredict(requestData);
    }

    /**
     * AI健康风险预测
     */
    @PostMapping("/predict/health-risk")
    public ResponseEntity<Map<String, Object>> predictHealthRisk(@RequestBody Map<String, Object> requestData) {
        log.info("AI健康风险预测请求 - 患者ID: {}", requestData.get("patient_id"));
        // 设置预测类型
        requestData.put("prediction_type", "health-risk");
        return flaskApiService.predictHealthRisk(requestData);
    }

    /**
     * AI疾病预测
     */
    @PostMapping("/predict/disease")
    public ResponseEntity<Map<String, Object>> predictDisease(@RequestBody Map<String, Object> requestData) {
        log.info("AI疾病预测请求 - 患者ID: {}", requestData.get("patient_id"));
        requestData.put("prediction_type", "disease");
        return flaskApiService.predictDisease(requestData);
    }

    /**
     * 批量AI预测
     */
    @PostMapping("/predict/batch")
    public ResponseEntity<Map<String, Object>> batchPredict(@RequestBody Map<String, Object> requestData) {
        log.info("批量AI预测请求 - 患者数量: {}", 
            requestData.get("patient_ids") != null ? ((java.util.List<?>) requestData.get("patient_ids")).size() : 0);
        return flaskApiService.batchPredict(requestData);
    }

    /**
     * 获取AI预测历史
     */
    @GetMapping("/predictions/history/{patientId}")
    public ResponseEntity<Map<String, Object>> getPredictionHistory(@PathVariable Long patientId) {
        log.info("获取AI预测历史 - 患者ID: {}", patientId);
        return flaskApiService.getPredictionHistory(patientId);
    }

    /**
     * 获取AI健康评分
     */
    @GetMapping("/health-score/{patientId}")
    public ResponseEntity<Map<String, Object>> getHealthScore(@PathVariable Long patientId) {
        log.info("获取AI健康评分 - 患者ID: {}", patientId);
        return flaskApiService.getHealthScore(patientId);
    }

    /**
     * 生成AI建议
     */
    @GetMapping("/recommendations/{patientId}")
    public ResponseEntity<Map<String, Object>> generateRecommendations(@PathVariable Long patientId) {
        log.info("生成AI建议 - 患者ID: {}", patientId);
        return flaskApiService.generateRecommendations(patientId);
    }

    /**
     * AI异常检测
     */
    @PostMapping("/detect/anomalies")
    public ResponseEntity<Map<String, Object>> detectAnomalies(@RequestBody Map<String, Object> requestData) {
        log.info("AI异常检测请求");
        return flaskApiService.detectAnomalies(requestData);
    }

    /**
     * 获取AI模型性能指标
     */
    @GetMapping("/models/metrics")
    public ResponseEntity<Map<String, Object>> getModelMetrics() {
        log.info("获取AI模型性能指标");
        return flaskApiService.getModelMetrics();
    }

    /**
     * 获取数据统计
     */
    @GetMapping("/statistics")
    public ResponseEntity<Map<String, Object>> getStatistics() {
        log.info("获取数据统计请求");
        return flaskApiService.getStatistics();
    }

    /**
     * 获取AI预测结果
     */
    @GetMapping("/predictions")
    public ResponseEntity<Map<String, Object>> getAiPredictions(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int per_page,
            @RequestParam(required = false) Long patient_id,
            @RequestParam(required = false) String prediction_type,
            @RequestParam(required = false) String risk_level) {
        
        log.info("获取AI预测结果 - 页码: {}, 患者ID: {}", page, patient_id);
        
        Map<String, Object> params = Map.of(
            "page", page,
            "per_page", per_page,
            "patient_id", patient_id != null ? patient_id : "",
            "prediction_type", prediction_type != null ? prediction_type : "",
            "risk_level", risk_level != null ? risk_level : ""
        );
        
        return flaskApiService.getAiPredictions(params);
    }
}
