package com.chensen.gank.mvp.contact;

import com.chensen.gank.bean.HttpResponse;
import com.chensen.gank.common.base.BasePresenter;
import com.chensen.gank.common.base.BaseView;

import java.util.List;

import static android.R.attr.data;

/**
 * author：chensen on 2016/12/6 09:06
 * desc：
 */

public class LeftMenuContact {

    public interface View extends BaseView {
        void showPic(HttpResponse data);

        void showWords(HttpResponse data);
    }

    public interface Presenter extends BasePresenter {
        void getPic();

        void getWords();
    }
}
