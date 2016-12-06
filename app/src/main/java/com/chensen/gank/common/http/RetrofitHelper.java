package com.chensen.gank.common.http;

import com.chensen.gank.bean.GanHuoBean;
import com.chensen.gank.bean.HttpResponse;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * author：chensen on 2016/11/30 14:27
 * desc：
 */

public class RetrofitHelper {
    public static RetrofitHelper instance;
    private ApiService apiService;

    public RetrofitHelper() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public static RetrofitHelper getInstance() {
        if (instance == null) {
            synchronized (RetrofitHelper.class) {
                if (instance == null) {
                    instance = new RetrofitHelper();
                }
            }

        }
        return instance;
    }


    /**
     * 获取所有干货列表
     */
    public Observable<HttpResponse> getAll(int size, int page) {
        if (apiService == null) {
            new RetrofitHelper();
        }
        return apiService.getAll(size, page);
    }

    /**
     * 获取福利列表
     */
    public Observable<HttpResponse> getFuli(int size, int page) {
        if (apiService == null) {
            new RetrofitHelper();
        }
        return apiService.getFuli(size, page);
    }

    /**
     * 获取Android列表
     */
    public Observable<HttpResponse> getAndroid(int size, int page) {
        if (apiService == null) {
            new RetrofitHelper();
        }
        return apiService.getAndroid(size, page);
    }

    /**
     * 获取ios列表
     */
    public Observable<HttpResponse> getIos(int size, int page) {
        if (apiService == null) {
            new RetrofitHelper();
        }
        return apiService.getIos(size, page);
    }

    /**
     * 获取休息视频列表
     */
    public Observable<HttpResponse> getVideo(int size, int page) {
        if (apiService == null) {
            new RetrofitHelper();
        }
        return apiService.getVieo(size, page);
    }

    /**
     * 获取前端列表
     */
    public Observable<HttpResponse> getFront(int size, int page) {
        if (apiService == null) {
            new RetrofitHelper();
        }
        return apiService.getFront(size, page);
    }

    /**
     * 获取拓展资源列表
     */
    public Observable<HttpResponse> getResource(int size, int page) {
        if (apiService == null) {
            new RetrofitHelper();
        }
        return apiService.getResource(size, page);
    }

    /**
     * 获取App列表
     */
    public Observable<HttpResponse> getApp(int size, int page) {
        if (apiService == null) {
            new RetrofitHelper();
        }
        return apiService.getApp(size, page);
    }

    /**
     * 获取App列表
     */
    public Observable<HttpResponse> getRecommend(int size, int page) {
        if (apiService == null) {
            new RetrofitHelper();
        }
        return apiService.getRecommend(size, page);
    }

}
