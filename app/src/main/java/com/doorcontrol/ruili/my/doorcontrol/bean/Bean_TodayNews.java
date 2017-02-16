package com.doorcontrol.ruili.my.doorcontrol.bean;

import java.util.List;

/**
 * @
 * @类名称: ${}
 * @类描述: ${type_name}
 * @创建人： Lyp
 * @创建时间：${date} ${time}
 * @备注：
 */
public class Bean_TodayNews {

    public Bean_TodayNews(String reason, ResultBean result, int error_code) {
        this.reason = reason;
        this.result = result;
        this.error_code = error_code;
    }


    private String reason;


    private ResultBean result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {


        public ResultBean(List<DataBean> data, String stat) {
            this.data = data;
            this.stat = stat;
        }

        private String stat;
        /**
         * title : 宋喆真是丧门星，连京城大律师也被他害惨了
         * date : 2016-08-29 12:15
         * author_name : 小利说事
         * thumbnail_pic_s : http://05.imgmini.eastday.com/mobile/20160829/20160829121540_147ec0094a5ce3e80ec463761107a968_1_mwpm_03200403.jpeg
         * thumbnail_pic_s02 : http://05.imgmini.eastday.com/mobile/20160829/20160829121540_147ec0094a5ce3e80ec463761107a968_1_mwpl_05500201.jpeg
         * thumbnail_pic_s03 : http://05.imgmini.eastday.com/mobile/20160829/20160829121540_147ec0094a5ce3e80ec463761107a968_1_mwpl_05500201.jpeg
         * url : http://mini.eastday.com/mobile/160829121540464.html?qid=juheshuju
         * uniquekey : 160829121540464
         * type : 头条
         * realtype : 娱乐
         */

        private List<DataBean> data;

        public String getStat() {
            return stat;
        }

        public void setStat(String stat) {
            this.stat = stat;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {

            public DataBean(String title, String date, String author_name, String thumbnail_pic_s) {
                this.title = title;
                this.date = date;
                this.author_name = author_name;
                this.thumbnail_pic_s = thumbnail_pic_s;
            }

            private String title;
            private String date;
            private String author_name;
            private String thumbnail_pic_s;
            private String thumbnail_pic_s02;
            private String thumbnail_pic_s03;
            private String url;
            private String uniquekey;
            private String type;
            private String realtype;


            @Override
            public String toString() {
                return "DataBean{" +
                        "title='" + title + '\'' +
                        ", date='" + date + '\'' +
                        ", author_name='" + author_name + '\'' +
                        ", thumbnail_pic_s='" + thumbnail_pic_s + '\'' +
                        ", thumbnail_pic_s02='" + thumbnail_pic_s02 + '\'' +
                        ", thumbnail_pic_s03='" + thumbnail_pic_s03 + '\'' +
                        ", url='" + url + '\'' +
                        ", uniquekey='" + uniquekey + '\'' +
                        ", type='" + type + '\'' +
                        ", realtype='" + realtype + '\'' +
                        '}';
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getAuthor_name() {
                return author_name;
            }

            public void setAuthor_name(String author_name) {
                this.author_name = author_name;
            }

            public String getThumbnail_pic_s() {
                return thumbnail_pic_s;
            }

            public void setThumbnail_pic_s(String thumbnail_pic_s) {
                this.thumbnail_pic_s = thumbnail_pic_s;
            }

            public String getThumbnail_pic_s02() {
                return thumbnail_pic_s02;
            }

            public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
                this.thumbnail_pic_s02 = thumbnail_pic_s02;
            }

            public String getThumbnail_pic_s03() {
                return thumbnail_pic_s03;
            }

            public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
                this.thumbnail_pic_s03 = thumbnail_pic_s03;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getUniquekey() {
                return uniquekey;
            }

            public void setUniquekey(String uniquekey) {
                this.uniquekey = uniquekey;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getRealtype() {
                return realtype;
            }

            public void setRealtype(String realtype) {
                this.realtype = realtype;
            }


        }


    }


}
