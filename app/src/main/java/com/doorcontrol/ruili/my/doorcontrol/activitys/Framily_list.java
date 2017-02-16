package com.doorcontrol.ruili.my.doorcontrol.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.doorcontrol.ruili.my.doorcontrol.R;
import com.doorcontrol.ruili.my.doorcontrol.adapters.myFramilyAdapter;
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
 * @类描述: ${type_name}
 * @创建人： Lyp
 * @创建时间：${date} ${time}
 * @备注：
 */
public class Framily_list extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "Framily_list";
    private ImageView mTell_activity_back;

    private ListView framilylist_lv;
    private Call<Bean_FamilyList> mFramilyLists;
    private Bean_FamilyList mBody;
    private myFramilyAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.framily_list);

        initView();
        LoadDate();
    }

    public void LoadDate() {

        String token = ShareUtil.getString("token");

        adapter = new myFramilyAdapter(Framily_list.this);
        //listview wushuju , 则显示 空
        framilylist_lv.setEmptyView(findViewById(R.id.list_textnull));
        framilylist_lv.setAdapter(adapter);
        //   if (LoginActivity.mToken != null) {
        if (token != null) {

            mFramilyLists = HttpManager.getInstance().getHttpClient().getFramilyLists(token);
            mFramilyLists.enqueue(new Callback<Bean_FamilyList>() {

                private Bean_FamilyList.DataBean mDataBean;
                private List<Bean_FamilyList.DataBean.HouseOwnersBean> mHouseOwners;
                private Bean_FamilyList.DataBean.HouseBean mHouse;

                @Override
                public void onResponse(Call<Bean_FamilyList> call, Response<Bean_FamilyList> response) {

                    mBody = response.body();
                    L.d(TAG,"失败");
                    if (response.body() != null) {
                        int resultCode = mBody.resultCode;
                        if (resultCode == 0) {
                            List<Bean_FamilyList.DataBean> data = mBody.getData();
//                            for (int i = 0; i < data.size(); i++) {
//
//                                mDataBean = data.get(i);
//                                mHouse = mDataBean.getHouse();
//                                int houseId = mHouse.getHouseId();
//                                String houseName = mHouse.getHouseName();
//                                String buildingName = mHouse.getBuildingName();
//                                String communityName = mHouse.getCommunityName();
//
//                                L.d("houseId" + houseId);
//                                L.d("buildingName" + buildingName);
//                                L.d("houseName" + houseName);
//                                L.d("communityName" + communityName);
//                                mHouseOwners = mDataBean.getHouseOwners();
//                                for (int j = 0; j < mHouseOwners.size(); j++) {
//
//                                    Bean_FamilyList.DataBean.HouseOwnersBean houseOwnersBean = mHouseOwners.get(j);
//
//                                    int houseOwnerId = houseOwnersBean.getHouseOwnerId();
//                                    String houseOwnerName = houseOwnersBean.getHouseOwnerName();
//                                    String phoneNumber = houseOwnersBean.getPhoneNumber();
//                                    int seq = houseOwnersBean.getSeq();
//
//
//                                    L.d("houseOwnerId" + houseOwnerId);
//                                    L.d("seq" + seq);
//                                    L.d("houseOwnerName" + houseOwnerName);
//                                    L.d("phoneNumber" + phoneNumber);
//                                }
//
//                            }
                       adapter.setData(data);

                        }
                    } else {
                        Toast.makeText(Framily_list.this, "resultCode is null ", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Bean_FamilyList> call, Throwable t) {


                    L.d(TAG,"失败");
                }
            });

        } else {

            Toast.makeText(Framily_list.this, "请登录后再试", Toast.LENGTH_SHORT).show();
        }

    }

    private void initView() {

        mTell_activity_back = (ImageView) findViewById(R.id.tell_activity_back);
        framilylist_lv = (ListView) findViewById(R.id.framilylist_lv);

        mTell_activity_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        finish();
    }
}
