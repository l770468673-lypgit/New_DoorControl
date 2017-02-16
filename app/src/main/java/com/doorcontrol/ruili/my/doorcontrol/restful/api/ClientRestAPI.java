package com.doorcontrol.ruili.my.doorcontrol.restful.api;

import com.doorcontrol.ruili.my.doorcontrol.restful.model.Bean_AddRecord;
import com.doorcontrol.ruili.my.doorcontrol.restful.model.Bean_FamilyList;
import com.doorcontrol.ruili.my.doorcontrol.restful.model.Bean_UserLogin;
import com.doorcontrol.ruili.my.doorcontrol.restful.model.Bean_listNews;
import com.doorcontrol.ruili.my.doorcontrol.restful.model.Bean_message;
import com.doorcontrol.ruili.my.doorcontrol.restful.model.CheckUpgradeResponse;
import com.doorcontrol.ruili.my.doorcontrol.restful.model.ResponseHead;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Define all rest API with server here Use open source Retrofit for http access
 * http://square.github.io/retrofit/
 *
 * @author
 */
public interface ClientRestAPI {


    /*
    @GET("data/sk/101010100.html")
    Call<Bean_UserLogin> testWeather();

  @GET("data/sk/101010100.html")
    Call<Bean_UserLogin> testWeather();
    // TODO
    @GET("checkUpdate")
    Call<UpgradeInfo> checkUpgrade();

    // Common interfaces
    @POST("/app/login/login/mobile")
    void userLoginMobile(@Query("mobile") String username,
                         @Query("code") String password, Callback<Login> callback);

    @POST("/app/login/logout")
    void userLogout(@Header("session") String session,
                    Callback<Login> callback);
                    */

    //  登陆
    @FormUrlEncoded
    @POST("user/login")
    Call<Bean_UserLogin> getUserLogin(@Field("loginName") String loginName, @Field("password") String password, @Field("deviceCode") String deviceCode);

    //  心跳http://localhost:8080/dams/api/user/heartBeat?   token=123
    //http://121.42.139.223:8080/dams/api/user/heartBeat?token=2eb164e1-a4ad-4852-95d2-630d7abeb89b&deviceCode=1234
    @FormUrlEncoded
    @POST("user/heartBeat")
    Call<ResponseHead> getIsLive(@Field("token") String token, @Field("deviceCode") String deviceCode);


    // 开门
    @FormUrlEncoded
    @POST("house/openDoor")
    Call<ResponseHead> getOpenDoor(@Field("token") String token);


    //添加家人
    @FormUrlEncoded
    @POST("house/addHouseOwner")
    Call<ResponseHead> getFrmilys(@Field("token") String token,
                                  @Field("houseId") String houseId,
                                  @Field("houseOwnerName") String houseOwnerName,
                                  @Field("phoneNumber") String phoneNumber);

    //house/getMyHouseOwners
    // 获取家人列表   house/getMyHouseOwners
    @GET("house/getMyHouseOwners")
    Call<Bean_FamilyList> getFramilyLists(@Query("token") String token);


    //house/deleteMyHouseOwner  删除家人
        @FormUrlEncoded
        @POST("house/deleteMyHouseOwner")
        Call<ResponseHead> getRemoveLists(@Field("token") String token,
                                          @Field("houseId") String houseId,
                                          @Field("houseOwnerId") String houseOwnerId);


 // 改密码
    //user/changePassword
    @FormUrlEncoded
    @POST("user/changePassword")
    Call<ResponseHead> getNewPassWord(@Field("password") String password,
                                      @Field("newPassword") String newPassword);

    ///house/setHouseOwnerOrders
    // tiao调整家人优先级
    @FormUrlEncoded
    @POST("house/setHouseOwnerOrders")
    Call<ResponseHead> getFramilylevel(@Field("token") String token,
                                       @Field("houseId") String houseId,
                                       @Field("houseOwnerOrders") String houseOwnerOrders);


    //notice/getNoticeLiByDevice?deviceCode=d2
    @GET("notice/getNoticeLiByDevice")
    Call<Bean_listNews> getListMessage(@Query("token") String token);


    // 开门记录
    //http://localhost:8080/dams/api/log/getMyLog
    @GET("log/getMyLog")
    Call<Bean_AddRecord> getOpenRecord(@Query("token") String token);

    //house/getMyNotioes  yezhu xinxi 业主信息
    @GET("house/getMyNotices")
    Call<Bean_message> getMessage(@Query("token") String token);



    //http://localhost:8080/dams/api/upgrade/getAppInfo?packageName=com.dame
    Call<CheckUpgradeResponse> getUpGrade(@Query("packageName")String packagename );
}









