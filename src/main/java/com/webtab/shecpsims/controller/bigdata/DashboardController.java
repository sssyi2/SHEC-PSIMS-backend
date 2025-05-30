package com.webtab.shecpsims.controller.bigdata;


import com.webtab.shecpsims.model.dto.bigdata.StatCardDTO;
import com.webtab.shecpsims.model.dto.bigdata.StatsCardsResponseDTO;
import com.webtab.shecpsims.service.bigdata.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/stats")
    public StatsCardsResponseDTO getStatCards() {
        try {
            List<StatCardDTO> cards = dashboardService.getStatCards();
            return StatsCardsResponseDTO.success(cards);
        } catch (Exception e) {
            return StatsCardsResponseDTO.error("获取统计数据失败: " + e.getMessage());
        }
    }
}