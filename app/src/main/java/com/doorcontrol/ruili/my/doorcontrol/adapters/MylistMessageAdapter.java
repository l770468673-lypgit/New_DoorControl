package com.doorcontrol.ruili.my.doorcontrol.adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.doorcontrol.ruili.my.doorcontrol.R;
import com.doorcontrol.ruili.my.doorcontrol.restful.model.Bean_listNews;

import java.util.List;

/**
 * @
 * @类名称: ${}
 * @类描述: ${type_name}
 * @创建人： Lyp
 * @创建时间：${date} ${time}
 * @备注：
 */
public class MylistMessageAdapter extends BaseAdapter {

    final int TYPE_0 = 0;
    final int TYPE_1 = 1;

    private List<Bean_listNews.DataBean> data;

    private Context mcontext;


    public MylistMessageAdapter(FragmentActivity mcomtent, List<Bean_listNews.DataBean> data) {

        this.mcontext = mcomtent;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {


        ViewHolder viewHolder;

        int itemViewType = getItemViewType(i);
        if (convertView == null) {
            viewHolder = new ViewHolder();

            switch (itemViewType) {

                case TYPE_1:
                    convertView = LayoutInflater.from(mcontext).inflate(R.layout.message_typeone, viewGroup, false);

                    viewHolder.noticeTitle = (TextView) convertView.findViewById(R.id.noticeTitle);
                    viewHolder.noticeInfo = (TextView) convertView.findViewById(R.id.noticeInfo);
                    viewHolder.target = (TextView) convertView.findViewById(R.id.target);

                    break;

                case TYPE_0:
                    convertView = LayoutInflater.from(mcontext).inflate(R.layout.message_typetwo, viewGroup, false);
                    viewHolder.noticeTitle = (TextView) convertView.findViewById(R.id.noticeTitle);
                    viewHolder.noticeInfo = (TextView) convertView.findViewById(R.id.noticeInfo);
                    break;
            }
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Bean_listNews.DataBean dataBean = data.get(i);

        // 设置资源
        switch (itemViewType) {
            case TYPE_1:
                viewHolder.noticeTitle.setText(dataBean.getNoticeTitle());  // 消息的标题
                viewHolder.noticeInfo.setText(dataBean.getNoticeInfo());//消息 的内容
                viewHolder.target.setText(dataBean.getTarget()); // 房间号
            case TYPE_0:
                viewHolder.noticeTitle.setText(dataBean.getNoticeTitle());  // 消息的标题
                viewHolder.noticeInfo.setText(dataBean.getNoticeInfo());//消息 的内容

                break;
        }
        return convertView;
    }


    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {

        int noticeType = data.get(position).getNoticeType();
        if (noticeType == 0) {
            return TYPE_0;
        } else if (noticeType == 1) {

            return TYPE_1;
        }

        return noticeType;
    }

    class ViewHolder {
        TextView noticeTitle, noticeInfo, target;

    }
}
