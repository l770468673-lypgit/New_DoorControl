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
public class Talking_Btn extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Talking_Btn";
    private ImageView talking_activity_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talking_btn);

        inirtView();
    }

    private void inirtView() {

        talking_activity_back = (ImageView) findViewById(R.id.talking_activity_back);
        talking_activity_back.setOnClickListener(this); // 返回键
    }


    // 返回键
    @Override
    public void onClick(View v) {

        finish();
    }
}
