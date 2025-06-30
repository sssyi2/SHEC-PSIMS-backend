package com.webtab.shecpsims.model.dto.bigdata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinancialSummaryDTO {
    private BigDecimal totalRevenue;
    private BigDecimal totalCost;
    private List<CostItemDTO> costBreakdown;
    private List<MonthlyTrendDTO> trends;

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public List<CostItemDTO> getCostBreakdown() {
        return costBreakdown;
    }

    public void setCostBreakdown(List<CostItemDTO> costBreakdown) {
        this.costBreakdown = costBreakdown;
    }

    public List<MonthlyTrendDTO> getTrends() {
        return trends;
    }

    public void setTrends(List<MonthlyTrendDTO> trends) {
        this.trends = trends;
    }
}