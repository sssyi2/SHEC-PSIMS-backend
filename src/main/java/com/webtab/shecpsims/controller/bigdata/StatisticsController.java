package com.webtab.shecpsims.controller.bigdata;

import com.webtab.shecpsims.common.BaseResponse;
import com.webtab.shecpsims.common.ResultUtils;
import com.webtab.shecpsims.model.dto.bigdata.StatisticsDTO;
import com.webtab.shecpsims.service.bigdata.StatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    private static final Logger logger = LoggerFactory.getLogger(StatisticsController.class);

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/data")
    public BaseResponse<StatisticsDTO> getStatisticsData(
            @RequestParam(value = "period", defaultValue = "week") String period) {
        try {
            logger.info("获取统计数据，周期: {}", period);
            StatisticsDTO statisticsData = statisticsService.getStatisticsData(period);
            return ResultUtils.success(statisticsData);
        } catch (Exception e) {
            logger.error("获取统计数据失败", e);
            return ResultUtils.error(-1, "获取统计数据失败: " + e.getMessage());
        }
    }
}