package com.doorcontrol.ruili.my.doorcontrol.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @
 * @类名称: ${type_name}
 * @类描述: ${todo}
 * @创建人：
 * @创建时间：${date} ${time}
 * @备注：
 */
public class News_Request_Url {

//    String httpUrl = "http://apis.baidu.com/3023/news/channel";
//    String httpArg = "id=popular&page=1";
//    String jsonResult = request(httpUrl, httpArg);
//    System.out.println(jsonResult);

    /**
     * @param :请求接口
     * @param httpArg :参数
     * @return 返回结果
     */
    public static String request(String httpUrl, String httpArg) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?" + httpArg;

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            // 填入apikey到HTTP header
            connection.setRequestProperty("apikey", "122f112830dccbcd587639df0666d697");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
