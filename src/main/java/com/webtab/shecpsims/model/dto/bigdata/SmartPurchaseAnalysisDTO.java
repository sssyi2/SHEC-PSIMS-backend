package com.webtab.shecpsims.model.dto.bigdata;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SmartPurchaseAnalysisDTO {
    private String analysisId;
    private String summary;
    private List<RecommendationItem> recommendations;
    private TrendAnalysis trendAnalysis;
    private CostAnalysis costAnalysis;
    private LocalDateTime generatedAt;

    @Data
    public static class RecommendationItem {
        private String id;
        private String name;
        private String code;
        private String category;
        private Integer currentStock;
        private Integer predictedConsumption;
        private Integer suggestedOrder;
        private String urgencyLevel;
        private BigDecimal unitPrice;
        private BigDecimal totalCost;
        private String reason;
    }

    @Data
    public static class TrendAnalysis {
        private String overallTrend;
        private List<String> highConsumptionCategories;
        private List<String> lowStockCategories;
        private Double averageStockDuration;
    }

    @Data
    public static class CostAnalysis {
        private BigDecimal totalBudgetRequired;
        private BigDecimal urgentItemsCost;
        private BigDecimal plannedItemsCost;
        private String costOptimizationSuggestion;
    }
}
