package com.doorcontrol.ruili.my.doorcontrol.Custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Ken on 2016/3/7.
 * 自定义侧边字母控件
 */
public class SideView extends View {

    /**
     * 标签
     */
    private String[] labels = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    private Paint paint;//基础画笔

    private int textHeight;//文本的高度

    private int index = -1;//表示当前点击字母下标

    private OnCheckedChangeListener onCheckedChangeListener;

    /**
     * 当这个控件需要通过代码初始化的时候，重写该构造方法
     *
     * @param context
     */
    public SideView(Context context) {
        super(context);
    }

    /**
     * 当这个控件需要在布局中通过标签的形式声明时，重写该构造方法
     *
     * @param context
     * @param attrs
     */
    public SideView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * 当这个控件需要在布局中通过标签的形式声明时，并且有style属性时，重写该构造方法
     *
     * @param
     * @param
     * @param
     */

    public SideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        this.onCheckedChangeListener = onCheckedChangeListener;
    }

    /**
     * 初始化
     */
    private void init() {
        paint = new Paint();
        paint.setTextSize(24);
        paint.setColor(Color.parseColor("#666666"));
        paint.setAntiAlias(true);//开启抗锯齿

        textHeight = (int) (paint.descent() - paint.ascent());//获得文本的高度
    }

//    /**
//     * 图片宽和高的比例
//     */
//    private float ratio = 2.43f;
//
//    public void setRatio(float ratio) {
//        this.ratio = ratio;
//    }

    /**
     * 绘制
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {


        int perTextHeight = getHeight() / labels.length;
        for (int i = 0; i < labels.length; i++) {
            if (index == i) {
                paint.setColor(Color.parseColor("#000000"));
                paint.setTextSize(36);
            } else {
                paint.setColor(Color.parseColor("#666666"));
                paint.setTextSize(24);

            }
            canvas.drawText(labels[i], getWidth() / 2 - paint.measureText(labels[i]) / 2, (i + 1) * perTextHeight, paint);
            //canvas.drawText(labels[i], getWidth() / 2 - paint.measureText(labels[i]) / 2, textHeight * (i + 1), paint);
        }
    }

    /**
     * 测量方法
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = measureSpec(widthMeasureSpec, 0);
        int height = measureSpec(heightMeasureSpec, 1);


        setMeasuredDimension(width, height);//保存测量出来的宽度和高度
    }

    /**
     * 测量工具方法
     *
     * @param spec 宽度或者高度的空间值
     * @param type 测量的类型，0 - 测量宽度， 1 - 表示测量高度
     * @return
     */
    private int measureSpec(int spec, int type) {

        int size = MeasureSpec.getSize(spec);//得到空间值的推荐大小
        int mode = MeasureSpec.getMode(spec);//得到空间值的模式

        switch (mode) {
            case MeasureSpec.EXACTLY:
                //该模式表示layout_width（或者layout_height），被设置为match_parent或者被设置为一个精确的值
                if (type == 0) {
                    //测量宽度
                    return (int) (paint.measureText(labels[0]) + getPaddingLeft() + getPaddingRight());
                } else if (type == 1) {
                    //测量高度
                    return textHeight * 26 + getPaddingTop() + getPaddingBottom();
                }
                break;
            case MeasureSpec.AT_MOST:
                //该模式表示尽可能的多，但是不会超过size，被设置为wrap_content时，为该模式
                if (type == 0) {
                    //测量宽度
                    return (int) (paint.measureText(labels[0]) + getPaddingLeft() + getPaddingRight());
                } else if (type == 1) {
                    //测量高度
                    return textHeight * 26 + getPaddingTop() + getPaddingBottom();
                }
            case MeasureSpec.UNSPECIFIED:
                //该模式表示想要多大就给多大，通常出现在scrollview、listview子控件中
                if (type == 0) {
                    //测量宽度
                    return (int) (paint.measureText(labels[0]) + getPaddingLeft() + getPaddingRight());
                } else if (type == 1) {
                    //测量高度
                    return textHeight * 26 + getPaddingTop() + getPaddingBottom();
                }
                break;
        }

        return size;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int height = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                eventlabel(height);
                break;
            case MotionEvent.ACTION_MOVE:
                eventlabel(height);
                break;
            case MotionEvent.ACTION_UP:
                index = -1;
                invalidate();

                if (onCheckedChangeListener != null) {
                    onCheckedChangeListener.eventup();
                }
                break;
        }
        invalidate();
        return true;

    }

    /**
     * 判断当前点击的字母
     *
     * @param y
     */
    private void eventlabel(int y) {
        int index = y / textHeight;//获得字母的下标
        if (index < 0) {
            index = 0;
        }
        if (index >= labels.length) {
            index = labels.length - 1;
        }

        if (this.index != index) {
            this.index = index;
            invalidate();//调用重绘
//          postInvalidate();

            //处理点击了某一个字母的事件情况
            if (onCheckedChangeListener != null) {
                onCheckedChangeListener.checkedLabel(this.index, labels[this.index]);
            }
        }
    }

    public interface OnCheckedChangeListener {
        void checkedLabel(int index, String label);

        void eventup();
    }

    /**
     * 子控件的摆放 -- 自定义ViewGroup时重写
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
//    @Override
//    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        super.onLayout(changed, left, top, right, bottom);
//    }
}
