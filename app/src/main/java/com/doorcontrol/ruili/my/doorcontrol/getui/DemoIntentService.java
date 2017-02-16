package com.doorcontrol.ruili.my.doorcontrol.getui;

import android.content.Context;
import android.os.Message;

import com.doorcontrol.ruili.my.doorcontrol.activitys.CallPhone;
import com.doorcontrol.ruili.my.doorcontrol.utils.L;
import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.PushConsts;
import com.igexin.sdk.PushManager;
import com.igexin.sdk.message.FeedbackCmdMessage;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTTransmitMessage;
import com.igexin.sdk.message.SetTagCmdMessage;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @
 * @类名称: ${}
 * @类描述: ${type_name}
 * @创建人： Lyp
 * @创建时间：${date} ${time}
 * @备注：
 */


/**
 * 继承 GTIntentService 接收来自个推的消息, 所有消息在线程中回调, 如果注册了该服务, 则务必要在 AndroidManifest中声明, 否则无法接受消息<br>
 * onReceiveMessageData 处理透传消息<br>
 * onReceiveClientId 接收 cid <br>
 * onReceiveOnlineState cid 离线上线通知 <br>
 * onReceiveCommandResult 各种事件处理回执 <br>
 */

public class DemoIntentService extends GTIntentService {
    private static final String TAG = "DemoIntentService";

    public DemoIntentService() {
    }

    @Override
    public void onReceiveServicePid(Context context, int pid) {

        L.d(TAG, "onReceiveServicePid  pid = " + pid);

    }

    //onReceiveMessageData 处理透传消息<br>
    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage msg) {
        String appid = msg.getAppid();

        String taskid = msg.getTaskId();
        String messageid = msg.getMessageId();
        byte[] payload = msg.getPayload();
        String pkg = msg.getPkgName();
        String cid = msg.getClientId();
        boolean result = PushManager.getInstance().sendFeedbackMessage(context, taskid, messageid, 90001);

        String payloads = new String(payload);

        L.d(TAG, "receiver payload = " + payloads);

        try {
            JSONObject jsonObject;
            jsonObject = new JSONObject(payloads);
            String type = jsonObject.getString("msgType");
            int msgType = Integer.parseInt(type);
            if (msgType == 3) {
            String vistorPicUrl = jsonObject.getString("vistorPicUrl");
                 L.d(TAG,"vistorPicUrl.toString url is "+vistorPicUrl.toString());
                sendmessage(vistorPicUrl,90);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        L.d(TAG, "appid = " + appid + "\ntaskid = " + taskid + "\npayloads = " + payloads + "\nresult = " + result
                + "\ncid = " + cid + "\npkg = " + pkg );



    }

    private void sendmessage(String payloads, int what) {
        Message msg = Message.obtain();
        msg.what = what;
        msg.obj = payloads;
        CallPhone.mCallhandler.sendMessage(msg);

    }


    //  onReceiveClientId 接收 cid <br>
    @Override
    public void onReceiveClientId(Context context, String clientid) {
        L.d(TAG, "onReceiveClientId -> " + "clientid = " + clientid);
        sendmessage(clientid, 89);
    }

    @Override
    public void onReceiveOnlineState(Context context, boolean online) {
        L.d(TAG, "onReceieveOnlineState -> " + (online ? "online" : "offline"));
    }


    // 毁掉方法
    @Override
    public void onReceiveCommandResult(Context context, GTCmdMessage cmdMessage) {


        int action = cmdMessage.getAction();


        if (action == PushConsts.SET_TAG_RESULT) {
            setTagResult((SetTagCmdMessage) cmdMessage);
        } else if ((action == PushConsts.THIRDPART_FEEDBACK)) {
            feedbackResult((FeedbackCmdMessage) cmdMessage);
        }


    }

    private void setTagResult(SetTagCmdMessage setTagCmdMsg) {
        String sn = setTagCmdMsg.getSn();
        String code = setTagCmdMsg.getCode();

        String text = "设置标签失败, 未知异常";
        switch (Integer.valueOf(code)) {
            case PushConsts.SETTAG_SUCCESS:
                text = "设置标签成功";
                break;

            case PushConsts.SETTAG_ERROR_COUNT:
                text = "设置标签失败, tag数量过大, 最大不能超过200个";
                break;

            case PushConsts.SETTAG_ERROR_FREQUENCY:
                text = "设置标签失败, 频率过快, 两次间隔应大于1s且一天只能成功调用一次";
                break;

            case PushConsts.SETTAG_ERROR_REPEAT:
                text = "设置标签失败, 标签重复";
                break;

            case PushConsts.SETTAG_ERROR_UNBIND:
                text = "设置标签失败, 服务未初始化成功";
                break;

            case PushConsts.SETTAG_ERROR_EXCEPTION:
                text = "设置标签失败, 未知异常";
                break;

            case PushConsts.SETTAG_ERROR_NULL:
                text = "设置标签失败, tag 为空";
                break;

            case PushConsts.SETTAG_NOTONLINE:
                text = "还未登陆成功";
                break;

            case PushConsts.SETTAG_IN_BLACKLIST:
                text = "该应用已经在黑名单中,请联系售后支持!";
                break;

            case PushConsts.SETTAG_NUM_EXCEED:
                text = "已存 tag 超过限制";
                break;

            default:
                break;
        }

        L.d(TAG, "settag result sn = " + sn + ", code = " + code + ", text = " + text);
    }

    private void feedbackResult(FeedbackCmdMessage feedbackCmdMsg) {
        String appid = feedbackCmdMsg.getAppid();
        String taskid = feedbackCmdMsg.getTaskId();
        String actionid = feedbackCmdMsg.getActionId();
        String result = feedbackCmdMsg.getResult();
        long timestamp = feedbackCmdMsg.getTimeStamp();
        String cid = feedbackCmdMsg.getClientId();

        L.d(TAG, "appid = " + appid + "\ntaskid = " + taskid + "\nactionid = " + actionid + "\nresult = " + result
                + "\ncid = " + cid + "\ntimestamp = " + timestamp);
    }


}