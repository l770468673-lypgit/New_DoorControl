package com.doorcontrol.ruili.my.doorcontrol.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.doorcontrol.ruili.my.doorcontrol.R;
import com.doorcontrol.ruili.my.doorcontrol.utils.L;

/**
 * @
 * @类名称: ${type_name}
 * @类描述: ${todo}
 * @创建人：
 * @创建时间：${date} ${time}
 * @备注：
 */
public class User_Activity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "User_Activity";

    private ImageView user_activity_back;
    private ImageView user_own_setting_img;  //用户头像
    private TextView user_own_setting_name; //  用户的姓名
    private TextView user_own_setting_phone; //  用户的手机号
    private Intent mLoginintent;
    private LinearLayout useractivity_login;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity);

        initView();
    }

    private void initView() {
        useractivity_login = (LinearLayout) findViewById(R.id.useractivity_login);
        user_activity_back = (ImageView) findViewById(R.id.user_activity_back);

        user_activity_back.setOnClickListener(this); // 返回上一页
        useractivity_login.setOnClickListener(this);
        user_own_setting_img = (ImageView) findViewById(R.id.user_own_setting_img);

        user_own_setting_name = (TextView) findViewById(R.id.user_own_setting_name);
        user_own_setting_phone = (TextView) findViewById(R.id.user_own_setting_phone);

        //  user_own_setting_img.setOnClickListener(this);
        //  user_own_setting_name.setOnClickListener(this);
        //  user_own_setting_phone.setOnClickListener(this);

    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == Constant.CODE.REUQEST_CODE && resultCode == Constant.CODE.RESULT_CODE) {
//            String name = (String) data.getSerializableExtra(Constant.KEYS.LOGINNAME);
//            String pass = (String) data.getSerializableExtra(Constant.KEYS.LOGINPASS);
//
//            user_own_setting_name.setText(name);
//
//            L.d("ddddddddddddddddddddd  name and pass  is  " + name + pass);
//            ShareUtil.putString(Constant.KEYS.LOGINNAME, name);
//
//
//        }
//    }

    //    // 返回上一页
    @Override
    public void onClick(View v) {
        int id = v.getId();
        //
        switch (id) {
            //
            case R.id.user_activity_back:
                User_Activity.this.finish();
                L.d(TAG, "Im finish.");
                break;
            case R.id.useractivity_login:
                mLoginintent = new Intent(this, LoginActivity.class);
                startActivity(mLoginintent);
                break;
        }
    }


}
