// MedicineInventoryController.java
package com.webtab.shecpsims.controller.bigdata;


import com.webtab.shecpsims.model.dto.ApiResponse;
import com.webtab.shecpsims.model.dto.bigdata.MedicineInventoryDTO;
import com.webtab.shecpsims.service.bigdata.MedicineInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/medicine")
public class MedicineInventoryController {

    @Autowired
    private  MedicineInventoryService medicineInventoryService;

    @GetMapping("/inventory")
    public ApiResponse<List<MedicineInventoryDTO>> getAllMedicineInventory() {
        List<MedicineInventoryDTO> inventoryList = medicineInventoryService.getAllMedicineInventory();
        return ApiResponse.success(inventoryList);
    }

    @GetMapping("/inventory/search")
    public ApiResponse<List<MedicineInventoryDTO>> searchMedicineInventory(@RequestParam String query) {
        List<MedicineInventoryDTO> searchResults = medicineInventoryService.searchMedicineInventory(query);
        return ApiResponse.success(searchResults);
    }
}