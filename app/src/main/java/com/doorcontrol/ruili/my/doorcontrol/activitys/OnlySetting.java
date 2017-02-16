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
public class OnlySetting extends AppCompatActivity implements View.OnClickListener {

    private ImageView setting_activity_back;

    private EditText homeid_setting;
    private EditText houseOwnerOrders_houseOwnerId, houseOwnerOrders_seq;

    private EditText houseOwnerOrders_newhouseOwnerId, houseOwnerOrders_newseq;
    private Button buttonlogin;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onlysetting);


        initView();

        // 更改 家人顺序
        // loadDate();


    }

    private void loadDate() {

        if (TextUtils.isEmpty(homeid_setting.getText())
                && TextUtils.isEmpty(houseOwnerOrders_houseOwnerId.getText())
                && TextUtils.isEmpty(houseOwnerOrders_seq.getText())
                && TextUtils.isEmpty(houseOwnerOrders_newhouseOwnerId.getText())
                && TextUtils.isEmpty(houseOwnerOrders_newseq.getText())) {
            Toast.makeText(this, "不能为空！", Toast.LENGTH_LONG).show();
        } else {
            //执行所需要的操作
            // 登录使用

            loadbuttonlogin();

        }


    }

    private void loadbuttonlogin() {
        String token = ShareUtil.getString("token");

        String homeid = homeid_setting.getText().toString().trim();

        String houseownerorders_houseownerid = houseOwnerOrders_houseOwnerId.getText().toString().trim(); // 家人id
        String houseownerorders_seq = houseOwnerOrders_seq.getText().toString().trim();
        String houseownerorders_newhouseownerid = houseOwnerOrders_newhouseOwnerId.getText().toString().trim();
        String houseownerorders_newseq = houseOwnerOrders_newseq.getText().toString().trim();

        String houseOwnerOrders = houseownerorders_houseownerid + "_" + houseownerorders_seq + "&"
                + houseownerorders_newhouseownerid + "_" + houseownerorders_newseq;

        Call<ResponseHead> framilylevel = HttpManager.getInstance().getHttpClient().getFramilylevel(token, homeid, houseOwnerOrders);

        framilylevel.enqueue(new Callback<ResponseHead>() {
            @Override
            public void onResponse(Call<ResponseHead> call, Response<ResponseHead> response) {
                ResponseHead body = response.body();
                if (body != null) {
                    if (body.resultCode == 0) {
                        L.d("调整成功");
                        finish();
                    } else {
                        L.d("调整失败");
                        Toast.makeText(OnlySetting.this, "调整失败", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    L.d("body  is null");
                }
            }

            @Override
            public void onFailure(Call<ResponseHead> call, Throwable t) {

            }
        });
    }

    private void initView() {
        setting_activity_back = (ImageView) findViewById(R.id.setting_activity_back);
        homeid_setting = (EditText) findViewById(R.id.homeid_setting);

        houseOwnerOrders_houseOwnerId = (EditText) findViewById(R.id.houseOwnerOrders_houseOwnerId);
        houseOwnerOrders_seq = (EditText) findViewById(R.id.houseOwnerOrders_seq);
        houseOwnerOrders_newhouseOwnerId = (EditText) findViewById(R.id.houseOwnerOrders_newhouseOwnerId);
        houseOwnerOrders_newseq = (EditText) findViewById(R.id.houseOwnerOrders_newseq);
        buttonlogin = (Button) findViewById(R.id.buttonlogin);

        setting_activity_back.setOnClickListener(this);
        buttonlogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.buttonlogin:
                loadDate();
                break;
            case R.id.setting_activity_back:
                finish();
                break;
        }

    }
}
