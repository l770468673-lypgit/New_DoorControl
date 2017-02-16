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
public class Bean_message extends ResponseHead {


    /**
     * data : [{"noticeId":27,"noticeType":1,"noticeTitle":"业主通知","noticeInfo":"业主通知业主通知业主通知业主通知业主通知","targetId":192,"noticeStatus":1,"communityId":8}]
     * totalCount : 0
     */

    private int totalCount;
    /**
     * noticeId : 27
     * noticeType : 1
     * noticeTitle : 业主通知
     * noticeInfo : 业主通知业主通知业主通知业主通知业主通知
     * targetId : 192
     * noticeStatus : 1
     * communityId : 8
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
        private int targetId;
        private int noticeStatus;
        private int communityId;

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

        public int getTargetId() {
            return targetId;
        }

        public void setTargetId(int targetId) {
            this.targetId = targetId;
        }

        public int getNoticeStatus() {
            return noticeStatus;
        }

        public void setNoticeStatus(int noticeStatus) {
            this.noticeStatus = noticeStatus;
        }

        public int getCommunityId() {
            return communityId;
        }

        public void setCommunityId(int communityId) {
            this.communityId = communityId;
        }
    }
}
