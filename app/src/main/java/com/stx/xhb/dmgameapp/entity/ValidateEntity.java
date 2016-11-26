package com.stx.xhb.dmgameapp.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pkxutao on 2016/11/26.
 */

public class ValidateEntity implements Serializable{


    /**
     * signal : 1
     * msg :
     * errors : []
     * data : {"leftSeconds":120}
     */

    private int signal;
    private String msg;
    private DataEntity data;
    private List<?> errors;

    public int getSignal() {
        return signal;
    }

    public void setSignal(int signal) {
        this.signal = signal;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public List<?> getErrors() {
        return errors;
    }

    public void setErrors(List<?> errors) {
        this.errors = errors;
    }

    public static class DataEntity {
        /**
         * leftSeconds : 120
         */

        private int leftSeconds;

        public int getLeftSeconds() {
            return leftSeconds;
        }

        public void setLeftSeconds(int leftSeconds) {
            this.leftSeconds = leftSeconds;
        }
    }
}
