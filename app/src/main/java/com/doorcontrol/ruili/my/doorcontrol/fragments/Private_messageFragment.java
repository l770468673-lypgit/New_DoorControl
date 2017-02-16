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
import com.doorcontrol.ruili.my.doorcontrol.adapters.PrivateMessageAdapter;
import com.doorcontrol.ruili.my.doorcontrol.manager.HttpManager;
import com.doorcontrol.ruili.my.doorcontrol.restful.model.Bean_message;
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
public class Private_messageFragment extends Fragment {
    private static final String TAG = "Private_messageFragment";

    private TextView message_list_textnull;
    private ListView privatemessage;
    private ProgressDialog mDialog;

    public static Fragment newInstance() {

        Bundle args = new Bundle();
        Private_messageFragment fragment = new Private_messageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.privatemessage, container, false);
        init(inflate);

        loaddatepri(inflate);
        return inflate;

    }


    private void loaddatepri(final View view) {
        mDialog = new ProgressDialog(getActivity());
        mDialog.setMessage("数据加载中......");
        mDialog.show();


        final String token = ShareUtil.getString("token");
        if (token != null) {
            Call<Bean_message> message = HttpManager.getInstance().getHttpClient().getMessage(token);
            message.enqueue(new Callback<Bean_message>() {
                @Override
                public void onResponse(Call<Bean_message> call, Response<Bean_message> response) {
                    Bean_message body = response.body();
                    if (body != null) {
                        mDialog.dismiss();
                        List<Bean_message.DataBean> data = body.getData();

                        PrivateMessageAdapter adapter = new PrivateMessageAdapter(getActivity(), data);

                        privatemessage.setEmptyView(view.findViewById(R.id.message_list_textnull));
                        privatemessage.setAdapter(adapter);


                    } else {

                    }
                }

                @Override
                public void onFailure(Call<Bean_message> call, Throwable t) {

                }
            });
        }else if (token == null) {

            mDialog.dismiss();
        }

    }


    private void init(View inflate) {

        message_list_textnull = (TextView) inflate.findViewById(R.id.message_list_textnull);
        privatemessage = (ListView) inflate.findViewById(R.id.privatemessage);

        privatemessage.setEmptyView(inflate.findViewById(R.id.message_list_textnull));
    }

}
