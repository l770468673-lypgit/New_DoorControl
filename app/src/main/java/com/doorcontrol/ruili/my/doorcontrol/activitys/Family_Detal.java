package com.doorcontrol.ruili.my.doorcontrol.activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.doorcontrol.ruili.my.doorcontrol.R;
import com.doorcontrol.ruili.my.doorcontrol.adapters.FamilyDetalAdapter;
import com.doorcontrol.ruili.my.doorcontrol.manager.HttpManager;
import com.doorcontrol.ruili.my.doorcontrol.restful.model.Bean_FamilyList;
import com.doorcontrol.ruili.my.doorcontrol.restful.model.ResponseHead;
import com.doorcontrol.ruili.my.doorcontrol.utils.L;
import com.doorcontrol.ruili.my.doorcontrol.utils.ShareUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @
 * @类名称: ${}
 * @类描述: $钥匙管理
 * @创建人： Lyp
 * @创建时间：${date} ${time}
 * @备注：
 */
public class Family_Detal extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemLongClickListener,
        FamilyDetalAdapter.CallbacktoItem {
    private static final String TAG = "Family_Detal";

    private ImageView familydetyal_activity_back;
    //  private Button edittextchange;
    private ImageButton detal_addkeymessage;
    private ListView frmily_listdetal;
    private FamilyDetalAdapter mAdapter;
    private TextView yaoshinull;// detal_changeseq;
    private Bundle mBundle;
    private Bean_FamilyList.DataBean mDatabean;
    private int mHouseIds;
    private String mHousIds;
    private List<Bean_FamilyList.DataBean.HouseOwnersBean> mHouseOwners;
    private List<Bean_FamilyList.DataBean.HouseOwnersBean> newhouseowens;
    private Integer mTag;


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fanily_detal);

        initview();
        //   loaddate();
        newhouseowens = new ArrayList<>();

    }

    @Override
    protected void onResume() {
        super.onResume();
        loaddate();
    }

    private void loaddate() {

        Intent intent = getIntent();
        mBundle = intent.getExtras();
        Bundle extras = intent.getExtras();
        int houseIds = extras.getInt("houseId");
        L.d(TAG, " house id is  " + houseIds);
        mHousIds = new String(houseIds + "");


        mDatabean = (Bean_FamilyList.DataBean) mBundle.getSerializable("bean");
        mHouseOwners = mDatabean.getHouseOwners();

        for (int i = 0; i < mHouseOwners.size(); i++) {
            newhouseowens.clear();
            newhouseowens.add(mHouseOwners.get(i));
        }


        mAdapter = new FamilyDetalAdapter(Family_Detal.this, mHouseOwners, this);


        frmily_listdetal.setEmptyView(findViewById(R.id.yaoshinull));

        frmily_listdetal.setAdapter(mAdapter);

        L.d(TAG, "sssssssssssssssssssssssssss  is   mAdapter.setData(databean)" + mDatabean.toString());

        //  lDate();

    }


    private void initview() {
        familydetyal_activity_back = (ImageView) findViewById(R.id.familydetyal_activity_back);
        detal_addkeymessage = (ImageButton) findViewById(R.id.detal_addkeymessage);
        frmily_listdetal = (ListView) findViewById(R.id.frmily_listdetal);
        yaoshinull = (TextView) findViewById(R.id.yaoshinull);
        //  detal_changeseq = (TextView) findViewById(R.id.detal_changeseq);
        //   edittextchange = (Button) findViewById(R.id.edittextchange);

        //  edittextchange.setOnClickListener(this);
        //detal_changeseq.setOnClickListener(this);
        familydetyal_activity_back.setOnClickListener(this);
        detal_addkeymessage.setOnClickListener(this);
        frmily_listdetal.setOnItemLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.familydetyal_activity_back:
                finish();
                break;
            //    case R.id.detal_changeseq:
            //     Toast.makeText(this, "sssssssssssssssssss", Toast.LENGTH_LONG).show();
            //     break;
            //            case R.id.edittextchange:
            //                Toast.makeText(this, "sssssssssssssssssss", Toast.LENGTH_LONG).show();
            //                            break;
            case R.id.detal_addkeymessage:
                Intent intent = new Intent(Family_Detal.this, AddKeyActivity.class);
                int houseId = mBundle.getInt("houseId");
                Bundle bundle = new Bundle();
                bundle.putInt("houseIds", houseId);
                intent.putExtras(bundle);
                L.d(TAG, "ssssssssssssssssssssss tiaozhuan  AddKeyActivity");
                startActivity(intent);
                break;
        }

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        AlertDialog.Builder builder = new AlertDialog.Builder(Family_Detal.this);

        builder.setMessage("确认删除吗");
        builder.setTitle("提示");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            private String mHouseOwnerIds;

            @Override
            public void onClick(DialogInterface dialog, int which) {

                String token = ShareUtil.getString("token");
                if (token != null) {
                    List<Bean_FamilyList.DataBean.HouseOwnersBean> houseOwners = mDatabean.getHouseOwners();
                    for (int i = 0; i < houseOwners.size(); i++) {
                        Bean_FamilyList.DataBean.HouseOwnersBean houseOwnersBean = houseOwners.get(i);
                        int houseOwnerId = houseOwnersBean.getHouseOwnerId();
                        mHouseOwnerIds = new String(houseOwnerId + "");
                    }
                    L.d(TAG, "sssssssss    mHouseOwnerIds   s  " + mHouseOwnerIds);
                    L.d(TAG, "mhouse id  is     " + mHousIds);

                    Call<ResponseHead> removeLists = HttpManager.getInstance().getHttpClient().getRemoveLists(
                            token,
                            mHousIds,
                            mHouseOwnerIds);

                    removeLists.enqueue(new Callback<ResponseHead>() {
                        @Override
                        public void onResponse(Call<ResponseHead> call, Response<ResponseHead> response) {
                            ResponseHead body = response.body();

                            if (body != null) {

                                L.d(TAG, "xxxxxxxxxxxxxx" + body.resultCode);
                                if (body.resultCode == 0) {
                                    Toast.makeText(Family_Detal.this, " 删除成功,请返回刷新 ", Toast.LENGTH_SHORT).show();


                                    L.d(TAG, "ssss    remove OK  and adapteris changed ");
                                } else if (body.resultCode == 1) {
                                    Toast.makeText(Family_Detal.this, "删除失败，非户主，没有权限" + body.resultCode, Toast.LENGTH_SHORT).show();
                                } else {

                                }

                            } else {
                                Toast.makeText(Family_Detal.this, "body is null, not   remove ", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseHead> call, Throwable t) {

                        }
                    });

                } else {
                    Toast.makeText(Family_Detal.this, "请登录后再试", Toast.LENGTH_SHORT).show();
                }

            }
        });
        builder.create().show();
        return false;
    }

    @Override
    public void Itemclick(View v) {
        // Toast.makeText(this, "dddddddddddd", Toast.LENGTH_LONG).show();
        mTag = (Integer) v.getTag();
        showAlertDialog();
        L.d(TAG, "tag is  " + mTag);
    }


    private void showAlertDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setView(LayoutInflater.from(this).inflate(R.layout.alert_dialog, null));
        dialog.show();
        dialog.getWindow().setContentView(R.layout.alert_dialog);

        Button btnPositive = (Button) dialog.findViewById(R.id.btn_add);
        Button btnNegative = (Button) dialog.findViewById(R.id.btn_cancel);
        final EditText etContent = (EditText) dialog.findViewById(R.id.et_content);

        //ok
        btnPositive.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Bean_FamilyList.DataBean.HouseOwnersBean houseOwnersBean = mHouseOwners.get(mTag);
                String newseq = etContent.getText().toString().trim();
                //当前的 房屋id 和 当前的排行
                int houseOwnerId = houseOwnersBean.getHouseOwnerId();
                String house_houseOwnerId = new String(houseOwnerId + "");
                int seq = houseOwnersBean.getSeq();
                String house_seq = new String(seq + "");

                String houseOwneroners = house_houseOwnerId + "_" + house_seq + "&" + house_houseOwnerId + "_" + newseq;

                L.d(TAG, "家庭id" + mHousIds + "houseid +seq" + house_seq + newseq);
                L.d(TAG, "家人id" + house_houseOwnerId + house_seq + newseq);


                if (isNullEmptyBlank(newseq)) {
                    etContent.setError("输入内如不能为空");
                } else {

                    String token = ShareUtil.getString("token");

                    Call<ResponseHead> framilylevel = HttpManager.getInstance().getHttpClient().getFramilylevel(token, mHousIds, houseOwneroners);
                    framilylevel.enqueue(new Callback<ResponseHead>() {
                        @Override
                        public void onResponse(Call<ResponseHead> call, Response<ResponseHead> response) {
                            ResponseHead body = response.body();
                            if (body != null) {
                                if (body.resultCode == 0) {
                                    L.d("调整成功");
                                    Toast.makeText(Family_Detal.this, "调整成功，请返回刷新", Toast.LENGTH_SHORT).show();

                                } else {

                                    L.d(TAG, "调整失败" + body.resultCode);
                                    Toast.makeText(Family_Detal.this, "调整失败", Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                L.d("body  is null");
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseHead> call, Throwable t) {

                        }
                    });


                    dialog.dismiss();

                }
            }
        });
        // 取消
        btnNegative.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
            }
        });
    }

    private static boolean isNullEmptyBlank(String str) {
        if (str == null || "".equals(str) || "".equals(str.trim()))
            return true;
        return false;
    }


}

