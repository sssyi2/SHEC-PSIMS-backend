// MedicineMapper.java
package com.webtab.shecpsims.mapper.bigdata;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.webtab.shecpsims.model.entity.bigdata.Medicine;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface MedicineMapper extends BaseMapper<Medicine> {
    @Select("SELECT * FROM medicines WHERE name LIKE CONCAT('%', #{query}, '%') OR code LIKE CONCAT('%', #{query}, '%')")
    List<Medicine> searchByNameOrCode(@Param("query") String query);
}