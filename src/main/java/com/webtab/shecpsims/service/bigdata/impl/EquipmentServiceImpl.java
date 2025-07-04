package com.webtab.shecpsims.service.bigdata.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.webtab.shecpsims.mapper.bigdata.DeviceMapper;
import com.webtab.shecpsims.model.dto.bigdata.EquipmentDistributionDTO;
import com.webtab.shecpsims.model.dto.bigdata.EquipmentSearchResultDTO;
import com.webtab.shecpsims.model.dto.bigdata.EquipmentUsageDTO;
import com.webtab.shecpsims.model.entity.bigdata.Device;
import com.webtab.shecpsims.service.bigdata.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class EquipmentServiceImpl extends ServiceImpl<DeviceMapper, Device> implements EquipmentService {

    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public EquipmentUsageDTO getEquipmentUsage() {
        EquipmentUsageDTO usageDTO = new EquipmentUsageDTO();
        
        // 统计使用中的设备数量
        Long inUseCount = deviceMapper.selectCount(
                new LambdaQueryWrapper<Device>()
                        .eq(Device::getStatus, 1)
                        .eq(Device::getIsDeleted, 0)
        );
        usageDTO.setInUse(inUseCount.intValue());
        
        // 统计闲置的设备数量
        Long idleCount = deviceMapper.selectCount(
                new LambdaQueryWrapper<Device>()
                        .eq(Device::getStatus, 0)
                        .eq(Device::getIsDeleted, 0)
        );
        usageDTO.setIdle(idleCount.intValue());
        
        // 统计维修中的设备数量
        Long maintenanceCount = deviceMapper.selectCount(
                new LambdaQueryWrapper<Device>()
                        .eq(Device::getStatus, 2)
                        .eq(Device::getIsDeleted, 0)
        );
        usageDTO.setInMaintenance(maintenanceCount.intValue());
        
        return usageDTO;
    }

    @Override
    public EquipmentDistributionDTO getEquipmentDistribution() {
        EquipmentDistributionDTO distributionDTO = new EquipmentDistributionDTO();
        
        // 查询所有未删除的设备
        List<Device> allDevices = deviceMapper.selectList(
                new LambdaQueryWrapper<Device>()
                        .eq(Device::getIsDeleted, 0)
                        .isNotNull(Device::getDepartmentName)
                        .ne(Device::getDepartmentName, "")
        );
        
        // 提取所有不同的部门名称并按字母顺序排序
        List<String> departments = allDevices.stream()
                .map(Device::getDepartmentName)
                .filter(StringUtils::hasText)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        
        distributionDTO.setDepartments(departments);
        
        // 初始化各部门各状态设备数量列表
        List<Integer> inUseByDept = new ArrayList<>();
        List<Integer> idleByDept = new ArrayList<>();
        List<Integer> maintenanceByDept = new ArrayList<>();
        
        // 统计每个部门各种状态的设备数量
        for (String department : departments) {
            // 统计该部门使用中的设备数量
            int inUseCount = (int) allDevices.stream()
                    .filter(device -> department.equals(device.getDepartmentName()) && device.getStatus() == 1)
                    .count();
            inUseByDept.add(inUseCount);
            
            // 统计该部门闲置的设备数量
            int idleCount = (int) allDevices.stream()
                    .filter(device -> department.equals(device.getDepartmentName()) && device.getStatus() == 0)
                    .count();
            idleByDept.add(idleCount);
            
            // 统计该部门维修中的设备数量
            int maintenanceCount = (int) allDevices.stream()
                    .filter(device -> department.equals(device.getDepartmentName()) && device.getStatus() == 2)
                    .count();
            maintenanceByDept.add(maintenanceCount);
        }
        
        distributionDTO.setInUseByDept(inUseByDept);
        distributionDTO.setIdleByDept(idleByDept);
        distributionDTO.setMaintenanceByDept(maintenanceByDept);
        
        return distributionDTO;
    }

    @Override
    public EquipmentSearchResultDTO searchEquipment(String keyword) {
        EquipmentSearchResultDTO resultDTO = new EquipmentSearchResultDTO();
        
        // 构建搜索条件
        LambdaQueryWrapper<Device> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Device::getIsDeleted, 0);
        
        if (StringUtils.hasText(keyword)) {
            queryWrapper.and(wrapper -> wrapper
                    .like(Device::getDeviceName, keyword)
                    .or()
                    .like(Device::getDeviceModel, keyword)
                    .or()
                    .like(Device::getDepartmentName, keyword)
            );
        }
        
        // 查询符合条件的设备
        List<Device> devices = deviceMapper.selectList(queryWrapper);
        
        // 统计使用情况
        EquipmentUsageDTO usageStats = new EquipmentUsageDTO();
        int inUse = 0, idle = 0, inMaintenance = 0;
        
        for (Device device : devices) {
            switch (device.getStatus()) {
                case 0: idle++; break;
                case 1: inUse++; break;
                case 2: inMaintenance++; break;
            }
        }
        
        usageStats.setInUse(inUse);
        usageStats.setIdle(idle);
        usageStats.setInMaintenance(inMaintenance);
        resultDTO.setUsageStats(usageStats);
        
        // 提取搜索结果中的部门并排序
        List<String> departments = devices.stream()
                .map(Device::getDepartmentName)
                .filter(StringUtils::hasText)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        
        // 统计各部门各状态设备分布
        EquipmentDistributionDTO distributionStats = new EquipmentDistributionDTO();
        List<Integer> inUseByDept = new ArrayList<>();
        List<Integer> idleByDept = new ArrayList<>();
        List<Integer> maintenanceByDept = new ArrayList<>();
        
        for (String department : departments) {
            // 该部门使用中的设备数量
            inUseByDept.add((int) devices.stream()
                    .filter(d -> department.equals(d.getDepartmentName()) && d.getStatus() == 1)
                    .count());
            
            // 该部门闲置的设备数量
            idleByDept.add((int) devices.stream()
                    .filter(d -> department.equals(d.getDepartmentName()) && d.getStatus() == 0)
                    .count());
            
            // 该部门维修中的设备数量
            maintenanceByDept.add((int) devices.stream()
                    .filter(d -> department.equals(d.getDepartmentName()) && d.getStatus() == 2)
                    .count());
        }
        
        distributionStats.setDepartments(departments);
        distributionStats.setInUseByDept(inUseByDept);
        distributionStats.setIdleByDept(idleByDept);
        distributionStats.setMaintenanceByDept(maintenanceByDept);
        
        resultDTO.setDistributionStats(distributionStats);
        resultDTO.setDepartments(departments);
        
        return resultDTO;
    }
}