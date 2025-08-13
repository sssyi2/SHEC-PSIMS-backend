package com.webtab.shecpsims.service.integration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.HashMap;
import java.util.Map;

/**
 * Flask AI服务调用类
 * 负责Java后端与Flask端的通信
 */
@Service
@Slf4j
public class FlaskApiService {

    @Autowired
    @Qualifier("flaskRestTemplate")
    private RestTemplate flaskRestTemplate;

    @Value("${flask.api.url:http://localhost:5000}")
    private String flaskApiUrl;

    /**
     * 创建请求头
     */
    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("User-Agent", "SHEC-PSIMS-Backend/1.0");
        return headers;
    }

    /**
     * 健康检查 - 检查Flask服务是否正常
     */
    public ResponseEntity<Map<String, Object>> healthCheck() {
        try {
            String url = flaskApiUrl + "/api/data/health";
            HttpEntity<String> entity = new HttpEntity<>(createHeaders());
            
            ResponseEntity<Map> response = flaskRestTemplate.exchange(
                url, HttpMethod.GET, entity, Map.class);
            
            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("data", response.getBody());
            result.put("message", "Flask服务连接正常");
            
            log.info("Flask健康检查成功");
            return ResponseEntity.ok(result);
            
        } catch (Exception e) {
            log.error("Flask健康检查失败: {}", e.getMessage());
            Map<String, Object> errorResult = new HashMap<>();
            errorResult.put("success", false);
            errorResult.put("message", "Flask服务连接失败");
            errorResult.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(errorResult);
        }
    }

    /**
     * 获取患者列表
     */
    public ResponseEntity<Map<String, Object>> getPatients(Map<String, Object> params) {
        try {
            String url = flaskApiUrl + "/api/data/patients";
            
            // 构建查询参数
            StringBuilder queryParams = new StringBuilder("?");
            params.forEach((key, value) -> {
                if (value != null) {
                    queryParams.append(key).append("=").append(value).append("&");
                }
            });
            
            if (queryParams.length() > 1) {
                url += queryParams.substring(0, queryParams.length() - 1);
            }
            
            HttpEntity<String> entity = new HttpEntity<>(createHeaders());
            ResponseEntity<Map> response = flaskRestTemplate.exchange(
                url, HttpMethod.GET, entity, Map.class);
            
            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("data", response.getBody());
            
            return ResponseEntity.ok(result);
            
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.error("获取患者列表失败: {} - {}", e.getStatusCode(), e.getResponseBodyAsString());
            return handleHttpError(e);
        } catch (Exception e) {
            log.error("获取患者列表异常: {}", e.getMessage());
            return handleGenericError(e);
        }
    }

    /**
     * 获取患者详情
     */
    public ResponseEntity<Map<String, Object>> getPatientDetail(Long patientId) {
        try {
            String url = flaskApiUrl + "/api/data/patients/" + patientId;
            HttpEntity<String> entity = new HttpEntity<>(createHeaders());
            
            ResponseEntity<Map> response = flaskRestTemplate.exchange(
                url, HttpMethod.GET, entity, Map.class);
            
            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("data", response.getBody());
            
            return ResponseEntity.ok(result);
            
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.error("获取患者详情失败: {} - {}", e.getStatusCode(), e.getResponseBodyAsString());
            return handleHttpError(e);
        } catch (Exception e) {
            log.error("获取患者详情异常: {}", e.getMessage());
            return handleGenericError(e);
        }
    }

    /**
     * AI预测分析 (通用接口)
     */
    public ResponseEntity<Map<String, Object>> aiPredict(Map<String, Object> requestData) {
        try {
            String url = flaskApiUrl + "/api/predict/analyze";
            
            HttpHeaders headers = createHeaders();
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestData, headers);
            
            ResponseEntity<Map> response = flaskRestTemplate.exchange(
                url, HttpMethod.POST, entity, Map.class);
            
            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("data", response.getBody());
            
            log.info("AI预测分析成功，患者ID: {}", requestData.get("patient_id"));
            return ResponseEntity.ok(result);
            
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.error("AI预测分析失败: {} - {}", e.getStatusCode(), e.getResponseBodyAsString());
            return handleHttpError(e);
        } catch (Exception e) {
            log.error("AI预测分析异常: {}", e.getMessage());
            return handleGenericError(e);
        }
    }

    /**
     * AI健康风险预测
     */
    public ResponseEntity<Map<String, Object>> predictHealthRisk(Map<String, Object> requestData) {
        try {
            String url = flaskApiUrl + "/api/predict/health-risk";
            
            HttpHeaders headers = createHeaders();
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestData, headers);
            
            ResponseEntity<Map> response = flaskRestTemplate.exchange(
                url, HttpMethod.POST, entity, Map.class);
            
            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("data", response.getBody());
            
            log.info("AI健康风险预测成功，患者ID: {}", requestData.get("patient_id"));
            return ResponseEntity.ok(result);
            
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.error("AI健康风险预测失败: {} - {}", e.getStatusCode(), e.getResponseBodyAsString());
            return handleHttpError(e);
        } catch (Exception e) {
            log.error("AI健康风险预测异常: {}", e.getMessage());
            return handleGenericError(e);
        }
    }

    /**
     * AI疾病预测
     */
    public ResponseEntity<Map<String, Object>> predictDisease(Map<String, Object> requestData) {
        try {
            String url = flaskApiUrl + "/api/predict/disease";
            
            HttpHeaders headers = createHeaders();
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestData, headers);
            
            ResponseEntity<Map> response = flaskRestTemplate.exchange(
                url, HttpMethod.POST, entity, Map.class);
            
            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("data", response.getBody());
            
            log.info("AI疾病预测成功，患者ID: {}", requestData.get("patient_id"));
            return ResponseEntity.ok(result);
            
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.error("AI疾病预测失败: {} - {}", e.getStatusCode(), e.getResponseBodyAsString());
            return handleHttpError(e);
        } catch (Exception e) {
            log.error("AI疾病预测异常: {}", e.getMessage());
            return handleGenericError(e);
        }
    }

    /**
     * 批量AI预测
     */
    public ResponseEntity<Map<String, Object>> batchPredict(Map<String, Object> requestData) {
        try {
            String url = flaskApiUrl + "/api/predict/batch";
            
            HttpHeaders headers = createHeaders();
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestData, headers);
            
            ResponseEntity<Map> response = flaskRestTemplate.exchange(
                url, HttpMethod.POST, entity, Map.class);
            
            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("data", response.getBody());
            
            log.info("批量AI预测成功");
            return ResponseEntity.ok(result);
            
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.error("批量AI预测失败: {} - {}", e.getStatusCode(), e.getResponseBodyAsString());
            return handleHttpError(e);
        } catch (Exception e) {
            log.error("批量AI预测异常: {}", e.getMessage());
            return handleGenericError(e);
        }
    }

    /**
     * 获取AI预测历史
     */
    public ResponseEntity<Map<String, Object>> getPredictionHistory(Long patientId) {
        try {
            String url = flaskApiUrl + "/api/data/ai-predictions?patient_id=" + patientId;
            HttpEntity<String> entity = new HttpEntity<>(createHeaders());
            
            ResponseEntity<Map> response = flaskRestTemplate.exchange(
                url, HttpMethod.GET, entity, Map.class);
            
            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("data", response.getBody());
            
            return ResponseEntity.ok(result);
            
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.error("获取AI预测历史失败: {} - {}", e.getStatusCode(), e.getResponseBodyAsString());
            return handleHttpError(e);
        } catch (Exception e) {
            log.error("获取AI预测历史异常: {}", e.getMessage());
            return handleGenericError(e);
        }
    }

    /**
     * 获取AI健康评分
     */
    public ResponseEntity<Map<String, Object>> getHealthScore(Long patientId) {
        try {
            String url = flaskApiUrl + "/api/predict/health-score/" + patientId;
            HttpEntity<String> entity = new HttpEntity<>(createHeaders());
            
            ResponseEntity<Map> response = flaskRestTemplate.exchange(
                url, HttpMethod.GET, entity, Map.class);
            
            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("data", response.getBody());
            
            return ResponseEntity.ok(result);
            
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.error("获取AI健康评分失败: {} - {}", e.getStatusCode(), e.getResponseBodyAsString());
            return handleHttpError(e);
        } catch (Exception e) {
            log.error("获取AI健康评分异常: {}", e.getMessage());
            return handleGenericError(e);
        }
    }

    /**
     * 生成AI建议
     */
    public ResponseEntity<Map<String, Object>> generateRecommendations(Long patientId) {
        try {
            String url = flaskApiUrl + "/api/predict/recommendations/" + patientId;
            HttpEntity<String> entity = new HttpEntity<>(createHeaders());
            
            ResponseEntity<Map> response = flaskRestTemplate.exchange(
                url, HttpMethod.GET, entity, Map.class);
            
            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("data", response.getBody());
            
            return ResponseEntity.ok(result);
            
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.error("生成AI建议失败: {} - {}", e.getStatusCode(), e.getResponseBodyAsString());
            return handleHttpError(e);
        } catch (Exception e) {
            log.error("生成AI建议异常: {}", e.getMessage());
            return handleGenericError(e);
        }
    }

    /**
     * AI异常检测
     */
    public ResponseEntity<Map<String, Object>> detectAnomalies(Map<String, Object> requestData) {
        try {
            String url = flaskApiUrl + "/api/predict/detect-anomalies";
            
            HttpHeaders headers = createHeaders();
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestData, headers);
            
            ResponseEntity<Map> response = flaskRestTemplate.exchange(
                url, HttpMethod.POST, entity, Map.class);
            
            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("data", response.getBody());
            
            return ResponseEntity.ok(result);
            
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.error("AI异常检测失败: {} - {}", e.getStatusCode(), e.getResponseBodyAsString());
            return handleHttpError(e);
        } catch (Exception e) {
            log.error("AI异常检测异常: {}", e.getMessage());
            return handleGenericError(e);
        }
    }

    /**
     * 获取AI模型性能指标
     */
    public ResponseEntity<Map<String, Object>> getModelMetrics() {
        try {
            String url = flaskApiUrl + "/api/data/models/metrics";
            HttpEntity<String> entity = new HttpEntity<>(createHeaders());
            
            ResponseEntity<Map> response = flaskRestTemplate.exchange(
                url, HttpMethod.GET, entity, Map.class);
            
            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("data", response.getBody());
            
            return ResponseEntity.ok(result);
            
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.error("获取AI模型性能指标失败: {} - {}", e.getStatusCode(), e.getResponseBodyAsString());
            return handleHttpError(e);
        } catch (Exception e) {
            log.error("获取AI模型性能指标异常: {}", e.getMessage());
            return handleGenericError(e);
        }
    }

    /**
     * 获取数据统计
     */
    public ResponseEntity<Map<String, Object>> getStatistics() {
        try {
            String url = flaskApiUrl + "/api/data/statistics";
            HttpEntity<String> entity = new HttpEntity<>(createHeaders());
            
            ResponseEntity<Map> response = flaskRestTemplate.exchange(
                url, HttpMethod.GET, entity, Map.class);
            
            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("data", response.getBody());
            
            return ResponseEntity.ok(result);
            
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.error("获取数据统计失败: {} - {}", e.getStatusCode(), e.getResponseBodyAsString());
            return handleHttpError(e);
        } catch (Exception e) {
            log.error("获取数据统计异常: {}", e.getMessage());
            return handleGenericError(e);
        }
    }

    /**
     * 获取AI预测结果
     */
    public ResponseEntity<Map<String, Object>> getAiPredictions(Map<String, Object> params) {
        try {
            String url = flaskApiUrl + "/api/data/ai-predictions";
            
            // 构建查询参数
            StringBuilder queryParams = new StringBuilder("?");
            params.forEach((key, value) -> {
                if (value != null) {
                    queryParams.append(key).append("=").append(value).append("&");
                }
            });
            
            if (queryParams.length() > 1) {
                url += queryParams.substring(0, queryParams.length() - 1);
            }
            
            HttpEntity<String> entity = new HttpEntity<>(createHeaders());
            ResponseEntity<Map> response = flaskRestTemplate.exchange(
                url, HttpMethod.GET, entity, Map.class);
            
            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("data", response.getBody());
            
            return ResponseEntity.ok(result);
            
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.error("获取AI预测结果失败: {} - {}", e.getStatusCode(), e.getResponseBodyAsString());
            return handleHttpError(e);
        } catch (Exception e) {
            log.error("获取AI预测结果异常: {}", e.getMessage());
            return handleGenericError(e);
        }
    }

    /**
     * 处理HTTP错误 - 通用方法处理HttpStatusCodeException
     */
    private ResponseEntity<Map<String, Object>> handleHttpError(HttpStatusCodeException e) {
        Map<String, Object> errorResult = new HashMap<>();
        errorResult.put("success", false);
        
        // 根据HTTP状态码类型设置不同的错误消息
        if (e instanceof HttpClientErrorException) {
            errorResult.put("message", "Flask服务调用失败");
        } else if (e instanceof HttpServerErrorException) {
            errorResult.put("message", "Flask服务内部错误");
        } else {
            errorResult.put("message", "Flask服务请求异常");
        }
        
        errorResult.put("error", e.getResponseBodyAsString());
        errorResult.put("status", e.getStatusCode().value());
        
        return ResponseEntity.status(e.getStatusCode()).body(errorResult);
    }

    /**
     * 处理HTTP客户端错误 - 4xx错误的具体处理
     */
    private ResponseEntity<Map<String, Object>> handleHttpError(HttpClientErrorException e) {
        Map<String, Object> errorResult = new HashMap<>();
        errorResult.put("success", false);
        errorResult.put("message", "Flask服务调用失败");
        errorResult.put("error", e.getResponseBodyAsString());
        errorResult.put("status", e.getStatusCode().value());
        
        return ResponseEntity.status(e.getStatusCode()).body(errorResult);
    }

    /**
     * 处理HTTP服务器错误 - 5xx错误的具体处理
     */
    private ResponseEntity<Map<String, Object>> handleHttpError(HttpServerErrorException e) {
        Map<String, Object> errorResult = new HashMap<>();
        errorResult.put("success", false);
        errorResult.put("message", "Flask服务内部错误");
        errorResult.put("error", e.getResponseBodyAsString());
        errorResult.put("status", e.getStatusCode().value());
        
        return ResponseEntity.status(e.getStatusCode()).body(errorResult);
    }

    /**
     * 处理通用错误
     */
    private ResponseEntity<Map<String, Object>> handleGenericError(Exception e) {
        Map<String, Object> errorResult = new HashMap<>();
        errorResult.put("success", false);
        errorResult.put("message", "服务调用异常");
        errorResult.put("error", e.getMessage());
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResult);
    }
}
