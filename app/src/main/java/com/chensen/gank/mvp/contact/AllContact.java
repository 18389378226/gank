package com.chensen.gank.mvp.contact;

import com.chensen.gank.bean.GanHuoBean;
import com.chensen.gank.common.base.BasePresenter;
import com.chensen.gank.common.base.BaseView;

import java.util.List;

/**
 * author：chensen on 2016/11/30 15:49
 * desc： 某一天的数据
 */

public class AllContact {

    public interface View extends BaseView {
        void showData(List<GanHuoBean> data);

        void showMoreData(List<GanHuoBean> data);
    }

    public interface Presenter extends BasePresenter {
        void getAll();

        void getAllMore();

    }
}
