package com.webtab.shecpsims.service.bigdata;

import com.webtab.shecpsims.model.dto.bigdata.EquipmentDistributionDTO;
import com.webtab.shecpsims.model.dto.bigdata.EquipmentSearchResultDTO;
import com.webtab.shecpsims.model.dto.bigdata.EquipmentUsageDTO;

/**
 * 设备服务接口
 */
public interface EquipmentService {

    /**
     * 获取设备使用率数据
     * @return 设备使用率数据
     */
    EquipmentUsageDTO getEquipmentUsage();

    /**
     * 获取设备分布情况数据
     * @return 设备分布情况数据
     */
    EquipmentDistributionDTO getEquipmentDistribution();

    /**
     * 根据关键字搜索设备并返回相关统计数据
     * @param keyword 搜索关键字
     * @return 设备搜索结果数据
     */
    EquipmentSearchResultDTO searchEquipment(String keyword);
}
