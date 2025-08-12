package com.webtab.shecpsims.model.dto.bigdata;

/**
 * 设备状态更新请求DTO
 */
public class UpdateEquipmentStatusDTO {
    
    /**
     * 设备状态: in-use, idle, maintenance
     */
    private String status;
    
    /**
     * 所属部门（可选）
     */
    private String department;
    
    // 构造方法
    public UpdateEquipmentStatusDTO() {}
    
    public UpdateEquipmentStatusDTO(String status, String department) {
        this.status = status;
        this.department = department;
    }
    
    // Getters and Setters
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
}
