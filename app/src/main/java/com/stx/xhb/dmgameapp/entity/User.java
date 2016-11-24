package com.stx.xhb.dmgameapp.entity;

/**
 * Created by hasty on 16/11/5.
 */
public class User {
    private String signal;
    private String msg;
    private String errors;
    private String data;

    public String getSignal() {
        return signal;
    }
    public void setSignal(String signal) {
        this.signal = signal;
    }

    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getErrors() {
        return errors;
    }
    public void setErrors(String errors) {
        this.errors = errors;
    }

    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

}
