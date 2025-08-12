package com.webtab.shecpsims.controller.bigdata;

import com.webtab.shecpsims.common.BaseResponse;
import com.webtab.shecpsims.common.ErrorCode;
import com.webtab.shecpsims.common.ResultUtils;
import com.webtab.shecpsims.model.dto.bigdata.*;
import com.webtab.shecpsims.service.bigdata.MedicineInventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/inventory-monitor")
@RequiredArgsConstructor
@Slf4j
public class InventoryMonitorController {

    private final MedicineInventoryService medicineInventoryService;

    @GetMapping("/dashboard")
    public BaseResponse<Map<String, Object>> getInventoryDashboard() {
        try {
            log.info("获取库存监控仪表板数据");
            
            List<MedicineInventoryDTO> allInventory = medicineInventoryService.getAllMedicineInventory();
            
            // 统计各类预警数量
            long criticalCount = allInventory.stream()
                    .filter(item -> "critical".equals(item.getWarningLevel()))
                    .count();
            
            long warningCount = allInventory.stream()
                    .filter(item -> "warning".equals(item.getWarningLevel()))
                    .count();
            
            long normalCount = allInventory.stream()
                    .filter(item -> "normal".equals(item.getWarningLevel()))
                    .count();
            
            // 计算总库存价值（模拟）
            double totalValue = allInventory.stream()
                    .mapToDouble(item -> item.getStock() * 10.0) // 假设平均单价10元
                    .sum();
            
            // 按类别统计
            Map<String, Long> categoryStats = allInventory.stream()
                    .collect(Collectors.groupingBy(MedicineInventoryDTO::getType, Collectors.counting()));
            
            // 低库存药品
            List<MedicineInventoryDTO> lowStockItems = allInventory.stream()
                    .filter(item -> !"normal".equals(item.getWarningLevel()))
                    .limit(10) // 只返回前10个
                    .collect(Collectors.toList());
            
            Map<String, Object> dashboard = Map.of(
                    "totalMedicines", allInventory.size(),
                    "criticalCount", criticalCount,
                    "warningCount", warningCount,
                    "normalCount", normalCount,
                    "totalValue", totalValue,
                    "categoryStats", categoryStats,
                    "lowStockItems", lowStockItems,
                    "lastUpdateTime", System.currentTimeMillis()
            );
            
            return ResultUtils.success(dashboard);
        } catch (Exception e) {
            log.error("获取库存监控仪表板数据失败", e);
            return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "获取库存监控仪表板数据失败: " + e.getMessage());
        }
    }

    @GetMapping("/alerts")
    public BaseResponse<List<InventoryAlertDTO>> getInventoryAlerts(
            @RequestParam(required = false) String level,
            @RequestParam(defaultValue = "50") Integer limit) {
        try {
            log.info("获取库存预警列表，级别: {}, 限制: {}", level, limit);
            List<MedicineInventoryDTO> allInventory = medicineInventoryService.getAllMedicineInventory();
            List<InventoryAlertDTO> alerts = allInventory.stream()
                    .filter(item -> !"normal".equals(item.getWarningLevel()))
                    .filter(item -> level == null || level.equals(item.getWarningLevel()))
                    .limit(limit)
                    .map(this::convertToAlert)
                    .collect(Collectors.toList());
            return ResultUtils.success(alerts);
        } catch (Exception e) {
            log.error("获取库存预警列表失败", e);
            return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "获取库存预警列表失败: " + e.getMessage());
        }
    }

    @GetMapping("/real-time-stats")
    public BaseResponse<Map<String, Object>> getRealTimeStats() {
        try {
            log.info("获取实时库存统计");
            
            List<MedicineInventoryDTO> allInventory = medicineInventoryService.getAllMedicineInventory();
            
            // 计算各种统计指标
            int totalMedicines = allInventory.size();
            double averageStock = allInventory.stream()
                    .mapToInt(MedicineInventoryDTO::getStock)
                    .average()
                    .orElse(0.0);
            
            double averageDaysRemaining = allInventory.stream()
                    .filter(item -> item.getDaysRemaining() != null)
                    .mapToInt(MedicineInventoryDTO::getDaysRemaining)
                    .average()
                    .orElse(0.0);
            
            // 趋势分析（简化版）
            String stockTrend = averageDaysRemaining > 15 ? "良好" : averageDaysRemaining > 7 ? "注意" : "警告";
            
            Map<String, Object> stats = Map.of(
                    "totalMedicines", totalMedicines,
                    "averageStock", Math.round(averageStock),
                    "averageDaysRemaining", Math.round(averageDaysRemaining * 10) / 10.0,
                    "stockTrend", stockTrend,
                    "updateTime", System.currentTimeMillis(),
                    "healthScore", calculateHealthScore(allInventory)
            );
            
            return ResultUtils.success(stats);
        } catch (Exception e) {
            log.error("获取实时库存统计失败", e);
            return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "获取实时库存统计失败: " + e.getMessage());
        }
    }

    @GetMapping("/category-analysis")
    public BaseResponse<List<CategoryAnalysisDTO>> getCategoryAnalysis() {
        try {
            log.info("获取分类库存分析");
            List<MedicineInventoryDTO> allInventory = medicineInventoryService.getAllMedicineInventory();
            Map<String, List<MedicineInventoryDTO>> categoryGroups = allInventory.stream()
                    .collect(Collectors.groupingBy(MedicineInventoryDTO::getType));
            
            List<CategoryAnalysisDTO> analysis = categoryGroups.entrySet().stream()
                    .map(entry -> {
                        String category = entry.getKey();
                        List<MedicineInventoryDTO> items = entry.getValue();
                        CategoryAnalysisDTO dto = new CategoryAnalysisDTO();
                        dto.setCategory(category);
                        dto.setTotalItems(items.size());
                        dto.setTotalStock(items.stream().mapToInt(MedicineInventoryDTO::getStock).sum());
                        dto.setCriticalItems((int) items.stream()
                                .filter(item -> "critical".equals(item.getWarningLevel()))
                                .count());
                        dto.setWarningItems((int) items.stream()
                                .filter(item -> "warning".equals(item.getWarningLevel()))
                                .count());
                        dto.setAverageDaysRemaining(items.stream()
                                .filter(item -> item.getDaysRemaining() != null)
                                .mapToInt(MedicineInventoryDTO::getDaysRemaining)
                                .average()
                                .orElse(0.0));
                        return dto;
                    })
                    .collect(Collectors.toList());
            
            return ResultUtils.success(analysis);
        } catch (Exception e) {
            log.error("获取分类库存分析失败", e);
            return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "获取分类库存分析失败: " + e.getMessage());
        }
    }

    /**
     * 转换为预警DTO
     */
    private InventoryAlertDTO convertToAlert(MedicineInventoryDTO inventory) {
        InventoryAlertDTO alert = new InventoryAlertDTO();
        alert.setId(inventory.getId());
        alert.setMedicineName(inventory.getName());
        alert.setMedicineCode(inventory.getCode());
        alert.setCurrentStock(inventory.getStock());
        alert.setDaysRemaining(inventory.getDaysRemaining());
        alert.setAlertLevel(inventory.getWarningLevel());
        alert.setAlertTime(System.currentTimeMillis());
        
        // 生成预警消息
        String message = switch (inventory.getWarningLevel()) {
            case "critical" -> String.format("库存严重不足！%s 仅剩 %d 天用量", inventory.getName(), inventory.getDaysRemaining());
            case "warning" -> String.format("库存偏低，%s 剩余 %d 天用量", inventory.getName(), inventory.getDaysRemaining());
            default -> String.format("%s 库存正常", inventory.getName());
        };
        alert.setMessage(message);
        
        return alert;
    }

    /**
     * 计算库存健康评分
     */
    private int calculateHealthScore(List<MedicineInventoryDTO> inventory) {
        if (inventory.isEmpty()) {
            return 0;
        }
        
        long criticalCount = inventory.stream()
                .filter(item -> "critical".equals(item.getWarningLevel()))
                .count();
        
        long warningCount = inventory.stream()
                .filter(item -> "warning".equals(item.getWarningLevel()))
                .count();
        
        double criticalRatio = (double) criticalCount / inventory.size();
        double warningRatio = (double) warningCount / inventory.size();
        
        // 评分算法：100分满分，严重预警扣40分，一般预警扣20分
        int score = (int) (100 - (criticalRatio * 40 + warningRatio * 20));
        return Math.max(0, Math.min(100, score));
    }
}