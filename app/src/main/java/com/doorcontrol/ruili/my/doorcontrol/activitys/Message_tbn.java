package com.doorcontrol.ruili.my.doorcontrol.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.doorcontrol.ruili.my.doorcontrol.R;
import com.doorcontrol.ruili.my.doorcontrol.adapters.MyNewsAdapter;
import com.doorcontrol.ruili.my.doorcontrol.bean.Bean_TodayNews;
import com.doorcontrol.ruili.my.doorcontrol.utils.Constant;
import com.doorcontrol.ruili.my.doorcontrol.utils.OkHttpUtil;
import com.doorcontrol.ruili.my.doorcontrol.utils.Parse_Json;

import java.util.List;

/**
 * @
 * @类名称: ${type_name}
 * @类描述:
 * @创建人：
 * @创建时间：${date} ${time}
 * @备注：
 */
public class Message_tbn extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private static final String TAG = "Message_tbn";
    private ImageView message_activity_back;  //返回键
    private List<Bean_TodayNews.ResultBean.DataBean> mNewDate;
    private String mNewurl;
    private ListView message_activity_lv;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_btn);

        initView();
    }


    private void LoadDate() {

        //
        //        FormBody formBody = new FormBody.Builder()
        //                .add("type", type)
        //                .add("id", "popular")
        //                .build();

        //        Request request2 = new Request.Builder()
        //                .url("http://apis.baidu.com/3023/news/channel")
        //                .post(formBody)
        //                .addHeader("apikey", "122f112830dccbcd587639df0666d697")
        //                .build();
        //        L.d(TAG, "request2  dd的数据时 ：==" + request2.toString());
        //
        //        Call call2 = okHttpClient.newCall(request2);
        //
        //        call2.enqueue(new Callback() {
        //            @Override
        //            public void onFailure(Call call, IOException e) {
        //
        //            }
        //
        //            @Override
        //            public void onResponse(Call call, Response response) throws IOException {
        //                String response_json = response.body().string();
        //                Log.d(TAG, "onResponse: 获得登陆结果：" + response_json);
        //
        ////                Headers headers = response.headers();
        ////                Set<String> names = headers.names();
        ////                for (String name : names) {
        ////                    String value = headers.get(name);
        ////                    Log.d(TAG, "onResponse: 响应头部数据：" + name + " : " + value);
        ////                }
        //            }
        //        });

        OkHttpUtil.downJSON(Constant.TODAYNEWS.NEWSURL, new OkHttpUtil.OnDownDataListener() {


            @Override
            public void onResponse(String url, String json) {
                //
                //                Bean_TodayNews bean_todayNews = new Gson().fromJson(json, Bean_TodayNews.class);
                //                Bean_TodayNews.ResultBean result = bean_todayNews.getResult();
                //                List<Bean_TodayNews.ResultBean.DataBean> data = result.getData();
                mNewDate = Parse_Json.getNewDate(json);

                MyNewsAdapter adapter = new MyNewsAdapter(Message_tbn.this, mNewDate);
                message_activity_lv.setAdapter(adapter);

            }

            @Override
            public void onFailure(String url, String error) {

            }
        });
    }

    private void initView() {
        message_activity_back = (ImageView) findViewById(R.id.message_activity_back);
        message_activity_back.setOnClickListener(this);//返回键

        message_activity_lv = (ListView) findViewById(R.id.message_activity_lv);
        LoadDate();


        message_activity_lv.setOnItemClickListener(this);
    }

    //返回键
    @Override
    public void onClick(View v) {

        finish();
    }

    //    条目点击事件
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {


        Intent intent = new Intent(Message_tbn.this, Mess_Web.class);
        Bean_TodayNews.ResultBean.DataBean dataBean = mNewDate.get(position);

        String uniquekey = dataBean.getUniquekey();
           intent.putExtra("mNewurl", dataBean.getUrl());


        startActivity(intent);


    }
}
