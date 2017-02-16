package com.doorcontrol.ruili.my.doorcontrol.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import android.view.View;

import com.doorcontrol.ruili.my.doorcontrol.activitys.MainActivity;
import com.doorcontrol.ruili.my.doorcontrol.fragments.Home_Fragment;
import com.doorcontrol.ruili.my.doorcontrol.manager.HttpManager;
import com.doorcontrol.ruili.my.doorcontrol.restful.model.Bean_UserLogin;
import com.doorcontrol.ruili.my.doorcontrol.restful.model.ResponseHead;
import com.doorcontrol.ruili.my.doorcontrol.utils.L;
import com.doorcontrol.ruili.my.doorcontrol.utils.ShareUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @
 * @类名称: ${}
 * @类描述: ${type_name}
 * @创建人： Lyp
 * @创建时间：${date} ${time}
 * @备注：
 */
public class LiveServices extends Service {
    private final static String TAG = "LiveServices";
    private static String sImei;

    @Override
    public void onCreate() {
        //L.d("DemoLog","TestService -> onCreate, Thread ID: " + Thread.currentThread().getId());
        super.onCreate();

        new Thread(new ThreadShow()).start();
    }

    public static void islive() {

        sImei = ShareUtil.getString("imei");
        String token = ShareUtil.getString("token");
        //   if ( LoginActivity.mToken != null) {
        if (token != null) {
            //    L.d("......................"+ LoginA`ctivity.mToken.toString());
            L.d(TAG, "......................" + token.toString());
            Call<ResponseHead> isLive = HttpManager.getInstance().getHttpClient().getIsLive(token, sImei);

            L.d(TAG, ".LiveServices  .islive is ...................." + isLive.toString());
            isLive.enqueue(new Callback<ResponseHead>() {


                private ResponseHead mBody;

                @Override
                public void onResponse(Call<ResponseHead> call, Response<ResponseHead> response) {

                    L.d(TAG, ".LiveServices  .response is .......response............." + response.body());
                    //  if (response.body() != null) {

                    mBody = response.body();
                    L.d(TAG, ".LiveServices  .body is .......body............." + mBody.resultCode);

                    if (mBody.resultCode == 3) { //body.resultCode == 1
                        // 注销
                        MainActivity.handler_main.UnRegist();

                        Home_Fragment.changeuser.setText("用户已经更换设备本次登陆已无效,点击重新登录");
                        Home_Fragment.changeuser.setVisibility(View.VISIBLE);

                    }
                    if (mBody.resultCode == 2) {


                        // 重新登录
                        Home_Fragment.changeuser.setText("");
                        Home_Fragment.changeuser.setVisibility(View.GONE);
                        String user = ShareUtil.getString("user");
                        String pass = ShareUtil.getString("pass");

                        // TODO: 2016/9/5
                        Call<Bean_UserLogin> userLogin = HttpManager.getInstance().getHttpClient().getUserLogin(user, pass, sImei);

                        userLogin.enqueue(new Callback<Bean_UserLogin>() {
                            @Override
                            public void onResponse(Call<Bean_UserLogin> call, Response<Bean_UserLogin> response) {
                                if (response.body() != null) {
                                    String token = response.body().userInfo.token.toString();
                                    ShareUtil.putString("token", token);
                                }
                            }

                            @Override
                            public void onFailure(Call<Bean_UserLogin> call, Throwable t) {

                            }
                        });
                    }
                    if (mBody.resultCode == 0) {
                        Home_Fragment.changeuser.setText("");
                        Home_Fragment.changeuser.setVisibility(View.GONE);
                        L.d(TAG, " ssssssssssssssssssssssssssssss   im is  live,, body is " + mBody.resultCode);

                    } else {
                        L.d(TAG, " ssssssssssssssssssssssssssssss  is not  , body is " + mBody.resultCode);
                    }
                }

                @Override
                public void onFailure(Call<ResponseHead> call, Throwable t) {

                    L.d(TAG, "LiveServices  is   Throwable  method  ");
                }
            });
            L.d(TAG, "--------------LiveServices-token  is  not   null -token is  ----" + token.toString());
        } else {
            L.d(TAG, "--------------LiveServices---token  is null  ");
        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        // L.d("DemoLog", "TestService -> onStartCommand, startId: " + startId + ", Thread ID: " + Thread.currentThread().getId());
        L.d(TAG, "--------------LiveServices------im onStartCommand");
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        //L.d("DemoLog", "TestService -> onBind, Thread ID: " + Thread.currentThread().getId());
        return null;
    }

    @Override
    public void onDestroy() {
        // L.d("DemoLog", "TestService -> onDestroy, Thread ID: " + Thread.currentThread().getId());
        if (LiveServices.this != null) {

        }
        super.onDestroy();
        L.d(TAG, "--------------------im died");
    }

    // 线程类
    class ThreadShow implements Runnable {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            while (true) {
                try {
                    Thread.sleep(10000);
                    Message msg = new Message();
                    msg.what = 44;
                    MainActivity.handler_main.sendMessage(msg);
                    // System.out.println("send...");
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    // System.out.println("thread error...");
                }
            }
        }
    }
}
