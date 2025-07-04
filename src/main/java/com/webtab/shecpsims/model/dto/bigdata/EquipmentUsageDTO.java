package com.webtab.shecpsims.model.dto.bigdata;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 设备使用情况数据传输对象
 */
@Data
public class EquipmentUsageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 使用中的设备数量
     */
    private Integer inUse;

    /**
     * 闲置的设备数量
     */
    private Integer idle;

    /**
     * 维修中的设备数量
     */
    private Integer inMaintenance;
}
