package com.chensen.gank.common.base;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chensen.gank.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * author：chensen on 2016/11/30 14:08
 * desc：
 */

public abstract class BaseActivity extends AppCompatActivity {
    private ArrayList<Activity> mActivitys = new ArrayList<>();
    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mActivitys.add(this);
        mContext = this;
        ButterKnife.bind(this);
        init();

    }

    public abstract int getLayoutId();

    protected abstract void init();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mActivitys.remove(this);
    }

    public void finishAll() {
        for (Activity activity : mActivitys) {
            if (activity != null) {
                activity.finish();
            }
        }
    }


    public void setToolBar(Toolbar toolBar, String title) {
        toolBar.setTitle(title);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAfterTransition();
                } else {
                    finish();
                }
            }
        });

    }
}
