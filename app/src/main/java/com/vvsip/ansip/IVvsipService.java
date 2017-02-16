/*
  vvphone is a SIP app for android.
  vvsip is a SIP library for softphone (SIP -rfc3261-)
  Copyright (C) 2003-2010  Bluegoby - <bluegoby@163.com>
*/

package com.vvsip.ansip;

import android.os.Handler;
/*
 * 服务接口
 */
public interface IVvsipService {
	public VvsipTask getVvsipTask();
	public int StartVvsipLayer();
	public int StopVvsipLayer();
	public void initiateOutgoingCall(String target);
    public void addListener(IVvsipServiceListener listener); 
    public void removeListener(IVvsipServiceListener listener);
    public void setAudioNormalMode();
    public void setAudioInCallMode();
    public void setSpeakerModeOff();
    public void setSpeakerModeOn();
	public void stopPlayer();
	public void restartNetworkDetection();
	public void setMessageHandler(Handler _mainActivityMessageHandler);
	public void register(String domain,String username,String passwd);
	public void setNeighbors();
	public void initiateOutgoingCallOnLan(String lan_callee);
	public void sendDTMF(String string);
}
