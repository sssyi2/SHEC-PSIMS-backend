# 设备调度建议功能 API 测试指南

## 功能概述

该功能为SHEC-PSIMS系统提供了智能设备调度建议，包括：
- 设备使用率统计
- 设备分布情况分析  
- 智能调度建议生成
- 设备状态管理
- 批量设备调度

## 数据库表结构

### devices 表
```sql
CREATE TABLE `devices` (
  `device_id` int NOT NULL AUTO_INCREMENT COMMENT '设备ID',
  `device_name` varchar(100) NOT NULL COMMENT '设备名称', 
  `device_model` varchar(100) DEFAULT NULL COMMENT '设备型号',
  `status` int NOT NULL DEFAULT '0' COMMENT '设备状态：0-闲置, 1-使用中, 2-维修中',
  `department_name` varchar(100) DEFAULT NULL COMMENT '所属部门名称',
  `purchase_date` datetime DEFAULT NULL COMMENT '购买日期',
  `last_maintenance_date` datetime DEFAULT NULL COMMENT '最后维护日期',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` int NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`device_id`)
);
```

## API 接口列表

### 1. 获取设备使用率数据
```
GET /api/equipment/usage
```

**响应示例：**
```json
{
  "code": 200,
  "data": {
    "inUse": 12,
    "idle": 8,
    "inMaintenance": 3
  }
}
```

### 2. 获取设备分布情况
```
GET /api/equipment/distribution
```

**响应示例：**
```json
{
  "code": 200,
  "data": {
    "departments": ["内科", "外科", "儿科", "急诊科", "重症监护"],
    "inUseByDept": [3, 2, 1, 4, 2],
    "idleByDept": [1, 2, 3, 1, 1],
    "maintenanceByDept": [0, 0, 0, 0, 3]
  }
}
```

### 3. 设备搜索
```
GET /api/equipment/search?keyword=心电图
```

**响应示例：**
```json
{
  "code": 200,
  "data": {
    "usageStats": {
      "inUse": 2,
      "idle": 1,
      "inMaintenance": 0
    },
    "distributionStats": {
      "departments": ["内科", "急诊科"],
      "inUseByDept": [1, 1],
      "idleByDept": [1, 0],
      "maintenanceByDept": [0, 0]
    },
    "departments": ["内科", "急诊科"]
  }
}
```

### 4. 获取设备调度建议 ⭐ 
```
GET /api/equipment/schedule-advice
```

**响应示例：**
```json
{
  "code": 200,
  "data": {
    "advice": "当前有8台设备处于闲置状态，3台设备需要维修，建议优先处理3台设备的调度安排，建议进行合理调度以提高使用效率",
    "urgent": [
      {
        "id": "9",
        "name": "儿童心电图机",
        "code": "ECG-CHI-001",
        "currentDepartment": "儿科",
        "suggestedDepartment": "急诊科",
        "urgencyLevel": "high",
        "reason": "急诊科使用率过高(100.0%)，儿科当前负载较低(25.0%)"
      }
    ],
    "statistics": {
      "totalEquipment": 23,
      "idleCount": 8,
      "maintenanceCount": 3,
      "availableCount": 6,
      "lastUpdated": "2024-08-04T10:30:00"
    }
  }
}
```

### 5. 获取所有设备列表
```
GET /api/equipment/list
```

### 6. 更新设备状态
```
PUT /api/equipment/{equipmentId}/status
Content-Type: application/json

{
  "status": "idle",  // idle, in-use, maintenance
  "department": "内科"  // 可选
}
```

### 7. 批量调度设备
```
POST /api/equipment/batch-schedule
Content-Type: application/json

{
  "schedules": [
    {
      "equipmentId": "9",
      "targetDepartment": "急诊科",
      "reason": "急诊科设备需求增加"
    }
  ]
}
```

## 前端集成

前端 API 调用文件位置：
- `src/api/bigdata/equipment.ts` - 设备相关 API 封装
- `src/components/bigdata/resource-advice/EquipmentUsagePanel.vue` - 设备使用面板组件

### 主要功能特性：
1. **智能调度建议**：基于各部门设备使用率自动生成调度建议
2. **优先级分类**：按紧急程度对调度任务进行high/medium/low分级
3. **实时统计**：显示设备总数、闲置数、维修数、可调度数等关键指标
4. **可视化展示**：使用甜甜圈图和堆叠柱状图展示数据
5. **搜索功能**：支持按设备名称、型号、部门进行搜索

## 测试数据初始化

1. **SQL 脚本方式**：
   运行 `db_scripts/insert_test_devices.sql` 文件

2. **Java 初始化器方式**：
   取消 `DeviceDataInitializer.java` 中的 `@Component` 注释，重启应用

## 调度算法逻辑

1. **使用率计算**：各部门设备使用率 = 使用中设备数 / 部门总设备数
2. **高负载识别**：使用率 > 80% 的部门标记为高负载
3. **低负载识别**：使用率 < 40% 的部门标记为低负载  
4. **调度建议生成**：从低负载部门的闲置设备中选择，推荐调度到高负载部门
5. **紧急程度评估**：
   - high: 目标部门使用率 > 90%
   - medium: 目标部门使用率 80%-90%
   - low: 目标部门使用率 < 80%

## 开发建议

1. **扩展设备类型**：可在 Device 实体中添加 `deviceType` 字段进行分类管理
2. **预测算法**：结合历史数据进行设备需求预测
3. **通知系统**：当生成高优先级调度建议时发送通知
4. **审批流程**：重要设备调度需要管理员审批
5. **调度记录**：记录设备调度历史便于追踪和分析
