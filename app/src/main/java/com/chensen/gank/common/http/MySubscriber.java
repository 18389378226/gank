package com.chensen.gank.common.http;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.orhanobut.logger.Logger;

import rx.Subscriber;

/**
 * author：chensen on 2016/12/2 09:03
 * desc：
 */

public class MySubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(T t) {
        Gson gson =new Gson();
        String s = gson.toJson(t);
        Logger.json(s);
    }
}
