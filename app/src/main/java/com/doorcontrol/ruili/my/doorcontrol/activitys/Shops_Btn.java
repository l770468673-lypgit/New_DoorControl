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

public class Shops_Btn extends AppCompatActivity implements View.OnClickListener {

    private ListView shop_listview;

    private TextView shop_text;
    private ImageView shop_activity_back;// 返回键

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shops_btn);

        initView();
    }

    private void initView() {
        shop_listview = (ListView) findViewById(R.id.shop_listview);
        shop_text = (TextView) findViewById(R.id.shop_text);
        shop_activity_back = (ImageView) findViewById(R.id.shop_activity_back);
        shop_listview.setEmptyView(shop_text);
        shop_activity_back.setOnClickListener(this);
    }

    // 返回键
    @Override
    public void onClick(View v) {
        finish();
    }
}
