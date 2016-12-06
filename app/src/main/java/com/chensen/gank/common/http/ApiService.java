package com.chensen.gank.common.http;

import com.chensen.gank.bean.HttpResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * author：chensen on 2016/11/30 15:59
 * desc：
 */

public interface ApiService {
    /**
     * 每日数据
     */
    @GET("data/all/{size}/{page}")
    Observable<HttpResponse> getAll(@Path("size") int size, @Path("page") int page);

    /**
     * 福利
     */
    @GET("data/福利/{size}/{page}")
    Observable<HttpResponse> getFuli(@Path("size") int size, @Path("page") int page);

    /**
     * android
     */
    @GET("data/Android/{size}/{page}")
    Observable<HttpResponse> getAndroid(@Path("size") int size, @Path("page") int page);

    /**
     * ios
     */
    @GET("data/iOS/{size}/{page}")
    Observable<HttpResponse> getIos(@Path("size") int size, @Path("page") int page);

    /**
     * 休息视频
     */
    @GET("data/休息视频/{size}/{page}")
    Observable<HttpResponse> getVieo(@Path("size") int size, @Path("page") int page);

    /**
     * 前端
     */
    @GET("data/前端/{size}/{page}")
    Observable<HttpResponse> getFront(@Path("size") int size, @Path("page") int page);

    /**
     * 拓展资源
     */
    @GET("data/拓展资源/{size}/{page}")
    Observable<HttpResponse> getResource(@Path("size") int size, @Path("page") int page);

    /**
     * app
     */
    @GET("data/App/{size}/{page}")
    Observable<HttpResponse> getApp(@Path("size") int size, @Path("page") int page);

    /**
     * 瞎推荐
     */
    @GET("data/瞎推荐/{size}/{page}")
    Observable<HttpResponse> getRecommend(@Path("size") int size, @Path("page") int page);


}
