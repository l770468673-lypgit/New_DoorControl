package com.doorcontrol.ruili.my.doorcontrol.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.doorcontrol.ruili.my.doorcontrol.R;
import com.doorcontrol.ruili.my.doorcontrol.activitys.NewKeyMessage;
import com.doorcontrol.ruili.my.doorcontrol.restful.model.Bean_FamilyList;

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
public class New_keyAdapter extends BaseAdapter {


    private NewKeyMessage mcontent;
    private List<Bean_FamilyList.DataBean> mlist;

    public New_keyAdapter(NewKeyMessage newKeyMessage) {

        this.mlist = new ArrayList<>();
        this.mcontent = newKeyMessage;
    }


    public void setData(List<Bean_FamilyList.DataBean> data) {
        this.mlist = data;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mlist != null ? mlist.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return mlist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mcontent).inflate(R.layout.newkeymessage_item, parent, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Bean_FamilyList.DataBean dataBean = mlist.get(position);
        Bean_FamilyList.DataBean.HouseBean house = dataBean.getHouse();
        //        house.getHouseName();
        //        house.getBuildingName();
        //        house.getCommunityName();
        viewHolder.shequ.setText(house.getCommunityName());
        viewHolder.louhao.setText(house.getBuildingName());
        viewHolder.fangjian.setText(house.getHouseName());
        viewHolder.danyuan.setText(house.getUnitCode() + "");

        return convertView;
    }

    class ViewHolder {
        TextView shequ, louhao, fangjian, danyuan;


        public ViewHolder(View convertView) {
            shequ = (TextView) convertView.findViewById(R.id.shequ);
            danyuan = (TextView) convertView.findViewById(R.id.danyuan);
            louhao = (TextView) convertView.findViewById(R.id.louhao);
            fangjian = (TextView) convertView.findViewById(R.id.fangjian);

        }
    }
}
