package com.chensen.gank.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chensen.gank.R;
import com.chensen.gank.adapter.AdapterFuli;
import com.chensen.gank.bean.GanHuoBean;
import com.chensen.gank.common.base.BaseFragment;
import com.chensen.gank.mvp.contact.FuliContact;
import com.chensen.gank.mvp.presenter.FuliPresenter;
import com.chensen.gank.ui.PicActivity;

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
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.floatActionBtn)
    FloatingActionButton floatActionBtn;

    private FuliPresenter mPresenter;
    private AdapterFuli mAdapter;
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

        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 2, LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(layoutManager);
        mAdapter = new AdapterFuli(mContext, mData, recyclerview);
        recyclerview.setAdapter(mAdapter);

        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getData();
            }
        });

        mAdapter.setOnItemClickListener(new AdapterFuli.OnItemCLickListener() {
            @Override
            public void OnClick(View view, int position) {
                Intent intent = new Intent(mContext, PicActivity.class);
                intent.putExtra("url", mData.get(position).getUrl());
                startActivity(intent);
            }
        });

        mAdapter.setOnLoadMoreListener(new AdapterFuli.OnLoadMoreListener() {
            @Override
            public void loadMore() {

                mPresenter.getMoreData();
            }
        });

        floatActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerview.scrollToPosition(0);
            }
        });

    }

    @Override
    public void setTheme(int color) {
        floatActionBtn.setBackgroundTintList(ColorStateList.valueOf(color));
    }


    @Override
    public void showData(List<GanHuoBean> data) {
        if(swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
        }
        mData.clear();
        mData.addAll(data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMoreData(List<GanHuoBean> data) {
        mAdapter.LoadComplete();
        if (data != null && data.size() > 0) {
            mData.addAll(data);
            mAdapter.notifyItemRangeChanged(mData.size() - data.size(), data.size());
            mAdapter.LoadComplete();

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
