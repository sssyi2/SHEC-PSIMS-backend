package com.webtab.shecpsims.mapper.bigdata;

import com.webtab.shecpsims.model.entity.bigdata.CostBreakdown;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CostBreakdownMapper {

    @Select("SELECT * FROM cost_breakdown WHERE financial_data_id = #{financialDataId}")
    List<CostBreakdown> findByFinancialDataId(@Param("financialDataId") Integer financialDataId);
}