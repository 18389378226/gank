package com.chensen.gank.mvp.presenter;

import android.support.v4.view.PagerAdapter;
import android.util.Log;

import com.chensen.gank.bean.GanHuoBean;
import com.chensen.gank.bean.HttpResponse;
import com.chensen.gank.common.http.RetrofitHelper;
import com.chensen.gank.mvp.contact.AllContact;

import java.util.ArrayList;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author：chensen on 2016/11/30 15:54
 * desc：
 */

public class AllPresenter implements AllContact.Presenter {
    private AllContact.View mView;

    private int page = 1;
    private int size = 20;

    public AllPresenter(AllContact.View mView) {
        this.mView = mView;
    }

    @Override
    public void getAll() {
        page = 1;
        RetrofitHelper.getInstance()
                .getAll(size, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mView != null) {

                            mView.showFail("获取数据失败");
                        }
                    }

                    @Override
                    public void onNext(HttpResponse httpResponse) {
                        if (mView != null) {
                            if (!httpResponse.isError()) {
                                if (httpResponse.getResults() != null && httpResponse.getResults().size() > 0) {
                                    mView.showData(httpResponse.getResults());

                                } else {
                                    mView.showFail("暂无数据");
                                }
                            } else {
                                mView.showFail("获取数据失败");
                            }

                        }
                    }
                });


    }

    @Override
    public void getAllMore() {
        page++;
        RetrofitHelper.getInstance()
                .getAll(size, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mView != null) {

                            mView.showFail(e.toString());
                        }
                    }

                    @Override
                    public void onNext(HttpResponse httpResponse) {
                        if (mView != null) {
                            mView.showMoreData(httpResponse.getResults());
                        }
                    }
                });

    }

    @Override
    public void detachView() {
        mView = null;

    }

}
