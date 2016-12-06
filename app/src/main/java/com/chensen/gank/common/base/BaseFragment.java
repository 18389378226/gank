package com.chensen.gank.common.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * author：chensen on 2016/11/30 14:15
 * desc：
 */

public abstract class BaseFragment extends Fragment {
    protected Context mContext;
    protected View mRootView;


    public BaseFragment(Context mContext) {
        this.mContext = mContext;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, mRootView);
        init();
        return mRootView;
    }


    protected abstract int getLayoutId();
    protected  abstract  void init();
    public    abstract  void setTheme(int color);
}
