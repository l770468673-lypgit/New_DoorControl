package com.doorcontrol.ruili.my.doorcontrol.Custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

public class MyView extends ImageView{

	private Paint paint=null;

	private void init(){
		paint=new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.parseColor("#7f212121"));

	}
	
	public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		init();
	}

	public MyView(Context context, AttributeSet attrs) {
		this(context, attrs,0);
		// TODO Auto-generated constructor stub
	}

	public MyView(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawCircle(getWidth()/2, getHeight()/2, getWidth()/2-1, paint);

		super.onDraw(canvas);
	}

}
