package com.doorcontrol.ruili.my.doorcontrol.activitys;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.doorcontrol.ruili.my.doorcontrol.Custom.CircleImageView;
import com.doorcontrol.ruili.my.doorcontrol.R;
import com.doorcontrol.ruili.my.doorcontrol.fragments.Neighb_Fragment;
import com.doorcontrol.ruili.my.doorcontrol.manager.HttpManager;
import com.doorcontrol.ruili.my.doorcontrol.restful.model.Bean_FamilyList;
import com.doorcontrol.ruili.my.doorcontrol.restful.model.Bean_UserLogin;
import com.doorcontrol.ruili.my.doorcontrol.utils.Constant;
import com.doorcontrol.ruili.my.doorcontrol.utils.L;
import com.doorcontrol.ruili.my.doorcontrol.utils.ShareUtil;

import java.util.List;

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
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";
    public static String mToken;
    private EditText et_name, et_pass;   // 手机号 ， 密码
    private Button login;
    private ImageView moremessage_activity_back;

    private CircleImageView login_own_setting_img;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        initView();

        // get iMEI


    }


    private void initView() {

        login_own_setting_img = (CircleImageView) findViewById(R.id.login_own_setting_img);
        moremessage_activity_back = (ImageView) findViewById(R.id.moremessage_activity_back);
        et_name = (EditText) findViewById(R.id.username);
        et_pass = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(this);
        moremessage_activity_back.setOnClickListener(this);
        login_own_setting_img.setOnClickListener(this);

    }


    // 登陆
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.login:
                boolean isTel = true;  //标记位：true-是手机号码；false-不是手机号码
                                /* 判断输入的用户名是否是电话号码 */
                if (et_name.getText().toString().length() == 11) {
                    for (int i = 0; i < et_name.getText().toString().length(); i++) {
                        char c = et_name.getText().toString().charAt(i);
                        if (!Character.isDigit(c)) {  // 数字0-9
                            isTel = false;
                            break; //只要有一位不符合要求退出循环
                        }
                    }
                }            /*只有用户名、密码不为空，并且用户名为11位手机号码才允许登陆*/
                if (TextUtils.isEmpty(et_name.getText())) {
                    Toast.makeText(this, "用户名不能为空！", Toast.LENGTH_LONG).show();
                } else if (!isTel) {
                    Toast.makeText(this, "用户名请输入11位手机号码！", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(et_pass.getText())) {
                    Toast.makeText(this, "密码名不能为空！", Toast.LENGTH_LONG).show();
                } else {
                    //执行所需要的操作
                    // 登录使用
                   // MainActivity.handler_main.UnRegist();
                    Login_in();
                }
                break;

            case R.id.moremessage_activity_back:
                finish();
                break;
            case R.id.login_own_setting_img:
                // 从相册去照片
                LoadMaca();
                break;
        }


    }

    public void LoadMaca() {
        // TODO Auto-generated method stub
        // 获取mac地址
        String localMacAddress = getLocalMacAddress();
        String mac = localMacAddress.replace(":", "");
        L.d(TAG + mac);
    }

    // 获取本机的 mac 06-28
    public String getLocalMacAddress() {
        WifiManager wifi = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        String macAddress = info.getMacAddress();
        return macAddress;
    }

    public void Login_in() {

        String imei = ShareUtil.getString("imei");

        Call<Bean_UserLogin> userLogin = HttpManager.getInstance().getHttpClient().getUserLogin(et_name.getText().toString(), et_pass.getText().toString(), imei);
        L.d(TAG, "dddddddddddddddddddddd   userLogin is  " + et_name.getText().toString() + "-----" + et_pass.getText().toString() + "-----" + imei);
        userLogin.enqueue(new Callback<Bean_UserLogin>() {
            @Override
            public void onResponse(Call<Bean_UserLogin> call, Response<Bean_UserLogin> response) {
                if (response.body() != null) {
                    Bean_UserLogin body = response.body();

                    int resultCode = body.resultCode;
                    if (resultCode == 0) {
                        L.d("dddddddddddddddddddddd   userLogin is  " + body + "-----" + response);

                        String nickName = body.userInfo.nickName.toString();
                        mToken = body.userInfo.token.toString();
                        String token = body.userInfo.token.toString();
                        String vosCode = body.vosInfo.vosCode.toString();
                        String vosGateWay = body.vosInfo.vosGateWay.toString();
                        String vosPassword = body.vosInfo.vosPassword.toString();
                        L.d(TAG, "dddddddddddddddddddddd   nickName is  " + nickName + "");
                        L.d(TAG, "dddddddddddddddddddddd   token is  " + token + "");
                        L.d(TAG, "dddddddddddddddddddddd   vosCode is  " + vosCode + "");
                        L.d(TAG, "dddddddddddddddddddddd   vosGateWay is  " + vosGateWay + "");
                        L.d(TAG, "dddddddddddddddddddddd   vosPassword is  " + vosPassword + "");

                        ShareUtil.putString("user", et_name.getText().toString());
                        ShareUtil.putString("pass", et_pass.getText().toString());
                        ShareUtil.putString("token", token);
                        ShareUtil.putString("password", et_pass.getText().toString());
                        ShareUtil.putString("vosCode", vosCode);
                        ShareUtil.putString("vosGateWay", vosGateWay);
                        ShareUtil.putString("vosPassword", vosPassword);

                        Neighb_Fragment.user_own_setting_name.setText(et_name.getText().toString());
                        ShareUtil.putString(Constant.Shares.USER_OWN_SETTING_NAME, et_name.getText().toString());
                        MainActivity.home_user_name.setText(nickName);
                        ShareUtil.putString(Constant.Shares.HOME_USER_NAME, nickName);

                        MainActivity.handler_main.startSipRegister();
                        if (token != null) {
                            Call<Bean_FamilyList> framilyLists = HttpManager.getInstance().getHttpClient().getFramilyLists(token);
                            framilyLists.enqueue(new Callback<Bean_FamilyList>() {
                                @Override
                                public void onResponse(Call<Bean_FamilyList> call, Response<Bean_FamilyList> response) {

                                    if (response.body() != null) {
                                        int resultCode = response.body().resultCode;
                                        if (resultCode == 0) {
                                            List<Bean_FamilyList.DataBean> data = response.body().getData();
                                            for (int i = 0; i < data.size(); i++) {
                                                Bean_FamilyList.DataBean dataBean = data.get(i);
                                                Bean_FamilyList.DataBean.HouseBean house = dataBean.getHouse();
                                                String communityName = house.getCommunityName();

                                                MainActivity.home_sp_city.setText(communityName);

                                                ShareUtil.putString("home_sp_city", communityName);
                                                L.d(TAG, "communityName" + communityName);
                                            }
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<Bean_FamilyList> call, Throwable t) {

                                }
                            });
                        }

                        finish();
                    } else if (resultCode == 1) {
                        Toast.makeText(LoginActivity.this, "密码输入错误！", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Bean_UserLogin> call, Throwable t) {

                Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
            }
        });


    }

}
