package com.webtab.shecpsims.service.bigdata.impl;

import com.webtab.shecpsims.mapper.bigdata.CostBreakdownMapper;
import com.webtab.shecpsims.mapper.bigdata.CostCategoriesMapper;
import com.webtab.shecpsims.mapper.bigdata.FinancialDataMapper;
import com.webtab.shecpsims.mapper.bigdata.MonthlyFinancialTrendsMapper;
import com.webtab.shecpsims.model.dto.bigdata.CostItemDTO;
import com.webtab.shecpsims.model.dto.bigdata.FinancialSummaryDTO;
import com.webtab.shecpsims.model.dto.bigdata.MonthlyTrendDTO;
import com.webtab.shecpsims.model.entity.bigdata.CostBreakdown;
import com.webtab.shecpsims.model.entity.bigdata.CostCategories;
import com.webtab.shecpsims.model.entity.bigdata.FinancialData;
import com.webtab.shecpsims.model.entity.bigdata.MonthlyFinancialTrends;
import com.webtab.shecpsims.service.bigdata.FinancialAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FinancialAnalysisServiceImpl implements FinancialAnalysisService {

    @Autowired
    private FinancialDataMapper financialDataMapper;

    @Autowired
    private CostBreakdownMapper costBreakdownMapper;

    @Autowired
    private MonthlyFinancialTrendsMapper monthlyTrendsMapper;

    @Autowired
    private CostCategoriesMapper costCategoriesMapper;

    @Override
    public FinancialSummaryDTO getFinancialSummary(String periodType) {
        if (!isValidPeriodType(periodType)) {
            throw new IllegalArgumentException("无效的周期类型: " + periodType);
        }

        // 1. 获取最新的财务数据
        FinancialData financialData = financialDataMapper.findLatestByPeriodType(periodType);
        if (financialData == null) {
            throw new RuntimeException("未找到" + getPeriodTypeDisplay(periodType) + "财务数据");
        }

        // 2. 获取成本明细
        List<CostBreakdown> costBreakdowns = costBreakdownMapper.findByFinancialDataId(financialData.getId());

        // 3. 获取成本类别配置
        List<CostCategories> categories = costCategoriesMapper.findAllActiveCategories();
        Map<String, String> categoryColors = categories.stream()
                .collect(Collectors.toMap(CostCategories::getCategoryName, CostCategories::getColorCode));

        // 4. 转换成本明细为DTO
        List<CostItemDTO> costItems = costBreakdowns.stream()
                .map(cb -> new CostItemDTO(
                        cb.getCostCategory(),
                        cb.getCostAmount(),
                        categoryColors.getOrDefault(cb.getCostCategory(), "#5470c6")
                ))
                .collect(Collectors.toList());

        // 5. 获取月度趋势数据
        List<MonthlyTrendDTO> trends = getTrends(periodType);

        // 6. 构建并返回最终DTO
        return new FinancialSummaryDTO(
                financialData.getTotalRevenue(),
                financialData.getTotalCost(),
                costItems,
                trends
        );
    }

    private List<MonthlyTrendDTO> getTrends(String periodType) {
        int currentYear = LocalDate.now().getYear();
        int currentMonth = LocalDate.now().getMonthValue();

        int startMonth;
        int endMonth;

        switch (periodType) {
            case "month":
                // 显示近7个月趋势
                startMonth = Math.max(1, currentMonth - 6);
                endMonth = currentMonth;
                break;
            case "quarter":
            case "year":
                // 显示当年每月趋势
                startMonth = 1;
                endMonth = currentMonth;
                break;
            default:
                return Collections.emptyList();
        }

        List<MonthlyFinancialTrends> trends = monthlyTrendsMapper
                .findTrendsByYearAndMonthRange(currentYear, startMonth, endMonth);

        String[] monthNames = {"1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"};

        return trends.stream()
                .map(t -> new MonthlyTrendDTO(
                        monthNames[t.getMonth() - 1],
                        t.getRevenue(),
                        t.getCost()
                ))
                .collect(Collectors.toList());
    }

    private boolean isValidPeriodType(String periodType) {
        return "month".equals(periodType) ||
                "quarter".equals(periodType) ||
                "year".equals(periodType);
    }

    private String getPeriodTypeDisplay(String periodType) {
        switch (periodType) {
            case "month": return "本月";
            case "quarter": return "本季度";
            case "year": return "本年度";
            default: return periodType;
        }
    }
}