package com.doorcontrol.ruili.my.doorcontrol.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.doorcontrol.ruili.my.doorcontrol.R;
import com.doorcontrol.ruili.my.doorcontrol.activitys.Message_tbn;
import com.doorcontrol.ruili.my.doorcontrol.bean.Bean_TodayNews;
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
public class MyNewsAdapter extends BaseAdapter {

    List<Bean_TodayNews.ResultBean.DataBean> list;
    private Message_tbn message_tbn;

    public MyNewsAdapter(Message_tbn message_tbn, List<Bean_TodayNews.ResultBean.DataBean> list) {

        this.message_tbn = message_tbn;
        this.list = list;

    }


    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        ViewHolder viewHolder;
        if (convertView == null) {

            convertView = LayoutInflater.from(message_tbn).inflate(R.layout.todaynews_item, viewGroup, false);

            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {

            viewHolder = (ViewHolder) convertView.getTag();
        }
        Bean_TodayNews.ResultBean.DataBean bean_todayNews = list.get(position);

        viewHolder.news_item_title.setText(bean_todayNews.getTitle());
        viewHolder.news_item_date.setText(bean_todayNews.getDate());
        viewHolder.news_item_autername.setText(bean_todayNews.getAuthor_name());

        String url = bean_todayNews.getUrl();
        Picasso.with(message_tbn).load(bean_todayNews.getThumbnail_pic_s()).into(viewHolder.news_item_img);

        return convertView;


    }

    class ViewHolder {

        TextView news_item_title, news_item_date, news_item_autername;
        ImageView news_item_img;

        public ViewHolder(View convertView) {
            news_item_title = (TextView) convertView.findViewById(R.id.news_item_title);
            news_item_date = (TextView) convertView.findViewById(R.id.news_item_date);
            news_item_autername = (TextView) convertView.findViewById(R.id.news_item_autername);
            news_item_img = (ImageView) convertView.findViewById(R.id.news_item_img);

        }
    }
}
