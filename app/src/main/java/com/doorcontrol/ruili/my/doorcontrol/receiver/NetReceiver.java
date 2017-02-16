package com.doorcontrol.ruili.my.doorcontrol.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.doorcontrol.ruili.my.doorcontrol.activitys.MainActivity;
import com.doorcontrol.ruili.my.doorcontrol.utils.L;

/**
 * @
 * @类名称: ${}
 * @类描述: ${type_name}
 * @创建人： Lyp
 * @创建时间：${date} ${time}
 * @备注：
 */
public class NetReceiver extends BroadcastReceiver {
    private ConnectivityManager connectivityManager;
    private NetworkInfo info;
    private final static String TAG = "NetReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            // Log.d("mark", "网络状态已经改变");
            connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            info = connectivityManager.getActiveNetworkInfo();
            if (info != null && info.isAvailable()) {

                String name = info.getTypeName();

                L.d( TAG,"当前网络名称：" + name);
                    MainActivity.handler_main.startSipRegister();
            } else {
                L.d(TAG,"没有可用网络");
            }
        }
    }


    // 平板不适用 无 卡槽
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        // Toast.makeText(context, "lllll", 0).show();
//        Log.d("asdf", "dddddddddddddddddddddd network status chagned");
//        NetworkInfo.State wifiState = null;
//        NetworkInfo.State mobileState = null;
//        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        wifiState = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
//        mobileState = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
//        if (wifiState != null && mobileState != null && NetworkInfo.State.CONNECTED != wifiState
//                && NetworkInfo.State.CONNECTED == mobileState) {
//            MainActivity.handler_main.startSipRegister();
//            // 手机网络连接成功
//        } else if (wifiState != null && mobileState != null && NetworkInfo.State.CONNECTED != wifiState
//                && NetworkInfo.State.CONNECTED != mobileState) {
//
//            // 手机没有任何的网络
//        } else if (wifiState != null && NetworkInfo.State.CONNECTED == wifiState) {
//            // 无线网络连接成功.
//            MainActivity.handler_main.startSipRegister();
//
//        }
//    }
}