package com.chensen.gank.ui;

import android.support.v7.widget.Toolbar;

import com.chensen.gank.R;
import com.chensen.gank.common.base.BaseActivity;
import com.chensen.gank.common.http.ImageLoader;

import butterknife.BindView;
import uk.co.senab.photoview.PhotoView;

/**
 * author：chensen on 2016/12/2 14:44
 * desc：
 */

public class PicActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.photoview)
    PhotoView photoview;


    private String url;


    @Override
    public int getLayoutId() {
        return R.layout.activity_pic;
    }

    @Override
    protected void init() {
        url = getIntent().getStringExtra("url");
        ImageLoader.load(mContext, url, photoview);
        setToolBar(toolbar, "妹纸");

    }
}
