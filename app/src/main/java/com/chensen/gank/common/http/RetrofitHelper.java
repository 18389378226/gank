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
     * 获取妹子列表
     */
    public Observable<HttpResponse> getAll(int size, int page) {
        if (apiService == null) {
            new RetrofitHelper();
        }
        return apiService.getAll(size, page);
    }


}
