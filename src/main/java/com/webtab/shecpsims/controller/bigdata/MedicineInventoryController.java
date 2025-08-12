// MedicineInventoryController.java
package com.webtab.shecpsims.controller.bigdata;


import com.webtab.shecpsims.common.BaseResponse;
import com.webtab.shecpsims.common.ErrorCode;
import com.webtab.shecpsims.common.ResultUtils;
import com.webtab.shecpsims.model.dto.bigdata.*;
import com.webtab.shecpsims.service.bigdata.MedicineInventoryService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/medicine")
@Slf4j
public class MedicineInventoryController {

    @Autowired
    private  MedicineInventoryService medicineInventoryService;

    @GetMapping("/inventory")
    public BaseResponse<List<MedicineInventoryDTO>> getAllMedicineInventory() {
        List<MedicineInventoryDTO> inventoryList = medicineInventoryService.getAllMedicineInventory();
        return ResultUtils.success(inventoryList);
    }

    @GetMapping("/inventory/search")
    public BaseResponse<List<MedicineInventoryDTO>> searchMedicineInventory(@RequestParam String query) {
        List<MedicineInventoryDTO> searchResults = medicineInventoryService.searchMedicineInventory(query);
        return ResultUtils.success(searchResults);
    }

    /**
     * 获取采购建议
     */
    @GetMapping("/purchase-advice")
    public BaseResponse<PurchaseAdviceDTO> getPurchaseAdvice() {
        try {
            PurchaseAdviceDTO purchaseAdvice = medicineInventoryService.getPurchaseAdvice();
            return ResultUtils.success(purchaseAdvice);
        } catch (Exception e) {
            return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "获取采购建议失败: " + e.getMessage());
        }
    }

    /**
     * 获取智能采购分析
     */
    @GetMapping("/smart-analysis")
    public BaseResponse<SmartPurchaseAnalysisDTO> getSmartPurchaseAnalysis() {
        try {
            SmartPurchaseAnalysisDTO analysis = medicineInventoryService.getSmartPurchaseAnalysis();
            return ResultUtils.success(analysis);
        } catch (Exception e) {
            return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "获取智能分析失败: " + e.getMessage());
        }
    }

    /**
     * 获取低库存药品列表
     */
    @GetMapping("/low-stock")
    public BaseResponse<List<MedicineInventoryDTO>> getLowStockMedicines(
            @RequestParam(defaultValue = "7") Integer daysThreshold) {
        try {
            List<MedicineInventoryDTO> lowStockMedicines = medicineInventoryService.getLowStockMedicines(daysThreshold);
            return ResultUtils.success(lowStockMedicines);
        } catch (Exception e) {
            return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "获取低库存药品失败: " + e.getMessage());
        }
    }

    /**
     * 批量更新库存
     */
    @PostMapping("/inventory/batch-update")
    public BaseResponse<Boolean> batchUpdateStock(@Valid @RequestBody BatchUpdateInventoryRequest request) {
        try {
            boolean success = medicineInventoryService.batchUpdateStock(request);
            if (success) {
                return ResultUtils.success(true);
            } else {
                return ResultUtils.error(ErrorCode.OPERATION_ERROR, "批量更新库存失败");
            }
        } catch (Exception e) {
            return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "批量更新库存失败: " + e.getMessage());
        }
    }

    /**
     * 获取药品趋势数据
     */
    @GetMapping("/trend/{medicineId}")
    public BaseResponse<List<Map<String, Object>>> getMedicineTrend(
            @PathVariable String medicineId,
            @RequestParam(defaultValue = "30") Integer days) {
        try {
            List<Map<String, Object>> trendData = medicineInventoryService.getMedicineTrend(medicineId, days);
            return ResultUtils.success(trendData);
        } catch (Exception e) {
            return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "获取趋势数据失败: " + e.getMessage());
        }
    }

    /**
     * 获取库存预警统计
     */
    @GetMapping("/warning-stats")
    public BaseResponse<Map<String, Object>> getWarningStats() {
        try {
            List<MedicineInventoryDTO> allInventory = medicineInventoryService.getAllMedicineInventory();
            
            long criticalCount = allInventory.stream()
                    .filter(item -> "critical".equals(item.getWarningLevel()))
                    .count();
            
            long warningCount = allInventory.stream()
                    .filter(item -> "warning".equals(item.getWarningLevel()))
                    .count();
            
            long normalCount = allInventory.stream()
                    .filter(item -> "normal".equals(item.getWarningLevel()))
                    .count();
            
            Map<String, Object> stats = Map.of(
                    "total", allInventory.size(),
                    "critical", criticalCount,
                    "warning", warningCount,
                    "normal", normalCount,
                    "criticalRatio", allInventory.size() > 0 ? (double) criticalCount / allInventory.size() * 100 : 0,
                    "warningRatio", allInventory.size() > 0 ? (double) warningCount / allInventory.size() * 100 : 0
            );
            
            return ResultUtils.success(stats);
        } catch (Exception e) {
            return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "获取预警统计失败: " + e.getMessage());
        }
    }

    /**
     * 获取药品分类列表
     */
    @GetMapping("/categories")
    public BaseResponse<List<Map<String, Object>>> getMedicineCategories() {
        try {
            List<Map<String, Object>> categories = medicineInventoryService.getMedicineCategories();
            return ResultUtils.success(categories);
        } catch (Exception e) {
            return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "获取药品分类失败: " + e.getMessage());
        }
    }

    /**
     * 转换预警级别为中文文本
     */
    private String getWarningLevelText(String warningLevel) {
        switch (warningLevel) {
            case "critical": return "严重不足";
            case "warning": return "库存预警";
            case "normal": return "库存正常";
            default: return "未知";
        }
    }
}