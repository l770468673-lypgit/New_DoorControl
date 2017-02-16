package com.doorcontrol.ruili.my.doorcontrol.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.doorcontrol.ruili.my.doorcontrol.R;
import com.doorcontrol.ruili.my.doorcontrol.activitys.Family_Detal;
import com.doorcontrol.ruili.my.doorcontrol.restful.model.Bean_FamilyList;
import com.doorcontrol.ruili.my.doorcontrol.utils.L;

import java.util.List;

/**
 * @
 * @类名称: ${}
 * @类描述: ${type_name}
 * @创建人： Lyp
 * @创建时间：${date} ${time}
 * @备注：
 */
public class FamilyDetalAdapter extends BaseAdapter implements View.OnClickListener {


    private Family_Detal mcontent;
    private List<Bean_FamilyList.DataBean.HouseOwnersBean> mlist;
    private Bean_FamilyList.DataBean mData;

    public CallbacktoItem mCallbacktoItem;

    public FamilyDetalAdapter(Family_Detal family_detal, List<Bean_FamilyList.DataBean.HouseOwnersBean> databean, CallbacktoItem Callback) {
        this.mlist = databean;
        this.mcontent = family_detal;
        this.notifyDataSetChanged();
        this.mCallbacktoItem = Callback;
    }

//    public FamilyDetalAdapter(Family_Detal family_detal, List<Bean_FamilyList.DataBean.HouseOwnersBean> databean) {
//        this.mlist = databean;
//        this.mcontent = family_detal;
//        this.notifyDataSetChanged();
//
//    }

    public interface CallbacktoItem {
        void Itemclick(View v);
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
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mcontent).inflate(R.layout.familydetal_item, parent, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Bean_FamilyList.DataBean.HouseOwnersBean houseOwnersBean = mlist.get(position);
        String houseOwnerName = houseOwnersBean.getHouseOwnerName();
        String phoneNumber = houseOwnersBean.getPhoneNumber();

        int seq = houseOwnersBean.getSeq();

        viewHolder.family_name_item.setText(houseOwnerName);
        viewHolder.family_seq_item.setText(seq + "");
        viewHolder.family_phone_item.setText(phoneNumber);

        viewHolder.edittextchange.setOnClickListener(this);
        viewHolder.edittextchange.setTag(position);
        L.d("FamilyDetalAdapter" + houseOwnerName.toString());
        return convertView;
    }

    @Override
    public void onClick(View v) {
        mCallbacktoItem.Itemclick(v);

    }


    public class ViewHolder {
        public  TextView family_name_item, family_phone_item;
        public  EditText family_seq_item;
        public   Button edittextchange;


        public ViewHolder(View convertView) {
            family_name_item = (TextView) convertView.findViewById(R.id.family_name_item);
            edittextchange = (Button) convertView.findViewById(R.id.edittextchange);
            family_phone_item = (TextView) convertView.findViewById(R.id.family_phone_item);
            family_seq_item = (EditText) convertView.findViewById(R.id.family_seq_item);

            family_seq_item.setFocusable(false);
            family_seq_item.setEnabled(false);

        }
    }
}
