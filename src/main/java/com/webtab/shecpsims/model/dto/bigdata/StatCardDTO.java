package com.webtab.shecpsims.model.dto.bigdata;

import lombok.Data;

@Data
public class StatCardDTO {
    private String title;
    private String value;
    private TrendDTO trend;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public TrendDTO getTrend() {
        return trend;
    }

    public void setTrend(TrendDTO trend) {
        this.trend = trend;
    }
}