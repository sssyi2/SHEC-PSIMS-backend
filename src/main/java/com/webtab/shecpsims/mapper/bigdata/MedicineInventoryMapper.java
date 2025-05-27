// MedicineInventoryMapper.java
package com.webtab.shecpsims.mapper.bigdata;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.webtab.shecpsims.model.entity.bigdata.MedicineInventory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MedicineInventoryMapper extends BaseMapper<MedicineInventory> {
    @Select("SELECT * FROM medicine_inventory WHERE medicine_id = #{medicineId}")
    MedicineInventory findByMedicineId(@Param("medicineId") String medicineId);
}