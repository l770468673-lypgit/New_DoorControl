package com.doorcontrol.ruili.my.doorcontrol.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.doorcontrol.ruili.my.doorcontrol.R;
import com.doorcontrol.ruili.my.doorcontrol.bean.Bean_City;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class CityAdapter extends BaseAdapter{

    private Context context;
    private List<Bean_City> datas;

    public CityAdapter(Context context){
        this.context = context;
        this.datas = new ArrayList<>();
    }

    public void setDatas(List<Bean_City> datas){
        this.datas = datas;
        this.notifyDataSetChanged();
    }

    public void addDatas(List<Bean_City> datas){
        this.datas.addAll(datas);
        this.notifyDataSetChanged();
    }

    public void remove(int position){
        this.datas.remove(position);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler viewHodler = null;
        if(convertView != null){
            viewHodler = (ViewHodler) convertView.getTag();
        } else {
            viewHodler = new ViewHodler();
            if(getItemViewType(position) == 0){
                convertView = LayoutInflater.from(context).inflate(R.layout.item_label, parent, false);
                viewHodler.tv = (TextView) convertView.findViewById(R.id.tv_label);
                convertView.setTag(viewHodler);
            } else if(getItemViewType(position) == 1){
                convertView = LayoutInflater.from(context).inflate(R.layout.item_city, parent, false);
                viewHodler.tv = (TextView) convertView.findViewById(R.id.tv_city);
                convertView.setTag(viewHodler);
            }
        }

        viewHodler.tv.setText(datas.get(position).getCityname());
        return convertView;
    }

    class ViewHodler{
        TextView tv;
    }

    @Override
    public int getItemViewType(int position) {
        return datas.get(position).getType();
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    /**
     * 过滤标签的点击事件
     * @param position
     * @return
     */
    @Override
    public boolean isEnabled(int position) {
        return datas.get(position).getType() == 1;
    }

    public int isEq(String label){
        for(int i = 0; i < datas.size(); i++){
            Bean_City ce = datas.get(i);
            if(ce.getType() == 0){
                if(ce.eq(label)){
                    return i;
                }
            }
        }

        return -1;
    }
}
