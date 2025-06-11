package com.webtab.shecpsims.model.dto.bigdata;

import lombok.Data;

@Data
public class TrendDTO {
    private String text;
    private String type;  // "up" or "down" or null

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}