package com.webtab.shecpsims.service.bigdata;

import com.webtab.shecpsims.model.dto.bigdata.FinancialSummaryDTO;

public interface FinancialAnalysisService {

    /**
     * 获取特定周期的财务摘要数据
     *
     * @param periodType 周期类型：month, quarter, year
     * @return 财务摘要数据
     */
    FinancialSummaryDTO getFinancialSummary(String periodType);
}