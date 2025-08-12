package com.webtab.shecpsims.model.dto.bigdata;

import lombok.Data;
import java.util.Map;

/**
 * 采购建议概览DTO
 */
@Data
public class PurchaseAdviceOverviewDTO {
    
    /**
     * 基础采购建议
     */
    private PurchaseAdviceDTO purchaseAdvice;
    
    /**
     * 智能分析结果
     */
    private SmartPurchaseAnalysisDTO smartAnalysis;
    
    /**
     * 概览摘要信息
     */
    private Map<String, Object> summary;
}
