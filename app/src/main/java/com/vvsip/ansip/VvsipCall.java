/*
  vvphone is a SIP app for android.
  vvsip is a SIP library for softphone (SIP -rfc3261-)
  Copyright (C) 2003-2010  Bluegoby - <bluegoby@163.com>
*/

package com.vvsip.ansip;

import android.graphics.drawable.Drawable;
import android.os.SystemClock;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

/*
 * 电话呼叫封装类，为主要功能类，功能：提供呼叫相关的功能，包括接电话，挂电话，DTMF
 */
public class VvsipCall implements Serializable {

	private static final long serialVersionUID = 1L;
	public long start_date;
	public long established_date;
	public long end_date;
	public String description;
	public String mRemoteUri;
	public int tid;
	public int cid;
	public int did;
	public boolean mIncomingCall;
	public boolean mMuted;
	public boolean mOnHold;
	public int mState;
	public String mDisplayName;
	Drawable mContactImage;
	public boolean mVideoStarted;
	
	public VvsipCall()
	{
		Calendar cal = new GregorianCalendar();
		start_date = cal.getTime().getTime();
		description = "--";
	}
	
	public boolean isIncomingCall()
	{
		return mIncomingCall;
	}
	
	public int answer(int code, int enable_audio)
	{
    	IVvsipService _service = VvsipService.getService();
    	if (_service==null)
    		return -999;
    	VvsipTask _vvsipTask = _service.getVvsipTask();
    	if (_vvsipTask==null)
    		return -998;
    	int i;
    	if (code<200)
    	{
    		i = _vvsipTask.vvsessionanswer(tid, did, code, 0);
    		if (i>=0)
    			mState=1;
    	}
    	else if (code<300)
    	{
    		i = _vvsipTask.vvsessionanswer(tid, did, code, 1);
    		if (i>=0) {
    			established_date = SystemClock.elapsedRealtime();
    			mState=2;
				AudioOutput.beready=true;
	    		AudioInput.beready=true;
    		}
    	}
    	else
    	{
			i = _vvsipTask.vvsessionanswer(tid, did, code, 0);
    		if (i>=0)
    		{
    			if (mState<2 && isIncomingCall()) {
        			description = ("δ������");
    			}
    		}
    		if (i>=0)
    			mState=3;
    	}
		return i;
	}
	
	public int stop()
	{
    	IVvsipService _service = VvsipService.getService();
    	if (_service==null)
    		return -999;
    	VvsipTask _vvsipTask = _service.getVvsipTask();
    	if (_vvsipTask==null)
    		return -998;
    	int i = _vvsipTask.vvsessionstop(cid, did, 486);
    	if (i>=0)
    	{
    		if (mState==2)
    		{
        		Calendar cal = new GregorianCalendar();
        		end_date = cal.getTime().getTime();
    		} else if (mState<2 && isIncomingCall()) {
    			description = "δ������";
    		} else if (mState<2) {
    			description = "--";
    		}
    	}
    	
		if (i>=0)
			mState = 3;
		return i;
	}


	public void sendrtpdtmf(String dtmf) {
    	if (cid>0 && mState==2)
   		{
			IVvsipService _service = VvsipService.getService();
	    	if (_service==null)
	    		return;
	    	VvsipTask _vvsipTask = _service.getVvsipTask();
	    	if (_vvsipTask==null)
	    		return;
	    	//_vvsipTask.vvsessionsenddtmfwithduration(pCall.did, "1", 480);
	    	_vvsipTask.vvsessionsendrtpdtmf(did, dtmf);
   		}
	}

	public void sendinfodtmf(String dtmf) {
    	if (cid>0 && mState==2)
   		{
			IVvsipService _service = VvsipService.getService();
	    	if (_service==null)
	    		return;
	    	VvsipTask _vvsipTask = _service.getVvsipTask();
	    	if (_vvsipTask==null)
	    		return;
	    	_vvsipTask.vvsessionsenddtmfwithduration(did, dtmf, 480);
   		}
	}
	
}
