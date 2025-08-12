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
            // 当数据库中没有数据时，返回默认数据
            return getDefaultFinancialSummary(periodType);
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

    /**
     * 当数据库中没有数据时，返回默认的财务摘要数据
     */
    private FinancialSummaryDTO getDefaultFinancialSummary(String periodType) {
        List<CostItemDTO> defaultCostItems;
        List<MonthlyTrendDTO> defaultTrends;
        java.math.BigDecimal totalRevenue;
        java.math.BigDecimal totalCost;

        switch (periodType) {
            case "month":
                totalRevenue = new java.math.BigDecimal("1250000");
                totalCost = new java.math.BigDecimal("850000");
                defaultCostItems = java.util.Arrays.asList(
                    new CostItemDTO("人力成本", new java.math.BigDecimal("480000"), "#5470c6"),
                    new CostItemDTO("设备投入", new java.math.BigDecimal("180000"), "#91cc75"),
                    new CostItemDTO("药品耗材", new java.math.BigDecimal("110000"), "#fac858"),
                    new CostItemDTO("其他", new java.math.BigDecimal("80000"), "#ee6666")
                );
                defaultTrends = java.util.Arrays.asList(
                    new MonthlyTrendDTO("1月", new java.math.BigDecimal("980000"), new java.math.BigDecimal("750000")),
                    new MonthlyTrendDTO("2月", new java.math.BigDecimal("1050000"), new java.math.BigDecimal("790000")),
                    new MonthlyTrendDTO("3月", new java.math.BigDecimal("1120000"), new java.math.BigDecimal("810000")),
                    new MonthlyTrendDTO("4月", new java.math.BigDecimal("1080000"), new java.math.BigDecimal("820000")),
                    new MonthlyTrendDTO("5月", new java.math.BigDecimal("1150000"), new java.math.BigDecimal("830000")),
                    new MonthlyTrendDTO("6月", new java.math.BigDecimal("1200000"), new java.math.BigDecimal("840000")),
                    new MonthlyTrendDTO("7月", new java.math.BigDecimal("1250000"), new java.math.BigDecimal("850000"))
                );
                break;
            case "quarter":
                totalRevenue = new java.math.BigDecimal("3450000");
                totalCost = new java.math.BigDecimal("2460000");
                defaultCostItems = java.util.Arrays.asList(
                    new CostItemDTO("人力成本", new java.math.BigDecimal("1320000"), "#5470c6"),
                    new CostItemDTO("设备投入", new java.math.BigDecimal("510000"), "#91cc75"),
                    new CostItemDTO("药品耗材", new java.math.BigDecimal("370000"), "#fac858"),
                    new CostItemDTO("其他", new java.math.BigDecimal("250000"), "#ee6666")
                );
                defaultTrends = java.util.Arrays.asList(
                    new MonthlyTrendDTO("4月", new java.math.BigDecimal("1080000"), new java.math.BigDecimal("820000")),
                    new MonthlyTrendDTO("5月", new java.math.BigDecimal("1150000"), new java.math.BigDecimal("830000")),
                    new MonthlyTrendDTO("6月", new java.math.BigDecimal("1200000"), new java.math.BigDecimal("840000"))
                );
                break;
            case "year":
                totalRevenue = new java.math.BigDecimal("14200000");
                totalCost = new java.math.BigDecimal("9800000");
                defaultCostItems = java.util.Arrays.asList(
                    new CostItemDTO("人力成本", new java.math.BigDecimal("5500000"), "#5470c6"),
                    new CostItemDTO("设备投入", new java.math.BigDecimal("2100000"), "#91cc75"),
                    new CostItemDTO("药品耗材", new java.math.BigDecimal("1400000"), "#fac858"),
                    new CostItemDTO("其他", new java.math.BigDecimal("800000"), "#ee6666")
                );
                defaultTrends = java.util.Arrays.asList(
                    new MonthlyTrendDTO("1月", new java.math.BigDecimal("980000"), new java.math.BigDecimal("750000")),
                    new MonthlyTrendDTO("2月", new java.math.BigDecimal("1050000"), new java.math.BigDecimal("790000")),
                    new MonthlyTrendDTO("3月", new java.math.BigDecimal("1120000"), new java.math.BigDecimal("810000")),
                    new MonthlyTrendDTO("4月", new java.math.BigDecimal("1080000"), new java.math.BigDecimal("820000")),
                    new MonthlyTrendDTO("5月", new java.math.BigDecimal("1150000"), new java.math.BigDecimal("830000")),
                    new MonthlyTrendDTO("6月", new java.math.BigDecimal("1200000"), new java.math.BigDecimal("840000")),
                    new MonthlyTrendDTO("7月", new java.math.BigDecimal("1250000"), new java.math.BigDecimal("850000"))
                );
                break;
            default:
                totalRevenue = new java.math.BigDecimal("1250000");
                totalCost = new java.math.BigDecimal("850000");
                defaultCostItems = java.util.Arrays.asList(
                    new CostItemDTO("人力成本", new java.math.BigDecimal("480000"), "#5470c6"),
                    new CostItemDTO("设备投入", new java.math.BigDecimal("180000"), "#91cc75"),
                    new CostItemDTO("药品耗材", new java.math.BigDecimal("110000"), "#fac858"),
                    new CostItemDTO("其他", new java.math.BigDecimal("80000"), "#ee6666")
                );
                defaultTrends = java.util.Arrays.asList(
                    new MonthlyTrendDTO("1月", new java.math.BigDecimal("980000"), new java.math.BigDecimal("750000")),
                    new MonthlyTrendDTO("2月", new java.math.BigDecimal("1050000"), new java.math.BigDecimal("790000")),
                    new MonthlyTrendDTO("3月", new java.math.BigDecimal("1120000"), new java.math.BigDecimal("810000"))
                );
        }

        return new FinancialSummaryDTO(totalRevenue, totalCost, defaultCostItems, defaultTrends);
    }
}