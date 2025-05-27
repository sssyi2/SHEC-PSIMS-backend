// MedicineInventoryServiceImpl.java
package com.webtab.shecpsims.service.bigdata.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.webtab.shecpsims.model.dto.bigdata.MedicineInventoryDTO;
import com.webtab.shecpsims.model.entity.bigdata.Medicine;
import com.webtab.shecpsims.model.entity.bigdata.MedicineInventory;
import com.webtab.shecpsims.mapper.bigdata.MedicineInventoryMapper;
import com.webtab.shecpsims.mapper.bigdata.MedicineMapper;
import com.webtab.shecpsims.service.bigdata.MedicineInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

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

        return result;
    }

    @Override
    public List<MedicineInventoryDTO> searchMedicineInventory(String query) {
        List<Medicine> medicines = medicineMapper.searchByNameOrCode(query);
        List<MedicineInventoryDTO> result = new ArrayList<>();

        for (Medicine medicine : medicines) {
            MedicineInventory inventory = inventoryMapper.findByMedicineId(medicine.getId());
            result.add(convertToDTO(medicine, inventory));
        }

        return result;
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
}