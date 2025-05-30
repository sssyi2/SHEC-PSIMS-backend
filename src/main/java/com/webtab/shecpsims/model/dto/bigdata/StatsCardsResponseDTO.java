package com.webtab.shecpsims.model.dto.bigdata;

import lombok.Data;

import java.util.List;

@Data
public class StatsCardsResponseDTO {
    private Integer code;
    private String message;
    private Data data;

    @lombok.Data
    public static class Data {
        private List<StatCardDTO> cards;
    }

    public static StatsCardsResponseDTO success(List<StatCardDTO> cards) {
        StatsCardsResponseDTO response = new StatsCardsResponseDTO();
        response.setCode(200);
        response.setMessage("success");

        Data data = new Data();
        data.setCards(cards);
        response.setData(data);

        return response;
    }

    public static StatsCardsResponseDTO error(String message) {
        StatsCardsResponseDTO response = new StatsCardsResponseDTO();
        response.setCode(500);
        response.setMessage(message);
        return response;
    }
}