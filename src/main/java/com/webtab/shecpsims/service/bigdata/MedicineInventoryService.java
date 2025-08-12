// MedicineInventoryService.java
package com.webtab.shecpsims.service.bigdata;


import com.webtab.shecpsims.model.dto.bigdata.BatchUpdateInventoryRequest;
import com.webtab.shecpsims.model.dto.bigdata.MedicineInventoryDTO;
import com.webtab.shecpsims.model.dto.bigdata.PurchaseAdviceDTO;
import com.webtab.shecpsims.model.dto.bigdata.SmartPurchaseAnalysisDTO;

import java.util.List;
import java.util.Map;

public interface MedicineInventoryService {
    /**
     * 获取所有药品库存
     */
    List<MedicineInventoryDTO> getAllMedicineInventory();

    /**
     * 搜索药品库存
     */
    List<MedicineInventoryDTO> searchMedicineInventory(String query);

    /**
     * 获取药品采购建议
     */
    PurchaseAdviceDTO getPurchaseAdvice();

    /**
     * 获取智能采购分析
     */
    SmartPurchaseAnalysisDTO getSmartPurchaseAnalysis();

    /**
     * 获取库存预警药品列表
     * @param daysThreshold 天数阈值，小于等于该天数的药品将被视为低库存
     */
    List<MedicineInventoryDTO> getLowStockMedicines(Integer daysThreshold);

    /**
     * 更新药品库存信息
     */
    void updateMedicineInventory(String medicineId, Map<String, Object> updateData);

    /**
     * 批量更新药品库存
     */
    void batchUpdateMedicineInventory(BatchUpdateInventoryRequest request);

    /**
     * 批量更新药品库存（带返回状态）
     */
    boolean batchUpdateStock(BatchUpdateInventoryRequest request);

    /**
     * 获取药品库存历史趋势
     */
    Map<String, Object> getMedicineInventoryTrend(String medicineId, Integer days);

    /**
     * 获取药品趋势数据列表
     */
    List<Map<String, Object>> getMedicineTrend(String medicineId, Integer days);

    /**
     * 获取药品分类列表
     */
    List<Map<String, Object>> getMedicineCategories();

}