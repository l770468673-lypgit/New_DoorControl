package com.doorcontrol.ruili.my.doorcontrol.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.doorcontrol.ruili.my.doorcontrol.Custom.CircleImageView;
import com.doorcontrol.ruili.my.doorcontrol.R;
import com.doorcontrol.ruili.my.doorcontrol.activitys.AboutHelp;
import com.doorcontrol.ruili.my.doorcontrol.activitys.LoginActivity;
import com.doorcontrol.ruili.my.doorcontrol.activitys.NewKeyMessage;
import com.doorcontrol.ruili.my.doorcontrol.activitys.OpenDoorRecord;
import com.doorcontrol.ruili.my.doorcontrol.utils.Constant;
import com.doorcontrol.ruili.my.doorcontrol.utils.ShareUtil;
import com.qihoo.appstore.common.updatesdk.lib.UpdateHelper;

import java.io.ByteArrayInputStream;

/**
 * @
 * @类名称: ${type_name}
 * @类描述: ${todo}
 * @创建人：
 * @创建时间：${date} ${time}
 * @备注：
 */
public class Neighb_Fragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "Neighb_Fragment";
    private LinearLayout neighbour_rgb;   // radiogroup
    private ImageView neighbour_notice, neighbour_private_message, neighbour_wuyebaixiu, neighbour_wuyetousu;   //4 ge 按钮
    private Intent mLoginintent;
    private CircleImageView user_own_setting_img;
    public static TextView user_own_setting_name;

    private LinearLayout opendoorRecord;
    private LinearLayout keymessage;
    private LinearLayout checkupdate;
    //  private LinearLayout tellsetting;
    private LinearLayout abouthelp;
    //  private LinearLayout onlysetting;
    private Intent mOnenintent;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.neighbfragment, container, false);

        initView(inflate);


        return inflate;
    }


    private void initView(View view) {

        String usernumber = ShareUtil.getString(Constant.Shares.USER_OWN_SETTING_NAME);
        user_own_setting_img = (CircleImageView) view.findViewById(R.id.user_own_setting_img);

//        String vistorPicUrl = ShareUtil.getString("vistorPicUrl");
//        if (vistorPicUrl != null) {
//            Picasso.with(getActivity()).load(vistorPicUrl).into(user_own_setting_img);
//        }

        String imagebitmap = ShareUtil.getString("imagebitmap");
        if (imagebitmap != null) {
            //第二步:利用Base64将字符串转换为ByteArrayInputStream
            byte[] byteArray = Base64.decode(imagebitmap, Base64.DEFAULT);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
            //第三步:利用ByteArrayInputStream生成Bitmap
            Bitmap bitmap = BitmapFactory.decodeStream(byteArrayInputStream);
            user_own_setting_img.setImageBitmap(bitmap);
        }
        //    neighbour_rgb = (LinearLayout) view.findViewById(R.id.neighbour_rgb);
        user_own_setting_name = (TextView) view.findViewById(R.id.user_own_setting_name);
        if (usernumber != null) {
            user_own_setting_name.setText(usernumber);
        }

        opendoorRecord = (LinearLayout) view.findViewById(R.id.opendoorRecord);
        checkupdate = (LinearLayout) view.findViewById(R.id.checkupdate);
        keymessage = (LinearLayout) view.findViewById(R.id.keymessage);
        //  tellsetting = (LinearLayout) view.findViewById(R.id.tellsetting);
        abouthelp = (LinearLayout) view.findViewById(R.id.abouthelp);
        //   onlysetting = (LinearLayout) view.findViewById(R.id.onlysetting);

        //        neighbour_notice = (ImageView) view.findViewById(R.id.neighbour_notice);
        //        neighbour_private_message = (ImageView) view.findViewById(R.id.neighbour_private_message);
        //        neighbour_wuyebaixiu = (ImageView) view.findViewById(R.id.neighbour_wuyebaixiu);
        //        neighbour_wuyetousu = (ImageView) view.findViewById(R.id.neighbour_wuyetousu);

        opendoorRecord.setOnClickListener(this);
        keymessage.setOnClickListener(this);
        //   tellsetting.setOnClickListener(this);
        abouthelp.setOnClickListener(this);
        checkupdate.setOnClickListener(this);
        //       onlysetting.setOnClickListener(this);


        // neighbour_notice.setOnClickListener(this);
        //    neighbour_private_message.setOnClickListener(this);
        // /  neighbour_wuyebaixiu.setOnClickListener(this);
        //    neighbour_wuyetousu.setOnClickListener(this);
        user_own_setting_img.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        int checkedId = v.getId();
        switch (checkedId) {
            //            case R.id.neighbour_notice:
            //                Toast.makeText(getActivity(), "公告", Toast.LENGTH_SHORT).show();
            //                break;
            //            case R.id.neighbour_private_message:
            //                Toast.makeText(getActivity(), "私信", Toast.LENGTH_SHORT).show();
            //                break;
            //            case R.id.neighbour_wuyebaixiu:
            //                Toast.makeText(getActivity(), "物业报修", Toast.LENGTH_SHORT).show();
            //                break;
            //            case R.id.neighbour_wuyetousu:
            //                Toast.makeText(getActivity(), "物业投诉", Toast.LENGTH_SHORT).show();
            //               break;
            case R.id.user_own_setting_img:
                // 点击头像登陆
                mLoginintent = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(mLoginintent, Constant.CODE.REUQEST_LOGIN);
                break;

            case R.id.opendoorRecord:
                mOnenintent = new Intent(getActivity(), OpenDoorRecord.class);
                startActivity(mOnenintent);
                break;

            case R.id.keymessage:
                mOnenintent = new Intent(getActivity(), NewKeyMessage.class);
                startActivity(mOnenintent);
                break;
            //            case R.id.tellsetting:
            //                mOnenintent = new Intent(getActivity(), Framily_list.class);
            //                startActivity(mOnenintent);
            //                break;
            case R.id.abouthelp:
                mOnenintent = new Intent(getActivity(), AboutHelp.class);
                startActivity(mOnenintent);
                break;
            case R.id.checkupdate:

                String packageName = getActivity().getPackageName();
                UpdateHelper.getInstance().init(getActivity(), Color.parseColor("#0A93DB"));
                UpdateHelper.getInstance().setDebugMode(true);
                UpdateHelper.getInstance().manualUpdate(packageName);
                break;
            //            case R.id.onlysetting:
            //                mOnenintent = new Intent(getActivity(), OnlySetting.class);
            //                startActivity(mOnenintent);
            //                break;

        }
    }

}
