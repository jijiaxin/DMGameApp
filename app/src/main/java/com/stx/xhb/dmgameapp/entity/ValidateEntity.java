package com.stx.xhb.dmgameapp.entity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Iterator;

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

//    public DataEntity getData() {
//        return data;
//    }
//
//    public void setData(DataEntity data) {
//        this.data = data;
//    }
//
//    public List<?> getErrors() {
//        return errors;
//    }
//
//    public void setErrors(List<?> errors) {
//        this.errors = errors;
//    }

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

    public static String getErroMsg(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        if (jsonObject.has("errors") && jsonObject.get("errors") != null){
            JSONObject erro =  jsonObject.getJSONObject("errors");
            Iterator<String> keys = erro.keys();
            while(keys.hasNext()) {
                return erro.getString(keys.next()).toString();
            }
        }
        return null;
    }
}
