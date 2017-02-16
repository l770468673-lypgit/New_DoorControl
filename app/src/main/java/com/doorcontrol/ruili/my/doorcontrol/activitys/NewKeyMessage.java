package com.doorcontrol.ruili.my.doorcontrol.activitys;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.doorcontrol.ruili.my.doorcontrol.R;
import com.doorcontrol.ruili.my.doorcontrol.adapters.New_keyAdapter;
import com.doorcontrol.ruili.my.doorcontrol.manager.HttpManager;
import com.doorcontrol.ruili.my.doorcontrol.restful.model.Bean_FamilyList;
import com.doorcontrol.ruili.my.doorcontrol.utils.L;
import com.doorcontrol.ruili.my.doorcontrol.utils.ShareUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @
 * @类名称: ${}
 * @类描述: ${type_name}  家人列表
 * @创建人： Lyp
 * @创建时间：${date} ${time}
 * @备注：
 */
public class NewKeyMessage extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private static final String TAG = "NewKeyMessage";
    private ProgressDialog mDialog;
    private ListView New_keymessage;
    private ImageView newkeymessage_activity_back;
    private TextView new_list_textnull, refishadapter;
    private New_keyAdapter mAdapter;
    private Call<Bean_FamilyList> mFramilyLists;
    public List<Bean_FamilyList.DataBean> mData;
    private List<Bean_FamilyList.DataBean.HouseOwnersBean> mHouseOwners;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newkeymessage);

        initView();

        // load 房屋数据
    //    loaddate();

        New_keymessage.setOnItemClickListener(this);


    }


    public List<Bean_FamilyList.DataBean> Date() {
        mDialog = new ProgressDialog(NewKeyMessage.this);
        mDialog.setMessage("数据加载中......");
        mDialog.show();

        String token = ShareUtil.getString("token");

        mAdapter = new New_keyAdapter(NewKeyMessage.this);
        New_keymessage.setEmptyView(findViewById(R.id.new_list_textnull));
        New_keymessage.setAdapter(mAdapter);
        if (token != null) {

            mFramilyLists = HttpManager.getInstance().getHttpClient().getFramilyLists(token);
            mFramilyLists.enqueue(new Callback<Bean_FamilyList>() {

                private Bean_FamilyList mBody;

                @Override
                public void onResponse(Call<Bean_FamilyList> call, Response<Bean_FamilyList> response) {

                    mBody = response.body();
                    L.d(TAG, "ok");
                    if (response.body() != null) {
                        mDialog.dismiss();
                        int resultCode = mBody.resultCode;
                        if (resultCode == 0) {
                            mData = mBody.getData();
                            for (int i = 0; i < mData.size(); i++) {
                                Bean_FamilyList.DataBean dataBean = mData.get(i);
                                mHouseOwners = dataBean.getHouseOwners();
                            }
                            mAdapter.setData(mData);
                        }
                    } else {
                        Toast.makeText(NewKeyMessage.this, "resultCode is null ", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Bean_FamilyList> call, Throwable t) {
                    L.d(TAG, "失败");
                }
            });

        }else if (token == null) {
            mDialog.dismiss();

            Toast.makeText(NewKeyMessage.this, "请登录后再试", Toast.LENGTH_SHORT).show();
        }

        return mData;
    }

    @Override
    protected void onResume() {
        super.onResume();


        Date();
    }

    private void loaddate() {
        Date();
    }

    private void initView() {
        New_keymessage = (ListView) findViewById(R.id.New_keymessage);
        newkeymessage_activity_back = (ImageView) findViewById(R.id.newkeymessage_activity_back);
        new_list_textnull = (TextView) findViewById(R.id.new_list_textnull);
        refishadapter = (TextView) findViewById(R.id.refishadapter);

        newkeymessage_activity_back.setOnClickListener(this);
        refishadapter.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.newkeymessage_activity_back:
                finish();
                break;
            case R.id.refishadapter:
                Date();
                L.d(TAG, "sssssssssssssss+  loaddate(); ");

                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(NewKeyMessage.this, Family_Detal.class);

        Bean_FamilyList.DataBean dataBean = mData.get(position);
        Bean_FamilyList.DataBean.HouseBean house = dataBean.getHouse();
        int houseId = house.getHouseId();

        Bundle bundle = new Bundle();
        bundle.putSerializable("bean", dataBean);
        bundle.putInt("houseId", houseId);

        L.d(TAG, " house id   is  " + houseId);
        intent.putExtras(bundle);
        //   intent.putExtra("houseid", position + 1);
        startActivity(intent);
    }
}
