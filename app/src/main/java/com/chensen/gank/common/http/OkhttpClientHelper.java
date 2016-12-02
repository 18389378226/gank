package com.chensen.gank.common.http;

import android.content.Context;

import com.chensen.gank.BuildConfig;
import com.chensen.gank.MyApplication;
import com.chensen.gank.common.Constants;
import com.chensen.gank.common.utils.Tool;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * author：chensen on 2016/11/30 14:34
 * desc：
 */

public class OkhttpClientHelper {
    private static OkhttpClientHelper instance;


    public static OkhttpClientHelper getInstance() {
        if (instance == null) {
            synchronized (OkhttpClientHelper.class) {
                instance = new OkhttpClientHelper();
            }
        }
        return instance;
    }


    public OkHttpClient getOkhttpClient() {
        File cacheFile = new File(MyApplication.getInstance().getCacheDir(), Constants.PATH_CACHE);

        if (!cacheFile.exists()) {
            cacheFile.mkdirs();
        }

        Cache cache = new Cache(cacheFile, 100 * 1024 * 1024);
        OkHttpClient client = null;
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client = new OkHttpClient.Builder()
                    .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                    .readTimeout(10000, TimeUnit.MILLISECONDS)
                    .connectTimeout(10000, TimeUnit.MILLISECONDS)
                    .writeTimeout(10000, TimeUnit.MILLISECONDS)
                    .cache(cache)
                    .addInterceptor(httpLoggingInterceptor)
                    .build();
        } else {
            client = new OkHttpClient.Builder()
                    .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                    .readTimeout(10000, TimeUnit.MILLISECONDS)
                    .connectTimeout(10000, TimeUnit.MILLISECONDS)
                    .writeTimeout(10000, TimeUnit.MILLISECONDS)
                    .cache(cache)
                    .build();
        }
        return client;


    }

    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            if (Tool.isNetworkConnected()) {
                int maxAge = 60 * 10; // 在线缓存在10分钟内可读取
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28; // 离线时缓存保存4周
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        }
    };

    public OkHttpClient getCacheOkHttpClient(Context context) {
        final File baseDir = context.getCacheDir();
        final File cacheDir = new File(baseDir, "HttpResponseCache");
        Cache cache = new Cache(cacheDir, 100 * 1024 * 1024);   //缓存可用大小为10M
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!Tool.isNetworkConnected()) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response originalResponse = chain.proceed(request);
                if (Tool.isNetworkConnected()) {
                    int maxAge = 60;                  //在线缓存一分钟
                    return originalResponse.newBuilder()
                            .removeHeader("Pragma")
                            .removeHeader("Cache-Control")
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .build();
                } else {
                    int maxStale = 60 * 60 * 24 * 4 * 7;     //离线缓存4周
                    return originalResponse.newBuilder()
                            .removeHeader("Pragma")
                            .removeHeader("Cache-Control")
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .build();
                }
            }
        };
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .addInterceptor(interceptor)
                .cache(cache)
                .build();
    }

}
