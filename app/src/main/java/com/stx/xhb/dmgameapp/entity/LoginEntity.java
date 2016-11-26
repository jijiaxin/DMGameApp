package com.stx.xhb.dmgameapp.entity;

import java.io.Serializable;

/**
 * Created by pkxutao on 2016/11/26.
 */

public class LoginEntity implements Serializable {


    /**
     * signal : 1
     * msg :
     * data : {"token":"2-2064978be85578e114dfdaea-7f0d2d57b35090dee89947a1","uid":"10046978","isActiveEmail":"1","isRealnameSys":"0","isCommercial":"0","isGender":"0","isActivePhone":"0","isLocked":"0","isAudit":"0","u":"eb7d561aefab690105dc9cdc9fda6f2992aad74509bc030c5577d4f4f4ef3f66fd566ac3482b349d5c80b971a4cafa123affb6c3e0cf39bbfdc059a79d944a26f3e36dd48fc9002f5708bf7507cd4937165082faf3a3c12d1692e02e008fd84f"}
     */

    private int signal;
    private String msg;
    private DataEntity data;

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

    public static class DataEntity {
        /**
         * token : 2-2064978be85578e114dfdaea-7f0d2d57b35090dee89947a1
         * uid : 10046978
         * isActiveEmail : 1
         * isRealnameSys : 0
         * isCommercial : 0
         * isGender : 0
         * isActivePhone : 0
         * isLocked : 0
         * isAudit : 0
         * u : eb7d561aefab690105dc9cdc9fda6f2992aad74509bc030c5577d4f4f4ef3f66fd566ac3482b349d5c80b971a4cafa123affb6c3e0cf39bbfdc059a79d944a26f3e36dd48fc9002f5708bf7507cd4937165082faf3a3c12d1692e02e008fd84f
         */

        private String token;
        private String uid;
        private String isActiveEmail;
        private String isRealnameSys;
        private String isCommercial;
        private String isGender;
        private String isActivePhone;
        private String isLocked;
        private String isAudit;
        private String u;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getIsActiveEmail() {
            return isActiveEmail;
        }

        public void setIsActiveEmail(String isActiveEmail) {
            this.isActiveEmail = isActiveEmail;
        }

        public String getIsRealnameSys() {
            return isRealnameSys;
        }

        public void setIsRealnameSys(String isRealnameSys) {
            this.isRealnameSys = isRealnameSys;
        }

        public String getIsCommercial() {
            return isCommercial;
        }

        public void setIsCommercial(String isCommercial) {
            this.isCommercial = isCommercial;
        }

        public String getIsGender() {
            return isGender;
        }

        public void setIsGender(String isGender) {
            this.isGender = isGender;
        }

        public String getIsActivePhone() {
            return isActivePhone;
        }

        public void setIsActivePhone(String isActivePhone) {
            this.isActivePhone = isActivePhone;
        }

        public String getIsLocked() {
            return isLocked;
        }

        public void setIsLocked(String isLocked) {
            this.isLocked = isLocked;
        }

        public String getIsAudit() {
            return isAudit;
        }

        public void setIsAudit(String isAudit) {
            this.isAudit = isAudit;
        }

        public String getU() {
            return u;
        }

        public void setU(String u) {
            this.u = u;
        }
    }
}
