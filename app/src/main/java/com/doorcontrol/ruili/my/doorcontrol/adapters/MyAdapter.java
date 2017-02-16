package com.doorcontrol.ruili.my.doorcontrol.adapters;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class MyAdapter extends PagerAdapter {

	private List<ImageView> mList;

	public MyAdapter(List<ImageView> list) {
		this.mList = list;
	}

	@Override
	public int getCount() {

		return mList != null ? mList.size() : 0;
	}

	// "把鸡蛋放到篮子中"
	// ViewPager:容器---->"篮子"
	// mList:集合--->母鸡
	// ImageView:view---->"鸡蛋"
	// instantiateItem:产生一个条目的方法.
	@Override
	public Object instantiateItem(ViewGroup container, int position) {

		// 将ImageView添加到ViewPager中.
		container.addView(mList.get(position));

		return mList.get(position);
	}

	// 判断这个view是否和instantiateItem方法的返回值object是否是同一个对象.
	// view:当前界面上正在展示的视图.
	// true:当前这个视图和object一致,直接复用之前的;false:只能重新创建一个.
	@Override
	public boolean isViewFromObject(View view, Object object) {

		return view == object;
	}

	// 从容器中移除某个视图
	//不是销毁!
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {

		container.removeView(mList.get(position));
	}

}
