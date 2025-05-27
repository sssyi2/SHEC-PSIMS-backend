package com.webtab.shecpsims.model.entity.user;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author taojianbing
 * @version V1.0
 * Copyright (c) 2025, taojianbing@hwadee.com All Rights Reserved.
 * @ProjectName:SSM01
 * @Title: R
 * @Package com.hwadee.entity
 * @Description: 统一响应数据格式
 * @date 2025/4/10 11:46
 */
@Data
@ToString
public class R implements Serializable {

//    private int code;
//    private String msg;
//    private Object data;
    public int code;
    public String msg;
    public Object data;

    public static R ok() {
        R r = new R();
        r.code = 200;
        return r;
    }

    public static R error(int code) {
        R r = new R();
        r.code = code;
        return r;
    }

    public R msg(String msg) {
        this.msg = msg;
        return this;
    }

    public R data(Object data) {
        this.data = data;
        return this;
    }


}
