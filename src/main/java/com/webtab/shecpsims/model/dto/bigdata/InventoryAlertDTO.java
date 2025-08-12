package com.webtab.shecpsims.model.dto.bigdata;

import lombok.Data;

/**
 * 库存预警DTO
 */
@Data
public class InventoryAlertDTO {
    
    /**
     * 药品ID
     */
    private String id;
    
    /**
     * 药品名称
     */
    private String medicineName;
    
    /**
     * 药品编码
     */
    private String medicineCode;
    
    /**
     * 当前库存
     */
    private Integer currentStock;
    
    /**
     * 剩余天数
     */
    private Integer daysRemaining;
    
    /**
     * 预警级别
     */
    private String alertLevel;
    
    /**
     * 预警消息
     */
    private String message;
    
    /**
     * 预警时间
     */
    private Long alertTime;
}
