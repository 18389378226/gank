package com.chensen.gank.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chensen.gank.R;
import com.chensen.gank.bean.GanHuoBean;
import com.chensen.gank.common.http.ImageLoader;
import com.chensen.gank.common.utils.ScreenUtil;
import com.chensen.gank.ui.PicActivity;

import java.util.ArrayList;

/**
 * author：chensen on 2016/12/2 16:11
 * desc：
 */

public class AdaptetFuli extends RecyclerView.Adapter<AdaptetFuli.ImageHolder> {
    private Context mContext;
    private ArrayList<GanHuoBean> mData = new ArrayList<>();
    private RecyclerView recyclerView;

    public AdaptetFuli(Context mContext, ArrayList<GanHuoBean> mData, RecyclerView recyclerView) {
        this.mContext = mContext;
        this.mData = mData;
        this.recyclerView = recyclerView;
        initRecyclerView();
    }

    private void initRecyclerView() {



    }

    @Override
    public ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_rv_fuli, parent, false);
        ImageHolder holder = new ImageHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ImageHolder holder, int position) {

        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.height = ScreenUtil.getScreenHeight(mContext)/3;

        ImageLoader.load(mContext, mData.get(position).getUrl(), holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ImageHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public ImageHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_pic);
        }
    }
}
