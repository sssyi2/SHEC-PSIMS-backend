package com.webtab.shecpsims.model.dto.bigdata;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Data
public class PurchaseAdviceDTO {
    private String advice;
    private List<UrgentItem> urgent;
    private StatisticsInfo statistics;

    @Data
    public static class UrgentItem {
        private String id;
        private String name;
        private String code;
        private Integer currentStock;
        private String unit;
        private Integer daysRemaining;
        private Integer suggestedOrder;
        private BigDecimal estimatedCost;
        private String priority; // high, medium, low
    }

    @Data
    public static class StatisticsInfo {
        private Integer totalMedicines;
        private Integer warningCount;
        private Integer criticalCount;
        private BigDecimal totalEstimatedCost;
        private LocalDateTime lastUpdated;
    }
}
