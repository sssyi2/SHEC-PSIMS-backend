package com.webtab.shecpsims.model.entity.elderlyhealth;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class R {

    private int code;
    private String msg;
    private Object data;

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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}