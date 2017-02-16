package com.doorcontrol.ruili.my.doorcontrol.bean;

/**
 * @
 * @类名称: ${}
 * @类描述: ${type_name}
 * @创建人： Lyp
 * @创建时间：${date} ${time}
 * @备注：
 */
public class Bean_Login {


    /**
     * resultCode : 0
     * userInfo : {"nickName":"","token":""}
     * vosInfo : {"vosCode":"101","vosPassword":"111111","vosGateWay":"127.0.0.1"}
     */

    private int resultCode;
    /**
     * nickName :
     * token :
     */

    private UserInfoBean userInfo;
    /**
     * vosCode : 101
     * vosPassword : 111111
     * vosGateWay : 127.0.0.1
     */

    private VosInfoBean vosInfo;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public VosInfoBean getVosInfo() {
        return vosInfo;
    }

    public void setVosInfo(VosInfoBean vosInfo) {
        this.vosInfo = vosInfo;
    }

    public static class UserInfoBean {
        private String nickName;
        private String token;

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        @Override
        public String toString() {
            return "UserInfoBean{" +
                    "nickName='" + nickName + '\'' +
                    ", token='" + token + '\'' +
                    '}';
        }
    }

    public static class VosInfoBean {
        private String vosCode;
        private String vosPassword;
        private String vosGateWay;

        public String getVosCode() {
            return vosCode;
        }

        public void setVosCode(String vosCode) {
            this.vosCode = vosCode;
        }

        public String getVosPassword() {
            return vosPassword;
        }

        public void setVosPassword(String vosPassword) {
            this.vosPassword = vosPassword;
        }

        public String getVosGateWay() {
            return vosGateWay;
        }

        public void setVosGateWay(String vosGateWay) {
            this.vosGateWay = vosGateWay;
        }

        @Override
        public String toString() {
            return "VosInfoBean{" +
                    "vosCode='" + vosCode + '\'' +
                    ", vosPassword='" + vosPassword + '\'' +
                    ", vosGateWay='" + vosGateWay + '\'' +
                    '}';
        }
    }
}
