package com.doorcontrol.ruili.my.doorcontrol.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
public class ADD_KeyFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "ADD_KeyFragment";

    private EditText key_houseId, key_houseOwnerName, key_phoneNumber;

    private Button key_addfamily;


    public static Fragment newInstance() {

        Bundle args = new Bundle();
        ADD_KeyFragment fragment = new ADD_KeyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.addkeymeassage, container, false);
        init(inflate);
        return inflate;

    }

    private void init(View inflate) {
        key_houseId = (EditText) inflate.findViewById(R.id.key_houseId);
        key_houseOwnerName = (EditText) inflate.findViewById(R.id.key_houseOwnerName);
        key_phoneNumber = (EditText) inflate.findViewById(R.id.key_phoneNumber);
        key_addfamily = (Button) inflate.findViewById(R.id.key_addfamily);

        key_addfamily.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.key_addfamily:
                if (TextUtils.isEmpty(key_houseId.getText().toString().trim())) {
                    Toast.makeText(getActivity(), "用户id不为空", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(key_houseOwnerName.getText().toString().trim())) {
                    Toast.makeText(getActivity(), "姓名", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(key_phoneNumber.getText().toString().trim())) {
                    Toast.makeText(getActivity(), "电话", Toast.LENGTH_SHORT).show();
                } else {

                    addFramly();
                }
                break;
        }
    }

    private void addFramly() {

        String token = ShareUtil.getString("token");

        if (token != null) {

            //            int a = Integer.parseInt(str);   int 转化string
            //            int b = Integer.valueOf(str).intValue();
            String houseId = key_houseId.getText().toString().trim();
            String houseOwnerName = key_houseOwnerName.getText().toString().trim();
            String phoneNumber = key_phoneNumber.getText().toString().trim();
            L.d(TAG,"houseId" + houseId);
            L.d(TAG,"houseOwnerName" + houseOwnerName);
            L.d(TAG,"phoneNumber" + phoneNumber);
            //int houseid = Integer.parseInt(houseId);
            //   L.d("ssssssssssssssssss"+LoginActivity.mToken.toString());
            Call<ResponseHead> frmilys = HttpManager.getInstance().getHttpClient().getFrmilys(token,
                    houseId,
                    houseOwnerName,
                    phoneNumber);

            frmilys.enqueue(new Callback<ResponseHead>() {
                @Override
                public void onResponse(Call<ResponseHead> call, Response<ResponseHead> response) {
                    ResponseHead body = response.body();
                    if (body != null) {


                        if (body.resultCode == 0) {
                            L.d(TAG,"assssssssssssssssssssssssss" + body.resultCode);
                            Toast.makeText(getActivity(), "添加成功", Toast.LENGTH_SHORT).show();
                            getActivity().finish();
                        } else {
                            Toast.makeText(getActivity(), "添加失败,", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseHead> call, Throwable t) {

                }
            });
        } else {
            Toast.makeText(getActivity(), "请登录后再试", Toast.LENGTH_SHORT).show();
        }

    }
}
