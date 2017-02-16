package com.doorcontrol.ruili.my.doorcontrol.restful.model;

import java.io.Serializable;
import java.util.List;

/**
 * @
 * @类名称: ${}
 * @类描述: ${type_name}
 * @创建人： Lyp
 * @创建时间：${date} ${time}
 * @备注：
 */
public class Bean_FamilyList extends ResponseHead  implements Serializable {
    /**
     * data : [{"house":{"houseId":175,"floorCode":9,"houseCode":8,"unitCode":1,"buildingId":20,"communityId":8,"houseName":"908","buildingName":"5","communityName":"易慧家"},"houseOwners":[{"houseOwnerId":112,"houseOwnerName":"王敏","phoneNumber":"13176397383","online":0,"seq":0}],"holder":true}]
     * totalCount : 0
     */

    private int totalCount;
    /**
     * house : {"houseId":175,"floorCode":9,"houseCode":8,"unitCode":1,"buildingId":20,"communityId":8,"houseName":"908","buildingName":"5","communityName":"易慧家"}
     * houseOwners : [{"houseOwnerId":112,"houseOwnerName":"王敏","phoneNumber":"13176397383","online":0,"seq":0}]
     * holder : true
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



    public static class DataBean  implements Serializable  {
        /**
         * houseId : 175
         * floorCode : 9
         * houseCode : 8
         * unitCode : 1
         * buildingId : 20
         * communityId : 8
         * houseName : 908
         * buildingName : 5
         * communityName : 易慧家
         */

        private HouseBean house;
        private boolean holder;
        /**
         * houseOwnerId : 112
         * houseOwnerName : 王敏
         * phoneNumber : 13176397383
         * online : 0
         * seq : 0
         */

        private List<HouseOwnersBean> houseOwners;

        public HouseBean getHouse() {
            return house;
        }

        public void setHouse(HouseBean house) {
            this.house = house;
        }

        public boolean isHolder() {
            return holder;
        }

        public void setHolder(boolean holder) {
            this.holder = holder;
        }

        public List<HouseOwnersBean> getHouseOwners() {
            return houseOwners;
        }

        public void setHouseOwners(List<HouseOwnersBean> houseOwners) {
            this.houseOwners = houseOwners;
        }

        public static class HouseBean implements Serializable{
            private int houseId;
            private int floorCode;
            private int houseCode;
            private int unitCode;
            private int buildingId;
            private int communityId;
            private String houseName;
            private String buildingName;
            private String communityName;

            public int getHouseId() {
                return houseId;
            }

            public void setHouseId(int houseId) {
                this.houseId = houseId;
            }

            public int getFloorCode() {
                return floorCode;
            }

            public void setFloorCode(int floorCode) {
                this.floorCode = floorCode;
            }

            public int getHouseCode() {
                return houseCode;
            }

            public void setHouseCode(int houseCode) {
                this.houseCode = houseCode;
            }

            public int getUnitCode() {
                return unitCode;
            }

            public void setUnitCode(int unitCode) {
                this.unitCode = unitCode;
            }

            public int getBuildingId() {
                return buildingId;
            }

            public void setBuildingId(int buildingId) {
                this.buildingId = buildingId;
            }

            public int getCommunityId() {
                return communityId;
            }

            public void setCommunityId(int communityId) {
                this.communityId = communityId;
            }

            public String getHouseName() {
                return houseName;
            }

            public void setHouseName(String houseName) {
                this.houseName = houseName;
            }

            public String getBuildingName() {
                return buildingName;
            }

            public void setBuildingName(String buildingName) {
                this.buildingName = buildingName;
            }

            public String getCommunityName() {
                return communityName;
            }

            public void setCommunityName(String communityName) {
                this.communityName = communityName;
            }
        }

        public static class HouseOwnersBean implements Serializable {
            private int houseOwnerId;
            private String houseOwnerName;
            private String phoneNumber;
            private int online;
            private int seq;

            public int getHouseOwnerId() {
                return houseOwnerId;
            }

            public void setHouseOwnerId(int houseOwnerId) {
                this.houseOwnerId = houseOwnerId;
            }

            public String getHouseOwnerName() {
                return houseOwnerName;
            }

            public void setHouseOwnerName(String houseOwnerName) {
                this.houseOwnerName = houseOwnerName;
            }

            public String getPhoneNumber() {
                return phoneNumber;
            }

            public void setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
            }

            public int getOnline() {
                return online;
            }

            public void setOnline(int online) {
                this.online = online;
            }

            public int getSeq() {
                return seq;
            }

            public void setSeq(int seq) {
                this.seq = seq;
            }
        }
    }












}
