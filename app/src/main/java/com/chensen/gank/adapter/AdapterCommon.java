package com.chensen.gank.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chensen.gank.R;
import com.chensen.gank.bean.GanHuoBean;
import com.chensen.gank.common.http.ImageLoader;

import java.util.ArrayList;

/**
 * author：chensen on 2016/12/1 09:49
 * desc：
 */

public class AdapterCommon extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<GanHuoBean> mData;
    private RecyclerView mRecyclerView;


    private final int item_type = 100;
    private final int load_type = 200;
    private final int image_type = 300;
    private boolean isLoading = false;
    private boolean isFirst = true;

    private OnItemClickListener mItemClickListener;
    private OnLoadMoreListener mLoadMoreListener;


    public AdapterCommon(Context mContext, ArrayList<GanHuoBean> mData, RecyclerView mRecyclerView) {
        this.mContext = mContext;
        this.mData = mData;
        this.mRecyclerView = mRecyclerView;
        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                final int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                int itemCount = layoutManager.getItemCount();
                if (lastVisibleItemPosition == itemCount - 1 && !isLoading && dy > 0) {
                    isLoading = true;
                    if (mLoadMoreListener != null) {
                        mLoadMoreListener.onLoadMore();
                    }

                }

            }
        });


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == load_type) {
            View loadView = LayoutInflater.from(mContext).inflate(R.layout.load_view, parent, false);
            LoadViewHolder viewHolder = new LoadViewHolder(loadView);
            return viewHolder;

        } else if (viewType == image_type) {
            View imageView = LayoutInflater.from(mContext).inflate(R.layout.image_view, parent, false);
            ImageViewHolder viewHolder = new ImageViewHolder(imageView);
            return viewHolder;

        } else {
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_view, parent, false);
            ItemViewHolder viewHolder = new ItemViewHolder(itemView);
            return viewHolder;

        }


    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {


        if (holder instanceof ItemViewHolder) {
            GanHuoBean ganHuoBean = mData.get(position);
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.textView.setText(ganHuoBean.getDesc() + "  [" + ganHuoBean.getWho() + "]");
            itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(view, position);
                    }
                }
            });

        } else if (holder instanceof ImageViewHolder) {
            GanHuoBean ganHuoBean = mData.get(position);
            ImageViewHolder imageViewHolder = (ImageViewHolder) holder;
            ImageLoader.load(mContext, ganHuoBean.getUrl(), imageViewHolder.imageView);
            imageViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mItemClickListener != null) {

                        mItemClickListener.onItemClick(view, position);
                    }
                }
            });
        } else if (holder instanceof LoadViewHolder) {
            if (getItemCount() == 1) {
                holder.itemView.setVisibility(View.INVISIBLE);
            } else {
                holder.itemView.setVisibility(View.VISIBLE);

            }

        }

    }

    @Override
    public int getItemCount() {
        return mData.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mData.size()) {
            return load_type;
        } else {
            if (mData.get(position).getType().equals("福利")) {
                return image_type;
            } else {

                return item_type;
            }
        }
    }

    public void loadCompleted() {
        isLoading = false;
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        this.mLoadMoreListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    class LoadViewHolder extends RecyclerView.ViewHolder {
        public LoadViewHolder(View itemView) {
            super(itemView);
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_desc);
        }
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public ImageViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_pic);
        }
    }
}
