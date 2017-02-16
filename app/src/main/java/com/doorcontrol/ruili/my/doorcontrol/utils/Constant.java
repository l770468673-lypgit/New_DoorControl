package com.doorcontrol.ruili.my.doorcontrol.utils;

/**
 * @
 * @类名称: ${type_name}
 * @类描述: ${todo}
 * @创建人：
 * @创建时间：${date} ${time}
 * @备注：
 */
public interface Constant {


    // 城市的url
    interface CITY_URL {

        String CITIES_URL = "http://ikft.house.qq.com/index.php?guid=866500021200250&devua=appkft_1080_1920_XiaomiMI4LTE_1.8.3_Android19&act=kftcitylistnew&channel=71&devid=866500021200250&appname=QQHouse&mod=appkft";

    }

    interface LOGINs {

        String user_login = "http://121.42.139.223:8080/dams/api/user/login";


    }

    interface GETDOOR {
        String DOOR_MESSAGE = "http://121.42.139.223:8080/dams/api/device/getDeviceInfo?deviceCode=";
        //  String DOOR_MESSAGE = "http://121.42.139.223::8080/dams/api/upgrade/getAppInfo?packageName=";
    }

    interface  TODAYNEWS{

        String NEWSURL="http://v.juhe.cn/toutiao/index?type=&key=6854c74dcdffade8c809b0c9f5a274a5";
      //  String NEWSURLCODE="type=&key=6854c74dcdffade8c809b0c9f5a274a5";
    }



    // 天气的 数据
    interface WEATHER {

        // appkey

        //请求地址：http://op.juhe.cn/onebox/weather/query
        //请求参数：cityname=%E9%9D%92%E5%B2%9B&dtype=json&key=09cf53a3da5d242fbcb657aa3ae02dd2
        //请求方式：GET
        //请求示例：http://op.juhe.cn/onebox/weather/query?cityname=%E6%B8%A9%E5%B7%9E&key=您申请的KEY

        String WEATHER_PATH = "http://op.juhe.cn/onebox/weather/query";
        String CITYNAME = "?cityname=";
        String DTYPE = "&dtype=json";
        String KEY = "&key=09cf53a3da5d242fbcb657aa3ae02dd2";


    }

    interface Shares {
        String TODAY_END_TEMP = "today_end_temp";
        String TODAY_CURRENT_WEATHER = "today_current_weather";
        String TODAY_START_TEMP = "today_start_temp";
        String TODAY_CURRENT_TEMP = "today_current_temp";
        String TODAY_WIND = "today_wind";
        String TODAY_WIND_NUMB = "today_wind_numb";
        String TODAY_STATE_WEATHER = "today_state_weather";
        String TODAY_DO_SOMGTHING = "today_do_somgthing";
        String TOMORROW_START_TEMP = "tomorrow_start_temp";
        String TOMORROW_CURRENT_WEATHER = "tomorrow_current_weather";
        String TOMORROW_END_TEMP = "tomorrow_end_temp";
        String AFTER_TOMORROW_START_TEMP = "after_tomorrow_start_temp";
        String AFTER_TOMORROW_END_TEMP = "after_tomorrow_end_temp";
        String TODAY_AIR_REGIME = "today_air_regime";
        String TODAY_AIR_REGIME_NUMBER = "today_air_regime_number";
        String AFTER_TOMORROW_CURRENT_WEATHER = "after_tomorrow_current_weather";


        String LOCATION_ADDRESS="appendaddress";
        String HOME_USER_NAME="home_user_name";
        String USER_OWN_SETTING_NAME="user_own_setting_name";
    }

    interface CODE {
        int REUQEST_CODE = 0x001;
        int RESULT_CODE = 0x002;
        int RESULT_LOGIN = 0x003;
        int REUQEST_LOGIN = 0x004;
    }

    interface KEYS {
        String CITYDATAS = "citydata";

        String CITY_ID = "cityid";
        String CITY_NAME = "home_sp_city";

        String LOGINPASS = "password";

        String LOGINNAME = "loginName";


    }

    interface NEWS {
        String NEWS_ID_NAME = "apikey";
        String NEWS_ID_ID = "122f112830dccbcd587639df0666d697";

        String NEWS = "http://apis.baidu.com/3023/news/channel?";
        String AGRS = "id=popular&page=1";
    }


}
