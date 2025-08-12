package com.webtab.shecpsims.controller.bigdata;

import com.webtab.shecpsims.common.BaseResponse;
import com.webtab.shecpsims.common.ResultUtils;
import com.webtab.shecpsims.model.dto.bigdata.*;
import com.webtab.shecpsims.service.bigdata.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 设备管理接口
 */
@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    /**
     * 获取设备使用率数据
     * @return 设备使用率数据
     */
    @GetMapping("/usage")
    public BaseResponse<EquipmentUsageDTO> getEquipmentUsage() {
        EquipmentUsageDTO equipmentUsage = equipmentService.getEquipmentUsage();
        return ResultUtils.success(equipmentUsage);
    }

    /**
     * 获取设备分布情况数据
     * @return 设备分布情况数据
     */
    @GetMapping("/distribution")
    public BaseResponse<EquipmentDistributionDTO> getEquipmentDistribution() {
        EquipmentDistributionDTO distribution = equipmentService.getEquipmentDistribution();
        return ResultUtils.success(distribution);
    }

    /**
     * 搜索设备并返回统计数据
     * @param keyword 搜索关键字
     * @return 设备搜索结果数据
     */
    @GetMapping("/search")
    public BaseResponse<EquipmentSearchResultDTO> searchEquipment(@RequestParam(required = false) String keyword) {
        EquipmentSearchResultDTO searchResult = equipmentService.searchEquipment(keyword);
        return ResultUtils.success(searchResult);
    }

    /**
     * 获取设备调度建议
     * @return 设备调度建议数据
     */
    @GetMapping("/schedule-advice")
    public BaseResponse<EquipmentScheduleAdviceDTO> getEquipmentScheduleAdvice() {
        EquipmentScheduleAdviceDTO scheduleAdvice = equipmentService.getEquipmentScheduleAdvice();
        return ResultUtils.success(scheduleAdvice);
    }

    /**
     * 获取所有设备列表
     * @return 设备列表
     */
    @GetMapping("/list")
    public BaseResponse<List<Object>> getAllEquipment() {
        List<Object> equipmentList = equipmentService.getAllEquipment();
        return ResultUtils.success(equipmentList);
    }

    /**
     * 更新设备状态
     * @param equipmentId 设备ID
     * @param updateRequest 更新请求
     * @return 操作结果
     */
    @PutMapping("/{equipmentId}/status")
    public BaseResponse<String> updateEquipmentStatus(
            @PathVariable String equipmentId,
            @RequestBody UpdateEquipmentStatusDTO updateRequest) {
        equipmentService.updateEquipmentStatus(equipmentId, updateRequest);
        return ResultUtils.success("设备状态更新成功");
    }

    /**
     * 批量调度设备
     * @param batchRequest 批量调度请求
     * @return 操作结果
     */
    @PostMapping("/batch-schedule")
    public BaseResponse<String> batchScheduleEquipment(@RequestBody BatchScheduleRequestDTO batchRequest) {
        equipmentService.batchScheduleEquipment(batchRequest);
        return ResultUtils.success("批量调度设备成功");
    }
}
