// MedicineInventoryService.java
package com.webtab.shecpsims.service.bigdata;


import com.webtab.shecpsims.model.dto.bigdata.MedicineInventoryDTO;

import java.util.List;

public interface MedicineInventoryService {
    /**
     * 获取所有药品库存
     */
    List<MedicineInventoryDTO> getAllMedicineInventory();

    /**
     * 搜索药品库存
     */
    List<MedicineInventoryDTO> searchMedicineInventory(String query);
}