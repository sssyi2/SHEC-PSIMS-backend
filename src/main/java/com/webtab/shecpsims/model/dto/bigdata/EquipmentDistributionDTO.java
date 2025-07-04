package com.webtab.shecpsims.model.dto.bigdata;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 设备分布情况数据传输对象
 */
@Data
public class EquipmentDistributionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 部门列表
     */
    private List<String> departments;

    /**
     * 各部门使用中的设备数量
     */
    private List<Integer> inUseByDept;

    /**
     * 各部门闲置的设备数量
     */
    private List<Integer> idleByDept;

    /**
     * 各部门维修中的设备数量
     */
    private List<Integer> maintenanceByDept;
}
