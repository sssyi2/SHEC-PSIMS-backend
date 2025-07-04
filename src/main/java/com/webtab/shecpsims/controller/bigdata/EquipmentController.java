package com.webtab.shecpsims.controller.bigdata;

import com.webtab.shecpsims.common.BaseResponse;
import com.webtab.shecpsims.common.ResultUtils;
import com.webtab.shecpsims.model.dto.bigdata.EquipmentDistributionDTO;
import com.webtab.shecpsims.model.dto.bigdata.EquipmentSearchResultDTO;
import com.webtab.shecpsims.model.dto.bigdata.EquipmentUsageDTO;
import com.webtab.shecpsims.service.bigdata.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



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
}
