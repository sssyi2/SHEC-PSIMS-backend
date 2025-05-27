package com.webtab.shecpsims.model.dto.bigdata;

import lombok.Data;

import java.util.List;


@Data
public class PurchaseAdviceDTO {
    private String advice;
    private List<UrgentItem> urgent;

    @Data
    public static class UrgentItem {
        private String id;
        private String name;
        private String code;
        private Integer currentStock;
        private String unit;
        private Integer daysRemaining;
        private Integer suggestedOrder;
    }
}
