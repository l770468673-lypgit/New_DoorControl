package com.doorcontrol.ruili.my.doorcontrol.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.doorcontrol.ruili.my.doorcontrol.Custom.SideView;
import com.doorcontrol.ruili.my.doorcontrol.R;
import com.doorcontrol.ruili.my.doorcontrol.adapters.CityAdapter;
import com.doorcontrol.ruili.my.doorcontrol.bean.Bean_City;
import com.doorcontrol.ruili.my.doorcontrol.utils.Constant;
import com.doorcontrol.ruili.my.doorcontrol.utils.OkHttpUtil;
import com.doorcontrol.ruili.my.doorcontrol.utils.Parse_Json;

import java.util.List;

/**
 * @
 * @类名称: ${type_name}
 * @类描述: ${todo}
 * @创建人：
 * @创建时间：${date} ${time}
 * @备注：
 */

public class Home_Select_City extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, SideView.OnCheckedChangeListener {

    private static final String TAG = "Home_Select_City";
    private ImageView live_activity_back; // 返回键
    private ListView lv_cities;   // listview
    private CityAdapter mAdapter;

    //字母侧边栏
  //  private SideView mSv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_select_city);
        initView();

        // 加载城市的 数据
        loaddate();
    }

    private void loaddate() {

        OkHttpUtil.downJSON(Constant.CITY_URL.CITIES_URL, new OkHttpUtil.OnDownDataListener() {
            private List<Bean_City> mCitiesByJSON;

            @Override
            public void onResponse(String url, String json) {
                // 城市的数据
                mCitiesByJSON = Parse_Json.getCitiesByJSON(json);
                mAdapter.setDatas(mCitiesByJSON);
            }

            @Override
            public void onFailure(String url, String error) {

            }
        });
    }

    private void initView() {
        live_activity_back = (ImageView) findViewById(R.id.live_activity_back);

     //   mSv = (SideView) findViewById(R.id.sv);
     //   mSv.setOnCheckedChangeListener(this);
        lv_cities = (ListView) findViewById(R.id.lv_cities);
        mAdapter = new CityAdapter(Home_Select_City.this);
        lv_cities.setAdapter(mAdapter);

        //条目点击事件
        lv_cities.setOnItemClickListener(this);
        live_activity_back.setOnClickListener(this);// 返回键

    }

    @Override
    public void onClick(View v) {
        finish();
    }

    //lv_cities  条目点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = getIntent();
        intent.putExtra(Constant.KEYS.CITYDATAS, (Bean_City) mAdapter.getItem(position));
        setResult(Constant.CODE.RESULT_CODE, intent);
        finish();

    }



    @Override
    public void checkedLabel(int index, String label) {
        int eq = mAdapter.isEq(label);
        lv_cities.smoothScrollToPositionFromTop(eq, 0);
    }

    @Override
    public void eventup() {

    }
}
