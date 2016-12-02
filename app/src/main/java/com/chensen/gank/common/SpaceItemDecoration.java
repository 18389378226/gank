package com.chensen.gank.common;

import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * author：chensen on 2016/12/1 14:57
 * desc：
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {


    private int space;

    public SpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
       // if (parent.getChildPosition(view) != 0) {
            outRect.top = space;

        //}
    }

}
