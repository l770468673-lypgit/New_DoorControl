package com.doorcontrol.ruili.my.doorcontrol.restful.model;

import java.util.List;

/**
 * @
 * @类名称: ${}
 * @类描述: ${type_name}
 * @创建人： Lyp
 * @创建时间：${date} ${time}
 * @备注：
 */
public class Bean_AddRecord extends ResponseHead {
    /**
     * data : [{"logId":5420,"logType":0,"deviceCode":"903000f10124a6c95ea4","phoneNumber":"15689123357","vosCode":"","cardCode":"","logTime":1487125790382,"vistorPicUrl":"http://121.42.139.223:8090/pic/1487125785623.jpg","strLogType":"APP开门","strLogTime":"2017-02-15 10:29:50"},{"logId":3492,"logType":2,"deviceCode":"901000f10124a6c96929","phoneNumber":"15689123357","vosCode":"","cardCode":"","logTime":1481530215386,"vistorPicUrl":"","strLogType":"来访拨打号码开门","strLogTime":"2016-12-12 16:10:15"},{"logId":3420,"logType":0,"deviceCode":"901000f10124a6c96aad","phoneNumber":"15689123357","vosCode":"","cardCode":"","logTime":1481341302582,"vistorPicUrl":"","strLogType":"APP开门","strLogTime":"2016-12-10 11:41:42"},{"logId":3419,"logType":0,"deviceCode":"901000f10124a6c96aad","phoneNumber":"15689123357","vosCode":"","cardCode":"","logTime":1481341302481,"vistorPicUrl":"","strLogType":"APP开门","strLogTime":"2016-12-10 11:41:42"},{"logId":3418,"logType":0,"deviceCode":"901000f10124a6c96aad","phoneNumber":"15689123357","vosCode":"","cardCode":"","logTime":1481341302381,"vistorPicUrl":"","strLogType":"APP开门","strLogTime":"2016-12-10 11:41:42"},{"logId":3417,"logType":0,"deviceCode":"901000f10124a6c96aad","phoneNumber":"15689123357","vosCode":"","cardCode":"","logTime":1481341302302,"vistorPicUrl":"","strLogType":"APP开门","strLogTime":"2016-12-10 11:41:42"},{"logId":3366,"logType":0,"deviceCode":"901000f10124a6c96929","phoneNumber":"15689123357","vosCode":"","cardCode":"","logTime":1481189378709,"vistorPicUrl":"","strLogType":"APP开门","strLogTime":"2016-12-08 17:29:38"},{"logId":3352,"logType":0,"deviceCode":"901000f10124a6c96929","phoneNumber":"15689123357","vosCode":"","cardCode":"","logTime":1481178526190,"vistorPicUrl":"","strLogType":"APP开门","strLogTime":"2016-12-08 14:28:46"},{"logId":3324,"logType":0,"deviceCode":"901000f10124a6c96aad","phoneNumber":"15689123357","vosCode":"","cardCode":"","logTime":1481164904871,"vistorPicUrl":"","strLogType":"APP开门","strLogTime":"2016-12-08 10:41:44"},{"logId":3287,"logType":0,"deviceCode":"901000f10124a6c96929","phoneNumber":"15689123357","vosCode":"","cardCode":"","logTime":1481009164410,"vistorPicUrl":"","strLogType":"APP开门","strLogTime":"2016-12-06 15:26:04"},{"logId":3286,"logType":0,"deviceCode":"901000f10124a6c96929","phoneNumber":"15689123357","vosCode":"","cardCode":"","logTime":1481009162722,"vistorPicUrl":"","strLogType":"APP开门","strLogTime":"2016-12-06 15:26:02"},{"logId":3285,"logType":0,"deviceCode":"901000f10124a6c96aad","phoneNumber":"15689123357","vosCode":"","cardCode":"","logTime":1481006088152,"vistorPicUrl":"","strLogType":"APP开门","strLogTime":"2016-12-06 14:34:48"},{"logId":3279,"logType":0,"deviceCode":"901000f10124a6c96929","phoneNumber":"15689123357","vosCode":"","cardCode":"","logTime":1481005660126,"vistorPicUrl":"","strLogType":"APP开门","strLogTime":"2016-12-06 14:27:40"},{"logId":3168,"logType":0,"deviceCode":"903000f10124a6c96d46","phoneNumber":"15689123357","vosCode":"","cardCode":"","logTime":1480668556547,"vistorPicUrl":"","strLogType":"APP开门","strLogTime":"2016-12-02 16:49:16"},{"logId":3167,"logType":0,"deviceCode":"903000f10124a6c96d46","phoneNumber":"15689123357","vosCode":"","cardCode":"","logTime":1480668556539,"vistorPicUrl":"","strLogType":"APP开门","strLogTime":"2016-12-02 16:49:16"},{"logId":3166,"logType":0,"deviceCode":"903000f10124a6c96d46","phoneNumber":"15689123357","vosCode":"","cardCode":"","logTime":1480668556279,"vistorPicUrl":"","strLogType":"APP开门","strLogTime":"2016-12-02 16:49:16"},{"logId":3165,"logType":0,"deviceCode":"903000f10124a6c96d46","phoneNumber":"15689123357","vosCode":"","cardCode":"","logTime":1480668555971,"vistorPicUrl":"","strLogType":"APP开门","strLogTime":"2016-12-02 16:49:15"},{"logId":3142,"logType":0,"deviceCode":"901000f10124a6c96aad","phoneNumber":"15689123357","vosCode":"","cardCode":"","logTime":1480643173497,"vistorPicUrl":"","strLogType":"APP开门","strLogTime":"2016-12-02 09:46:13"},{"logId":3141,"logType":0,"deviceCode":"901000f10124a6c96aad","phoneNumber":"15689123357","vosCode":"","cardCode":"","logTime":1480643173472,"vistorPicUrl":"","strLogType":"APP开门","strLogTime":"2016-12-02 09:46:13"},{"logId":3140,"logType":0,"deviceCode":"901000f10124a6c96aad","phoneNumber":"15689123357","vosCode":"","cardCode":"","logTime":1480643173428,"vistorPicUrl":"","strLogType":"APP开门","strLogTime":"2016-12-02 09:46:13"}]
     * totalCount : 0
     */

    private int totalCount;
    /**
     * logId : 5420
     * logType : 0
     * deviceCode : 903000f10124a6c95ea4
     * phoneNumber : 15689123357
     * vosCode :
     * cardCode :
     * logTime : 1487125790382
     * vistorPicUrl : http://121.42.139.223:8090/pic/1487125785623.jpg
     * strLogType : APP开门
     * strLogTime : 2017-02-15 10:29:50
     */

    private List<DataBean> data;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private int logId;
        private int logType;
        private String deviceCode;
        private String phoneNumber;
        private String vosCode;
        private String cardCode;
        private long logTime;
        private String vistorPicUrl;
        private String strLogType;
        private String strLogTime;

        public int getLogId() {
            return logId;
        }

        public void setLogId(int logId) {
            this.logId = logId;
        }

        public int getLogType() {
            return logType;
        }

        public void setLogType(int logType) {
            this.logType = logType;
        }

        public String getDeviceCode() {
            return deviceCode;
        }

        public void setDeviceCode(String deviceCode) {
            this.deviceCode = deviceCode;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getVosCode() {
            return vosCode;
        }

        public void setVosCode(String vosCode) {
            this.vosCode = vosCode;
        }

        public String getCardCode() {
            return cardCode;
        }

        public void setCardCode(String cardCode) {
            this.cardCode = cardCode;
        }

        public long getLogTime() {
            return logTime;
        }

        public void setLogTime(long logTime) {
            this.logTime = logTime;
        }

        public String getVistorPicUrl() {
            return vistorPicUrl;
        }

        public void setVistorPicUrl(String vistorPicUrl) {
            this.vistorPicUrl = vistorPicUrl;
        }

        public String getStrLogType() {
            return strLogType;
        }

        public void setStrLogType(String strLogType) {
            this.strLogType = strLogType;
        }

        public String getStrLogTime() {
            return strLogTime;
        }

        public void setStrLogTime(String strLogTime) {
            this.strLogTime = strLogTime;
        }
    }
    //
//    private int totalCount;
//    /**
//     * logId : 3013
//     * logType : 0
//     * deviceCode : 901000f10124a6c96aad
//     * phoneNumber : 15166689550
//     * vosCode :
//     * cardCode :
//     * logTime : 1480506572124
//     * strLogType : APP开门
//     * strLogTime : 2016-11-30 19:49:32
//     */
//
//    private List<DataBean> data;
//
//    public int getTotalCount() {
//        return totalCount;
//    }
//
//    public void setTotalCount(int totalCount) {
//        this.totalCount = totalCount;
//    }
//
//    public List<DataBean> getData() {
//        return data;
//    }
//
//    public void setData(List<DataBean> data) {
//        this.data = data;
//    }
//
//    public static class DataBean {
//        private int logId;
//        private int logType;
//        private String deviceCode;
//        private String phoneNumber;
//        private String vosCode;
//        private String cardCode;
//        private long logTime;
//        private String strLogType;
//        private String strLogTime;
//
//        public int getLogId() {
//            return logId;
//        }
//
//        public void setLogId(int logId) {
//            this.logId = logId;
//        }
//
//        public int getLogType() {
//            return logType;
//        }
//
//        public void setLogType(int logType) {
//            this.logType = logType;
//        }
//
//        public String getDeviceCode() {
//            return deviceCode;
//        }
//
//        public void setDeviceCode(String deviceCode) {
//            this.deviceCode = deviceCode;
//        }
//
//        public String getPhoneNumber() {
//            return phoneNumber;
//        }
//
//        public void setPhoneNumber(String phoneNumber) {
//            this.phoneNumber = phoneNumber;
//        }
//
//        public String getVosCode() {
//            return vosCode;
//        }
//
//        public void setVosCode(String vosCode) {
//            this.vosCode = vosCode;
//        }
//
//        public String getCardCode() {
//            return cardCode;
//        }
//
//        public void setCardCode(String cardCode) {
//            this.cardCode = cardCode;
//        }
//
//        public long getLogTime() {
//            return logTime;
//        }
//
//        public void setLogTime(long logTime) {
//            this.logTime = logTime;
//        }
//
//        public String getStrLogType() {
//            return strLogType;
//        }
//
//        public void setStrLogType(String strLogType) {
//            this.strLogType = strLogType;
//        }
//
//        public String getStrLogTime() {
//            return strLogTime;
//        }
//
//        public void setStrLogTime(String strLogTime) {
//            this.strLogTime = strLogTime;
//        }
//    }


}
