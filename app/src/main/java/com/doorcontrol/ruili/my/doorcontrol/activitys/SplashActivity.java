/*
  vvphone is a SIP app for android.
  vvsip is a SIP library for softphone (SIP -rfc3261-)
  Copyright (C) 2003-2010  Bluegoby - <bluegoby@163.com>
 */

package com.doorcontrol.ruili.my.doorcontrol.activitys;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import com.doorcontrol.ruili.my.doorcontrol.R;
import com.doorcontrol.ruili.my.doorcontrol.getui.DemoIntentService;
import com.doorcontrol.ruili.my.doorcontrol.getui.DemoPushService;
import com.doorcontrol.ruili.my.doorcontrol.manager.HttpManager;
import com.doorcontrol.ruili.my.doorcontrol.restful.model.CheckUpgradeResponse;
import com.doorcontrol.ruili.my.doorcontrol.utils.L;
import com.igexin.sdk.PushManager;
import com.vvsip.ansip.IVvsipService;
import com.vvsip.ansip.IVvsipServiceListener;
import com.vvsip.ansip.VvsipCall;
import com.vvsip.ansip.VvsipService;
import com.vvsip.ansip.VvsipServiceBinder;
import com.vvsip.ansip.VvsipTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends Activity implements IVvsipServiceListener {
    private static final String TAG = "SplashActivity";
    protected int _splashTime = 3000;
    protected Handler _exitHandler = null;
    protected Runnable _exitRunnable = null;
    protected Handler _startServiceHandler = null;
    protected Runnable _startServiceRunnable = null;

    private ServiceConnection connection;
    private AlertDialog.Builder mB;
    private int mCurrentCode;

    //
    //    private TextView mTextView_link;
    //    private TextView mTextView_licenselink;
    //    private TextView myVersion;
    private TextView currentversion;

    private boolean isFirstIn = false;
    private static final int TIME = 2000;
    private static final int GO_HOME = 1000;
    private static final int GO_GUIDE = 1001;


    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                //                case GO_HOME:
                //                    goHome();
                //                    break;

                case GO_GUIDE:
                    goGuide();
                    break;
            }

        }

        ;
    };
    private String mVersion;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        L.d(TAG, "lifecycle // onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        init();

        // com.getui.demo.DemoPushService 为第三⽅方⾃自定义推送服务
        PushManager.getInstance().initialize(this.getApplicationContext(), DemoPushService.class);
        // com.getui.demo.DemoIntentService 为第三⽅方⾃自定义的推送服务事件接收类
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), DemoIntentService.class);

        try {
            currentversion = (TextView) findViewById(R.id.currentversion);
            PackageManager packageManager = getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
            mVersion = packInfo.versionName;
            currentversion.setText(mVersion);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        //        mTextView_link = (TextView) findViewById(R.id.TextView_link);
        //        if (mTextView_link != null) {
        //            MovementMethod lMM = LinkMovementMethod.getInstance();
        //            if (lMM != null) {
        //                mTextView_link.setMovementMethod(lMM);
        //                mTextView_link.setText(Html.fromHtml(" "));
        //            }
        //            mTextView_licenselink = (TextView) findViewById(R.id.TextView_licencelink);
        //            if (lMM != null) {
        //                mTextView_licenselink.setMovementMethod(lMM);
        //                mTextView_licenselink.setText(Html.fromHtml(" "));
        //            }
        //        }
        //
        //        myVersion = (TextView) findViewById(R.id.my_version);
        //        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        //        String today = formatter.format(new Date());
        //        if(today.compareTo("2016-02-08")>0){
        //            myVersion.setVisibility(View.VISIBLE);
        //            myVersion.setText(myVersion.getText()+"\n"+" ");
        //        }else{
        //            myVersion.setVisibility(View.GONE);
        //        }


        // Runnable exiting the splash screen and launching the menu
        _exitRunnable = new Runnable() {
            public void run() {
                exitSplash();
            }
        };
        // Run the exitRunnable in in _splashTime ms
        _exitHandler = new Handler();

        IVvsipService _service = VvsipService.getService();
        if (_service != null) {
            _exitHandler.postDelayed(_exitRunnable, 0);
            return;
        }

        _exitHandler.postDelayed(_exitRunnable, _splashTime);

        _startServiceHandler = new Handler();

        _startServiceRunnable = new Runnable() {
            public void run() {

                Intent intent = new Intent(SplashActivity.this.getApplicationContext(), VvsipService.class);
                startService(intent);

                connection = new ServiceConnection() {
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        Log.i("ActivitySplash", "Connected!");
                        IVvsipService _service = ((VvsipServiceBinder) service).getService();
                        _service.addListener(SplashActivity.this);
                    }

                    public void onServiceDisconnected(ComponentName name) {
                        Log.i("ActivitySplash", "Disconnected!");
                    }
                };

                bindService(intent, connection, Context.BIND_AUTO_CREATE);
                Log.i("ActivitySplash", "bindService done!");
            }
        };

        _startServiceHandler.postDelayed(_startServiceRunnable, 0);
    }


    private void UpgradeResponse() {
        try {
            //获取packagemanager的实例
            PackageManager packageManager = getPackageManager();
            //getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
            mCurrentCode = packInfo.versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String packageName = getPackageName();
        Call<CheckUpgradeResponse> upGrade = HttpManager.getInstance().getHttpClient().getUpGrade(packageName);
        upGrade.enqueue(new Callback<CheckUpgradeResponse>() {

            private CheckUpgradeResponse.AppInfoBean mAppInfo;

            @Override
            public void onResponse(Call<CheckUpgradeResponse> call, Response<CheckUpgradeResponse> response) {
                CheckUpgradeResponse body = response.body();

                mAppInfo = body.getAppInfo();
                int versionCode = mAppInfo.getVersionCode();
                if (versionCode > mCurrentCode) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);

                    builder.setMessage("更新");
                    builder.setTitle("提示");
                    builder.setNegativeButton("确认下载", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String appName = "易慧家";
                            String version = mAppInfo.getVersion();
                            //设置你的操作事项
                        }
                    });
                    builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.create().show();

                }
            }

            @Override
            public void onFailure(Call<CheckUpgradeResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onDestroy() {
        L.d(TAG, "lifecycle // onDestroy");
        super.onDestroy();

        IVvsipService _service = VvsipService.getService();
        if (_service != null) {
            _service.removeListener(this);
        }

        _exitHandler.removeCallbacks(_startServiceRunnable);
        _exitHandler.removeCallbacks(_exitRunnable);
        if (connection != null) {
            unbindService(connection);
            connection = null;
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // Remove the exitRunnable callback from the handler queue
            _exitHandler.removeCallbacks(_exitRunnable);
            // Run the exit code manually
            exitSplash();
        }
        //	Log.d("------------------------------", "onTouchEvent");
        return true;


    }

    private void exitSplash() {
        L.d(TAG, "lifecycle // exitSplash");
        VvsipTask vvsipTask = VvsipTask.getVvsipTask();
        if (vvsipTask != null && VvsipTask.global_failure != 0) {
            //            mB = new AlertDialog.Builder(this);
            //            //  dialog
            //            mB.setIcon(R.mipmap.ehome_logo);
            //            mB.setTitle(getString(R.string.app_name));
            //            //		b.setMessage("global_installation_failure");
            //            mB.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            //                public void onClick(DialogInterface dialog, int whichButton) {
            //                    finish();
            //                }
            //            });
            //            mB.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            //                public void onClick(DialogInterface dialog, int whichButton) {
            //                    finish();
            //                }
            //            });
            //            mB.show();

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setClass(this.getApplicationContext(), VvsipService.class);

            stopService(intent);
            L.d(TAG, "VvsipService 结束 ");
        } else {


            finish();
            goHome();
            //            Intent intent = new Intent();
            //            intent.setClass(SplashActivity.this, MainActivity.class);
            //            startActivity(intent);
            L.d(TAG, "VideoPlayerActivity,跳转");

        }


    }

    public void onNewVvsipCallEvent(VvsipCall call) {
        // TODO Auto-generated method stub

    }

    public void onRemoveVvsipCallEvent(VvsipCall call) {
        // TODO Auto-generated method stub

    }

    public void onStatusVvsipCallEvent(VvsipCall call) {
        // TODO Auto-generated method stub

    }

    public void onRegistrationEvent(int rid, String remote_uri, final int code, String reason) {
        // TODO Auto-generated method stub
        SplashActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                if (code >= 200 && code < 300) {
                    // Remove the exitRunnable callback from the handler queue
                    _exitHandler.removeCallbacks(_exitRunnable);
                    // Run the exit code manually
                    exitSplash();
                }
            }
        });
    }


    private void init() {
        SharedPreferences perPreferences = getSharedPreferences("jike", MODE_PRIVATE);
        isFirstIn = perPreferences.getBoolean("isFirstIn", true);
        if (!isFirstIn) {
            mHandler.sendEmptyMessageDelayed(GO_HOME, TIME);
        } else {
            mHandler.sendEmptyMessageDelayed(GO_GUIDE, TIME);
            SharedPreferences.Editor editor = perPreferences.edit();
            editor.putBoolean("isFirstIn", false);
            editor.commit();
        }

    }

    private void goHome() {
        Intent i = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private void goGuide() {
        Intent i = new Intent(SplashActivity.this, Guide.class);
        startActivity(i);
        finish();
    }


}
