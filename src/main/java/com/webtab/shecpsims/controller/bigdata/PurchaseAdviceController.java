package com.webtab.shecpsims.controller.bigdata;

import com.webtab.shecpsims.common.BaseResponse;
import com.webtab.shecpsims.common.ErrorCode;
import com.webtab.shecpsims.common.ResultUtils;
import com.webtab.shecpsims.model.dto.bigdata.*;
import com.webtab.shecpsims.service.bigdata.MedicineInventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/purchase-advice")
@RequiredArgsConstructor
@Slf4j
public class PurchaseAdviceController {

    private final MedicineInventoryService medicineInventoryService;

    @GetMapping("/overview")
    public BaseResponse<PurchaseAdviceOverviewDTO> getPurchaseAdviceOverview() {
        try {
            log.info("获取采购建议概览");
            
            // 获取基础采购建议
            PurchaseAdviceDTO purchaseAdvice = medicineInventoryService.getPurchaseAdvice();
            
            // 获取智能分析
            SmartPurchaseAnalysisDTO smartAnalysis = medicineInventoryService.getSmartPurchaseAnalysis();
            
            // 构建概览响应
            PurchaseAdviceOverviewDTO overview = new PurchaseAdviceOverviewDTO();
            overview.setPurchaseAdvice(purchaseAdvice);
            overview.setSmartAnalysis(smartAnalysis);
            
            // 添加额外的汇总信息
            overview.setSummary(generateOverviewSummary(purchaseAdvice, smartAnalysis));
            
            return ResultUtils.success(overview);
        } catch (Exception e) {
            log.error("获取采购建议概览失败", e);
            return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "获取采购建议概览失败: " + e.getMessage());
        }
    }

    @GetMapping("/urgent")
    public BaseResponse<List<PurchaseAdviceDTO.UrgentItem>> getUrgentPurchaseList(
            @RequestParam(required = false) String priority) {
        try {
            log.info("获取紧急采购清单，优先级过滤: {}", priority);
            
            PurchaseAdviceDTO purchaseAdvice = medicineInventoryService.getPurchaseAdvice();
            List<PurchaseAdviceDTO.UrgentItem> urgentItems = purchaseAdvice.getUrgent();
            
            // 根据优先级过滤
            if (priority != null && !priority.isEmpty()) {
                urgentItems = urgentItems.stream()
                        .filter(item -> priority.equals(item.getPriority()))
                        .collect(Collectors.toList());
            }
            
            return ResultUtils.success(urgentItems);
        } catch (Exception e) {
            log.error("获取紧急采购清单失败", e);
            return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "获取紧急采购清单失败: " + e.getMessage());
        }
    }

    @GetMapping("/recommendations")
    public BaseResponse<List<SmartPurchaseAnalysisDTO.RecommendationItem>> getSmartRecommendations(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String urgencyLevel) {
        try {
            log.info("获取智能推荐，类别: {}, 紧急程度: {}", category, urgencyLevel);
            
            SmartPurchaseAnalysisDTO smartAnalysis = medicineInventoryService.getSmartPurchaseAnalysis();
            List<SmartPurchaseAnalysisDTO.RecommendationItem> recommendations = smartAnalysis.getRecommendations();
            
            // 应用过滤条件
            if (category != null && !category.isEmpty()) {
                recommendations = recommendations.stream()
                        .filter(item -> category.equals(item.getCategory()))
                        .collect(Collectors.toList());
            }
            
            if (urgencyLevel != null && !urgencyLevel.isEmpty()) {
                recommendations = recommendations.stream()
                        .filter(item -> urgencyLevel.equals(item.getUrgencyLevel()))
                        .collect(Collectors.toList());
            }
            
            return ResultUtils.success(recommendations);
        } catch (Exception e) {
            log.error("获取智能推荐失败", e);
            return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "获取智能推荐失败: " + e.getMessage());
        }
    }

    @GetMapping("/cost-analysis")
    public BaseResponse<SmartPurchaseAnalysisDTO.CostAnalysis> getCostAnalysis() {
        try {
            log.info("获取成本分析");
            
            SmartPurchaseAnalysisDTO smartAnalysis = medicineInventoryService.getSmartPurchaseAnalysis();
            SmartPurchaseAnalysisDTO.CostAnalysis costAnalysis = smartAnalysis.getCostAnalysis();
            
            return ResultUtils.success(costAnalysis);
        } catch (Exception e) {
            log.error("获取成本分析失败", e);
            return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "获取成本分析失败: " + e.getMessage());
        }
    }

    @GetMapping("/trend-analysis")
    public BaseResponse<SmartPurchaseAnalysisDTO.TrendAnalysis> getTrendAnalysis() {
        try {
            log.info("获取趋势分析");
            
            SmartPurchaseAnalysisDTO smartAnalysis = medicineInventoryService.getSmartPurchaseAnalysis();
            SmartPurchaseAnalysisDTO.TrendAnalysis trendAnalysis = smartAnalysis.getTrendAnalysis();
            
            return ResultUtils.success(trendAnalysis);
        } catch (Exception e) {
            log.error("获取趋势分析失败", e);
            return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "获取趋势分析失败: " + e.getMessage());
        }
    }

    @PostMapping("/generate-purchase-plan")
    public BaseResponse<PurchasePlanDTO> generatePurchasePlan(
            @RequestParam(required = false) BigDecimal budgetLimit,
            @RequestParam(required = false) String priorityFilter) {
        try {
            log.info("生成采购计划，预算限制: {}, 优先级: {}", budgetLimit, priorityFilter);
            
            // 获取智能推荐
            SmartPurchaseAnalysisDTO smartAnalysis = medicineInventoryService.getSmartPurchaseAnalysis();
            List<SmartPurchaseAnalysisDTO.RecommendationItem> recommendations = smartAnalysis.getRecommendations();
            
            // 应用优先级过滤
            if (priorityFilter != null && !priorityFilter.isEmpty()) {
                recommendations = recommendations.stream()
                        .filter(item -> priorityFilter.equals(item.getUrgencyLevel()))
                        .collect(Collectors.toList());
            }
            
            // 按优先级和成本排序
            recommendations.sort((a, b) -> {
                // 首先按紧急程度排序
                int urgencyComparison = getUrgencyPriority(a.getUrgencyLevel()) - getUrgencyPriority(b.getUrgencyLevel());
                if (urgencyComparison != 0) {
                    return urgencyComparison;
                }
                // 然后按成本排序
                return a.getTotalCost().compareTo(b.getTotalCost());
            });
            
            // 应用预算限制
            BigDecimal totalCost = BigDecimal.ZERO;
            List<SmartPurchaseAnalysisDTO.RecommendationItem> plannedItems = recommendations;
            
            if (budgetLimit != null) {
                plannedItems = recommendations.stream()
                        .filter(item -> {
                            BigDecimal currentTotal = totalCost.add(item.getTotalCost());
                            if (currentTotal.compareTo(budgetLimit) <= 0) {
                                totalCost.add(item.getTotalCost());
                                return true;
                            }
                            return false;
                        })
                        .collect(Collectors.toList());
            }
            
            // 构建采购计划
            PurchasePlanDTO plan = new PurchasePlanDTO();
            plan.setPlannedItems(plannedItems);
            plan.setTotalCost(totalCost);
            plan.setBudgetLimit(budgetLimit);
            plan.setItemCount(plannedItems.size());
            plan.setPlanSummary(String.format("本次采购计划包含 %d 种药品，预计总成本 %.2f 元", 
                    plannedItems.size(), totalCost.doubleValue()));
            
            return ResultUtils.success(plan);
        } catch (Exception e) {
            log.error("生成采购计划失败", e);
            return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "生成采购计划失败: " + e.getMessage());
        }
    }

    /**
     * 生成概览摘要
     */
    private Map<String, Object> generateOverviewSummary(PurchaseAdviceDTO purchaseAdvice, SmartPurchaseAnalysisDTO smartAnalysis) {
        int urgentCount = purchaseAdvice.getUrgent().size();
        BigDecimal totalCost = smartAnalysis.getCostAnalysis().getTotalBudgetRequired();
        int recommendationCount = smartAnalysis.getRecommendations().size();
        
        return Map.of(
                "urgentItemsCount", urgentCount,
                "totalRecommendations", recommendationCount,
                "estimatedTotalCost", totalCost,
                "highPriorityItems", purchaseAdvice.getUrgent().stream()
                        .filter(item -> "high".equals(item.getPriority()))
                        .count(),
                "costOptimizationPotential", "预计可节省成本15-20%"
        );
    }

    /**
     * 获取紧急程度优先级数值
     */
    private int getUrgencyPriority(String urgencyLevel) {
        return switch (urgencyLevel) {
            case "critical" -> 1;
            case "warning" -> 2;
            case "normal" -> 3;
            default -> 4;
        };
    }
}
