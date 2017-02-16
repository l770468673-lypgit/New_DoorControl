package com.doorcontrol.ruili.my.doorcontrol.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
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
public class Home_Moremessage extends AppCompatActivity implements View.OnClickListener {

    private TextView xiaoxichaung_message_jitiao;  // 共有几条消息记录

    private ImageView moremessage_activity_back;  // back键

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_moremessage);

        initView();
    }

    private void initView() {
        xiaoxichaung_message_jitiao = (TextView) findViewById(R.id.xiaoxichaung_message_jitiao);
        moremessage_activity_back = (ImageView) findViewById(R.id.moremessage_activity_back);
        moremessage_activity_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
