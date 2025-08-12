package com.webtab.shecpsims.model.dto.bigdata;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

/**
 * 采购计划DTO
 */
@Data
public class PurchasePlanDTO {
    
    /**
     * 计划采购的药品项目
     */
    private List<SmartPurchaseAnalysisDTO.RecommendationItem> plannedItems;
    
    /**
     * 总成本
     */
    private BigDecimal totalCost;
    
    /**
     * 预算限制
     */
    private BigDecimal budgetLimit;
    
    /**
     * 药品数量
     */
    private Integer itemCount;
    
    /**
     * 计划摘要
     */
    private String planSummary;
    
    /**
     * 是否超出预算
     */
    public boolean isOverBudget() {
        if (budgetLimit == null || totalCost == null) {
            return false;
        }
        return totalCost.compareTo(budgetLimit) > 0;
    }
    
    /**
     * 获取预算剩余
     */
    public BigDecimal getRemainingBudget() {
        if (budgetLimit == null || totalCost == null) {
            return BigDecimal.ZERO;
        }
        return budgetLimit.subtract(totalCost);
    }
}
