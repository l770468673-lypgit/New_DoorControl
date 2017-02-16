package com.doorcontrol.ruili.my.doorcontrol.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.doorcontrol.ruili.my.doorcontrol.activitys.KeyMessage;

import java.util.List;

/**
 * @
 * @类名称: ${}
 * @类描述: ${type_name}
 * @创建人： Lyp
 * @创建时间：${date} ${time}
 * @备注：
 */
public class KeyMessAdapter extends FragmentPagerAdapter {



    private List<Fragment> mList;
    private List<String> mtablist;

    public KeyMessAdapter(KeyMessage keyMessage, List<Fragment> fragmentslist, List<String> tablist) {
        super(keyMessage.getSupportFragmentManager());
        this.mList=fragmentslist;
        this.mtablist=tablist;

    }

    @Override
    public int getCount() {

        return mList != null ? mList.size() : 0;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return mtablist.get(position);
    }

}
