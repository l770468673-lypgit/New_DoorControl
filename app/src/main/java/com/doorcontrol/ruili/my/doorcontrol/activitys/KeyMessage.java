package com.doorcontrol.ruili.my.doorcontrol.activitys;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.doorcontrol.ruili.my.doorcontrol.R;
import com.doorcontrol.ruili.my.doorcontrol.adapters.KeyMessAdapter;
import com.doorcontrol.ruili.my.doorcontrol.fragments.ADD_KeyFragment;
import com.doorcontrol.ruili.my.doorcontrol.fragments.Remove_KeyFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @
 * @类名称: ${}
 * @类描述: ${type_name}
 * @创建人： Lyp
 * @创建时间：${date} ${time}
 * @备注：
 */
public class KeyMessage extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "KeyMessage";
    private TabLayout keymessage_tab;
    private ViewPager key_message_vvp;

    private ImageView keymessage_activity_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.keymessage);

        initView();
    }

    private void initView() {

        keymessage_activity_back = (ImageView) findViewById(R.id.keymessage_activity_back);
        keymessage_tab = (TabLayout) findViewById(R.id.keymessage_tab);
        key_message_vvp = (ViewPager) findViewById(R.id.key_message_vvp);

        keymessage_tab.addTab(keymessage_tab.newTab());

        List<String> tablist = new ArrayList<>();
        tablist.add("添加家人");
        tablist.add("删除家人");

        List<Fragment> fragmentslist = new ArrayList<>();
        fragmentslist.add(ADD_KeyFragment.newInstance());
        fragmentslist.add(Remove_KeyFragment.newInstance());


        KeyMessAdapter adapter = new KeyMessAdapter(KeyMessage.this, fragmentslist, tablist);
        key_message_vvp.setAdapter(adapter);

        keymessage_activity_back.setOnClickListener(this);
        // 协同布局
        keymessage_tab.setupWithViewPager(key_message_vvp);
        keymessage_tab.setTabsFromPagerAdapter(adapter);
    }


    @Override
    public void onClick(View view) {

        int id = view.getId();

        switch (id) {
            case R.id.keymessage_activity_back:
                KeyMessage.this.finish();
                break;
        }
    }
}
