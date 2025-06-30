package com.webtab.shecpsims.mapper.bigdata;

import com.webtab.shecpsims.model.entity.bigdata.MonthlyFinancialTrends;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MonthlyFinancialTrendsMapper {

    @Select("SELECT * FROM monthly_financial_trends WHERE year = #{year} ORDER BY month")
    List<MonthlyFinancialTrends> findTrendsByYear(@Param("year") Integer year);

    @Select("SELECT * FROM monthly_financial_trends WHERE year = #{year} " +
            "AND month BETWEEN #{startMonth} AND #{endMonth} ORDER BY month")
    List<MonthlyFinancialTrends> findTrendsByYearAndMonthRange(
            @Param("year") Integer year,
            @Param("startMonth") Integer startMonth,
            @Param("endMonth") Integer endMonth
    );
}