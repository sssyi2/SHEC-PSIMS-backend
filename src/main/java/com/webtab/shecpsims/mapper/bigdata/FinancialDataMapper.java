package com.webtab.shecpsims.mapper.bigdata;

import com.webtab.shecpsims.model.entity.bigdata.FinancialData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;

@Mapper
public interface FinancialDataMapper {

    @Select("SELECT * FROM financial_data WHERE period_type = #{periodType} " +
            "AND period_start <= #{currentDate} AND period_end >= #{currentDate}")
    FinancialData findCurrentPeriodData(@Param("periodType") String periodType,
                                        @Param("currentDate") LocalDate currentDate);

    @Select("SELECT * FROM financial_data WHERE period_type = #{periodType} " +
            "ORDER BY period_end DESC LIMIT 1")
    FinancialData findLatestByPeriodType(@Param("periodType") String periodType);
}