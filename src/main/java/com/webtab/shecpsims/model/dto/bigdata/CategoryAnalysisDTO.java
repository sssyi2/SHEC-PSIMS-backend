package com.webtab.shecpsims.model.dto.bigdata;

import lombok.Data;

/**
 * 分类分析DTO
 */
@Data
public class CategoryAnalysisDTO {
    
    /**
     * 药品类别
     */
    private String category;
    
    /**
     * 药品总数
     */
    private Integer totalItems;
    
    /**
     * 总库存量
     */
    private Integer totalStock;
    
    /**
     * 严重预警药品数
     */
    private Integer criticalItems;
    
    /**
     * 一般预警药品数
     */
    private Integer warningItems;
    
    /**
     * 平均剩余天数
     */
    private Double averageDaysRemaining;
    
    /**
     * 获取预警比例
     */
    public Double getWarningRatio() {
        if (totalItems == null || totalItems == 0) {
            return 0.0;
        }
        return (double) (criticalItems + warningItems) / totalItems * 100;
    }
    
    /**
     * 获取健康状态
     */
    public String getHealthStatus() {
        double ratio = getWarningRatio();
        if (ratio > 50) {
            return "危险";
        } else if (ratio > 20) {
            return "警告";
        } else {
            return "良好";
        }
    }
}
