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
public class Remove_KeyFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "Remove_KeyFragment";

    private EditText key_removehouseId, key_removehouseOwnerid;


    private Button key_removefamily;

    public static Fragment newInstance() {

        Bundle args = new Bundle();
        Remove_KeyFragment fragment = new Remove_KeyFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.removemessage, container, false);
        init(inflate);
        return inflate;
    }

    private void init(View inflate) {
        key_removehouseId = (EditText) inflate.findViewById(R.id.key_removehouseId);
        key_removehouseOwnerid = (EditText) inflate.findViewById(R.id.key_removehouseOwnerid);
        key_removefamily = (Button) inflate.findViewById(R.id.key_removefamily);


        key_removefamily.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.key_removefamily:
                if (TextUtils.isEmpty(key_removehouseId.getText().toString().trim())) {
                    Toast.makeText(getActivity(), "用户id不为空", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(key_removehouseOwnerid.getText().toString().trim())) {
                    Toast.makeText(getActivity(), "家人id不为空", Toast.LENGTH_SHORT).show();
                } else {

                    removeFramly();
                    //Toast.makeText(getActivity(), "执行删除了", Toast.LENGTH_SHORT).show();
                }

                break;
        }

    }

    private void removeFramly() {

         String token = ShareUtil.getString("token");
        if (token != null) {
             String removehouseId = key_removehouseId.getText().toString().trim();
             String removehouseOwnerid = key_removehouseOwnerid.getText().toString().trim();

            Call<ResponseHead> removeLists = HttpManager.getInstance().getHttpClient().getRemoveLists(
                    token,
                    removehouseId,
                    removehouseOwnerid);

            removeLists.enqueue(new Callback<ResponseHead>() {
                @Override
                public void onResponse(Call<ResponseHead> call, Response<ResponseHead> response) {
                    ResponseHead body = response.body();

                    if (body != null) {

                        L.d(TAG, "xxxxxxxxxxxxxx" + body.resultCode);
                        if (body.resultCode == 0) {
                            Toast.makeText(getActivity(), " 删除成功 ", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "body.resultCode  !=0 ", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getActivity(), "body is null, not   remove " , Toast.LENGTH_SHORT).show();
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
