package com.doorcontrol.ruili.my.doorcontrol.activitys;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.doorcontrol.ruili.my.doorcontrol.R;
import com.doorcontrol.ruili.my.doorcontrol.adapters.OpenDoorAdapter;
import com.doorcontrol.ruili.my.doorcontrol.manager.HttpManager;
import com.doorcontrol.ruili.my.doorcontrol.restful.model.Bean_AddRecord;
import com.doorcontrol.ruili.my.doorcontrol.utils.L;
import com.doorcontrol.ruili.my.doorcontrol.utils.ShareUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @
 * @类名称: ${}
 * @类描述: ${type_name}  开门记录
 * @创建人： Lyp
 * @创建时间：${date} ${time}
 * @备注：
 */
public class OpenDoorRecord extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "OpenDoorRecord";
    private ImageView mOpendoor_activity_back;
    private Call<Bean_AddRecord> mOpenRecord;
    private ListView opendoorlist;
    private TextView opendoortext;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opendoorrecord);

        initView();

        // jiazai 加载开门记录
        loadDates();
    }

    private void loadDates() {
        mDialog = new ProgressDialog(OpenDoorRecord.this);
        mDialog.setMessage("数据加载中......");
        mDialog.show();

        String token = ShareUtil.getString("token");

        L.d(TAG, "ssssssssssssssstokentokentokentokenss" + token);

        if (token != null) {
            mOpenRecord = HttpManager.getInstance().getHttpClient().getOpenRecord(token);

            mOpenRecord.enqueue(new Callback<Bean_AddRecord>() {

                private Bean_AddRecord mBody;

                @Override
                public void onResponse(Call<Bean_AddRecord> call, Response<Bean_AddRecord> response) {
                    if (response.body() != null) {

                        mDialog.dismiss();
                        mBody = response.body();

                        int resultCode = mBody.resultCode;
                        if (resultCode == 0) {
                            List<Bean_AddRecord.DataBean> data = mBody.getData();

                            OpenDoorAdapter adapter = new OpenDoorAdapter(OpenDoorRecord.this, data);
                            opendoorlist.setEmptyView(findViewById(R.id.opendoortext));
                            opendoorlist.setAdapter(adapter);

                            L.d(TAG, "data is " + data.toString());
                        } else {
                            Toast.makeText(OpenDoorRecord.this, "resultCode!=0", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(OpenDoorRecord.this, "resultCode!=null", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Bean_AddRecord> call, Throwable t) {

                }
            });
        } else if (token == null) {
            mDialog.dismiss();

            Toast.makeText(OpenDoorRecord.this, "请登录后再试", Toast.LENGTH_SHORT).show();
        }


    }

    private void initView() {


        mOpendoor_activity_back = (ImageView) findViewById(R.id.opendoor_activity_back);
        opendoorlist = (ListView) findViewById(R.id.opendoorlist);
        opendoortext = (TextView) findViewById(R.id.opendoortext);

        mOpendoor_activity_back.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        finish();
    }
}
