package com.doorcontrol.ruili.my.doorcontrol.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.doorcontrol.ruili.my.doorcontrol.fragments.Home_Fragment;

import java.util.List;

/**
 * @
 * @类名称: ${}
 * @类描述: ${type_name}
 * @创建人： Lyp
 * @创建时间：${date} ${time}
 * @备注：
 */
public class Home_MessageAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mList;
    private List<String> mtablist;
    private Home_Fragment mHome_fragment;

    public Home_MessageAdapter(FragmentManager fm) {
        super(fm);
    }

    public Home_MessageAdapter(FragmentActivity activity, List<Fragment> fragmentslist, List<String> tablist) {
        super(activity.getSupportFragmentManager());
        this.mList = fragmentslist;
        this.mtablist = tablist;
    }


    @Override
    public Fragment getItem(int position) {
        return mList.get(position);

    }

    @Override
    public int getCount() {
        return mList != null ? mList.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mtablist.get(position);
    }
}
