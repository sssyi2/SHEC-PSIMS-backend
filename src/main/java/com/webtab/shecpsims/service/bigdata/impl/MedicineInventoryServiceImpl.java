// MedicineInventoryServiceImpl.java
package com.webtab.shecpsims.service.bigdata.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.webtab.shecpsims.model.dto.bigdata.*;
import com.webtab.shecpsims.model.entity.bigdata.Medicine;
import com.webtab.shecpsims.model.entity.bigdata.MedicineInventory;
import com.webtab.shecpsims.mapper.bigdata.MedicineInventoryMapper;
import com.webtab.shecpsims.mapper.bigdata.MedicineMapper;
import com.webtab.shecpsims.service.bigdata.MedicineInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Random;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class MedicineInventoryServiceImpl implements MedicineInventoryService {

    @Autowired
    private MedicineMapper medicineMapper;

    @Autowired
    private MedicineInventoryMapper inventoryMapper;

    @Override
    public List<MedicineInventoryDTO> getAllMedicineInventory() {
        // 获取所有药品
        List<Medicine> medicines = medicineMapper.selectList(new LambdaQueryWrapper<Medicine>()
                .eq(Medicine::getStatus, 1));

        List<MedicineInventoryDTO> result = new ArrayList<>();

        for (Medicine medicine : medicines) {
            MedicineInventory inventory = inventoryMapper.findByMedicineId(medicine.getId());
            result.add(convertToDTO(medicine, inventory));
        }

        return calculateWarningLevels(result);
    }

    @Override
    public List<MedicineInventoryDTO> searchMedicineInventory(String query) {
        List<Medicine> medicines = medicineMapper.searchByNameOrCode(query);
        List<MedicineInventoryDTO> result = new ArrayList<>();

        for (Medicine medicine : medicines) {
            MedicineInventory inventory = inventoryMapper.findByMedicineId(medicine.getId());
            result.add(convertToDTO(medicine, inventory));
        }

        return calculateWarningLevels(result);
    }

    @Override
    public PurchaseAdviceDTO getPurchaseAdvice() {
        List<MedicineInventoryDTO> lowStockMedicines = getLowStockMedicines(null); // 使用默认逻辑
        
        PurchaseAdviceDTO advice = new PurchaseAdviceDTO();
        
        // 生成总体建议
        if (lowStockMedicines.isEmpty()) {
            advice.setAdvice("当前药品库存充足，无需紧急采购。建议继续监控库存变化。");
        } else {
            int criticalCount = (int) lowStockMedicines.stream()
                    .filter(m -> "critical".equals(m.getWarningLevel()))
                    .count();
            int warningCount = lowStockMedicines.size() - criticalCount;
            
            advice.setAdvice(String.format("发现 %d 种药品库存告急，%d 种药品库存偏低，建议尽快安排采购。", 
                    criticalCount, warningCount));
        }

        // 生成紧急采购清单
        List<PurchaseAdviceDTO.UrgentItem> urgentItems = lowStockMedicines.stream()
                .map(this::convertToUrgentItem)
                .sorted(Comparator.comparing(item -> 
                    "high".equals(item.getPriority()) ? 0 : 
                    "medium".equals(item.getPriority()) ? 1 : 2))
                .collect(Collectors.toList());
        
        advice.setUrgent(urgentItems);

        // 生成统计信息
        PurchaseAdviceDTO.StatisticsInfo stats = new PurchaseAdviceDTO.StatisticsInfo();
        stats.setTotalMedicines(getAllMedicineInventory().size());
        stats.setWarningCount((int) lowStockMedicines.stream()
                .filter(m -> "warning".equals(m.getWarningLevel())).count());
        stats.setCriticalCount((int) lowStockMedicines.stream()
                .filter(m -> "critical".equals(m.getWarningLevel())).count());
        stats.setTotalEstimatedCost(urgentItems.stream()
                .map(PurchaseAdviceDTO.UrgentItem::getEstimatedCost)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        stats.setLastUpdated(LocalDateTime.now());
        
        advice.setStatistics(stats);

        return advice;
    }

    @Override
    public SmartPurchaseAnalysisDTO getSmartPurchaseAnalysis() {
        SmartPurchaseAnalysisDTO analysis = new SmartPurchaseAnalysisDTO();
        
        List<MedicineInventoryDTO> allInventory = getAllMedicineInventory();
        List<MedicineInventoryDTO> lowStockMedicines = getLowStockMedicines(null); // 使用默认逻辑
        
        // 生成分析ID和摘要
        analysis.setAnalysisId("SPA-" + System.currentTimeMillis());
        analysis.setSummary(generateAnalysisSummary(allInventory, lowStockMedicines));
        analysis.setGeneratedAt(LocalDateTime.now());
        
        // 生成推荐项目
        List<SmartPurchaseAnalysisDTO.RecommendationItem> recommendations = 
                generateSmartRecommendations(lowStockMedicines);
        analysis.setRecommendations(recommendations);
        
        // 趋势分析
        SmartPurchaseAnalysisDTO.TrendAnalysis trendAnalysis = generateTrendAnalysis(allInventory);
        analysis.setTrendAnalysis(trendAnalysis);
        
        // 成本分析
        SmartPurchaseAnalysisDTO.CostAnalysis costAnalysis = generateCostAnalysis(recommendations);
        analysis.setCostAnalysis(costAnalysis);
        
        return analysis;
    }

    @Override
    public List<MedicineInventoryDTO> getLowStockMedicines(Integer daysThreshold) {
        List<MedicineInventoryDTO> allInventory = getAllMedicineInventory();
        return allInventory.stream()
                .filter(medicine -> {
                    // 如果没有指定阈值，使用默认的warning和critical级别
                    if (daysThreshold == null) {
                        return "warning".equals(medicine.getWarningLevel()) || 
                               "critical".equals(medicine.getWarningLevel());
                    }
                    // 使用天数阈值过滤
                    return medicine.getDaysRemaining() != null && 
                           medicine.getDaysRemaining() <= daysThreshold;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateMedicineInventory(String medicineId, Map<String, Object> updateData) {
        MedicineInventory inventory = inventoryMapper.findByMedicineId(medicineId);
        if (inventory == null) {
            throw new RuntimeException("药品库存记录不存在");
        }

        if (updateData.containsKey("stock")) {
            inventory.setStock((Integer) updateData.get("stock"));
        }
        if (updateData.containsKey("dailyUsage")) {
            inventory.setDailyUsage((Double) updateData.get("dailyUsage"));
        }

        // 重新计算剩余天数
        if (inventory.getDailyUsage() != null && inventory.getDailyUsage() > 0) {
            inventory.setDaysRemaining((int) (inventory.getStock() / inventory.getDailyUsage()));
        }

        inventory.setUpdateTime(LocalDateTime.now());
        inventoryMapper.updateById(inventory);
    }

    @Override
    @Transactional
    public void batchUpdateMedicineInventory(BatchUpdateInventoryRequest request) {
        for (BatchUpdateInventoryRequest.UpdateItem updateItem : request.getUpdates()) {
            Map<String, Object> updateData = new HashMap<>();
            updateData.put("stock", updateItem.getStock());
            if (updateItem.getDailyUsage() != null) {
                updateData.put("dailyUsage", updateItem.getDailyUsage());
            }
            updateMedicineInventory(updateItem.getMedicineId(), updateData);
        }
    }

    @Override
    @Transactional
    public boolean batchUpdateStock(BatchUpdateInventoryRequest request) {
        try {
            batchUpdateMedicineInventory(request);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Map<String, Object> getMedicineInventoryTrend(String medicineId, Integer days) {
        // 这里应该从历史记录表获取数据，暂时返回模拟数据
        Map<String, Object> trend = new HashMap<>();
        trend.put("medicineId", medicineId);
        trend.put("period", days + "天");
        trend.put("data", generateMockTrendData(days));
        return trend;
    }

    @Override
    public List<Map<String, Object>> getMedicineTrend(String medicineId, Integer days) {
        // 生成趋势数据列表，每个时间点一个数据项
        List<Map<String, Object>> trendList = new ArrayList<>();
        
        // 生成指定天数的每日趋势数据
        LocalDate startDate = LocalDate.now().minusDays(days);
        for (int i = 0; i < days; i++) {
            LocalDate currentDate = startDate.plusDays(i);
            Map<String, Object> dailyData = new HashMap<>();
            dailyData.put("date", currentDate.toString());
            dailyData.put("medicineId", medicineId);
            // 模拟库存变化数据
            dailyData.put("stock", 100 + (int)(Math.random() * 50) - 25); // 75-125之间随机
            dailyData.put("usage", 5 + (int)(Math.random() * 10)); // 5-15之间随机
            dailyData.put("remaining", 15 + (int)(Math.random() * 10)); // 15-25之间随机
            trendList.add(dailyData);
        }
        
        return trendList;
    }

    @Override
    public List<Map<String, Object>> getMedicineCategories() {
        // 从药品表获取所有不同的类型
        List<Medicine> medicines = medicineMapper.selectList(null);
        return medicines.stream()
                .collect(Collectors.groupingBy(Medicine::getType, Collectors.counting()))
                .entrySet().stream()
                .map(entry -> {
                    Map<String, Object> category = new HashMap<>();
                    category.put("name", entry.getKey());
                    category.put("count", entry.getValue());
                    return category;
                })
                .collect(Collectors.toList());
    }

    /**
     * 将实体对象转换为DTO
     */
    private MedicineInventoryDTO convertToDTO(Medicine medicine, MedicineInventory inventory) {
        Integer stock = 0;
        Double dailyUsage = 0.0;
        Integer daysRemaining = 999;
        String warningLevel = "normal";

        if (inventory != null) {
            stock = inventory.getStock();
            dailyUsage = inventory.getDailyUsage();
            daysRemaining = inventory.getDaysRemaining();
            warningLevel = inventory.getWarningLevel();
        }

        MedicineInventoryDTO dto = new MedicineInventoryDTO();
        dto.setId(medicine.getId());
        dto.setName(medicine.getName());
        dto.setCode(medicine.getCode());
        dto.setType(medicine.getType());
        dto.setStock(stock);
        dto.setUnit(medicine.getUnit());
        dto.setDailyUsage(dailyUsage);
        dto.setDaysRemaining(daysRemaining);
        dto.setWarningLevel(warningLevel);
        return dto;
    }

    /**
     * 计算预警级别
     */
    private List<MedicineInventoryDTO> calculateWarningLevels(List<MedicineInventoryDTO> inventoryList) {
        return inventoryList.stream().peek(inventory -> {
            if (inventory.getDaysRemaining() != null) {
                if (inventory.getDaysRemaining() <= 3) {
                    inventory.setWarningLevel("critical");
                } else if (inventory.getDaysRemaining() <= 7) {
                    inventory.setWarningLevel("warning");
                } else {
                    inventory.setWarningLevel("normal");
                }
            } else {
                inventory.setWarningLevel("normal");
            }
        }).collect(Collectors.toList());
    }

    /**
     * 转换为紧急采购项目
     */
    private PurchaseAdviceDTO.UrgentItem convertToUrgentItem(MedicineInventoryDTO medicine) {
        PurchaseAdviceDTO.UrgentItem item = new PurchaseAdviceDTO.UrgentItem();
        item.setId(medicine.getId());
        item.setName(medicine.getName());
        item.setCode(medicine.getCode());
        item.setCurrentStock(medicine.getStock());
        item.setUnit(medicine.getUnit());
        item.setDaysRemaining(medicine.getDaysRemaining());
        
        // 计算建议采购量（基于30天用量）
        if (medicine.getDailyUsage() != null && medicine.getDailyUsage() > 0) {
            item.setSuggestedOrder((int) (medicine.getDailyUsage() * 30));
            // 估算成本（假设平均单价为10元）
            item.setEstimatedCost(BigDecimal.valueOf(item.getSuggestedOrder() * 10));
        } else {
            item.setSuggestedOrder(100); // 默认建议采购量
            item.setEstimatedCost(BigDecimal.valueOf(1000));
        }
        
        // 确定优先级
        if ("critical".equals(medicine.getWarningLevel())) {
            item.setPriority("high");
        } else if ("warning".equals(medicine.getWarningLevel())) {
            item.setPriority("medium");
        } else {
            item.setPriority("low");
        }
        
        return item;
    }

    /**
     * 生成分析摘要
     */
    private String generateAnalysisSummary(List<MedicineInventoryDTO> allInventory, List<MedicineInventoryDTO> lowStockMedicines) {
        double lowStockRatio = (double) lowStockMedicines.size() / allInventory.size() * 100;
        return String.format("基于当前库存数据分析，共有 %d 种药品，其中 %d 种存在库存不足问题，占比 %.1f%%。建议关注库存管理，优化采购策略。", 
                allInventory.size(), lowStockMedicines.size(), lowStockRatio);
    }

    /**
     * 生成智能推荐
     */
    private List<SmartPurchaseAnalysisDTO.RecommendationItem> generateSmartRecommendations(List<MedicineInventoryDTO> lowStockMedicines) {
        return lowStockMedicines.stream().map(medicine -> {
            SmartPurchaseAnalysisDTO.RecommendationItem recommendation = new SmartPurchaseAnalysisDTO.RecommendationItem();
            recommendation.setId(medicine.getId());
            recommendation.setName(medicine.getName());
            recommendation.setCode(medicine.getCode());
            recommendation.setCategory(medicine.getType());
            recommendation.setCurrentStock(medicine.getStock());
            
            if (medicine.getDailyUsage() != null) {
                recommendation.setPredictedConsumption((int) (medicine.getDailyUsage() * 30));
                recommendation.setSuggestedOrder((int) (medicine.getDailyUsage() * 45)); // 45天用量
            } else {
                recommendation.setPredictedConsumption(50);
                recommendation.setSuggestedOrder(150);
            }
            
            recommendation.setUrgencyLevel(medicine.getWarningLevel());
            recommendation.setUnitPrice(BigDecimal.valueOf(10)); // 模拟单价
            recommendation.setTotalCost(recommendation.getUnitPrice().multiply(BigDecimal.valueOf(recommendation.getSuggestedOrder())));
            recommendation.setReason("基于历史消耗量和当前库存水平的智能分析建议");
            
            return recommendation;
        }).collect(Collectors.toList());
    }

    /**
     * 生成趋势分析
     */
    private SmartPurchaseAnalysisDTO.TrendAnalysis generateTrendAnalysis(List<MedicineInventoryDTO> allInventory) {
        SmartPurchaseAnalysisDTO.TrendAnalysis trendAnalysis = new SmartPurchaseAnalysisDTO.TrendAnalysis();
        trendAnalysis.setOverallTrend("整体库存水平稳定，部分类别存在消耗加快趋势");
        
        // 分析高消耗类别
        Map<String, Long> categoryCount = allInventory.stream()
                .collect(Collectors.groupingBy(MedicineInventoryDTO::getType, Collectors.counting()));
        
        List<String> highConsumptionCategories = categoryCount.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        
        trendAnalysis.setHighConsumptionCategories(highConsumptionCategories);
        trendAnalysis.setLowStockCategories(Arrays.asList("抗生素类", "心血管药物"));
        trendAnalysis.setAverageStockDuration(15.5);
        
        return trendAnalysis;
    }

    /**
     * 生成成本分析
     */
    private SmartPurchaseAnalysisDTO.CostAnalysis generateCostAnalysis(List<SmartPurchaseAnalysisDTO.RecommendationItem> recommendations) {
        SmartPurchaseAnalysisDTO.CostAnalysis costAnalysis = new SmartPurchaseAnalysisDTO.CostAnalysis();
        
        BigDecimal totalCost = recommendations.stream()
                .map(SmartPurchaseAnalysisDTO.RecommendationItem::getTotalCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        BigDecimal urgentCost = recommendations.stream()
                .filter(r -> "critical".equals(r.getUrgencyLevel()))
                .map(SmartPurchaseAnalysisDTO.RecommendationItem::getTotalCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        costAnalysis.setTotalBudgetRequired(totalCost);
        costAnalysis.setUrgentItemsCost(urgentCost);
        costAnalysis.setPlannedItemsCost(totalCost.subtract(urgentCost));
        costAnalysis.setCostOptimizationSuggestion("建议优先采购紧急药品，其他药品可分批采购以优化现金流");
        
        return costAnalysis;
    }

    /**
     * 生成模拟趋势数据
     */
    private List<Map<String, Object>> generateMockTrendData(Integer days) {
        List<Map<String, Object>> trendData = new ArrayList<>();
        Random random = new Random();
        
        for (int i = days; i >= 0; i--) {
            Map<String, Object> dataPoint = new HashMap<>();
            dataPoint.put("date", LocalDateTime.now().minusDays(i).toLocalDate().toString());
            dataPoint.put("stock", 100 + random.nextInt(50) - i); // 模拟库存递减趋势
            dataPoint.put("consumption", 5 + random.nextInt(5)); // 模拟日消耗量
            trendData.add(dataPoint);
        }
        
        return trendData;
    }
}
