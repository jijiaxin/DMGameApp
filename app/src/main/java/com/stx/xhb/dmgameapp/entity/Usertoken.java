package com.stx.xhb.dmgameapp.entity;

/**
 * Created by hasty on 16/11/5.
 */
public class UserToken {
    private int signal;
    private String msg;
    // private String errors;
    private Results data;

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

   /* public String getErrors() {
        return errors;
    }
    public void setErrors(String errors) {
        this.errors = errors;
    }*/

    public Results getData() {
        return data;
    }
    public void setData(Results data) {
        this.data = data;
    }

    public class Results {

        private String GUID;
        public String getGUID()
        {
            return GUID;
        }
        public void setGUID(String GUID)
        {
            this.GUID=GUID;
        }

        @Override
        public String toString(){
            return "data:[GUID=" +  GUID
                    +"]";
        }
    }

    @Override
    public String toString(){

        return "Usertoken:[signal=" + signal
                + ", msg=" + msg
                +", data=" + data
                +"]";
    }
}
