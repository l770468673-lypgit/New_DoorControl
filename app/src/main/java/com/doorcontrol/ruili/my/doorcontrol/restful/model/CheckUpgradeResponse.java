package com.doorcontrol.ruili.my.doorcontrol.restful.model;

/**
 * @
 * @类名称: ${}
 * @类描述: ${type_name}
 * @创建人： Lyp
 * @创建时间：${date} ${time}
 * @备注：
 */
public class CheckUpgradeResponse extends ResponseHead {


    /**
     * packageName : com.dams
     * appUrl :
     * version : 1.0
     * versionCode : 1
     * md5 :
     */

    private AppInfoBean appInfo;

    public AppInfoBean getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(AppInfoBean appInfo) {
        this.appInfo = appInfo;
    }

    public static class AppInfoBean {
        private String packageName;
        private String appUrl;
        private String version;
        private int versionCode;
        private String md5;

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public String getAppUrl() {
            return appUrl;
        }

        public void setAppUrl(String appUrl) {
            this.appUrl = appUrl;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public int getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(int versionCode) {
            this.versionCode = versionCode;
        }

        public String getMd5() {
            return md5;
        }

        public void setMd5(String md5) {
            this.md5 = md5;
        }

        @Override
        public String toString() {
            return "AppInfoBean{" +
                    "packageName='" + packageName + '\'' +
                    ", appUrl='" + appUrl + '\'' +
                    ", version='" + version + '\'' +
                    ", versionCode=" + versionCode +
                    ", md5='" + md5 + '\'' +
                    '}';
        }
    }
}
