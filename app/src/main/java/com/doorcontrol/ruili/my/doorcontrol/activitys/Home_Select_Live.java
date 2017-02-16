package com.doorcontrol.ruili.my.doorcontrol.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.doorcontrol.ruili.my.doorcontrol.R;

/**
 * @
 * @类名称: ${type_name}
 * @类描述: ${todo}
 * @创建人：
 * @创建时间：${date} ${time}
 * @备注：
 */

public class Home_Select_Live extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "Home_Select_Live";
    private ImageView live_activity_back; // 返回键

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_select_live);

        initView();
    }

    private void initView() {

        live_activity_back = (ImageView) findViewById(R.id.live_activity_back);
        live_activity_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
