package com.chensen.gank.mvp.presenter;

import android.support.v4.view.PagerAdapter;

import com.chensen.gank.bean.HttpResponse;
import com.chensen.gank.common.http.MySubscriber;
import com.chensen.gank.common.http.RetrofitHelper;
import com.chensen.gank.mvp.contact.FuliContact;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author：chensen on 2016/12/2 16:28
 * desc：
 */

public class FuliPresenter implements FuliContact.Presenter {

    private int page = 1;
    private int size = 20;

    private FuliContact.View mView;

    public FuliPresenter(FuliContact.View mView) {
        this.mView = mView;
    }

    @Override
    public void getData() {
        page = 1;
        RetrofitHelper.getInstance()
                .getFuli(size, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<HttpResponse>() {
                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.showFail("获取数据失败");
                    }

                    @Override
                    public void onNext(HttpResponse httpResponse) {
                        super.onNext(httpResponse);
                        if (!httpResponse.isError()) {
                            if (httpResponse.getResults() != null && httpResponse.getResults().size() > 0) {

                                mView.showData(httpResponse.getResults());
                            } else {
                                mView.showFail("获取数据失败");
                            }

                        } else {
                            mView.showFail("获取数据失败");
                        }


                    }
                });


    }

    @Override
    public void getMoreData() {
        page++;
        RetrofitHelper.getInstance()
                .getFuli(size, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<HttpResponse>() {
                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.showFail("获取数据失败");
                    }

                    @Override
                    public void onNext(HttpResponse httpResponse) {
                        super.onNext(httpResponse);
                        if (!httpResponse.isError()) {
                            mView.showMoreData(httpResponse.getResults());
                        } else {
                            mView.showFail("获取数据失败");
                        }


                    }
                });

    }

    @Override
    public void detachView() {
        mView=null;

    }
}
