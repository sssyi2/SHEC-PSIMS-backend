package com.webtab.shecpsims.config;

import com.webtab.shecpsims.mapper.bigdata.DeviceMapper;
import com.webtab.shecpsims.model.entity.bigdata.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * 设备数据初始化器
 * 仅在开发环境使用，用于初始化测试数据
 */
//@Component  // 取消注释以启用数据初始化
public class DeviceDataInitializer implements CommandLineRunner {

    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public void run(String... args) throws Exception {
        // 检查是否已有数据
        Long count = deviceMapper.selectCount(null);
        if (count > 0) {
            System.out.println("设备数据已存在，跳过初始化");
            return;
        }

        // 创建测试设备数据
        List<Device> testDevices = createTestDevices();
        
        // 批量插入
        for (Device device : testDevices) {
            deviceMapper.insert(device);
        }
        
        System.out.println("已初始化 " + testDevices.size() + " 条设备测试数据");
    }

    private List<Device> createTestDevices() {
        return Arrays.asList(
            createDevice("心电图机-飞利浦", "ECG-PHI-001", 1, "内科"),
            createDevice("血压监测仪", "BP-MON-001", 0, "内科"),
            createDevice("血糖仪", "BG-MET-001", 1, "内科"),
            createDevice("输液泵-A型", "INF-PUM-A01", 0, "内科"),
            
            createDevice("手术显微镜", "SUR-MIC-001", 1, "外科"),
            createDevice("电刀设备", "ELE-KNI-001", 1, "外科"),
            createDevice("麻醉机", "ANE-MAC-001", 0, "外科"),
            createDevice("呼吸机-迈瑞", "VEN-MIN-001", 0, "外科"),
            
            createDevice("儿童心电图机", "ECG-CHI-001", 0, "儿科"),
            createDevice("婴儿培养箱", "INC-BAB-001", 1, "儿科"),
            createDevice("儿童呼吸机", "VEN-CHI-001", 0, "儿科"),
            createDevice("黄疸治疗仪", "JAU-TRE-001", 0, "儿科"),
            
            createDevice("除颤器", "DEF-EQU-001", 1, "急诊科"),
            createDevice("急救呼吸机", "VEN-EME-001", 1, "急诊科"),
            createDevice("心电监护仪", "ECG-MON-001", 1, "急诊科"),
            createDevice("急救推车", "EME-CAR-001", 1, "急诊科"),
            
            createDevice("多参数监护仪", "MUL-MON-001", 1, "重症监护"),
            createDevice("呼吸机-德尔格", "VEN-DRA-001", 1, "重症监护"),
            createDevice("血液净化机", "BLO-PUR-001", 2, "重症监护"),
            createDevice("体外循环机", "EXT-CIR-001", 1, "重症监护"),
            
            createDevice("X光机-西门子", "XRA-SIE-001", 0, "影像科"),
            createDevice("CT扫描仪", "CT-SCA-001", 1, "影像科"),
            createDevice("MRI设备", "MRI-EQU-001", 2, "影像科"),
            createDevice("超声诊断仪", "ULT-DIA-001", 1, "影像科")
        );
    }

    private Device createDevice(String name, String model, Integer status, String department) {
        Device device = new Device();
        device.setDeviceName(name);
        device.setDeviceModel(model);
        device.setStatus(status);
        device.setDepartmentName(department);
        device.setPurchaseDate(LocalDateTime.now().minusMonths((int)(Math.random() * 12)));
        device.setLastMaintenanceDate(LocalDateTime.now().minusDays((int)(Math.random() * 90)));
        return device;
    }
}
