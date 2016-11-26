package com.stx.xhb.dmgameapp.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pkxutao on 2016/11/26.
 */

public class UserEntity implements Serializable {


    /**
     * signal : 1
     * msg : 操作成功。
     * data : {"memberId":"10046978","homeVerCode":"0","cfgVerCode":"0","newMsgNum":0,"regDate":"1480153840","favNewsNum":0,"username":"ddnddn","nickname":"ddnddn","avatar":{"prefix":"http://u.skykiwi.com/data/avatar/","dir":"010/04/69/","name":"78","namePostfix":"_avatar_","ext":"jpg"},"cmtCounter":"0","shareCounter":"0","shareGrade":0,"grade":"0","updateGrade":0,"levelInfo":"天维会员","regMethod":"0","age":0,"distance":0,"isGentle":0,"signature":"","telephone":"","birthday":"","label":[],"resume":""}
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
         * memberId : 10046978
         * homeVerCode : 0
         * cfgVerCode : 0
         * newMsgNum : 0
         * regDate : 1480153840
         * favNewsNum : 0
         * username : ddnddn
         * nickname : ddnddn
         * avatar : {"prefix":"http://u.skykiwi.com/data/avatar/","dir":"010/04/69/","name":"78","namePostfix":"_avatar_","ext":"jpg"}
         * cmtCounter : 0
         * shareCounter : 0
         * shareGrade : 0
         * grade : 0
         * updateGrade : 0
         * levelInfo : 天维会员
         * regMethod : 0
         * age : 0
         * distance : 0
         * isGentle : 0
         * signature :
         * telephone :
         * birthday :
         * label : []
         * resume :
         */

        private String memberId;
        private String homeVerCode;
        private String cfgVerCode;
        private int newMsgNum;
        private String regDate;
        private int favNewsNum;
        private String username;
        private String nickname;
        private AvatarEntity avatar;
        private String cmtCounter;
        private String shareCounter;
        private int shareGrade;
        private String grade;
        private int updateGrade;
        private String levelInfo;
        private String regMethod;
        private int age;
        private int distance;
        private int isGentle;
        private String signature;
        private String telephone;
        private String birthday;
        private String resume;
        private List<?> label;

        public String getMemberId() {
            return memberId;
        }

        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }

        public String getHomeVerCode() {
            return homeVerCode;
        }

        public void setHomeVerCode(String homeVerCode) {
            this.homeVerCode = homeVerCode;
        }

        public String getCfgVerCode() {
            return cfgVerCode;
        }

        public void setCfgVerCode(String cfgVerCode) {
            this.cfgVerCode = cfgVerCode;
        }

        public int getNewMsgNum() {
            return newMsgNum;
        }

        public void setNewMsgNum(int newMsgNum) {
            this.newMsgNum = newMsgNum;
        }

        public String getRegDate() {
            return regDate;
        }

        public void setRegDate(String regDate) {
            this.regDate = regDate;
        }

        public int getFavNewsNum() {
            return favNewsNum;
        }

        public void setFavNewsNum(int favNewsNum) {
            this.favNewsNum = favNewsNum;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public AvatarEntity getAvatar() {
            return avatar;
        }

        public void setAvatar(AvatarEntity avatar) {
            this.avatar = avatar;
        }

        public String getCmtCounter() {
            return cmtCounter;
        }

        public void setCmtCounter(String cmtCounter) {
            this.cmtCounter = cmtCounter;
        }

        public String getShareCounter() {
            return shareCounter;
        }

        public void setShareCounter(String shareCounter) {
            this.shareCounter = shareCounter;
        }

        public int getShareGrade() {
            return shareGrade;
        }

        public void setShareGrade(int shareGrade) {
            this.shareGrade = shareGrade;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public int getUpdateGrade() {
            return updateGrade;
        }

        public void setUpdateGrade(int updateGrade) {
            this.updateGrade = updateGrade;
        }

        public String getLevelInfo() {
            return levelInfo;
        }

        public void setLevelInfo(String levelInfo) {
            this.levelInfo = levelInfo;
        }

        public String getRegMethod() {
            return regMethod;
        }

        public void setRegMethod(String regMethod) {
            this.regMethod = regMethod;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public int getIsGentle() {
            return isGentle;
        }

        public void setIsGentle(int isGentle) {
            this.isGentle = isGentle;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getResume() {
            return resume;
        }

        public void setResume(String resume) {
            this.resume = resume;
        }

        public List<?> getLabel() {
            return label;
        }

        public void setLabel(List<?> label) {
            this.label = label;
        }

        public static class AvatarEntity {
            /**
             * prefix : http://u.skykiwi.com/data/avatar/
             * dir : 010/04/69/
             * name : 78
             * namePostfix : _avatar_
             * ext : jpg
             */

            private String prefix;
            private String dir;
            private String name;
            private String namePostfix;
            private String ext;

            public String getPrefix() {
                return prefix;
            }

            public void setPrefix(String prefix) {
                this.prefix = prefix;
            }

            public String getDir() {
                return dir;
            }

            public void setDir(String dir) {
                this.dir = dir;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNamePostfix() {
                return namePostfix;
            }

            public void setNamePostfix(String namePostfix) {
                this.namePostfix = namePostfix;
            }

            public String getExt() {
                return ext;
            }

            public void setExt(String ext) {
                this.ext = ext;
            }
        }
    }
}
