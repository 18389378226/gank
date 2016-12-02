package com.chensen.gank.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chensen.gank.R;
import com.chensen.gank.adapter.AdaptetFuli;
import com.chensen.gank.bean.GanHuoBean;
import com.chensen.gank.common.base.BaseFragment;
import com.chensen.gank.mvp.contact.FuliContact;
import com.chensen.gank.mvp.presenter.FuliPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：chensen on 2016/12/2 14:17
 * desc：福利
 */

@SuppressLint("ValidFragment")
public class FuLiFragment extends BaseFragment implements FuliContact.View {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    private FuliPresenter mPresenter;
    private AdaptetFuli mAdaptet;
    private ArrayList<GanHuoBean> mData = new ArrayList<>();

    public FuLiFragment(Context mContext) {
        super(mContext);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_fuli;
    }

    @Override
    protected void init() {
        mPresenter = new FuliPresenter(this);
        mPresenter.getData();


        mAdaptet = new AdaptetFuli(mContext, mData, recyclerview);
        recyclerview.setAdapter(mAdaptet);
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 2, LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(layoutManager);








    }


    @Override
    public void showData(List<GanHuoBean> data) {
        mData.clear();
        mData.addAll(data);
        mAdaptet.notifyDataSetChanged();
    }

    @Override
    public void showMoreData(List<GanHuoBean> data) {
        if (data != null && data.size() > 0) {
            mData.addAll(data);
            mAdaptet.notifyItemRangeChanged(mData.size() - data.size(), data.size());

        } else {
            Toast.makeText(mContext, "没有更多数据了", 0).show();
        }

    }

    @Override
    public void showFail(String msg) {
        Toast.makeText(mContext, msg, 0).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void DismissLoading() {

    }
}
