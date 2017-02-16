package com.doorcontrol.ruili.my.doorcontrol.application;

import android.app.Application;
import android.telephony.TelephonyManager;

import com.doorcontrol.ruili.my.doorcontrol.getui.DemoIntentService;
import com.doorcontrol.ruili.my.doorcontrol.getui.DemoPushService;
import com.doorcontrol.ruili.my.doorcontrol.manager.HttpManager;
import com.doorcontrol.ruili.my.doorcontrol.utils.L;
import com.doorcontrol.ruili.my.doorcontrol.utils.OkHttpUtil;
import com.doorcontrol.ruili.my.doorcontrol.utils.ShareUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.igexin.sdk.PushManager;


/**
 * Created by liuya on 2016/3/27.
 */
public class AppContext extends Application {

    /**
     * ━━━━━━━━━━神兽出没━━━━━━━━━
     * <p>
     * 　　　　　　　　┏┓　　　┏┓+ +
     * 　　　　　　　┏┛┻━━━┛┻┓ + +
     * 　　　　　　　┃　　　　　　　┃
     * 　　　　　　　┃　　　━　　　┃ ++ + + +
     * 　　　　　　　┃ ████━████  ┃+
     * 　　　　　　　┃　　　　　　　┃ +
     * 　　　　　　　┃　　　┻　　　┃
     * 　　　　　　　┃　　　　　　　┃ + +
     * 　　　　　　　┗━┓　　　┏━┛
     * 　　　　　　　　　┃　　　┃
     * 　　　　　　　　　┃　　　┃ + + + +
     * 　　　　　　　　　┃　　　┃　　　　Code is far away from bug with the animal protecting
     * 　　　　　　　　　┃　　　┃ + 　　　　神兽保佑,代码无bug
     * 　　　　　　　　　┃　　　┃
     * 　　　　　　　　　┃　　　┃　　+
     * 　　　　　　　　　┃　 　　┗━━━┓ + +
     * 　　　　　　　　　┃ 　　　　　　　┣┓
     * 　　　　　　　　　┃ 　　　　　　　┏┛
     * 　　　　　　　　　┗┓┓┏━┳┓┏┛ + + + +
     * 　　　　　　　　　　┃┫┫　┃┫┫
     * 　　　　　　　　　　┗┻┛　┗┻┛+ + + +
     * <p>
     * ━━━━━━感觉萌萌哒━━━━━━
     */

    private static AppContext instance;

    public static AppContext getInstance() {
        return instance;
    }


    private static final String TAG = "AppContext";

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //初始化共享参数
        ShareUtil.initShared(this);
        //初始化okHttp
        OkHttpUtil.initOkHttp();
        Fresco.initialize(getApplicationContext());
        //  初始化
        HttpManager.getInstance();
        String imei = getImei();
        ShareUtil.putString("imei", imei);

        // com.getui.demo.DemoPushService 为第三⽅方⾃自定义推送服务
        PushManager.getInstance().initialize(this.getApplicationContext(), DemoPushService.class);
        // com.getui.demo.DemoIntentService 为第三⽅方⾃自定义的推送服务事件接收类
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), DemoIntentService.class);
        //        Call<Bean_UserLogin> call = HttpManager.getInstance().getHttpClient().testWeather();
        //
        //        call.enqueue(new HttpCallback<Bean_UserLogin>() {
        //            @Override
        //            protected boolean processData(Bean_UserLogin weather) {
        //
        //                return false;
        //            }
        //        });


    }

    public  String getImei() {

        TelephonyManager mTm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        String imei = mTm.getDeviceId();
        L.d(TAG, "ddddddddddddddddddddddddddd" + imei);

        return imei;
    }


}
