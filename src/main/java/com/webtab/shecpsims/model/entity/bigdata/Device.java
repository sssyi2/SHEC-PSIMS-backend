package com.webtab.shecpsims.model.entity.bigdata;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("devices")
public class Device implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 设备ID
     */
    @TableId(value = "device_id", type = IdType.AUTO)
    private Integer deviceId;

    /**
     * 设备名称
     */
    private String deviceName;
    
    /**
     * 设备型号
     */
    private String deviceModel;
    
    /**
     * 设备状态：0-闲置, 1-使用中, 2-维修中
     */
    private Integer status;
    
    /**
     * 所属部门名称
     */
    private String departmentName;
    
    /**
     * 购买日期
     */
    private LocalDateTime purchaseDate;
    
    /**
     * 最后维护日期
     */
    private LocalDateTime lastMaintenanceDate;
    
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    
    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDeleted;
}