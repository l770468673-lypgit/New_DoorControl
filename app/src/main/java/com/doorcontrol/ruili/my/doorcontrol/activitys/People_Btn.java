package com.doorcontrol.ruili.my.doorcontrol.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.doorcontrol.ruili.my.doorcontrol.R;

/**
 * @
 * @类名称: ${type_name}
 * @类描述: ${todo}
 * @创建人：
 * @创建时间：${date} ${time}
 * @备注：
 */
public class People_Btn extends AppCompatActivity implements View.OnClickListener {
    private ImageView people_activity_back;

    private ListView people_btn_listview; //  listview 显示数据

    private TextView kerenlaifang_message_jitiao;  // 来访纪录总条数

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.people_btn);

        initView();
    }

    private void initView() {

        kerenlaifang_message_jitiao=(TextView) findViewById(R.id.kerenlaifang_message_jitiao);

        people_activity_back = (ImageView) findViewById(R.id.people_activity_back);
        people_activity_back.setOnClickListener(this);

        people_btn_listview = (ListView) findViewById(R.id.people_btn_listview);

    }



    // 返回键
    @Override
    public void onClick(View v) {
        finish();

    }
}
