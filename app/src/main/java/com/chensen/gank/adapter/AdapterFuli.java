package com.chensen.gank.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
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

public class AdapterFuli extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<GanHuoBean> mData = new ArrayList<>();
    private RecyclerView recyclerView;

    private OnItemCLickListener onItemCLickListener;
    private OnLoadMoreListener onLoadMoreListener;
    private boolean isLoad = false;

    private final int item_type = 100;
    private final int load_type = 200;

    public AdapterFuli(Context mContext, ArrayList<GanHuoBean> mData, RecyclerView recyclerView) {
        this.mContext = mContext;
        this.mData = mData;
        this.recyclerView = recyclerView;
        initRecyclerView();
    }

    private void initRecyclerView() {

        GridLayoutManager manager = (GridLayoutManager) recyclerView.getLayoutManager();
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == mData.size()) {
                    return 2;

                } else {
                    return 1;
                }
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                GridLayoutManager manager = (GridLayoutManager) recyclerView.getLayoutManager();
                int lastVisibleItemPosition = manager.findLastVisibleItemPosition();
                int itemCount = manager.getItemCount();
                if (lastVisibleItemPosition > itemCount - 2 && !isLoad && dy > 0) {
                    if (onLoadMoreListener != null) {
                        isLoad = true;
                        onLoadMoreListener.loadMore();

                    }
                }


            }
        });


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == item_type) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_rv_fuli, parent, false);
            ImageHolder holder = new ImageHolder(view);
            return holder;
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.load_view, parent, false);
            LoadHolder holder = new LoadHolder(view);
            return holder;
        }


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof ImageHolder) {

            ImageHolder imageHolder = (ImageHolder) holder;
            ViewGroup.LayoutParams layoutParams = imageHolder.itemView.getLayoutParams();
            layoutParams.height = ScreenUtil.getScreenHeight(mContext) / 3;

            ImageLoader.load(mContext, mData.get(position).getUrl(), imageHolder.imageView);


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemCLickListener != null) {
                        onItemCLickListener.OnClick(view, position);
                    }
                }
            });


        }
    }

    @Override
    public int getItemCount() {
        return mData.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mData.size()) {
            return load_type;
        } else {
            return item_type;

        }
    }

    class ImageHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public ImageHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_pic);
        }


    }

    class LoadHolder extends RecyclerView.ViewHolder {

        public LoadHolder(View itemView) {
            super(itemView);
        }
    }


    public interface OnItemCLickListener {
        void OnClick(View view, int position);
    }


    public interface OnLoadMoreListener {
        void loadMore();
    }


    public void setOnItemClickListener(OnItemCLickListener listener) {
        this.onItemCLickListener = listener;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        this.onLoadMoreListener = listener;
    }

    public void LoadComplete() {
        isLoad = false;
    }
}

