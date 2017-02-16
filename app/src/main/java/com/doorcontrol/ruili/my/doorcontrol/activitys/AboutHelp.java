package com.doorcontrol.ruili.my.doorcontrol.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.doorcontrol.ruili.my.doorcontrol.R;
import com.doorcontrol.ruili.my.doorcontrol.manager.HttpManager;
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
public class AboutHelp extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "AboutHelp";
    private ImageView about_activity_back;

    private Button help_ChangePassword;
    private EditText about_oldpass, about_newpass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.abouthelp);


        intiView();
    }

    private void intiView() {

        about_activity_back = (ImageView) findViewById(R.id.about_activity_back);
        help_ChangePassword = (Button) findViewById(R.id.help_ChangePassword);
        about_oldpass = (EditText) findViewById(R.id.about_oldpass);
        about_newpass = (EditText) findViewById(R.id.about_newpass);

        help_ChangePassword.setOnClickListener(this);
        about_activity_back.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        int id = view.getId();

        switch (id) {

            case R.id.help_ChangePassword:

                if (TextUtils.isEmpty(about_oldpass.getText().toString().trim())) {
                    Toast.makeText(AboutHelp.this, "旧密码不为空", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(about_newpass.getText().toString().trim())) {
                    Toast.makeText(AboutHelp.this, "密码不为空", Toast.LENGTH_SHORT).show();
                } else {

                    ChangPassWord();

                }

                break;

            case R.id.about_activity_back:

                finish();
                break;
        }

    }

    private void ChangPassWord() {

        String token = ShareUtil.getString("token");
     //   if (LoginActivity.mToken != null) {
        if (token!= null) {
            L.d("TAG" + token.toString());

            Call<ResponseHead> newPassWord = HttpManager.getInstance().getHttpClient().
                    getNewPassWord(about_oldpass.getText().toString(), about_newpass.getText().toString());
            newPassWord.enqueue(new Callback<ResponseHead>() {
                @Override
                public void onResponse(Call<ResponseHead> call, Response<ResponseHead> response) {
                    if (response.body() != null) {

                        Toast.makeText(AboutHelp.this, "密码已更改", Toast.LENGTH_SHORT).show();

                    }else {
                        Toast.makeText(AboutHelp.this, "密码更改失败", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<ResponseHead> call, Throwable t) {

                }
            });

        } else {
            Toast.makeText(AboutHelp.this, "请登录后再试", Toast.LENGTH_SHORT).show();
        }
    }
}
