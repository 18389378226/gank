package com.chensen.gank.mvp.presenter;

import com.chensen.gank.bean.HttpResponse;
import com.chensen.gank.common.http.MySubscriber;
import com.chensen.gank.common.http.RetrofitHelper;
import com.chensen.gank.mvp.contact.CommonContact;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author：chensen on 2016/11/30 15:54
 * desc：全部
 */

public class AllPresenter implements CommonContact.Presenter {
    private CommonContact.View mView;

    private int page = 1;
    private int size = 20;

    public AllPresenter(CommonContact.View mView) {
        this.mView = mView;
    }

    @Override
    public void getData() {
        page = 1;
        RetrofitHelper.getInstance()
                .getAll(size, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<HttpResponse>() {
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
                        super.onNext(httpResponse);
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
    public void getMoreData() {
        page++;
        RetrofitHelper.getInstance()
                .getAll(size, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<HttpResponse>() {
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
                        super.onNext(httpResponse);
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
