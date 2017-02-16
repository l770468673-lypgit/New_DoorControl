package com.doorcontrol.ruili.my.doorcontrol.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.doorcontrol.ruili.my.doorcontrol.Custom.CircleImageView;
import com.doorcontrol.ruili.my.doorcontrol.R;
import com.doorcontrol.ruili.my.doorcontrol.activitys.OpenDoorRecord;
import com.doorcontrol.ruili.my.doorcontrol.restful.model.Bean_AddRecord;
import com.doorcontrol.ruili.my.doorcontrol.utils.L;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * @
 * @类名称: ${}
 * @类描述: ${type_name}
 * @创建人： Lyp
 * @创建时间：${date} ${time}
 * @备注：
 */
public class OpenDoorAdapter extends BaseAdapter {
    private static final String TAG = "OpenDoorAdapter";
    final int TYPE_0 = 0;
    final int TYPE_1 = 4;

    private OpenDoorRecord mRecord;
    private List<Bean_AddRecord.DataBean> mDataBeen;

    public OpenDoorAdapter(OpenDoorRecord openDoorRecord, List<Bean_AddRecord.DataBean> data) {
        this.mRecord = openDoorRecord;
        this.mDataBeen = data;
    }

    @Override
    public int getCount() {
        return mDataBeen != null ? mDataBeen.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mDataBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHodler;
        if (convertView == null) {
            convertView = LayoutInflater.from(mRecord).inflate(R.layout.opendoor_item, parent, false);
            viewHodler = new ViewHolder(convertView);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHolder) convertView.getTag();
        }
        Bean_AddRecord.DataBean dataBean = mDataBeen.get(position);


        long logTime = dataBean.getLogTime();
        String vistorPicUrl = dataBean.getVistorPicUrl();


         if (vistorPicUrl.length()>3) {

            L.d(TAG, "vistorPicUrl  si  =" + vistorPicUrl.toString());

             Picasso.with(mRecord)
                     .load(vistorPicUrl)
                     .resize(50, 50)
                     .centerCrop()
                     .into(viewHodler.opendoor_img);
        }else {
             viewHodler.opendoor_img.setImageDrawable(mRecord.getResources().getDrawable(R.mipmap.logo_ehme));
         }

        if (logTime != 0) {
            int logtype = dataBean.getLogType();


            if (logtype == 4) {
                String cardCode = dataBean.getCardCode();
                viewHodler.numberphone.setText(cardCode);
            } else {
                String phoneNumber = dataBean.getPhoneNumber();
                viewHodler.numberphone.setText(phoneNumber);

            }

            String strLogType = dataBean.getStrLogType();
            String strLogTime = dataBean.getStrLogTime();

            viewHodler.opendoor_type.setText(strLogType);
            viewHodler.opendoor_time.setText(strLogTime);
        } else {

        }


        return convertView;


    }


    class ViewHolder {
        TextView opendoor_time, opendoor_type, numberphone, cardphone;
        CircleImageView opendoor_img;

        public ViewHolder(View convertView) {
            opendoor_time = (TextView) convertView.findViewById(R.id.opendoor_time);
            opendoor_type = (TextView) convertView.findViewById(R.id.opendoor_type);
            numberphone = (TextView) convertView.findViewById(R.id.numberphone);
            opendoor_img = (CircleImageView) convertView.findViewById(R.id.opendoor_img);

        }

    }
}
