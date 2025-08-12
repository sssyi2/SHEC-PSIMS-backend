# 药品采购建议系统 API 接口文档

## 基础药品库存管理 (`/api/medicine`)

### 1. 获取所有药品库存
- **GET** `/api/medicine/inventory`
- **描述**: 获取所有药品的库存信息
- **响应**: `List<MedicineInventoryDTO>`

### 2. 搜索药品库存
- **GET** `/api/medicine/inventory/search?query={searchTerm}`
- **描述**: 根据关键词搜索药品库存
- **参数**: 
  - `query`: 搜索关键词
- **响应**: `List<MedicineInventoryDTO>`

### 3. 获取采购建议
- **GET** `/api/medicine/purchase-advice`
- **描述**: 获取基础采购建议，包含紧急药品列表和统计信息
- **响应**: `PurchaseAdviceDTO`

### 4. 获取智能采购分析
- **GET** `/api/medicine/smart-analysis`
- **描述**: 获取智能采购分析，包含推荐、趋势和成本分析
- **响应**: `SmartPurchaseAnalysisDTO`

### 5. 获取低库存药品
- **GET** `/api/medicine/low-stock?daysThreshold={days}`
- **描述**: 获取低库存药品列表
- **参数**:
  - `daysThreshold`: 天数阈值，默认7天
- **响应**: `List<MedicineInventoryDTO>`

### 6. 批量更新库存
- **POST** `/api/medicine/inventory/batch-update`
- **描述**: 批量更新药品库存
- **请求体**: `BatchUpdateInventoryRequest`
- **响应**: `Boolean`

### 7. 获取药品趋势数据
- **GET** `/api/medicine/trend/{medicineId}?days={days}`
- **描述**: 获取指定药品的趋势数据
- **参数**:
  - `medicineId`: 药品ID
  - `days`: 天数，默认30天
- **响应**: `List<Map<String, Object>>`

### 8. 获取库存预警统计
- **GET** `/api/medicine/warning-stats`
- **描述**: 获取库存预警的统计信息
- **响应**: `Map<String, Object>`

---

## 采购建议高级功能 (`/api/purchase-advice`)

### 1. 获取采购建议概览
- **GET** `/api/purchase-advice/overview`
- **描述**: 获取完整的采购建议概览，包含基础建议和智能分析
- **响应**: `PurchaseAdviceOverviewDTO`

### 2. 获取紧急采购清单
- **GET** `/api/purchase-advice/urgent?priority={priority}`
- **描述**: 获取紧急采购清单，可按优先级过滤
- **参数**:
  - `priority`: 优先级过滤（high/medium/low）
- **响应**: `List<PurchaseAdviceDTO.UrgentItem>`

### 3. 获取智能推荐
- **GET** `/api/purchase-advice/recommendations?category={category}&urgencyLevel={level}`
- **描述**: 获取智能推荐列表，支持分类和紧急程度过滤
- **参数**:
  - `category`: 药品类别过滤
  - `urgencyLevel`: 紧急程度过滤
- **响应**: `List<SmartPurchaseAnalysisDTO.RecommendationItem>`

### 4. 获取成本分析
- **GET** `/api/purchase-advice/cost-analysis`
- **描述**: 获取采购成本分析
- **响应**: `SmartPurchaseAnalysisDTO.CostAnalysis`

### 5. 获取趋势分析
- **GET** `/api/purchase-advice/trend-analysis`
- **描述**: 获取消耗趋势分析
- **响应**: `SmartPurchaseAnalysisDTO.TrendAnalysis`

### 6. 生成采购计划
- **POST** `/api/purchase-advice/generate-purchase-plan?budgetLimit={budget}&priorityFilter={priority}`
- **描述**: 基于预算和优先级生成采购计划
- **参数**:
  - `budgetLimit`: 预算限制（可选）
  - `priorityFilter`: 优先级过滤（可选）
- **响应**: `PurchasePlanDTO`

---

## 库存实时监控 (`/api/inventory-monitor`)

### 1. 获取库存监控仪表板
- **GET** `/api/inventory-monitor/dashboard`
- **描述**: 获取库存监控仪表板的综合数据
- **响应**: `Map<String, Object>`
  - `totalMedicines`: 药品总数
  - `criticalCount`: 严重预警数量
  - `warningCount`: 一般预警数量
  - `normalCount`: 正常库存数量
  - `totalValue`: 总库存价值
  - `categoryStats`: 分类统计
  - `lowStockItems`: 低库存药品

### 2. 获取库存预警列表
- **GET** `/api/inventory-monitor/alerts?level={level}&limit={limit}`
- **描述**: 获取库存预警列表
- **参数**:
  - `level`: 预警级别过滤（critical/warning）
  - `limit`: 返回数量限制，默认50
- **响应**: `List<InventoryAlertDTO>`

### 3. 获取实时库存统计
- **GET** `/api/inventory-monitor/real-time-stats`
- **描述**: 获取实时库存统计数据
- **响应**: `Map<String, Object>`
  - `totalMedicines`: 药品总数
  - `averageStock`: 平均库存
  - `averageDaysRemaining`: 平均剩余天数
  - `stockTrend`: 库存趋势
  - `healthScore`: 库存健康评分

### 4. 获取分类库存分析
- **GET** `/api/inventory-monitor/category-analysis`
- **描述**: 获取按类别的库存分析
- **响应**: `List<CategoryAnalysisDTO>`

---

## 数据传输对象 (DTOs)

### 核心DTO类型

1. **MedicineInventoryDTO**: 药品库存信息
2. **PurchaseAdviceDTO**: 采购建议响应
3. **SmartPurchaseAnalysisDTO**: 智能分析响应
4. **PurchaseAdviceOverviewDTO**: 采购建议概览
5. **PurchasePlanDTO**: 采购计划
6. **InventoryAlertDTO**: 库存预警信息
7. **CategoryAnalysisDTO**: 分类分析信息
8. **BatchUpdateInventoryRequest**: 批量更新请求

### 嵌套类型

- **PurchaseAdviceDTO.UrgentItem**: 紧急药品项目
- **PurchaseAdviceDTO.StatisticsInfo**: 统计信息
- **SmartPurchaseAnalysisDTO.RecommendationItem**: 推荐项目
- **SmartPurchaseAnalysisDTO.TrendAnalysis**: 趋势分析
- **SmartPurchaseAnalysisDTO.CostAnalysis**: 成本分析

---

## 使用场景示例

### 1. 获取完整采购建议流程
```
1. GET /api/purchase-advice/overview  # 获取概览
2. GET /api/purchase-advice/urgent    # 获取紧急清单
3. POST /api/purchase-advice/generate-purchase-plan  # 生成计划
```

### 2. 库存监控流程
```
1. GET /api/inventory-monitor/dashboard      # 获取仪表板
2. GET /api/inventory-monitor/alerts         # 获取预警
3. GET /api/inventory-monitor/real-time-stats # 获取统计
```

### 3. 智能分析流程
```
1. GET /api/medicine/smart-analysis          # 获取智能分析
2. GET /api/purchase-advice/cost-analysis    # 获取成本分析
3. GET /api/purchase-advice/trend-analysis   # 获取趋势分析
```

---

## 响应格式

所有API响应都遵循统一的格式：

```json
{
    "code": 200,
    "message": "操作成功",
    "data": { ... },
    "timestamp": 1691234567890
}
```

- `code`: 响应状态码
- `message`: 响应消息
- `data`: 响应数据
- `timestamp`: 响应时间戳

---

## API测试建议

### 使用Postman或其他API测试工具
1. **环境配置**: 设置基础URL为 `http://localhost:8080`
2. **请求头**: 设置 `Content-Type: application/json`
3. **参数测试**: 测试各种参数组合和边界值
4. **错误处理**: 验证错误响应的格式和内容

### 常用测试场景
- 正常参数测试
- 边界值测试
- 空值和异常值测试
- 权限和安全测试
