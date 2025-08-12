package com.webtab.shecpsims.model.dto.bigdata;

import java.util.List;

/**
 * 批量设备调度请求DTO
 */
public class BatchScheduleRequestDTO {
    
    /**
     * 调度安排列表
     */
    private List<ScheduleItem> schedules;
    
    /**
     * 调度项目
     */
    public static class ScheduleItem {
        private String equipmentId;
        private String targetDepartment;
        private String reason;
        
        // 构造方法
        public ScheduleItem() {}
        
        public ScheduleItem(String equipmentId, String targetDepartment, String reason) {
            this.equipmentId = equipmentId;
            this.targetDepartment = targetDepartment;
            this.reason = reason;
        }
        
        // Getters and Setters
        public String getEquipmentId() { return equipmentId; }
        public void setEquipmentId(String equipmentId) { this.equipmentId = equipmentId; }
        
        public String getTargetDepartment() { return targetDepartment; }
        public void setTargetDepartment(String targetDepartment) { this.targetDepartment = targetDepartment; }
        
        public String getReason() { return reason; }
        public void setReason(String reason) { this.reason = reason; }
    }
    
    // 构造方法
    public BatchScheduleRequestDTO() {}
    
    public BatchScheduleRequestDTO(List<ScheduleItem> schedules) {
        this.schedules = schedules;
    }
    
    // Getters and Setters
    public List<ScheduleItem> getSchedules() { return schedules; }
    public void setSchedules(List<ScheduleItem> schedules) { this.schedules = schedules; }
}
