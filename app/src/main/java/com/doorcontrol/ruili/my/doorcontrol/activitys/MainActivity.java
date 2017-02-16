package com.doorcontrol.ruili.my.doorcontrol.activitys;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.doorcontrol.ruili.my.doorcontrol.Custom.CircleImageView;
import com.doorcontrol.ruili.my.doorcontrol.R;
import com.doorcontrol.ruili.my.doorcontrol.fragments.Home_Fragment;
import com.doorcontrol.ruili.my.doorcontrol.fragments.Neighb_Fragment;
import com.doorcontrol.ruili.my.doorcontrol.getui.DemoIntentService;
import com.doorcontrol.ruili.my.doorcontrol.getui.DemoPushService;
import com.doorcontrol.ruili.my.doorcontrol.manager.HttpManager;
import com.doorcontrol.ruili.my.doorcontrol.receiver.NetReceiver;
import com.doorcontrol.ruili.my.doorcontrol.restful.model.ResponseHead;
import com.doorcontrol.ruili.my.doorcontrol.services.LiveServices;
import com.doorcontrol.ruili.my.doorcontrol.utils.Constant;
import com.doorcontrol.ruili.my.doorcontrol.utils.L;
import com.doorcontrol.ruili.my.doorcontrol.utils.ShareUtil;
import com.doorcontrol.ruili.my.doorcontrol.utils.Utils;
import com.igexin.sdk.PushManager;
import com.vvsip.ansip.IVvsipService;
import com.vvsip.ansip.IVvsipServiceListener;
import com.vvsip.ansip.VvsipCall;
import com.vvsip.ansip.VvsipDTMF;
import com.vvsip.ansip.VvsipService;
import com.vvsip.ansip.VvsipTask;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener, IVvsipServiceListener {
    private static final long MAX_TIME = 2000;
    private RadioButton home_btn, Neighborhoods_btn;   //  底部按钮
    private boolean isBackPressed = false;
    private RadioGroup home_rgb;     // RadioGroup

    private LinearLayout lly_user_name;  //本 用户的 点击事件
    private CircleImageView home_user_imgs;//  圆形的 头像
    public static TextView home_user_name;//  用户的姓名
    private static final String TAG = "MainActivity";

    private ImageView opendoorimg;
    public static ImageButton opendoor;
    public static Button home_sp_city;   // 选择的城市
    //   private Button home_sp_place;   // 选择的小区

    /* 头像文件 */
    private static final String IMAGE_FILE_NAME = "temp_head_image.jpg";

    /* 请求识别码 */
    private static final int CODE_GALLERY_REQUEST = 0xa0;  //  相册
    private static final int CODE_CAMERA_REQUEST = 0xa1;   //相机
    private static final int CODE_RESULT_REQUEST = 0xa2;

    // 裁剪后图片的宽(X)和高(Y),480 X 480的正方形。
    private static int output_X = 480;
    private static int output_Y = 480;


    //    private int cityId = 1;
    //    private String cityName = "北京";
    static String mTag = "MainActivity";

    public static HandlerMain handler_main;
    private List<VvsipCall> mVvsipCalls = null;

    private List<String> neighborsList;

    //    static String host = "139.129.202.235:5060";
    //    //static String user = "960000003";//
    //    static String user = "960000022";//
    //    static String pwd = "yihuijia123?";
    public static IVvsipService mService;
    private NetReceiver netReceiver;
    private Intent mIntent;
    public PowerManager.WakeLock mWakeLock;
    private Bitmap mPhoto;


    public class HandlerMain extends Handler {

        private String mSub_number;

        // 注册
        public void startSipRegister() {
            this.postDelayed(new Runnable() {

                public void run() {
                    // TODO Auto-generated method stub
                    initRegist();
                }
            }, 3000);
        }

        //  接电话
        public void startAnswer() {

            for (VvsipCall _pCall : mVvsipCalls) {
                if (_pCall.cid > 0 && _pCall.mState < 2 && _pCall.mIncomingCall) {
                    // ANSWER EXISTING CALL
                    int i = _pCall.answer(200, 1);
                    mService = VvsipService.getService();
                    if (mService != null) {
                        if (i >= 0) {
                            mService.stopPlayer();
                            mService.setSpeakerModeOff();
                            mService.setAudioInCallMode();
                        }
                    }
                    break;
                }
            }
        }

        //挂电话
        public void HangupPhone() {

            VvsipCall pCall = null;
            L.d(TAG, "onClick1");
            for (VvsipCall _pCall : mVvsipCalls) {
                if (_pCall.cid > 0 && _pCall.mState <= 2) {
                    pCall = _pCall;
                    break;
                }
                //			if (_pCall.cid > 0 && _pCall.mState < 2 && _pCall.mIncomingCall) {
                //				_pCall.answer(486, 0);
                //				IVvsipService _service = VvsipService.getService();
                //				if (_service == null)
                //					return;
                //				_service.setSpeakerModeOff();
                //				return;
                //			}
            }
            Log.e(mTag, "onClick2");
            if (pCall == null)
                return;
            Log.e(mTag, "onClick3");
            IVvsipService _service = VvsipService.getService();
            if (_service == null)
                return;
            VvsipTask _vvsipTask = _service.getVvsipTask();
            if (_vvsipTask == null)
                return;
            pCall.stop();
            _service.setSpeakerModeOff();
            return;


        }

        // 发送dtmf
        public void sendDTMF() {
            MainActivity.this.runOnUiThread(new Runnable() {
                public void run() {
                    IVvsipService _service = VvsipService.getService();
                    if (_service == null)
                        return;
                    _service.sendDTMF("*");
                }
            });

        }

        //注销
        public void UnRegist() {
            MainActivity.this.runOnUiThread(new Runnable() {
                public void run() {
                    IVvsipService _service = VvsipService.getService();
                    if (_service == null)
                        return;
                    _service.StopVvsipLayer();
                }
            });

        }


        // 土司消失
        private Runnable timeoutKeydown = new Runnable() {
            public void run() {
                // TODO Auto-generated method stub
                opendoorimg.setVisibility(View.GONE);

            }
        };

        public void startKeyTimeout() {
            this.removeCallbacks(timeoutKeydown);
            this.postDelayed(timeoutKeydown, 1500);
        }

        public void stopKeyTimeout() {
            this.removeCallbacks(timeoutKeydown);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            L.d(TAG, "ddddddddddddddddddd   ddddddddd jni call back " + msg.what + " " + msg.obj);

            //挂断
            if ((msg.what == 22 || msg.what == 21)
                    && (msg.obj.toString().contains("CALL: CLOSED")
                    || msg.obj.toString().contains("CALL: RELEASED"))) {

                CallPhone.but_close.callOnClick();


                L.d(TAG, "ddddddddddddddddddd   断挂断a挂断挂断挂断挂断挂断挂断aa");
            }
            //振铃
            else if (msg.what == 5
                    && msg.obj.toString().contains("100 Trying")) {

                L.d(TAG, "ddddddddddddddddddda响铃响铃响铃响铃响铃响铃a");
            }
            //接通
            else if (msg.what == 7
                    && msg.obj.toString().contains("200 OK")) {

                L.d(TAG, "ddddddddddddddddddd   话通话通话通话通话a");
            } else if (msg.what == 88) {
                // ascii 码 * 号键开门
                if (msg.obj.toString().contains("Received DTMF: *")) {
                    // ----高压开门---- // 拉高接口电压
                    // 正在通话的时候 * --暂停 -执行声音1 开门
                    // showTextToast(R.layout.toast);
                }

            }
            //-1未接来电
            if (msg.what == -1 && msg.obj.toString().contains("未接来电")) {

                CallPhone.but_close.callOnClick();
                // MainActivity.handler_main.HangupPhone();
            }

            if (msg.what == 9 && msg.obj.toString().contains("486 Busy Here")) {
                handler_main.HangupPhone();
                mWakeLock.release();

            }

            // 来电话
            else if (msg.what == -1 && (msg.obj.toString().contains("来电"))) {

                //来电(<sip:701@139.129.202.235>)str.substring(str.indexOf("value1"),str.indexOf("value2")
                String phonenumber = msg.obj.toString();
                mSub_number = phonenumber.substring(phonenumber.indexOf(":"), phonenumber.indexOf("@"));

                PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
                mWakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "TAG");
                mWakeLock.acquire();
                // ----高压开门---- // 拉高接口电压
                // 正在通话的时候 * --暂停 -执行声音1 开门
                // showTextToast(R.layout.toast);..
                Bundle bundle = new Bundle();
                mIntent = new Intent(MainActivity.this, CallPhone.class);
                bundle.putString("number", mSub_number);
                mIntent.putExtras(bundle);
                startActivity(mIntent);
            }
            if (msg.what == 44) {
                LiveServices.islive();
            }

            if (msg.what == 90) {

                String picobj = (String) msg.obj;
                L.d(TAG, "------------obj  90 is---------------------- oncreate" + picobj);
                //  mIntent = new Intent(MainActivity.this, CallPhone.class);
//                Intent in = new Intent();
//                Bundle picbundle = new Bundle();
//                picbundle.putString("picobj", picobj);
//
//
//                // startActivity(mIntent);
//
//                in.putExtras(picbundle);
//                startActivity(in);
            }
            if (msg.what == 89) {
                String obj = (String) msg.obj;
                L.d(TAG, "----------obj  89 is-------------------- clientid" + obj);

                //                mIntent= new Intent(MainActivity.this,CallPhone.class);
                //                startActivity(mIntent);

            }


        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        handler_main = new HandlerMain();
        netReceiver = new NetReceiver();
        setContentView(R.layout.activity_main);


        //   UpgradeResponse();// 检查更新
        startServices();
        getServices();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        this.registerReceiver(netReceiver, filter);
        // 初始化首页的控件
        initView();

        //
        //        String packageName = getPackageName();
        //        UpdateHelper.getInstance().init(getApplicationContext(), Color.parseColor("#0A93DB"));
        //        UpdateHelper.getInstance().setDebugMode(true);
        //        UpdateHelper.getInstance().manualUpdate(packageName);

        // 注册
        //  initzhuce();
        L.d(TAG, "---------------------------------- oncreate");

        //接电话
        handler_main.startAnswer();

        // 发送dtmf
        handler_main.sendDTMF();

        // 挂电话
        handler_main.HangupPhone();

       // getImei();
    }


    private String getImei() {

        TelephonyManager mTm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        String imei = mTm.getDeviceId();
        L.d(TAG, "imei is  " + imei);
        ShareUtil.putString("imei", imei);

        return imei;
    }

    private void startServices() {
        mIntent = new Intent(this, LiveServices.class);
        startService(mIntent);
    }

    private void initRegist() {
        IVvsipService _service = VvsipService.getService();
        if (_service != null) {
            //    _service.register(host, user, pwd);
            String token = ShareUtil.getString("token");
            if (token != null) {
                // if ( LoginActivity.mToken!= null) {

                String vosGateWay = ShareUtil.getString("vosGateWay");//host
                String vosCode = ShareUtil.getString("vosCode");
                String vosPassword = ShareUtil.getString("vosPassword");

                if (vosPassword != null && vosGateWay != null && vosCode != null) {
                    _service.register(vosGateWay, vosCode, vosPassword);
                    L.d(TAG, "ddd i'm is showing" + vosCode.toString());
                }
                //   _service.register(host, user, pwd);
            } else {
                L.d(TAG, "ddd i'm is NOT showing+—  token");
            }

        }


    }

    @Override
    public void onDestroy() {

        IVvsipService _service = VvsipService.getService();
        if (_service != null)
            _service.removeListener(this);
        if (mVvsipCalls != null) {
            mVvsipCalls.clear();
            mVvsipCalls = null;
        }

        super.onDestroy();
        //   Log.d(mTag, "lifecycle // onDestroy");
        if (netReceiver != null) {
            unregisterReceiver(netReceiver);
        }

        handler_main.UnRegist();


        /** 退出Activity是，停止服务 */
        stopService(mIntent);
    }

    private void getServices() {
        IVvsipService _service = VvsipService.getService();
        Log.d(mTag, "lifecycle // _service");
        if (_service != null) {
            Log.i(mTag, "ddd reg stack " + handler_main);
            _service.addListener(this);
            _service.setMessageHandler(handler_main);
            VvsipDTMF _vvsipDtmf = VvsipDTMF.getVvsipDTMF();
            if (_vvsipDtmf != null) {
                Log.i(mTag, "ddd reg stack stack dtmf " + _vvsipDtmf);
                _vvsipDtmf.setHandler(handler_main);
            }

            Log.d(mTag, "lifecycle // addListener");
        } else {
            Log.d(mTag, "lifecycle // _service==null");
        }

        if (mVvsipCalls == null) {
            mVvsipCalls = new ArrayList<VvsipCall>();
        }

    }

    private void initView() {
        String username = ShareUtil.getString(Constant.Shares.HOME_USER_NAME);

        opendoorimg = (ImageView) findViewById(R.id.opendoorimg);
        home_rgb = (RadioGroup) findViewById(R.id.rgb);         //  RadioGroup
        home_btn = (RadioButton) findViewById(R.id.home_btn);  //首页 --btn
        Neighborhoods_btn = (RadioButton) findViewById(R.id.Neighborhoods_btn);  //  社区的 --btn
        lly_user_name = (LinearLayout) findViewById(R.id.lly_user_name);     // 个人页面的布局 lly


        home_sp_city = (Button) findViewById(R.id.home_sp_city);// 选择的小区

        String city_name = ShareUtil.getString(Constant.KEYS.CITY_NAME);
        if (city_name == null) {
            home_sp_city.setText("您的小区");
        } else {
            home_sp_city.setText(city_name);
        }

        home_user_imgs = (CircleImageView) findViewById(R.id.home_user_imgs); // 刘晓庆__图标
        home_user_name = (TextView) findViewById(R.id.home_user_name);     //   刘晓庆

        if (username != null) {
            home_user_name.setText(username);
        }

        home_rgb.setOnCheckedChangeListener(this);           // 首页点击按钮的条状页面的操作 --> 跳转页面


        //  home_sp_city.setOnClickListener(this);
        //  home_sp_place.setOnClickListener(this);

        home_rgb.getChildAt(0).performClick();//模拟点击第一个RB
        opendoor = (ImageButton) findViewById(R.id.opendoor);


        String imagebitmap = ShareUtil.getString("imagebitmap");

        if (imagebitmap != null) {
            //第二步:利用Base64将字符串转换为ByteArrayInputStream
            byte[] byteArray = Base64.decode(imagebitmap, Base64.DEFAULT);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
            //第三步:利用ByteArrayInputStream生成Bitmap
            Bitmap bitmap = BitmapFactory.decodeStream(byteArrayInputStream);
            home_user_imgs.setImageBitmap(bitmap);
        }

        //  lly_user_name.setOnClickListener(this);       // 个人中心的   页面跳转


        opendoor.setOnClickListener(this);
        //opendoor.setOnTouchListener(this);

    }

    //返回当前的城市
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        //        if (requestCode == Constant.CODE.REUQEST_CODE && resultCode == Constant.CODE.RESULT_CODE) {
        //                    Bean_City citydatas = (Bean_City) intent.getSerializableExtra(Constant.KEYS.CITYDATAS);
        //                    //     int cityid = city_id.getCityid();
        //                    //cityName = citydatas.getCityname();
        //                   // home_sp_city.setText(this.cityName);
        //
        //                    //ShareUtil.putString(Constant.KEYS.CITY_NAME, cityName);
        //                }
        //
        //                // 用户没有进行有效的设置操作，返回
        //                if (resultCode == RESULT_CANCELED) {
        //                    Toast.makeText(getApplication(), "取消", Toast.LENGTH_LONG).show();
        //                    return;
        //                }

        switch (requestCode) {
            // 画册 然后 剪切
            case CODE_GALLERY_REQUEST:
                cropRawPhoto(intent.getData());
                break;

            //x相机
            case CODE_CAMERA_REQUEST:
                if (Utils.hasSdcard()) {
                    File tempFile = new File(Environment.getExternalStorageDirectory(), IMAGE_FILE_NAME);
                    Uri uri = Uri.fromFile(tempFile);
                    cropRawPhoto(Uri.fromFile(tempFile));
                } else {
                    Toast.makeText(getApplication(), "没有SDCard!", Toast.LENGTH_LONG)
                            .show();
                }

                break;

            case CODE_RESULT_REQUEST:
                // intent不是空 设置
                if (intent != null) {
                    setImageToHeadView(intent);
                }

                break;
        }
        super.onActivityResult(requestCode, resultCode, intent);

    }

    // 首页点击按钮的条状页面的操作 --> 跳转页面
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.home_btn:
                getSupportFragmentManager().beginTransaction().replace(R.id.home_frg, new Home_Fragment()).commit();
                break;
            case R.id.Neighborhoods_btn:
                getSupportFragmentManager().beginTransaction().replace(R.id.home_frg, new Neighb_Fragment()).commit();
                break;
        }
    }


    //双击退出
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == event.getKeyCode()) {
            if (isBackPressed) {
                finish();
            } else {
                //				new Timer().schedule(task, when);
                isBackPressed = true;
                Toast.makeText(MainActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();

                new Timer().schedule(new TimerTask() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        isBackPressed = false;
                    }
                }, MAX_TIME);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void OpenDoors() {

        String token = ShareUtil.getString("token");
        if (token != null) {
            L.d("body..mainactivity.." + token);
            final Call<ResponseHead> openDoor = HttpManager.getInstance().getHttpClient().getOpenDoor(token);
            openDoor.enqueue(new Callback<ResponseHead>() {
                @Override
                public void onResponse(Call<ResponseHead> call, Response<ResponseHead> response) {

                    ResponseHead body = response.body();
                    L.d("body..mainactivity.." + body.resultCode);
                    if (body.resultCode == 0) {

                        //重新设置按下时的背景图片
                        //  openDoor.setImageDrawable(getResources().getDrawable(R.drawable.opendoor_btn_selected));
                        opendoorimg.setVisibility(View.VISIBLE);
                        L.d("opoooooooooooooooooooo 按下 开门");


                        handler_main.stopKeyTimeout();
                        handler_main.startKeyTimeout();
                    } else {
                        Toast.makeText(MainActivity.this, "开门失败", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseHead> call, Throwable t) {
                    L.d("-------------- ------im error  ");
                }
            });
        } else {
            Toast.makeText(MainActivity.this, "不登陆无法开门，请先登录", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // com.getui.demo.DemoPushService 为第三⽅方⾃自定义推送服务
        PushManager.getInstance().initialize(this.getApplicationContext(), DemoPushService.class);
        // com.getui.demo.DemoIntentService 为第三⽅方⾃自定义的推送服务事件接收类
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), DemoIntentService.class);
        String clientid = PushManager.getInstance().getClientid(this);
        L.d(TAG, "clientid is the  " + clientid);



        String imei = ShareUtil.getString("imei");
        L.d(TAG, "imei is  " + imei);
        boolean b = PushManager.getInstance().bindAlias(MainActivity.this, imei);
        L.d(TAG, " 绑定" + b + "ss");

    }

    // 个人中心的   页面跳转
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.opendoor:

                //if (LoginActivity.mToken != null) {
                OpenDoors();

                break;

            /**
             *
             case R.id.home_sp_city:
             按钮的返回值
             Intent home_sp_city_intent = new Intent(MainActivity.this, Home_Select_City.class);
             startActivityForResult(home_sp_city_intent, Constant.CODE.REUQEST_CODE);
             break;
             case R.id.home_sp_place:
             Intent home_sp_place_intent = new Intent(MainActivity.this, Home_Select_Live.class);
             startActivity(home_sp_place_intent);
             break;

             case R.id.lly_user_name:


             AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

             builder.setMessage("选取您的头像");

             builder.setTitle("选取您的头像");
             builder.setNegativeButton("从相册选取", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
            choseHeadImageFromGallery();

            }
            });
             builder.setPositiveButton("拍照", new DialogInterface.OnClickListener() {

            @Override public void onClick(DialogInterface dialog, int which) {

            choseHeadImageFromCameraCapture();
            }
            });
             builder.create().show();

             break;

             */
        }
    }


    @Override
    public void onNewVvsipCallEvent(final VvsipCall call) {

        MainActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                try {
                    if (call == null) {
                        return;
                    }

                    if (mVvsipCalls == null)
                        return;
                    mVvsipCalls.add(call);

                    if (Build.VERSION.SDK_INT >= 5) {
                        getWindow()
                                .addFlags( // WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                                        // |
                                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                                                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                                                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                    }

                } catch (Exception e) {
                    Log.e(mTag, "onNewVvsipCallEvent: " + e);
                }
            }
        });
    }

    @Override
    public void onRemoveVvsipCallEvent(final VvsipCall call) {

        MainActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                try {
                    if (call == null) {
                        return;
                    }

                    // 4 crash detected here for 4.0.9 with mVvsipCalls=NULL
                    if (mVvsipCalls == null)
                        return;
                    mVvsipCalls.remove(call);

                    if (mVvsipCalls.size() == 0) {
                        if (Build.VERSION.SDK_INT >= 5) {
                            getWindow()
                                    .clearFlags( // WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                                            // |
                                            WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                                                    | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                                                    | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                        }
                    }
                } catch (Exception e) {
                    L.e(mTag, "onRemoveVvsipCallEvent: " + e);
                }
            }
        });
    }

    @Override
    public void onStatusVvsipCallEvent(VvsipCall call) {

    }

    @Override
    public void onRegistrationEvent(int rid, String remote_uri, int code, String reason) {

    }

    /**
     * 裁剪原始的图片
     */
    public void cropRawPhoto(Uri uri) {

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");

        // 设置裁剪
        intent.putExtra("crop", "true");

        // aspectX , aspectY :宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX , outputY : 裁剪图片宽高
        intent.putExtra("outputX", output_X);
        intent.putExtra("outputY", output_Y);
        intent.putExtra("return-data", true);

        startActivityForResult(intent, CODE_RESULT_REQUEST);
    }


    /**
     * 提取保存裁剪之后的图片数据，并设置头像部分的View
     */
    private void setImageToHeadView(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mPhoto = extras.getParcelable("data");
            home_user_imgs.setImageBitmap(mPhoto);

            //第一步:将Bitmap压缩至字节数组输出流ByteArrayOutputStream
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            mPhoto.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
            //第二步:利用Base64将字节数组输出流中的数据转换成字符串String
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            String imageString = new String(Base64.encodeToString(byteArray, Base64.DEFAULT));
            //第三步:将String保持至SharedPreferences
            //            SharedPreferences sharedPreferences=getSharedPreferences("testSP", Context.MODE_PRIVATE);
            //            SharedPreferences.Editor editor=sharedPreferences.edit();

            ShareUtil.putString("imagebitmap", imageString);
        }
    }

    // 从本地相册选取图片作为头像
    private void choseHeadImageFromGallery() {
        Intent intentFromGallery = new Intent();
        // 设置文件类型
        intentFromGallery.setType("image/*");
        intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intentFromGallery, CODE_GALLERY_REQUEST);
    }

    // 启动手机相机拍摄照片作为头像
    private void choseHeadImageFromCameraCapture() {
        Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // 判断存储卡是否可用，存储照片文件
        if (Utils.hasSdcard()) {
            Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), IMAGE_FILE_NAME));
            String extraOutput = MediaStore.EXTRA_OUTPUT;  //自定义裁切输出位置
            intentFromCapture.putExtra(extraOutput, uri);
        }

        startActivityForResult(intentFromCapture, CODE_CAMERA_REQUEST);
    }


}
