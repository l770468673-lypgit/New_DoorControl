package com.doorcontrol.ruili.my.doorcontrol.bean;

import java.util.List;

//
//
///**
// * @
// * @类名称: ${type_name}
// * @类描述: ${todo}
// * @创建人：
// * @创建时间：${date} ${time}
// * @备注：
// */
public class Bean_Weather {


    /**
     * reason : successed!
     * result : {"data":{"pubdate":"2016-12-05","pubtime":"08:00:00","realtime":{"city_code":"101120201","city_name":"青岛","date":"2016-12-05","time":"09:00:00","week":1,"moon":"十一月初七","dataUptime":1480901824,"weather":{"temperature":"5","humidity":"88","info":"雾","img":"18"},"wind":{"direct":"北风","power":"4级","offset":null,"windspeed":null}},"life":{"date":"2016-12-5","info":{"chuanyi":["冷","天气冷，建议着棉服、羽绒服、皮夹克加羊毛衫等冬季服装。年老体弱者宜着厚棉衣、冬大衣或厚羽绒服。"],"ganmao":["极易发","昼夜温差极大，且风力较强，极易发生感冒，请特别注意增减衣服保暖防寒。"],"kongtiao":["开启制暖空调","您将感到有些冷，可以适当开启制暖空调调节室内温度，以免着凉感冒。"],"xiche":["较不宜","较不宜洗车，未来一天无雨，风力较大，如果执意擦洗汽车，要做好蒙上污垢的心理准备。"],"yundong":["较不宜","阴天，且天气寒冷，推荐您在室内进行低强度运动；若坚持户外运动，请选择合适的运动并注意保暖。"],"ziwaixian":["最弱","属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"]}},"weather":[{"date":"2016-12-05","info":{"day":["2","阴","9","北风","5-6 级","06:54"],"night":["0","晴","0","北风","4-5 级","16:44"]},"week":"一","nongli":"十一月初七"},{"date":"2016-12-06","info":{"dawn":["0","晴","0","北风","4-5 级","16:44"],"day":["0","晴","7","西南风","4-5 级","06:55"],"night":["0","晴","1","北风","4-5 级","16:44"]},"week":"二","nongli":"十一月初八"},{"date":"2016-12-07","info":{"dawn":["0","晴","1","北风","4-5 级","16:44"],"day":["0","晴","10","北风","4-5 级","06:56"],"night":["1","多云","3","南风","4-5 级","16:44"]},"week":"三","nongli":"十一月初九"},{"date":"2016-12-08","info":{"dawn":["1","多云","3","南风","4-5 级","16:44"],"day":["1","多云","10","北风","4-5 级","06:57"],"night":["1","多云","1","北风","4-5 级","16:44"]},"week":"四","nongli":"十一月初十 "},{"date":"2016-12-09","info":{"dawn":["1","多云","1","北风","4-5 级","16:44"],"day":["0","晴","6","北风","3-4 级","06:57"],"night":["0","晴","1","北风","3-4 级","16:44"]},"week":"五","nongli":"十一月十一"}],"f3h":{"temperature":[{"jg":"20161205080000","jb":"5"},{"jg":"20161205110000","jb":"8"},{"jg":"20161205140000","jb":"8"},{"jg":"20161205170000","jb":"4"},{"jg":"20161205200000","jb":"3"},{"jg":"20161205230000","jb":"2"},{"jg":"20161206020000","jb":"1"},{"jg":"20161206050000","jb":"0"},{"jg":"20161206080000","jb":"0"}],"precipitation":[{"jg":"20161205080000","jf":"0"},{"jg":"20161205110000","jf":"0"},{"jg":"20161205140000","jf":"0"},{"jg":"20161205170000","jf":"0"},{"jg":"20161205200000","jf":"0"},{"jg":"20161205230000","jf":"0"},{"jg":"20161206020000","jf":"0"},{"jg":"20161206050000","jf":"0"},{"jg":"20161206080000","jf":"0"}]},"pm25":{"key":"Qingdao","show_desc":0,"pm25":{"curPm":"172","pm25":"133","pm10":"209","level":4,"quality":"中度污染","des":"对污染物比较敏感的人群，例如儿童和老年人、呼吸道疾病或心脏病患者，以及喜爱户外活动的人，他们的健康状况会受到影响，但对健康人群基本没有影响。"},"dateTime":"2016年12月05日09时","cityName":"青岛"},"jingqu":"","jingqutq":"","date":"","isForeign":"0"}}
     * error_code : 0
     */

    private String reason;
    /**
     * data : {"pubdate":"2016-12-05","pubtime":"08:00:00","realtime":{"city_code":"101120201","city_name":"青岛","date":"2016-12-05","time":"09:00:00","week":1,"moon":"十一月初七","dataUptime":1480901824,"weather":{"temperature":"5","humidity":"88","info":"雾","img":"18"},"wind":{"direct":"北风","power":"4级","offset":null,"windspeed":null}},"life":{"date":"2016-12-5","info":{"chuanyi":["冷","天气冷，建议着棉服、羽绒服、皮夹克加羊毛衫等冬季服装。年老体弱者宜着厚棉衣、冬大衣或厚羽绒服。"],"ganmao":["极易发","昼夜温差极大，且风力较强，极易发生感冒，请特别注意增减衣服保暖防寒。"],"kongtiao":["开启制暖空调","您将感到有些冷，可以适当开启制暖空调调节室内温度，以免着凉感冒。"],"xiche":["较不宜","较不宜洗车，未来一天无雨，风力较大，如果执意擦洗汽车，要做好蒙上污垢的心理准备。"],"yundong":["较不宜","阴天，且天气寒冷，推荐您在室内进行低强度运动；若坚持户外运动，请选择合适的运动并注意保暖。"],"ziwaixian":["最弱","属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"]}},"weather":[{"date":"2016-12-05","info":{"day":["2","阴","9","北风","5-6 级","06:54"],"night":["0","晴","0","北风","4-5 级","16:44"]},"week":"一","nongli":"十一月初七"},{"date":"2016-12-06","info":{"dawn":["0","晴","0","北风","4-5 级","16:44"],"day":["0","晴","7","西南风","4-5 级","06:55"],"night":["0","晴","1","北风","4-5 级","16:44"]},"week":"二","nongli":"十一月初八"},{"date":"2016-12-07","info":{"dawn":["0","晴","1","北风","4-5 级","16:44"],"day":["0","晴","10","北风","4-5 级","06:56"],"night":["1","多云","3","南风","4-5 级","16:44"]},"week":"三","nongli":"十一月初九"},{"date":"2016-12-08","info":{"dawn":["1","多云","3","南风","4-5 级","16:44"],"day":["1","多云","10","北风","4-5 级","06:57"],"night":["1","多云","1","北风","4-5 级","16:44"]},"week":"四","nongli":"十一月初十 "},{"date":"2016-12-09","info":{"dawn":["1","多云","1","北风","4-5 级","16:44"],"day":["0","晴","6","北风","3-4 级","06:57"],"night":["0","晴","1","北风","3-4 级","16:44"]},"week":"五","nongli":"十一月十一"}],"f3h":{"temperature":[{"jg":"20161205080000","jb":"5"},{"jg":"20161205110000","jb":"8"},{"jg":"20161205140000","jb":"8"},{"jg":"20161205170000","jb":"4"},{"jg":"20161205200000","jb":"3"},{"jg":"20161205230000","jb":"2"},{"jg":"20161206020000","jb":"1"},{"jg":"20161206050000","jb":"0"},{"jg":"20161206080000","jb":"0"}],"precipitation":[{"jg":"20161205080000","jf":"0"},{"jg":"20161205110000","jf":"0"},{"jg":"20161205140000","jf":"0"},{"jg":"20161205170000","jf":"0"},{"jg":"20161205200000","jf":"0"},{"jg":"20161205230000","jf":"0"},{"jg":"20161206020000","jf":"0"},{"jg":"20161206050000","jf":"0"},{"jg":"20161206080000","jf":"0"}]},"pm25":{"key":"Qingdao","show_desc":0,"pm25":{"curPm":"172","pm25":"133","pm10":"209","level":4,"quality":"中度污染","des":"对污染物比较敏感的人群，例如儿童和老年人、呼吸道疾病或心脏病患者，以及喜爱户外活动的人，他们的健康状况会受到影响，但对健康人群基本没有影响。"},"dateTime":"2016年12月05日09时","cityName":"青岛"},"jingqu":"","jingqutq":"","date":"","isForeign":"0"}
     */

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
        /**
         * pubdate : 2016-12-05
         * pubtime : 08:00:00
         * realtime : {"city_code":"101120201","city_name":"青岛","date":"2016-12-05","time":"09:00:00","week":1,"moon":"十一月初七","dataUptime":1480901824,"weather":{"temperature":"5","humidity":"88","info":"雾","img":"18"},"wind":{"direct":"北风","power":"4级","offset":null,"windspeed":null}}
         * life : {"date":"2016-12-5","info":{"chuanyi":["冷","天气冷，建议着棉服、羽绒服、皮夹克加羊毛衫等冬季服装。年老体弱者宜着厚棉衣、冬大衣或厚羽绒服。"],"ganmao":["极易发","昼夜温差极大，且风力较强，极易发生感冒，请特别注意增减衣服保暖防寒。"],"kongtiao":["开启制暖空调","您将感到有些冷，可以适当开启制暖空调调节室内温度，以免着凉感冒。"],"xiche":["较不宜","较不宜洗车，未来一天无雨，风力较大，如果执意擦洗汽车，要做好蒙上污垢的心理准备。"],"yundong":["较不宜","阴天，且天气寒冷，推荐您在室内进行低强度运动；若坚持户外运动，请选择合适的运动并注意保暖。"],"ziwaixian":["最弱","属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"]}}
         * weather : [{"date":"2016-12-05","info":{"day":["2","阴","9","北风","5-6 级","06:54"],"night"
         * :["0","晴","0","北风","4-5 级","16:44"]},"week":"一","nongli":"十一月初七"},
         * {"date":"2016-12-06","info":{"dawn":["0","晴","0","北风","4-5 级","16:44"],"day":["0","晴","7","西南风","4-5 级","06:55"],"night":["0","晴","1","北风","4-5 级","16:44"]},"week":"二","nongli":"十一月初八"},{"date":"2016-12-07","info":{"dawn":["0","晴","1","北风","4-5 级","16:44"],"day":["0","晴","10","北风","4-5 级","06:56"],"night":["1","多云","3","南风","4-5 级","16:44"]},"week":"三","nongli":"十一月初九"},{"date":"2016-12-08","info":{"dawn":["1","多云","3","南风","4-5 级","16:44"],"day":["1","多云","10","北风","4-5 级","06:57"],"night":["1","多云","1","北风","4-5 级","16:44"]},"week":"四","nongli":"十一月初十 "},{"date":"2016-12-09","info":{"dawn":["1","多云","1","北风","4-5 级","16:44"],"day":["0","晴","6","北风","3-4 级","06:57"],"night":["0","晴","1","北风","3-4 级","16:44"]},"week":"五","nongli":"十一月十一"}]
         * f3h : {"temperature":[{"jg":"20161205080000","jb":"5"},{"jg":"20161205110000","jb":"8"},{"jg":"20161205140000","jb":"8"},{"jg":"20161205170000","jb":"4"},{"jg":"20161205200000","jb":"3"},{"jg":"20161205230000","jb":"2"},{"jg":"20161206020000","jb":"1"},{"jg":"20161206050000","jb":"0"},{"jg":"20161206080000","jb":"0"}],"precipitation":[{"jg":"20161205080000","jf":"0"},{"jg":"20161205110000","jf":"0"},{"jg":"20161205140000","jf":"0"},{"jg":"20161205170000","jf":"0"},{"jg":"20161205200000","jf":"0"},{"jg":"20161205230000","jf":"0"},{"jg":"20161206020000","jf":"0"},{"jg":"20161206050000","jf":"0"},{"jg":"20161206080000","jf":"0"}]}
         * pm25 : {"key":"Qingdao","show_desc":0,"pm25":{"curPm":"172","pm25":"133","pm10":"209","level":4,"quality":"中度污染","des":"对污染物比较敏感的人群，例如儿童和老年人、呼吸道疾病或心脏病患者，以及喜爱户外活动的人，他们的健康状况会受到影响，但对健康人群基本没有影响。"},"dateTime":"2016年12月05日09时","cityName":"青岛"}
         * jingqu :
         * jingqutq :
         * date :
         * isForeign : 0
         */

        private DataBean data;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            private String pubdate;
            private String pubtime;
            /**
             * city_code : 101120201
             * city_name : 青岛
             * date : 2016-12-05
             * time : 09:00:00
             * week : 1
             * moon : 十一月初七
             * dataUptime : 1480901824
             * weather : {"temperature":"5","humidity":"88","info":"雾","img":"18"}
             * wind : {"direct":"北风","power":"4级","offset":null,"windspeed":null}
             */

            private RealtimeBean realtime;
            /**
             * date : 2016-12-5
             * info : {"chuanyi":["冷","天气冷，建议着棉服、羽绒服、皮夹克加羊毛衫等冬季服装。年老体弱者宜着厚棉衣、冬大衣或厚羽绒服。"],"ganmao":["极易发","昼夜温差极大，且风力较强，极易发生感冒，请特别注意增减衣服保暖防寒。"],"kongtiao":["开启制暖空调","您将感到有些冷，可以适当开启制暖空调调节室内温度，以免着凉感冒。"],"xiche":["较不宜","较不宜洗车，未来一天无雨，风力较大，如果执意擦洗汽车，要做好蒙上污垢的心理准备。"],"yundong":["较不宜","阴天，且天气寒冷，推荐您在室内进行低强度运动；若坚持户外运动，请选择合适的运动并注意保暖。"],"ziwaixian":["最弱","属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"]}
             */

            private LifeBean life;
            private String jingqu;
            private String jingqutq;
            private String date;
            private String isForeign;
            /**
             * date : 2016-12-05
             * info : {"day":["2","阴","9","北风","5-6 级","06:54"],"night":["0","晴","0","北风","4-5 级","16:44"]}
             * week : 一
             * nongli : 十一月初七
             */

            private List<WeatherBean> weather;

            public String getPubdate() {
                return pubdate;
            }

            public void setPubdate(String pubdate) {
                this.pubdate = pubdate;
            }

            public String getPubtime() {
                return pubtime;
            }

            public void setPubtime(String pubtime) {
                this.pubtime = pubtime;
            }

            public RealtimeBean getRealtime() {
                return realtime;
            }

            public void setRealtime(RealtimeBean realtime) {
                this.realtime = realtime;
            }

            public LifeBean getLife() {
                return life;
            }

            public void setLife(LifeBean life) {
                this.life = life;
            }

            public String getJingqu() {
                return jingqu;
            }

            public void setJingqu(String jingqu) {
                this.jingqu = jingqu;
            }

            public String getJingqutq() {
                return jingqutq;
            }

            public void setJingqutq(String jingqutq) {
                this.jingqutq = jingqutq;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getIsForeign() {
                return isForeign;
            }

            public void setIsForeign(String isForeign) {
                this.isForeign = isForeign;
            }

            public List<WeatherBean> getWeather() {
                return weather;
            }

            public void setWeather(List<WeatherBean> weather) {
                this.weather = weather;
            }

            public static class RealtimeBean {
                private String city_code;
                private String city_name;
                private String date;
                private String time;
                private int week;
                private String moon;
                private int dataUptime;
                /**
                 * temperature : 5
                 * humidity : 88
                 * info : 雾
                 * img : 18
                 */

                private WeatherBean weather;
                /**
                 * direct : 北风
                 * power : 4级
                 * offset : null
                 * windspeed : null
                 */

                private WindBean wind;

                public String getCity_code() {
                    return city_code;
                }

                public void setCity_code(String city_code) {
                    this.city_code = city_code;
                }

                public String getCity_name() {
                    return city_name;
                }

                public void setCity_name(String city_name) {
                    this.city_name = city_name;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public int getWeek() {
                    return week;
                }

                public void setWeek(int week) {
                    this.week = week;
                }

                public String getMoon() {
                    return moon;
                }

                public void setMoon(String moon) {
                    this.moon = moon;
                }

                public int getDataUptime() {
                    return dataUptime;
                }

                public void setDataUptime(int dataUptime) {
                    this.dataUptime = dataUptime;
                }

                public WeatherBean getWeather() {
                    return weather;
                }

                public void setWeather(WeatherBean weather) {
                    this.weather = weather;
                }

                public WindBean getWind() {
                    return wind;
                }

                public void setWind(WindBean wind) {
                    this.wind = wind;
                }

                public static class WeatherBean {
                    private String temperature;
                    private String humidity;
                    private String info;
                    private String img;

                    public String getTemperature() {
                        return temperature;
                    }

                    public void setTemperature(String temperature) {
                        this.temperature = temperature;
                    }

                    public String getHumidity() {
                        return humidity;
                    }

                    public void setHumidity(String humidity) {
                        this.humidity = humidity;
                    }

                    public String getInfo() {
                        return info;
                    }

                    public void setInfo(String info) {
                        this.info = info;
                    }

                    public String getImg() {
                        return img;
                    }

                    public void setImg(String img) {
                        this.img = img;
                    }
                }

                public static class WindBean {
                    private String direct;
                    private String power;
                    private Object offset;
                    private Object windspeed;

                    public String getDirect() {
                        return direct;
                    }

                    public void setDirect(String direct) {
                        this.direct = direct;
                    }

                    public String getPower() {
                        return power;
                    }

                    public void setPower(String power) {
                        this.power = power;
                    }

                    public Object getOffset() {
                        return offset;
                    }

                    public void setOffset(Object offset) {
                        this.offset = offset;
                    }

                    public Object getWindspeed() {
                        return windspeed;
                    }

                    public void setWindspeed(Object windspeed) {
                        this.windspeed = windspeed;
                    }
                }
            }

            public static class LifeBean {
                private String date;
                private InfoBean info;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public InfoBean getInfo() {
                    return info;
                }

                public void setInfo(InfoBean info) {
                    this.info = info;
                }

                public static class InfoBean {
                    private List<String> chuanyi;
                    private List<String> ganmao;
                    private List<String> kongtiao;
                    private List<String> xiche;
                    private List<String> yundong;
                    private List<String> ziwaixian;

                    public List<String> getChuanyi() {
                        return chuanyi;
                    }

                    public void setChuanyi(List<String> chuanyi) {
                        this.chuanyi = chuanyi;
                    }

                    public List<String> getGanmao() {
                        return ganmao;
                    }

                    public void setGanmao(List<String> ganmao) {
                        this.ganmao = ganmao;
                    }

                    public List<String> getKongtiao() {
                        return kongtiao;
                    }

                    public void setKongtiao(List<String> kongtiao) {
                        this.kongtiao = kongtiao;
                    }

                    public List<String> getXiche() {
                        return xiche;
                    }

                    public void setXiche(List<String> xiche) {
                        this.xiche = xiche;
                    }

                    public List<String> getYundong() {
                        return yundong;
                    }

                    public void setYundong(List<String> yundong) {
                        this.yundong = yundong;
                    }

                    public List<String> getZiwaixian() {
                        return ziwaixian;
                    }

                    public void setZiwaixian(List<String> ziwaixian) {
                        this.ziwaixian = ziwaixian;
                    }
                }
            }

            public static class WeatherBean {
                private String date;
                private InfoBean info;
                private String week;
                private String nongli;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public InfoBean getInfo() {
                    return info;
                }

                public void setInfo(InfoBean info) {
                    this.info = info;
                }

                public String getWeek() {
                    return week;
                }

                public void setWeek(String week) {
                    this.week = week;
                }

                public String getNongli() {
                    return nongli;
                }

                public void setNongli(String nongli) {
                    this.nongli = nongli;
                }

                public static class InfoBean {
                    private List<String> day;
                    private List<String> night;

                    public List<String> getDay() {
                        return day;
                    }

                    public void setDay(List<String> day) {
                        this.day = day;
                    }

                    public List<String> getNight() {
                        return night;
                    }

                    public void setNight(List<String> night) {
                        this.night = night;
                    }
                }
            }
        }
    }
}
