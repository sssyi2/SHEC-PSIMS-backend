package com.webtab.shecpsims.model.dto.bigdata;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;

@Data
public class BatchUpdateInventoryRequest {
    private List<UpdateItem> updates;

    @Data
    public static class UpdateItem {
        @NotNull(message = "药品ID不能为空")
        private String medicineId;

        @NotNull(message = "库存数量不能为空")
        @Positive(message = "库存数量必须大于0")
        private Integer stock;
        
        private Double dailyUsage;
        
        private String updateReason;
    }
}
