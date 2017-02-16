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
public class Bean_listNews extends  ResponseHead {


    /**
     * data : [{"noticeId":1,"noticeType":0,"noticeTitle":"11","noticeInfo":"11","target":""},{"noticeId":2,"noticeType":1,"noticeTitle":"2224","noticeInfo":"2224","target":"101"}]
     * totalCount : 2
     */

    private int totalCount;
    /**
     * noticeId : 1
     * noticeType : 0
     * noticeTitle : 11
     * noticeInfo : 11
     * target :
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
        private int noticeId;
        private int noticeType;
        private String noticeTitle;
        private String noticeInfo;
        private String target;

        public int getNoticeId() {
            return noticeId;
        }

        public void setNoticeId(int noticeId) {
            this.noticeId = noticeId;
        }

        public int getNoticeType() {
            return noticeType;
        }

        public void setNoticeType(int noticeType) {
            this.noticeType = noticeType;
        }

        public String getNoticeTitle() {
            return noticeTitle;
        }

        public void setNoticeTitle(String noticeTitle) {
            this.noticeTitle = noticeTitle;
        }

        public String getNoticeInfo() {
            return noticeInfo;
        }

        public void setNoticeInfo(String noticeInfo) {
            this.noticeInfo = noticeInfo;
        }

        public String getTarget() {
            return target;
        }

        public void setTarget(String target) {
            this.target = target;
        }
    }
}
