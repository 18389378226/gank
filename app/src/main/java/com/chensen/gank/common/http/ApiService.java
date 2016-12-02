package com.chensen.gank.common.http;

import com.chensen.gank.bean.GanHuoBean;
import com.chensen.gank.bean.HttpResponse;

import java.util.List;

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


}
