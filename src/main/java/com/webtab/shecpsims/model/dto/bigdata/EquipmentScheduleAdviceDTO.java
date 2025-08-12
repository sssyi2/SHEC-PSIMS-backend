package com.webtab.shecpsims.model.dto.bigdata;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 设备调度建议DTO
 */
public class EquipmentScheduleAdviceDTO {
    
    /**
     * 总体调度建议
     */
    private String advice;
    
    /**
     * 紧急调度清单
     */
    private List<UrgentScheduleItem> urgent;
    
    /**
     * 调度统计信息
     */
    private ScheduleStatistics statistics;
    
    /**
     * 紧急调度项目
     */
    public static class UrgentScheduleItem {
        private String id;
        private String name;
        private String code;
        private String currentDepartment;
        private String suggestedDepartment;
        private String urgencyLevel; // high, medium, low
        private String reason;
        
        // 构造方法
        public UrgentScheduleItem() {}
        
        public UrgentScheduleItem(String id, String name, String code, String currentDepartment, 
                                 String suggestedDepartment, String urgencyLevel, String reason) {
            this.id = id;
            this.name = name;
            this.code = code;
            this.currentDepartment = currentDepartment;
            this.suggestedDepartment = suggestedDepartment;
            this.urgencyLevel = urgencyLevel;
            this.reason = reason;
        }
        
        // Getters and Setters
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        
        public String getCode() { return code; }
        public void setCode(String code) { this.code = code; }
        
        public String getCurrentDepartment() { return currentDepartment; }
        public void setCurrentDepartment(String currentDepartment) { this.currentDepartment = currentDepartment; }
        
        public String getSuggestedDepartment() { return suggestedDepartment; }
        public void setSuggestedDepartment(String suggestedDepartment) { this.suggestedDepartment = suggestedDepartment; }
        
        public String getUrgencyLevel() { return urgencyLevel; }
        public void setUrgencyLevel(String urgencyLevel) { this.urgencyLevel = urgencyLevel; }
        
        public String getReason() { return reason; }
        public void setReason(String reason) { this.reason = reason; }
    }
    
    /**
     * 调度统计信息
     */
    public static class ScheduleStatistics {
        private Integer totalEquipment;
        private Integer idleCount;
        private Integer maintenanceCount;
        private Integer availableCount;
        private LocalDateTime lastUpdated;
        
        // 构造方法
        public ScheduleStatistics() {}
        
        public ScheduleStatistics(Integer totalEquipment, Integer idleCount, 
                                Integer maintenanceCount, Integer availableCount) {
            this.totalEquipment = totalEquipment;
            this.idleCount = idleCount;
            this.maintenanceCount = maintenanceCount;
            this.availableCount = availableCount;
            this.lastUpdated = LocalDateTime.now();
        }
        
        // Getters and Setters
        public Integer getTotalEquipment() { return totalEquipment; }
        public void setTotalEquipment(Integer totalEquipment) { this.totalEquipment = totalEquipment; }
        
        public Integer getIdleCount() { return idleCount; }
        public void setIdleCount(Integer idleCount) { this.idleCount = idleCount; }
        
        public Integer getMaintenanceCount() { return maintenanceCount; }
        public void setMaintenanceCount(Integer maintenanceCount) { this.maintenanceCount = maintenanceCount; }
        
        public Integer getAvailableCount() { return availableCount; }
        public void setAvailableCount(Integer availableCount) { this.availableCount = availableCount; }
        
        public LocalDateTime getLastUpdated() { return lastUpdated; }
        public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }
    }
    
    // 构造方法
    public EquipmentScheduleAdviceDTO() {}
    
    public EquipmentScheduleAdviceDTO(String advice, List<UrgentScheduleItem> urgent, ScheduleStatistics statistics) {
        this.advice = advice;
        this.urgent = urgent;
        this.statistics = statistics;
    }
    
    // Getters and Setters
    public String getAdvice() { return advice; }
    public void setAdvice(String advice) { this.advice = advice; }
    
    public List<UrgentScheduleItem> getUrgent() { return urgent; }
    public void setUrgent(List<UrgentScheduleItem> urgent) { this.urgent = urgent; }
    
    public ScheduleStatistics getStatistics() { return statistics; }
    public void setStatistics(ScheduleStatistics statistics) { this.statistics = statistics; }
}
