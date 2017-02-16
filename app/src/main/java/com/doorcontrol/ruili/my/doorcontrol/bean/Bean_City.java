package com.doorcontrol.ruili.my.doorcontrol.bean;

import java.io.Serializable;

/**
 * Created by Ken on 2016/3/7.
 * 城市的实体类
 */
public class Bean_City implements Serializable{

    private int cityid;
    private String cityname;
    private int type = 1;//对象类型 0 - 字母标签, 1 - 城市名称

    public Bean_City(String cityname, int type) {
        this.cityname = cityname;
        this.type = type;
    }

    public Bean_City(){

    }

    public int getCityid() {
        return cityid;
    }

    public void setCityid(int cityid) {
        this.cityid = cityid;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CityEntity{" +
                "type=" + type +
                ", cityname='" + cityname + '\'' +
                '}';
    }

    /**
     * 比较标签是否相等
     * @return
     */
    public boolean eq(String label){
        return this.cityname.equals(label);
    }
}
