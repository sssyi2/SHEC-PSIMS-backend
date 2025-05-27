// MedicineInventory.java
package com.webtab.shecpsims.model.entity.bigdata;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("medicine_inventory")
public class MedicineInventory {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String medicineId;
    private Integer stock;
    private Double dailyUsage;
    private Integer daysRemaining;
    private String warningLevel;
    private LocalDateTime updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(String medicineId) {
        this.medicineId = medicineId;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getDailyUsage() {
        return dailyUsage;
    }

    public void setDailyUsage(Double dailyUsage) {
        this.dailyUsage = dailyUsage;
    }

    public Integer getDaysRemaining() {
        return daysRemaining;
    }

    public void setDaysRemaining(Integer daysRemaining) {
        this.daysRemaining = daysRemaining;
    }

    public String getWarningLevel() {
        return warningLevel;
    }

    public void setWarningLevel(String warningLevel) {
        this.warningLevel = warningLevel;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}