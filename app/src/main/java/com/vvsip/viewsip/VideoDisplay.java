/*
  vvphone is a SIP app for android.
  vvsip is a SIP library for softphone (SIP -rfc3261-)
  Copyright (C) 2003-2010  Bluegoby - <bluegoby@163.com>
 */
package com.vvsip.viewsip;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * SurfaceView的封装，功能：显示对方视频
 */
public class VideoDisplay extends SurfaceView {
	private final String mTag = "VideoDisplay";


	
	public VideoDisplay(Context context, AttributeSet attrset) {
		super(context, attrset);
		
	}

	public VideoDisplay(Context context) {
		super(context);
		
	}

	

	public Bitmap lockIncomingImage(int width, int height) {
		return null;
	}

	@SuppressLint("WrongCall")
	public synchronized void unlockIncomingImage() {
		
	}

	@Override
	protected void onDraw(Canvas canvas) {

		
	}

}
