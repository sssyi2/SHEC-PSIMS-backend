package com.webtab.shecpsims.service.bigdata.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.webtab.shecpsims.mapper.bigdata.DeviceMapper;
import com.webtab.shecpsims.model.dto.bigdata.*;
import com.webtab.shecpsims.model.entity.bigdata.Device;
import com.webtab.shecpsims.service.bigdata.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
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

    @Override
    public EquipmentScheduleAdviceDTO getEquipmentScheduleAdvice() {
        // 获取所有设备数据用于分析
        List<Device> allDevices = deviceMapper.selectList(
                new LambdaQueryWrapper<Device>().eq(Device::getIsDeleted, 0)
        );
        
        // 统计各种状态的设备数量
        int totalEquipment = allDevices.size();
        int idleCount = (int) allDevices.stream().filter(d -> d.getStatus() == 0).count();
        int maintenanceCount = (int) allDevices.stream().filter(d -> d.getStatus() == 2).count();
        int inUseCount = (int) allDevices.stream().filter(d -> d.getStatus() == 1).count();
        int availableCount = (int) Math.floor(idleCount * 0.8); // 假设80%的闲置设备可以调度
        
        // 按部门统计设备使用情况
        Map<String, List<Device>> devicesByDept = allDevices.stream()
                .filter(d -> StringUtils.hasText(d.getDepartmentName()))
                .collect(Collectors.groupingBy(Device::getDepartmentName));
        
        // 生成调度建议
        List<EquipmentScheduleAdviceDTO.UrgentScheduleItem> urgentItems = generateScheduleAdvice(devicesByDept);
        
        // 生成总体建议文本
        String advice = generateAdviceText(idleCount, maintenanceCount, urgentItems.size());
        
        // 创建统计信息
        EquipmentScheduleAdviceDTO.ScheduleStatistics statistics = 
                new EquipmentScheduleAdviceDTO.ScheduleStatistics(totalEquipment, idleCount, maintenanceCount, availableCount);
        
        return new EquipmentScheduleAdviceDTO(advice, urgentItems, statistics);
    }

    @Override
    public void updateEquipmentStatus(String equipmentId, UpdateEquipmentStatusDTO updateRequest) {
        LambdaUpdateWrapper<Device> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Device::getDeviceId, Integer.parseInt(equipmentId))
                    .eq(Device::getIsDeleted, 0);
        
        // 根据状态字符串转换为数字状态
        Integer status = convertStatusToNumber(updateRequest.getStatus());
        if (status != null) {
            updateWrapper.set(Device::getStatus, status);
        }
        
        if (StringUtils.hasText(updateRequest.getDepartment())) {
            updateWrapper.set(Device::getDepartmentName, updateRequest.getDepartment());
        }
        
        updateWrapper.set(Device::getUpdateTime, LocalDateTime.now());
        
        deviceMapper.update(null, updateWrapper);
    }

    @Override
    public void batchScheduleEquipment(BatchScheduleRequestDTO batchRequest) {
        for (BatchScheduleRequestDTO.ScheduleItem item : batchRequest.getSchedules()) {
            LambdaUpdateWrapper<Device> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(Device::getDeviceId, Integer.parseInt(item.getEquipmentId()))
                        .eq(Device::getIsDeleted, 0)
                        .set(Device::getDepartmentName, item.getTargetDepartment())
                        .set(Device::getUpdateTime, LocalDateTime.now());
            
            deviceMapper.update(null, updateWrapper);
        }
    }

    @Override
    public List<Object> getAllEquipment() {
        List<Device> devices = deviceMapper.selectList(
                new LambdaQueryWrapper<Device>().eq(Device::getIsDeleted, 0)
        );
        
        return devices.stream().map(device -> {
            Map<String, Object> equipmentMap = new HashMap<>();
            equipmentMap.put("id", device.getDeviceId().toString());
            equipmentMap.put("name", device.getDeviceName());
            equipmentMap.put("code", device.getDeviceModel());
            equipmentMap.put("type", "医疗设备"); // 默认设备类型，如需细分可后续扩展
            equipmentMap.put("department", device.getDepartmentName());
            equipmentMap.put("status", convertStatusToString(device.getStatus()));
            equipmentMap.put("lastUsed", device.getUpdateTime());
            equipmentMap.put("maintenanceDate", device.getLastMaintenanceDate());
            return equipmentMap;
        }).collect(Collectors.toList());
    }

    /**
     * 生成调度建议
     */
    private List<EquipmentScheduleAdviceDTO.UrgentScheduleItem> generateScheduleAdvice(Map<String, List<Device>> devicesByDept) {
        List<EquipmentScheduleAdviceDTO.UrgentScheduleItem> urgentItems = new ArrayList<>();
        
        // 分析各部门设备使用情况
        Map<String, Double> deptUsageRate = new HashMap<>();
        for (Map.Entry<String, List<Device>> entry : devicesByDept.entrySet()) {
            String dept = entry.getKey();
            List<Device> devices = entry.getValue();
            long inUseCount = devices.stream().filter(d -> d.getStatus() == 1).count();
            double usageRate = devices.isEmpty() ? 0 : (double) inUseCount / devices.size();
            deptUsageRate.put(dept, usageRate);
        }
        
        // 找出使用率高和低的部门
        List<String> highUsageDepts = deptUsageRate.entrySet().stream()
                .filter(e -> e.getValue() > 0.8)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        
        List<String> lowUsageDepts = deptUsageRate.entrySet().stream()
                .filter(e -> e.getValue() < 0.4)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        
        // 生成紧急调度建议
        int urgentCount = 0;
        for (String lowUsageDept : lowUsageDepts) {
            if (urgentCount >= 5) break; // 最多显示5个紧急建议
            
            List<Device> idleDevices = devicesByDept.get(lowUsageDept).stream()
                    .filter(d -> d.getStatus() == 0)
                    .limit(2)
                    .collect(Collectors.toList());
            
            for (Device device : idleDevices) {
                if (urgentCount >= 5) break;
                
                String targetDept = highUsageDepts.isEmpty() ? "急诊科" : highUsageDepts.get(urgentCount % highUsageDepts.size());
                String urgencyLevel = deptUsageRate.get(targetDept) > 0.9 ? "high" : "medium";
                String reason = String.format("%s使用率过高(%.1f%%)，%s当前负载较低(%.1f%%)", 
                        targetDept, deptUsageRate.getOrDefault(targetDept, 0.0) * 100,
                        lowUsageDept, deptUsageRate.get(lowUsageDept) * 100);
                
                urgentItems.add(new EquipmentScheduleAdviceDTO.UrgentScheduleItem(
                        device.getDeviceId().toString(),
                        device.getDeviceName(),
                        device.getDeviceModel(),
                        lowUsageDept,
                        targetDept,
                        urgencyLevel,
                        reason
                ));
                urgentCount++;
            }
        }
        
        return urgentItems;
    }

    /**
     * 生成建议文本
     */
    private String generateAdviceText(int idleCount, int maintenanceCount, int urgentCount) {
        StringBuilder advice = new StringBuilder();
        
        if (idleCount > 0) {
            advice.append(String.format("当前有%d台设备处于闲置状态", idleCount));
        }
        
        if (maintenanceCount > 0) {
            if (advice.length() > 0) advice.append("，");
            advice.append(String.format("%d台设备需要维修", maintenanceCount));
        }
        
        if (urgentCount > 0) {
            if (advice.length() > 0) advice.append("，");
            advice.append(String.format("建议优先处理%d台设备的调度安排", urgentCount));
        }
        
        if (advice.length() > 0) {
            advice.append("，建议进行合理调度以提高使用效率");
        } else {
            advice.append("当前设备配置合理，无需特殊调度");
        }
        
        return advice.toString();
    }

    /**
     * 状态字符串转数字
     */
    private Integer convertStatusToNumber(String status) {
        if (status == null) return null;
        switch (status.toLowerCase()) {
            case "idle": return 0;
            case "in-use": return 1;
            case "maintenance": return 2;
            default: return null;
        }
    }

    /**
     * 状态数字转字符串
     */
    private String convertStatusToString(Integer status) {
        if (status == null) return "unknown";
        switch (status) {
            case 0: return "idle";
            case 1: return "in-use";
            case 2: return "maintenance";
            default: return "unknown";
        }
    }
}