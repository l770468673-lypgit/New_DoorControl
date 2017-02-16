package com.doorcontrol.ruili.my.doorcontrol.utils;

import com.doorcontrol.ruili.my.doorcontrol.bean.Bean_City;
import com.doorcontrol.ruili.my.doorcontrol.bean.Bean_TodayNews;
import com.doorcontrol.ruili.my.doorcontrol.bean.Bean_Weather;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @
 * @类名称: ${type_name}
 * @类描述: ${todo}
 * @创建人：
 * @创建时间：${date} ${time}
 * @备注：
 */
public class Parse_Json {

    public static List<String> Parse_bean_Pm(String json) {

        try {

            List<String> bean_pmList = new ArrayList<>();

            JSONObject jsonObject_all = new JSONObject(json);
            JSONObject jsonObject_result = jsonObject_all.getJSONObject("result");
            JSONObject jsonObject_data = jsonObject_result.getJSONObject("data");
            JSONObject jsonObject_pm25 = jsonObject_data.getJSONObject("pm25");
            JSONObject pm25 = jsonObject_pm25.getJSONObject("pm25");
            String curPm = pm25.getString("curPm");
            String quality = pm25.getString("quality");


            bean_pmList.add(curPm);
            bean_pmList.add(quality);
            return bean_pmList;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }


    private static String[] labels = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    /**
     * 解析城市选择列表
     * <p>
     * CityEntity: cityname=A, type=0
     * CityEntity: cityname=鞍山， type=1
     * CityEntity: cityname=安阳， type=1
     * CityEntity: cityname=B, type=0
     * CityEntity: cityname=北京， type=1
     * ...
     *
     * @param json
     */
    public static List<Bean_City> getCitiesByJSON(String json) {
        if (json != null) {
            List<Bean_City> cityEntities = new ArrayList<>();

            try {
                JSONObject jsonObject_all = new JSONObject(json);
                int code = jsonObject_all.getInt("retcode");
                if (code == 0) {
                    //表示获取json成功
                    JSONObject jsonObject_cities = jsonObject_all.getJSONObject("cities");

                    for (int i = 0; i < labels.length; i++) {
                        //设置城市标签
                        Bean_City cityEntity = new Bean_City(labels[i], 0);

                        //设置城市列表
                        JSONArray jsonArray_lable_cities = jsonObject_cities.optJSONArray(labels[i]);//获得相应标签下的城市列表
                        if (jsonArray_lable_cities != null) {
                            TypeToken<List<Bean_City>> tt = new TypeToken<List<Bean_City>>() {
                            };
                            List<Bean_City> citylist = new Gson().fromJson(jsonArray_lable_cities.toString(), tt.getType());

                            cityEntities.add(cityEntity);
                            cityEntities.addAll(citylist);
                        }
                    }

                    L.d(cityEntities.toString());

                    return cityEntities;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    // 天气
    public static List<Bean_Weather.ResultBean.DataBean.WeatherBean.InfoBean> getWeathertoday(String json) {
        if (json != null) {
            //            List<Bean_Weather.ResultBean.DataBean.WeatherBean.InfoBean> cityEntities = new ArrayList<>();

            List<Bean_Weather.ResultBean.DataBean.WeatherBean.InfoBean> weatherbean = new ArrayList<>();

            try {
                JSONObject jsonobject_all = new JSONObject(json);
                JSONObject jsonobject_result = jsonobject_all.getJSONObject("result");
                JSONObject jsonobject_data = jsonobject_result.getJSONObject("data");
                JSONArray jsonarray_weather = jsonobject_data.getJSONArray("weather");
                for (int i = 0; i < jsonarray_weather.length(); i++) {
                    JSONObject jsonObject_weather_item = jsonarray_weather.getJSONObject(i);
                    JSONObject weather_info = jsonObject_weather_item.getJSONObject("info");
                    JSONArray day = weather_info.getJSONArray("day");
                    String zaoshang = (String) day.get(0);
                    String tianqi = (String) day.get(1);
                    String wansahng = (String) day.get(2);


                    List<String> list = new ArrayList<>();
                    list.add(zaoshang);
                    list.add(wansahng);
                    list.add(tianqi);

                    Bean_Weather.ResultBean.DataBean.WeatherBean.InfoBean infoBean = new Bean_Weather.ResultBean.DataBean.WeatherBean.InfoBean();

                    weatherbean.add(infoBean);
                }

                return weatherbean;
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        return null;
    }

    // 风俗 和 风力
    public static List<String> getwinbean(String json) {
        if (json != null) {
            //            List<Bean_Weather.ResultBean.DataBean.WeatherBean.InfoBean> cityEntities = new ArrayList<>();

            List<String> windbean = new ArrayList<>();

            try {
                JSONObject jsonobject_all = new JSONObject(json);
                JSONObject jsonobject_result = jsonobject_all.getJSONObject("result");
                JSONObject jsonobject_data = jsonobject_result.getJSONObject("data");
                JSONObject jsonobject_realtime = jsonobject_data.getJSONObject("realtime");
                JSONObject jsonobject_wind = jsonobject_realtime.getJSONObject("wind");
                String nanfeng = (String) jsonobject_wind.get("direct");
                String fengsu = (String) jsonobject_wind.get("power");

                // Bean_Weather.ResultBean.DataBean.RealtimeBean.WindBean windBean = new Bean_Weather.ResultBean.DataBean.RealtimeBean.WindBean(nanfeng, fengsu);


                windbean.add(nanfeng);
                windbean.add(fengsu);

                return windbean;
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        return null;
    }


    //pm
    public static List<String> getPm(String json) {
        List<String> listpm25 = new ArrayList<>();

        try {
            JSONObject jsonobject_all = new JSONObject(json);
            JSONObject jsonobject_result = jsonobject_all.getJSONObject("result");
            JSONObject jsonobject_data = jsonobject_result.getJSONObject("data");
            JSONObject jsonobject_pm25 = jsonobject_data.getJSONObject("pm25");
            JSONObject pm25 = jsonobject_pm25.getJSONObject("pm25");
            String curPm = (String) pm25.get("curPm");
            String quality = (String) pm25.get("quality");



        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static List<Bean_TodayNews.ResultBean.DataBean> getNewDate(String json) {

        Bean_TodayNews bean_todayNews = new Gson().fromJson(json, Bean_TodayNews.class);
        Bean_TodayNews.ResultBean result = bean_todayNews.getResult();
        List<Bean_TodayNews.ResultBean.DataBean> data = result.getData();
        return data;
    }

}
