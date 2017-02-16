package com.doorcontrol.ruili.my.doorcontrol.activitys;

import android.content.Intent;
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
public class AddKeyActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "AddKeyActivity";
    private ImageView addkeyl_activity_back;
    private Button addkey_btn;
    private EditText add_framily_number, add_family_name;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addkeyactivity);


        initview();
    }

    private void initview() {
        addkeyl_activity_back = (ImageView) findViewById(R.id.addkeyl_activity_back);
        addkey_btn = (Button) findViewById(R.id.addkey_btn);
        add_framily_number = (EditText) findViewById(R.id.add_framily_number);
        add_family_name = (EditText) findViewById(R.id.add_family_name);

        addkey_btn.setOnClickListener(this);
        addkeyl_activity_back.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {

            case R.id.addkeyl_activity_back:
                finish();
                break;

            case R.id.addkey_btn:

                if (TextUtils.isEmpty(add_framily_number.getText().toString().trim())) {
                    Toast.makeText(this, "电话不为空", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(add_family_name.getText().toString().trim())) {
                    Toast.makeText(this, "姓名不为空", Toast.LENGTH_SHORT).show();
                } else {
                    AddKey();
                }
                break;

        }
    }

    private void AddKey() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int houseId = bundle.getInt("houseIds");
        L.d(TAG, "sssssssssssssssss" + houseId);
        String houseid = String.valueOf(houseId);
        String token = ShareUtil.getString("token");
        L.d(TAG, "sssssssssssssssss" + token);
        if (token != null) {

            String phonenumber = add_framily_number.getText().toString().trim();
            String username = add_family_name.getText().toString().trim();

            Call<ResponseHead> frmilys = HttpManager.getInstance().getHttpClient().getFrmilys(token,
                    houseid,
                    username,
                    phonenumber);

            L.d(TAG, "sssssssssssssssss" + frmilys);
            frmilys.enqueue(new Callback<ResponseHead>() {
                @Override
                public void onResponse(Call<ResponseHead> call, Response<ResponseHead> response) {
                    ResponseHead body = response.body();
                    if (body != null) {
                        if (body.resultCode == 0) {

                            L.d(TAG, "assssssssssssssssssssssssss" + body.resultCode);
                            Toast.makeText(AddKeyActivity.this, "添加成功，请返回刷新", Toast.LENGTH_SHORT).show();

                            finish();

                        } else {
                            Toast.makeText(AddKeyActivity.this, "添加失败,", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseHead> call, Throwable t) {
                    Toast.makeText(AddKeyActivity.this, "添加失败,", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(AddKeyActivity.this, "请登录后再试", Toast.LENGTH_SHORT).show();
        }


    }
}
