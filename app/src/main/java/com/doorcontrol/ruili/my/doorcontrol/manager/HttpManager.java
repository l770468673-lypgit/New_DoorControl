package com.doorcontrol.ruili.my.doorcontrol.manager;


import com.doorcontrol.ruili.my.doorcontrol.restful.api.ClientRestAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Use open source Retrofit for http access http://square.github.io/retrofit/
 * 
 * @author lizhiqiang3
 * 
 */
public class HttpManager {
    private final static String TAG = "HttpManager";

    // 通用接口                                         121.42.139.223/iDAMS   ----api_qd.ehome   api.qd-ehome.com:
    private final static String CONNECT_LOGIN = "http://api.qd-ehome.com:8090/dams/api/";


    private final static String SERVER_REST_API_URL = CONNECT_LOGIN;
            //"http://121.42.12.140/mmwd";

    private static HttpManager sHttpManager;

    public static HttpManager getInstance() {
        if (sHttpManager == null) {

            synchronized (HttpManager.class) {
                if (sHttpManager == null) {
                    sHttpManager = new HttpManager();
                    sHttpManager.init();
                }
            }
        }
        return sHttpManager;
    }


    private ClientRestAPI mHttpClient;
    private Retrofit mRetrofit;

    private void init() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(CONNECT_LOGIN)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mHttpClient = mRetrofit.create(ClientRestAPI.class);

    }

    public ClientRestAPI getHttpClient() {
        return mHttpClient;
    }
}
