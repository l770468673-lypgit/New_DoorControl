package com.doorcontrol.ruili.my.doorcontrol.activitys;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.doorcontrol.ruili.my.doorcontrol.Custom.CircleImageView;
import com.doorcontrol.ruili.my.doorcontrol.R;
import com.doorcontrol.ruili.my.doorcontrol.manager.HttpManager;
import com.doorcontrol.ruili.my.doorcontrol.restful.model.ResponseHead;
import com.doorcontrol.ruili.my.doorcontrol.utils.L;
import com.doorcontrol.ruili.my.doorcontrol.utils.ShareUtil;
import com.squareup.picasso.Picasso;

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
public class CallPhone extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = "CallPhone";
    static Button but_close, but_open, but_ansewr; // 挂断，开门， 接听,头像
    private TextView call_textview, call_usernumber;
    private TextView minText;       //分
    private TextView secText;       //秒

    private boolean isPaused = false;
    private String timeUsed;
    private int timeUsedInsec;
    private CircleImageView circleimage_user;  // 来点头像
    public static int WaitisPhoneing = 0;  //  是否接听
    public static CallPhonehandler mCallhandler;

    public class CallPhonehandler extends  Handler{

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == 90) {

                String picobj = (String) msg.obj;
                L.d(TAG, "------------obj  90 is---------------------- oncreate" + picobj);
                if (picobj != null) {
                    Picasso.with(CallPhone.this).load(picobj).into(circleimage_user);

                }

            }
            if (msg.what == 89) {
                String obj = (String) msg.obj;
                L.d(TAG, "----------obj  89 is-------------------- clientid" + obj);



            }

        }
    }


    public Handler uiHandle = new Handler() {

        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    if (!isPaused) {
                        addTimeUsed();
                        updateClockUI();
                    }

                    uiHandle.sendEmptyMessageDelayed(1, 1000);

                    break;
                default:
                    break;
            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.callphone);
        mCallhandler = new CallPhonehandler();

        initView();

        but_close.setOnClickListener(this);
        but_open.setOnClickListener(this);
        but_ansewr.setOnClickListener(this);
        //  initPhone();

    }


    //获得 电话号码
    private void initPhone() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String number = extras.getString("number");

            String substringnumber = number.substring(1, number.length());
            call_usernumber.setText(substringnumber);
        }

    }

    private void initView() {
        but_close = (Button) findViewById(R.id.but_close);
        but_open = (Button) findViewById(R.id.but_open);
        but_ansewr = (Button) findViewById(R.id.but_ansewr);

        call_textview = (TextView) findViewById(R.id.call_textview);
        call_usernumber = (TextView) findViewById(R.id.call_usernumber);
        circleimage_user = (CircleImageView) findViewById(R.id.circleimage_user);

        minText = (TextView) findViewById(R.id.min);
        secText = (TextView) findViewById(R.id.sec);
        //    Bundle extras = getIntent().getExtras();
        // String picobj = extras.getString("picobj");


//        String vistorPicUrl = ShareUtil.getString("vistorPicUrl");
//        if (vistorPicUrl != null) {
//            Picasso.with(CallPhone.this).load(vistorPicUrl).into(circleimage_user);
//
//        }


    }

    @Override
    protected void onPause() {
         super.onPause();
        isPaused = true;

    }


    @Override
    protected void onResume() {
         super.onResume();
        isPaused = false;
    }

    private void startTime() {
        uiHandle.sendEmptyMessageDelayed(1, 1000);
    }

    /**
     * 更新时间的显示
     */
    private void updateClockUI() {
        minText.setText(getMin() + ":");
        secText.setText(getSec());
    }

    public void addTimeUsed() {
        timeUsedInsec = timeUsedInsec + 1;
        timeUsed = this.getMin() + ":" + this.getSec();
    }

    public CharSequence getMin() {
        return String.valueOf(timeUsedInsec / 60);
    }

    public CharSequence getSec() {
        int sec = timeUsedInsec % 60;
        return sec < 10 ? "0" + sec : String.valueOf(sec);
    }


    @Override
    public void onClick(View v) {

        if (v == but_close) {
            //挂断电话
            finish();
            WaitisPhoneing = 0;
            MainActivity.handler_main.HangupPhone();
            //  Intent intent = new Intent(this, MainActivity.class);
            //   startActivity(intent);
//            boolean vistorPicUrl1 = ShareUtil.removekey("vistorPicUrl");
        //    circleimage_user.setImageDrawable(getResources().getDrawable(R.mipmap.logo_ehme));

          //  L.d(TAG, "vistorPicUrl1  删除  " + vistorPicUrl1);
        }

        if (v == but_open) {
            if (WaitisPhoneing == 0) {
                //    opendoor();   不接电话主动开门
            }

            if (WaitisPhoneing == 1) {
                MainActivity.handler_main.sendDTMF();
                // Toast.makeText(CallPhone.this, "已开门，已接电话", Toast.LENGTH_LONG).show();
                //
              //  circleimage_user.setImageDrawable(getResources().getDrawable(R.mipmap.logo_ehme));
                WaitisPhoneing = 0;
//                boolean vistorPicUrl1 = ShareUtil.removekey("vistorPicUrl");
//                L.d(TAG, "vistorPicUrl1  删除  " + vistorPicUrl1);
            }
        }

        if (v == but_ansewr) {
            //接电话
            WaitisPhoneing = 1;
            MainActivity.handler_main.startAnswer();

            Animation animation = AnimationUtils.loadAnimation(this, R.anim.call_answer);
            but_ansewr.startAnimation(animation);

            uiHandle.postDelayed(new Runnable() {
                @Override
                public void run() {
                    call_textview.setText("正在通话中");
                }
            }, 1000);

            //为按钮Start注册监听器
            uiHandle.removeMessages(1);
            startTime();
            isPaused = false;
        }

    }

    private void opendoor() {
        String token = ShareUtil.getString("token");
        if (token != null) {
            final Call<ResponseHead> openDoor = HttpManager.getInstance().getHttpClient().getOpenDoor(token);
            openDoor.enqueue(new Callback<ResponseHead>() {
                @Override
                public void onResponse(Call<ResponseHead> call, Response<ResponseHead> response) {

                    ResponseHead body = response.body();
                    L.d(TAG, "" + body.resultCode);
                    if (body.resultCode == 0) {
                        Message message = new Message();
                        message.what = 22;
                        MainActivity.handler_main.HangupPhone();


                        //重新设置按下时的背景图片
                        //  openDoor.setImageDrawable(getResources().getDrawable(R.drawable.opendoor_btn_selected));
                        //   opendoorimg.setVisibility(View.VISIBLE);
                        //   L.d(TAG,"opoooooooooooooooooooo 按下 开门");

                        // handler_main.stopKeyTimeout();
                        //  handler_main.startKeyTimeout();
                    } else {
                        Toast.makeText(CallPhone.this, "开门失败", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseHead> call, Throwable t) {
                    L.d(TAG, "-------------- ------im error  ");
                }
            });
        } else {

        }
    }


}
