package com.doorcontrol.ruili.my.doorcontrol.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.doorcontrol.ruili.my.doorcontrol.R;
import com.doorcontrol.ruili.my.doorcontrol.adapters.MylistMessageAdapter;
import com.doorcontrol.ruili.my.doorcontrol.manager.HttpManager;
import com.doorcontrol.ruili.my.doorcontrol.restful.model.Bean_listNews;
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
public class Punlic_messageFragment extends Fragment {
    private static final String TAG = "Punlic_messageFragment";

    private ListView publicmessage;

    private TextView message_list_textnull;
    private ProgressDialog mDialog;

    public static Fragment newInstance() {

        Bundle args = new Bundle();
        Punlic_messageFragment fragment = new Punlic_messageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.publicmesasage, container, false);
        init(inflate);


        LoadMyListView(inflate);

        return inflate;

    }


    private void LoadMyListView(final View view) {
        mDialog = new ProgressDialog(getActivity());
        mDialog.setMessage("数据加载中......");
        mDialog.show();


        String token = ShareUtil.getString("token");
        L.d(TAG, "'ssssstttssssssssss" + token);
        if (token != null) {


            Call<Bean_listNews> listMessage = HttpManager.getInstance().getHttpClient().getListMessage(token);
            listMessage.enqueue(new Callback<Bean_listNews>() {
                @Override
                public void onResponse(Call<Bean_listNews> call, Response<Bean_listNews> response) {
                    if (response.body() != null) {

                        mDialog.dismiss();
                        Bean_listNews body = response.body();
                        List<Bean_listNews.DataBean> data = body.getData();

                        L.d(TAG, "DDDDDDDDDDDDDDDDDDDDDDDDDD  data." + data.toString());

                        MylistMessageAdapter adapter = new MylistMessageAdapter(getActivity(), data);
                        publicmessage.setEmptyView(view.findViewById(R.id.message_list_textnull));
                        publicmessage.setAdapter(adapter);

                    }
                }

                @Override
                public void onFailure(Call<Bean_listNews> call, Throwable t) {

                }
            });


        } else if (token == null) {
            mDialog.dismiss();

        }

    }

    private void init(View inflate) {
        publicmessage = (ListView) inflate.findViewById(R.id.publicmessage);
        message_list_textnull = (TextView) inflate.findViewById(R.id.message_list_textnull);
        publicmessage.setEmptyView(inflate.findViewById(R.id.message_list_textnull));
    }

}
