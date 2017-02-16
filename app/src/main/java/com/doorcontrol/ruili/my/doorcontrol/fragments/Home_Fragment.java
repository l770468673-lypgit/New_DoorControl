package com.doorcontrol.ruili.my.doorcontrol.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.doorcontrol.ruili.my.doorcontrol.Custom.MyListView;
import com.doorcontrol.ruili.my.doorcontrol.R;
import com.doorcontrol.ruili.my.doorcontrol.activitys.LoginActivity;
import com.doorcontrol.ruili.my.doorcontrol.adapters.Home_MessageAdapter;
import com.doorcontrol.ruili.my.doorcontrol.adapters.MyAdapter;
import com.doorcontrol.ruili.my.doorcontrol.bean.Bean_Weather;
import com.doorcontrol.ruili.my.doorcontrol.utils.Constant;
import com.doorcontrol.ruili.my.doorcontrol.utils.L;
import com.doorcontrol.ruili.my.doorcontrol.utils.OkHttpUtil;
import com.doorcontrol.ruili.my.doorcontrol.utils.Parse_Json;
import com.doorcontrol.ruili.my.doorcontrol.utils.ShareUtil;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
public class Home_Fragment extends Fragment implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private static final String TAG = "Home_Fragment";
    private TextView today_current_temp;// 当前的气温
    private TextView today_current_weather;// 当前的多云
    private TextView today_start_temp;// 当前的开始的温度
    private TextView today_end_temp;// 当前的结束的温度
    private TextView today_wind;// 当前的风速东南风
    private TextView today_wind_numb;// 当前的风速级别3级
    private TextView today_state_weather;// 适宜外出
    private TextView today_do_somgthing;// 天气较好，但风力较大，
    private MyListView mylistview;//信息的数据

    private TextView today_pm;// 当前的pm状况
    private TextView today_air_regime;// 当前的空气质量
    private TextView today_air_regime_number;// 当前的空气质量 的 指数

    private TextView tomorrow_current_weather;// 明天多云
    private TextView tomorrow_start_temp;// 明天的开始温度
    private TextView tomorrow_end_temp;// 明天的结束温度

    private TextView after_tomorrow_current_weather;// 后天多云
    private TextView after_tomorrow_start_temp;// 后天的开始温度
    private TextView after_tomorrow_end_temp;// 后天的结束温度


    private ViewPager home_message_vvp;
    private TabLayout hometab;
    public static TextView changeuser;


    private TextView getcity; // dingweidingwei de定位的城市

    private TextView message_list_textnull;//为空
    private List<ImageView> list;
    private TextView tvTitles;
    private LinearLayout llPoints;
    private String[] titles;
    // 标记前一个小圆点的位置
    private int prePosition = 0;
    private ViewPager home_vvp;

    public static LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.homefragment, container, false);

        LocationClient();

        initLocation();
        // 控件初始化
        initview(inflate);
        //     L.d("i'm  home fragment -------------s");

        //shezhi viewpager
        loadViewPager();


        return inflate;
    }


    private void loadViewPager() {

        List<ImageView> list = new ArrayList<ImageView>();
        int[] images = getImages();
        titles = getTitles();

        for (int i = 0; i < images.length; i++) {
            ImageView iv = new ImageView(getActivity());
            iv.setBackgroundResource(images[i]);
            list.add(iv);

            // 创建小圆点
            View point = new View(getActivity());
            point.setBackgroundResource(R.drawable.dot_normal);
            // 此处40是指40个px.并不是40dp.
            // LayoutParams params = new LayoutParams(40, 40);
            DisplayMetrics metrics = new DisplayMetrics();
            // TypedValue.applyDimension:
            float width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                    30, metrics);
            float height = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_PX, 30, metrics);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) width, (int) height);
            point.setLayoutParams(params);
            params.leftMargin = 5;
            llPoints.addView(point);

        }

        MyAdapter adapter = new MyAdapter(list);
        home_vvp.setAdapter(adapter);

        // mVp.setOnPageChangeListener(new OnPageChangeListener() {
        // @Override
        // public void onPageSelected(int arg0) {
        // }
        // @Override
        // public void onPageScrolled(int arg0, float arg1, int arg2) {
        // }
        // @Override
        // public void onPageScrollStateChanged(int arg0) {
        // }
        // });

        // 设置默认标题
        tvTitles.setText(titles[0]);
        // 设置默认的小圆点
        llPoints.getChildAt(0).setBackgroundResource(R.drawable.dot_enable);


        home_vvp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // 当某个页面被选择的时候
            @Override
            public void onPageSelected(int position) {

                // 标题切换
                tvTitles.setText(titles[position]);

                // 实现小圆点的不同状态切换效果
                llPoints.getChildAt(position).setBackgroundResource(
                        R.drawable.dot_enable);
                llPoints.getChildAt(prePosition).setBackgroundResource(
                        R.drawable.dot_normal);
                prePosition = position;
            }

            // 当页面滚动的时候调用的方法
            // offset:0--1:百分比
            // distance:移动过的真实的距离值.
            @Override
            public void onPageScrolled(int position, float offset, int distance) {
                Log.i("TAG", "offset=" + offset + ",distance=" + distance);
            }

            // 页面滚动状态方式改变的方法4635632654+46623


            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });


    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 9000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);

        //开始定位
        mLocationClient.start();

    }

    private void LocationClient() {
        mLocationClient = new LocationClient(getActivity());     //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);    //注册监听函数
    }

    private int[] getImages() {

        return new int[]{R.mipmap.banner, R.mipmap.banner_2
        };

    }

    private String[] getTitles() {
        return new String[]{"易慧家门禁", "易慧家智慧社区"};
    }

    private void DownLoadDate() {
        try {

            String daddress = ShareUtil.getString("appendaddress");
            String qingdao = URLEncoder.encode(daddress, "UTF-8");


            String URL = Constant.WEATHER.WEATHER_PATH + Constant.WEATHER.CITYNAME + qingdao + Constant.WEATHER.DTYPE + Constant.WEATHER.KEY;
            //  L.d("URL is " + URL.toString());
            if (URL != null)
                OkHttpUtil.downJSON(URL, new OkHttpUtil.OnDownDataListener() {
                    @Override
                    public void onResponse(String url, String json) {
                        if (json != null) {

                            //手动解析
                            //                            List<Bean_Weather.ResultBean.DataBean.WeatherBean.InfoBean> weathertoday = Parse_Json.getWeathertoday(json);
                            //
                            //                            //今天
                            //                            Bean_Weather.ResultBean.DataBean.WeatherBean.InfoBean jintianinfoBean = weathertoday.get(0);
                            //                            List<String> today = jintianinfoBean.getDay();
                            //                            String baitiankaishi = today.get(0);
                            //                            String baitianjieshu = today.get(2);
                            //
                            //                            Bean_Weather.ResultBean.DataBean.WeatherBean.InfoBean mingtianinfoBean = weathertoday.get(0);
                            //                            List<String> tommorrow = jintianinfoBean.getDay();
                            //                            String mingtbaitiankaishi = tommorrow.get(0);
                            //                            String mingtbaitianjieshu = tommorrow.get(2);
                            //                            Bean_Weather.ResultBean.DataBean.WeatherBean.InfoBean houttianinfoBean = weathertoday.get(2);
                            //                            List<String> aftertoday = jintianinfoBean.getDay();
                            //                            String houtbaitiankaishi = aftertoday.get(0);
                            //                            String houttbaitianjieshu = aftertoday.get(2);
                            //
                            //
                            //                            List<String> getwinbean = Parse_Json.getwinbean(json);
                            //                            String nanfeng = getwinbean.get(0);
                            //                            String fengsu = getwinbean.get(1);
                            //


                            // 解析返回的是 当前的对象
                            Bean_Weather bean_weather = new Gson().fromJson(json, Bean_Weather.class);
                            if (bean_weather != null) {
                                // 解析返回当天 白天的 数据集合
                                List<String> day = bean_weather.getResult().getData().getWeather().get(0).getInfo().getDay();
                                L.d("day", "day....." + day.toString());
                                if (day != null) {
                                    // 当天结束温度
                                    today_end_temp.setText(day.get(2));
                                    ShareUtil.putString(Constant.Shares.TODAY_END_TEMP, day.get(2));
                                    // 多云
                                    today_current_weather.setText(day.get(1));
                                    ShareUtil.putString(Constant.Shares.TODAY_CURRENT_WEATHER, day.get(1));
                                    L.d(TAG, "--------------------------------------day not  null");
                                } else {
                                    L.d(TAG, "--------------------------------------day==null");
                                }
                            } else {
                                L.d(TAG, "bean_weather==null");
                            }
                            // 解析返回是晚上的 数据集合
                            List<String> night = bean_weather.getResult().getData().getWeather().get(0).getInfo().getNight();
                            if (night != null) {
                                // 当前的开始的温度
                                today_start_temp.setText(night.get(2));
                                ShareUtil.putString(Constant.Shares.TODAY_START_TEMP, night.get(2));
                            }
                            //返回当前的温度
                            String temperature = bean_weather.getResult().getData().getRealtime().getWeather().getTemperature();
                            if (temperature != null) {
                                today_current_temp.setText(temperature);
                                ShareUtil.putString(Constant.Shares.TODAY_CURRENT_TEMP, temperature);
                            }

                            String direct = bean_weather.getResult().getData().getRealtime().getWind().getDirect();
                            if (direct != null) {
                                //  当前的风速
                                today_wind.setText(direct);
                                ShareUtil.putString(Constant.Shares.TODAY_WIND, direct);
                            }
                            String power = bean_weather.getResult().getData().getRealtime().getWind().getPower();
                            if (power != null) {
                                //3疾风/
                                today_wind_numb.setText(power);
                                ShareUtil.putString(Constant.Shares.TODAY_WIND_NUMB, power);
                            }

                            // 运动
                            List<String> yundong = bean_weather.getResult().getData().getLife().getInfo().getYundong();
                            if (yundong != null) {
                                today_state_weather.setText(yundong.get(0));// 较适宜
                                ShareUtil.putString(Constant.Shares.TODAY_STATE_WEATHER, yundong.get(0));
                            }

                            // 紫外线
                            List<String> ziwaixian = bean_weather.getResult().getData().getLife().getInfo().getZiwaixian();
                            if (ziwaixian != null) {
                                today_do_somgthing.setText(ziwaixian.get(0));//天气好
                                ShareUtil.putString(Constant.Shares.TODAY_DO_SOMGTHING, ziwaixian.get(0));
                            }

                            // 明天白天
                            List<String> mingtain = bean_weather.getResult().getData().getWeather().get(1).getInfo().getDay();
                            if (mingtain != null) {
                                //明天结束的温度

                                tomorrow_end_temp.setText(mingtain.get(2));
                                ShareUtil.putString(Constant.Shares.TOMORROW_END_TEMP, mingtain.get(2));
                                // 明天天气多云
                                tomorrow_current_weather.setText(mingtain.get(1));
                                ShareUtil.putString(Constant.Shares.TOMORROW_CURRENT_WEATHER, mingtain.get(1));

                                tomorrow_start_temp.setText(mingtain.get(0));
                                ShareUtil.putString(Constant.Shares.TOMORROW_START_TEMP, mingtain.get(0));

                            }
                            //明天晚上
                            //                            List<String> mingtainwansahng = bean_weather.getResult().getData().getWeather().get(1).getInfo().getNight();
                            //                            if (mingtainwansahng != null) {
                            //                                //明天开始的温度
                            //                            }

                            // 后天白天
                            List<String> houtian = bean_weather.getResult().getData().getWeather().get(2).getInfo().getDay();
                            if (houtian != null) {
                                //后天的开始温度
                                after_tomorrow_start_temp.setText(houtian.get(0));
                                ShareUtil.putString(Constant.Shares.AFTER_TOMORROW_START_TEMP, houtian.get(0));
                                // 后天多云
                                after_tomorrow_current_weather.setText(houtian.get(1));
                                ShareUtil.putString(Constant.Shares.AFTER_TOMORROW_CURRENT_WEATHER, houtian.get(1));

                                // after_tomorrow_start_temp.setText(houtian.get(2));
                                after_tomorrow_end_temp.setText(houtian.get(2));
                                ShareUtil.putString(Constant.Shares.AFTER_TOMORROW_END_TEMP, houtian.get(2));

                            }
                            //后天晚上
                            //      List<String> houtianwansahng = bean_weather.getResult().getData().getWeather().get(2).getInfo().getNight();
                            //  if (houtianwansahng != null) {
                            //后天的结束温度
                            //     after_tomorrow_start_temp.setText(houtianwansahng.get(2));
                            //       after_tomorrow_end_temp.setText(houtianwansahng.get(2));
                            //       ShareUtil.putString(Constant.Shares.AFTER_TOMORROW_END_TEMP, houtianwansahng.get(2));
                            //    }


                            //设置PM的数据
                            List<String> bean_pmList = Parse_Json.Parse_bean_Pm(json);
                            if (bean_pmList != null) {
                                today_air_regime.setText(bean_pmList.get(1));
                                ShareUtil.putString(Constant.Shares.TODAY_AIR_REGIME, bean_pmList.get(1));
                                today_air_regime_number.setText(bean_pmList.get(0));
                                ShareUtil.putString(Constant.Shares.TODAY_AIR_REGIME_NUMBER, bean_pmList.get(0));
                            }
                        } else {
                            L.d(TAG, "ssssssssssssssssssssssssssssssssssssssssss      is    null                ----");
                        }
                    }

                    @Override
                    public void onFailure(String url, String error) {
                        L.d(TAG, "失败----了");
                    }
                });
            //
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void initview(View view) {
        home_vvp = (ViewPager) view.findViewById(R.id.home_vvp);

        //        mylistview = (MyListView) view.findViewById(R.id.mylistview);
        //   getcity = (TextView) view.findViewById(R.id.getcity);
        tvTitles = (TextView) view.findViewById(R.id.tv_titles);
        changeuser = (TextView) view.findViewById(R.id.changeuser);
        llPoints = (LinearLayout) view.findViewById(R.id.ll_points);
        today_state_weather = (TextView) view.findViewById(R.id.today_state_weather);
        today_do_somgthing = (TextView) view.findViewById(R.id.today_do_somgthing);
        today_wind_numb = (TextView) view.findViewById(R.id.today_wind_numb);
        today_current_temp = (TextView) view.findViewById(R.id.today_current_temp);
        today_current_weather = (TextView) view.findViewById(R.id.today_current_weather);
        today_start_temp = (TextView) view.findViewById(R.id.today_start_temp);
        today_end_temp = (TextView) view.findViewById(R.id.today_end_temp);
        today_wind = (TextView) view.findViewById(R.id.today_wind);
        today_pm = (TextView) view.findViewById(R.id.today_pm);
        today_air_regime = (TextView) view.findViewById(R.id.today_air_regime);
        today_air_regime_number = (TextView) view.findViewById(R.id.today_air_regime_number);
        tomorrow_current_weather = (TextView) view.findViewById(R.id.tomorrow_current_weather);
        tomorrow_start_temp = (TextView) view.findViewById(R.id.tomorrow_start_temp);
        tomorrow_end_temp = (TextView) view.findViewById(R.id.tomorrow_end_temp);
        after_tomorrow_current_weather = (TextView) view.findViewById(R.id.after_tomorrow_current_weather);
        after_tomorrow_start_temp = (TextView) view.findViewById(R.id.after_tomorrow_start_temp);
        after_tomorrow_end_temp = (TextView) view.findViewById(R.id.after_tomorrow_end_temp);
        home_message_vvp = (ViewPager) view.findViewById(R.id.home_message_vvp);
        hometab = (TabLayout) view.findViewById(R.id.hometab);

        hometab.addTab(hometab.newTab());
        changeuser.setOnClickListener(this);


        List<String> tablist = new ArrayList<>();
        tablist.add("公共信息");
        tablist.add("私人信息");

        List<Fragment> fragmentslist = new ArrayList<>();
        fragmentslist.add(Punlic_messageFragment.newInstance());
        fragmentslist.add(Private_messageFragment.newInstance());


        Home_MessageAdapter adapter = new Home_MessageAdapter(getActivity(), fragmentslist, tablist);

        home_message_vvp.setAdapter(adapter);

        home_message_vvp.setOffscreenPageLimit(2);
        // 协同布局
        hometab.setupWithViewPager(home_message_vvp);
        hometab.setTabsFromPagerAdapter(adapter);
        //        message_number = (TextView) view.findViewById(R.id.message_number);
        //        home_moremessage = (TextView) view.findViewById(R.id.home_moremessage);

        //        homefragment_rgb = (RadioGroup) view.findViewById(R.id.homefragment_rgb);
        //
        //        people_btn = (RadioButton) view.findViewById(R.id.people_btn);
        //        message_btn = (RadioButton) view.findViewById(R.id.message_btn);


        //        talking_btn = (RadioButton) view.findViewById(R.id.talking_btn);
        //        shops_btn = (RadioButton) view.findViewById(R.id.shops_btn);


        // url
        String jieshuwendu = ShareUtil.getString(Constant.Shares.TODAY_END_TEMP);
        //        today_end_temp
        String dangqiantianqi = ShareUtil.getString(Constant.Shares.TODAY_CURRENT_WEATHER);
        //        today_current_weather
        String dangqiankaishiwendu = ShareUtil.getString(Constant.Shares.TODAY_START_TEMP);
        //        today_start_temp
        String dangqianjieshuwendu = ShareUtil.getString(Constant.Shares.TODAY_CURRENT_TEMP);
        //        today_current_temp
        String dangqianfengsu = ShareUtil.getString(Constant.Shares.TODAY_WIND);
        //        today_wind
        String dangqianfengsujibie = ShareUtil.getString(Constant.Shares.TODAY_WIND_NUMB);
        //        today_wind_numb
        String shiyiwaichu = ShareUtil.getString(Constant.Shares.TODAY_STATE_WEATHER);
        //        today_state_weather
        String zuoshenme = ShareUtil.getString(Constant.Shares.TODAY_DO_SOMGTHING);
        //        today_do_somgthing
        String mingtiankaishiwendu = ShareUtil.getString(Constant.Shares.TOMORROW_START_TEMP);
        //  tomorrow_start_temp
        String mingtaitianqi = ShareUtil.getString(Constant.Shares.TOMORROW_CURRENT_WEATHER);
        //  tomorrow_current_weather
        String mingtianjieshuwendu = ShareUtil.getString(Constant.Shares.TOMORROW_END_TEMP);
        //  tomorrow_end_temp
        String houtiankaishiwendu = ShareUtil.getString(Constant.Shares.AFTER_TOMORROW_START_TEMP);
        //  after_tomorrow_start_temp
        String houtianjieshuwendu = ShareUtil.getString(Constant.Shares.AFTER_TOMORROW_END_TEMP);
        //  after_tomorrow_end_temp
        String dangqiankongqizhiliang = ShareUtil.getString(Constant.Shares.TODAY_AIR_REGIME);
        //  today_air_regime
        String dangqiankongqizhiliangzhishu = ShareUtil.getString(Constant.Shares.TODAY_AIR_REGIME_NUMBER);
        String houtiandetianqi = ShareUtil.getString(Constant.Shares.AFTER_TOMORROW_CURRENT_WEATHER);
        String locationaddress = ShareUtil.getString(Constant.Shares.LOCATION_ADDRESS);


        if (today_end_temp != null) {
            today_end_temp.setText(jieshuwendu);
        }

        if (today_current_weather != null) {
            today_current_weather.setText(dangqiantianqi);
        }

        if (today_start_temp != null) {
            today_start_temp.setText(dangqiankaishiwendu);
        }

        if (today_current_temp != null) {
            today_current_temp.setText(dangqianjieshuwendu);
        }
        if (today_wind != null) {
            today_wind.setText(dangqianfengsu);
        }
        if (today_wind_numb != null) {
            today_wind_numb.setText(dangqianfengsujibie);
        }
        if (today_state_weather != null) {
            today_state_weather.setText(shiyiwaichu);
        }
        if (today_do_somgthing != null) {
            today_do_somgthing.setText(zuoshenme);
        }
        if (tomorrow_start_temp != null) {
            tomorrow_start_temp.setText(mingtiankaishiwendu);
        }
        if (tomorrow_current_weather != null) {
            tomorrow_current_weather.setText(mingtaitianqi);
        }
        if (tomorrow_end_temp != null) {
            tomorrow_end_temp.setText(mingtianjieshuwendu);
        }
        if (after_tomorrow_start_temp != null) {
            after_tomorrow_start_temp.setText(houtiankaishiwendu);
        }
        if (after_tomorrow_end_temp != null) {
            after_tomorrow_end_temp.setText(houtianjieshuwendu);
        }
        if (today_air_regime != null) {
            today_air_regime.setText(dangqiankongqizhiliang);
        }
        if (today_air_regime_number != null) {
            today_air_regime_number.setText(dangqiankongqizhiliangzhishu);
        }
        if (after_tomorrow_current_weather != null) {
            after_tomorrow_current_weather.setText(houtiandetianqi);
        }
        //        if (getcity != null) {
        //            getcity.setText(locationaddress);
        //        }


    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {

            //            case R.id.people_btn:
            //                Intent people_btn_intent = new Intent(getActivity(), People_Btn.class);
            //                startActivity(people_btn_intent);
            //                break;
            //            case R.id.message_btn:
            //
            //                Intent message_btn_intent = new Intent(getActivity(), Message_tbn.class);
            //                startActivity(message_btn_intent);
            //                break;
            //            case R.id.talking_btn:
            //                Intent talking_btn_intent = new Intent(getActivity(), Talking_Btn.class);
            //                startActivity(talking_btn_intent);
            //
            //                break;
            //            case R.id.shops_btn:
            //                Intent shops_btn_intent = new Intent(getActivity(), Shops_Btn.class);
            //                startActivity(shops_btn_intent);
            //
            //                break;
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.changeuser:

                //MainActivity.handler_main.UnRegist();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
        }
    }


    // 更多消息的页面
    //    @Override
    //    public void onClick(View v) {
    //        Intent home_moremessage_intent = new Intent(getActivity(), Home_Moremessage.class);
    //        startActivity(home_moremessage_intent);
    //    }


    private class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //Receive Location
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude:纬度 : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude ：经度: ");
            sb.append(location.getLongitude());
            sb.append("\nradius：半径 : ");
            sb.append(location.getRadius());
            // 获取 location
            String appendaddress = location.getCity();

            if (appendaddress != null) {
                ShareUtil.putString("appendaddress", appendaddress);
            }
            //    getcity.setText(appendaddress);


            if (appendaddress != null) {
                // 下载天气的json数据
                DownLoadDate();
            }


            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());// 单位：公里每小时
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());
                sb.append("\nheight : ");
                sb.append(location.getAltitude());// 单位：米
                sb.append("\ndirection : ");
                sb.append(location.getDirection());// 单位度
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());

                sb.append("\ndescribe : ");
                sb.append("gps定位成功");


            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                //运营商信息
                sb.append("\noperationers : ");
                sb.append(location.getOperators());
                sb.append("\ndescribe : ");
                sb.append("网络定位成功");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }

            if (sb != null) {

                Home_Fragment.mLocationClient.stop();
            }
            sb.append("\nlocationdescribe : ");
            sb.append(location.getLocationDescribe());// 位置语义化信息

            List<Poi> list = location.getPoiList();// POI数据
            if (list != null) {
                sb.append("\npoilist size = : ");
                sb.append(list.size());
                for (Poi p : list) {
                    sb.append("\npoi= : ");
                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                }
            }
            Log.i("BaiduLocationApiDem", sb.toString());

        }
    }
}
