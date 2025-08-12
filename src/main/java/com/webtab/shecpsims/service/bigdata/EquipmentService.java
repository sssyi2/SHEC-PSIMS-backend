package com.webtab.shecpsims.service.bigdata;

import com.webtab.shecpsims.model.dto.bigdata.*;

import java.util.List;

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

    /**
     * 获取设备调度建议
     * @return 设备调度建议数据
     */
    EquipmentScheduleAdviceDTO getEquipmentScheduleAdvice();

    /**
     * 更新设备状态
     * @param equipmentId 设备ID
     * @param updateRequest 更新请求
     */
    void updateEquipmentStatus(String equipmentId, UpdateEquipmentStatusDTO updateRequest);

    /**
     * 批量调度设备
     * @param batchRequest 批量调度请求
     */
    void batchScheduleEquipment(BatchScheduleRequestDTO batchRequest);

    /**
     * 获取所有设备列表
     * @return 设备列表
     */
    List<Object> getAllEquipment();
}
