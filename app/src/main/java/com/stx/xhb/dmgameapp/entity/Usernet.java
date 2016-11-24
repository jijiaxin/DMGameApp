package com.stx.xhb.dmgameapp.entity;

import java.util.List;

/**
 * Created by hasty on 16/11/5.
 */
public class Usernet {
    private int signal;
    private String msg;
   // private String errors;
    private List<Results> data;

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

    public List<Results> getData() {
        return data;
    }
    public void setData(List<Results> data) {
        this.data = data;
    }

    public class Results {
        private String token;
        private String uid;
        private String isLocked;
        private String isAudit;
        private String leftSeconds;
        private String GUID;

        public String getToken()
        {
            return token;
        }
        public void setToken(String token)
        {
            this.token=token;
        }

        public String getUid()
        {
            return uid;
        }
        public void setUid(String uid)
        {
            this.uid=uid;
        }

        public String getIsLocked()
        {
            return isLocked;
        }
        public void setIsLocked(String isLocked)
        {
            this.isLocked=isLocked;
        }

        public String getIsAudit()
        {
            return isAudit;
        }
        public void setIsAudit(String isAudit)
        {
            this.isAudit=isAudit;
        }

        public String getLeftSeconds()
        {
            return leftSeconds;
        }
        public void setLeftSeconds(String leftSeconds)
        {
            this.leftSeconds=leftSeconds;
        }

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
            return "data:[token=" + token
                    + ", uid=" + uid
                    +", isLocked=" + isLocked
                    +", isAudit=" + isAudit
                    +",GUID=" + GUID
                    +"]";
        }
    }

    @Override
    public String toString(){

        return "Usernet:[signal=" + signal
                + ", msg=" + msg
                +", data=" + data
                +"]";
    }
}
