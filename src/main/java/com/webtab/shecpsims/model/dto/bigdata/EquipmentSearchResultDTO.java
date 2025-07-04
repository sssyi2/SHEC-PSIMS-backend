package com.webtab.shecpsims.model.dto.bigdata;

import lombok.Data;

import java.io.Serializable;

/**
 * 设备搜索结果数据传输对象
 */
@Data
public class EquipmentSearchResultDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 设备使用率统计
     */
    private EquipmentUsageDTO usageStats;

    /**
     * 设备分布情况统计
     */
    private EquipmentDistributionDTO distributionStats;

    /**
     * 部门列表
     */
    private java.util.List<String> departments;
}
