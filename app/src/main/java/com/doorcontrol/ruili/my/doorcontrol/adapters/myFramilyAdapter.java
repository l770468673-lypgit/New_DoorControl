package com.doorcontrol.ruili.my.doorcontrol.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.doorcontrol.ruili.my.doorcontrol.R;
import com.doorcontrol.ruili.my.doorcontrol.activitys.Framily_list;
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
public class myFramilyAdapter extends BaseAdapter {


    private Framily_list mcontent;
    private List<Bean_FamilyList.DataBean> mlist;


    public myFramilyAdapter(Framily_list content) {
        this.mlist = new ArrayList<>();
        this.mcontent = content;
    }

    public void setData(List<Bean_FamilyList.DataBean> data) {
        this.mlist = data;
        notifyDataSetChanged();
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
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mcontent).inflate(R.layout.framilylist_item, viewGroup, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Bean_FamilyList.DataBean dataBean = mlist.get(position);
        List<Bean_FamilyList.DataBean.HouseOwnersBean> houseOwners = dataBean.getHouseOwners();
        Bean_FamilyList.DataBean.HouseBean house = dataBean.getHouse();
        //        viewHolder.item_houseOwnerid.setText();
        //        viewHolder.item_houseOwnername.setText(houseownername);
        //        viewHolder.item_phonenumber.setText(phonenumber);


        //   viewHolder.item_seq.setText();
     //   viewHolder.item_build.setText(house.getBuildingName());
     //   viewHolder.item_community.setText(house.getCommunityName());
        viewHolder.item_houseId.setText(house.getHouseId() + "");
     //   viewHolder.item_houseName.setText(house.getHouseName());

        //--
        viewHolder.ll_addview.removeAllViews();

        for (int i = 0; i < houseOwners.size(); i++) {

            Bean_FamilyList.DataBean.HouseOwnersBean houseOwnersBean = houseOwners.get(i);

            View view = LayoutInflater.from(mcontent).inflate(R.layout.view_ll_addpeson, null);

            EditText houseOwnerid = (EditText) view.findViewById(R.id.item_houseOwnerid);
            EditText houseOwnername = (EditText) view.findViewById(R.id.item_houseOwnername);
            //   EditText phonenumber = (EditText) view.findViewById(R.id.item_phonenumber);
            EditText seq = (EditText) view.findViewById(R.id.item_seq);

            //   int a = Integer.parseInt(str);   int 转化string
            //   viewHolder.item_seq.setText(houseOwnersBean.getSeq()+" ");
            houseOwnerid.setText(houseOwnersBean.getHouseOwnerId() + "");
            houseOwnername.setText(houseOwnersBean.getHouseOwnerName());
            //    phonenumber.setText(houseOwnersBean.getPhoneNumber());
            seq.setText(houseOwnersBean.getSeq() + "");


            viewHolder.ll_addview.addView(view);
        }

        return convertView;
    }


    class ViewHolder {
        EditText item_houseId,
               // item_houseName,
                item_seq;
              //  item_build,
               // item_community;
        LinearLayout ll_addview;

        public ViewHolder(View convertView) {


            item_houseId = (EditText) convertView.findViewById(R.id.item_houseId);
            //    item_houseName = (EditText) convertView.findViewById(R.id.item_houseName);
            //   item_houseName = (EditText) convertView.findViewById(R.id.item_houseName);
            //  item_houseOwnerid = (EditText) convertView.findViewById(R.id.item_houseOwnerid);
            //    item_houseOwnername = (EditText) convertView.findViewById(R.id.item_houseOwnername);
            //   item_phonenumber = (EditText) convertView.findViewById(R.id.item_phonenumber);
            item_seq = (EditText) convertView.findViewById(R.id.item_seq);
            //   item_build = (EditText) convertView.findViewById(R.id.item_build);
            //    item_community = (EditText) convertView.findViewById(R.id.item_community);
            ll_addview = (LinearLayout) convertView.findViewById(R.id.ll_addview);

        }
    }
}
