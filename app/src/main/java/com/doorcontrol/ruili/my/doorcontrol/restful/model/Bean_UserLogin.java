package com.doorcontrol.ruili.my.doorcontrol.restful.model;

/**
 * @
 * @类名称: ${}
 * @类描述: ${type_name}
 * @创建人： Lyp
 * @创建时间：${date} ${time}
 * @备注：
 */
public class Bean_UserLogin extends ResponseHead {


    /**
     * "userInfo": {
     * "nickName": "",
     * "token": ""
     * },
     * "vosInfo": {
     * "vosCode": "101",
     * "vosPassword": "111111",
     * "vosGateWay": "127.0.0.1"
     * }
     */
    public static class UserInfo {
        public String nickName;
        public String token;
        @Override
        public String toString() {
            // TODO Auto-generated method stub
            return super.toString();
        }
    }
    public UserInfo userInfo;

    public static class VosInfo {
        public String vosCode;
        public String vosPassword;
        public String vosGateWay;
        @Override
        public String toString() {
            // TODO Auto-generated method stub
            return super.toString();
        }
    }
    public VosInfo vosInfo;


}
