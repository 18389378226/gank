package com.chensen.gank.mvp.presenter;

import com.chensen.gank.bean.HttpResponse;
import com.chensen.gank.common.http.RetrofitHelper;
import com.chensen.gank.mvp.contact.LeftMenuContact;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author：chensen on 2016/12/6 09:04
 * desc：
 */

public class LeftMenuPresenter implements LeftMenuContact.Presenter {

    private LeftMenuContact.View mView;

    private int page = 1;
    private int size = 1;

    public LeftMenuPresenter(LeftMenuContact.View mView) {
        this.mView = mView;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void getPic() {
        RetrofitHelper.getInstance()
                .getFuli(1, 1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<HttpResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showFail("获取数据失败");

                    }

                    @Override
                    public void onNext(HttpResponse httpResponse) {

                        if (!httpResponse.isError()) {


                            if (httpResponse.getResults() != null && httpResponse.getResults().size() > 0) {
                                mView.showPic(httpResponse);
                            }

                        }

                    }
                });


    }

    @Override
    public void getWords() {

        RetrofitHelper.getInstance()
                .getVideo(1, 1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<HttpResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showFail("获取数据失败");

                    }

                    @Override
                    public void onNext(HttpResponse httpResponse) {

                        if (!httpResponse.isError()) {


                            if (httpResponse.getResults() != null && httpResponse.getResults().size() > 0) {
                                mView.showWords(httpResponse);
                            }

                        }

                    }
                });

    }
}
