package com.doorcontrol.ruili.my.doorcontrol.bean;

/**
 * @
 * @类名称: ${type_name}
 * @类描述: ${todo}
 * @创建人：
 * @创建时间：${date} ${time}
 * @备注：
 */
public class Bean_PM {
    private String quality;// 优
    private String curPm;  // 当前 质量

    public Bean_PM(String quality, String curPm) {
        this.quality = quality;
        this.curPm = curPm;
    }

    @Override
    public String toString() {
        return "Bean_PM{" +
                "quality='" + quality + '\'' +
                ", curPm='" + curPm + '\'' +
                '}';
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getCurPm() {
        return curPm;
    }

    public void setCurPm(String curPm) {
        this.curPm = curPm;
    }
}
