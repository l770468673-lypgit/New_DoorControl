package com.doorcontrol.ruili.my.doorcontrol.Custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * @
 * @类名称: ${}
 * @类描述: ${type_name}
 * @创建人： Lyp
 * @创建时间：${date} ${time}
 * @备注：
 */
public class MyListView extends ListView {


    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    /**
     * 控件的 显示高度
     * 由 onMeasure 方法确定的
     *
     */

    /**
     *
     * @param widthMeasureSpec 布局中获取 宽和高
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        //创建高度 和  模式
        heightMeasureSpec=MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);



    }

}
