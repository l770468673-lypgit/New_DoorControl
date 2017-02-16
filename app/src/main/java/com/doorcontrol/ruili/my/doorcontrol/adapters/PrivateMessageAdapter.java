package com.doorcontrol.ruili.my.doorcontrol.adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.doorcontrol.ruili.my.doorcontrol.R;
import com.doorcontrol.ruili.my.doorcontrol.restful.model.Bean_message;

import java.util.List;

/**
 * @
 * @类名称: ${}
 * @类描述: ${type_name}
 * @创建人： Lyp
 * @创建时间：${date} ${time}
 * @备注：
 */
public class PrivateMessageAdapter extends BaseAdapter {
    private Context mContext;
    private List<Bean_message.DataBean> mdata;

    public PrivateMessageAdapter(FragmentActivity activity, List<Bean_message.DataBean> data) {
        this.mContext = activity;
        this.mdata = data;
    }

    @Override
    public int getCount() {
        return mdata != null ? mdata.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHodler;
        if (convertView == null) {

            convertView = LayoutInflater.from(mContext).inflate(R.layout.messageprivate, parent, false);
            viewHodler = new ViewHolder(convertView);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHolder) convertView.getTag();
        }
        Bean_message.DataBean dataBean = mdata.get(position);

        viewHodler.prinoticeTitle.setText(dataBean.getNoticeTitle());
        viewHodler.prinoticeInfo.setText(dataBean.getNoticeInfo());
        return convertView;
    }

    class ViewHolder {
        TextView prinoticeTitle, prinoticeInfo;

        public ViewHolder(View convertView) {
            prinoticeTitle = (TextView) convertView.findViewById(R.id.prinoticeTitle);
            prinoticeInfo = (TextView) convertView.findViewById(R.id.prinoticeInfo);

        }
    }
}
