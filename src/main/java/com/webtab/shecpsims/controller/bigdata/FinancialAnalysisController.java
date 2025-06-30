package com.webtab.shecpsims.controller.bigdata;

import com.webtab.shecpsims.common.BaseResponse;
import com.webtab.shecpsims.common.ErrorCode;
import com.webtab.shecpsims.common.ResultUtils;
import com.webtab.shecpsims.model.dto.bigdata.FinancialSummaryDTO;
import com.webtab.shecpsims.service.bigdata.FinancialAnalysisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/finance")
public class FinancialAnalysisController {

    private static final Logger logger = LoggerFactory.getLogger(FinancialAnalysisController.class);

    @Autowired
    private FinancialAnalysisService financialAnalysisService;

    @GetMapping("/summary")
    public BaseResponse<FinancialSummaryDTO> getFinancialSummary(
            @RequestParam(value = "periodType", defaultValue = "month") String periodType) {

        try {
            FinancialSummaryDTO summary = financialAnalysisService.getFinancialSummary(periodType);
            return ResultUtils.success(summary);
        } catch (Exception e) {
            logger.error("获取财务摘要失败", e);
            return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "获取财务摘要失败: " + e.getMessage());
        }
    }
}