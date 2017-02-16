package com.doorcontrol.ruili.my.doorcontrol.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.doorcontrol.ruili.my.doorcontrol.R;

/**
 * @
 * @类名称: ${}
 * @类描述: ${type_name}
 * @创建人： Lyp
 * @创建时间：${date} ${time}
 * @备注：
 */
public class Mess_Web extends AppCompatActivity {


    private Message_tbn mlist;
    private WebView msg_webview;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mess_web);

        initView();

        loadWebDate();
    }

    private void loadWebDate() {

        Intent intent = getIntent();

        String mNewurl = intent.getStringExtra("mNewurl");
        //设置WebView属性，能够执行Javascript脚本
        msg_webview.getSettings().setJavaScriptEnabled(true);


        //加载需要显示的网页
        msg_webview.loadUrl(mNewurl);
        //  Log.d("flag","------------------->url: "+mZIXUNDETAL);
        //设置Web视图
        msg_webview.setWebViewClient(new HelloWebViewClient());
    }

    private void initView() {
        msg_webview= (WebView) findViewById(R.id.msg_webview);
    }


    private class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && msg_webview.canGoBack()) {
            msg_webview.goBack(); //goBack()表示返回WebView的上一页面
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
