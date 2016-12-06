package com.chensen.gank.ui;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.chensen.gank.R;
import com.chensen.gank.common.base.BaseActivity;
import com.chensen.gank.common.utils.SharedPreferencesUtils;
import com.chensen.gank.common.utils.SystemUtils;
import com.chensen.gank.theme.ColorView;

import butterknife.BindView;

/**
 * author：chensen on 2016/12/2 15:10
 * desc： 显示网页
 */

public class CustomWebViewActivity extends BaseActivity {
    @BindView(R.id.status_bar)
    ColorView mStatusBar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;
    @BindView(R.id.webview)
    WebView webview;

    private String url;
    private String title;


    @Override
    public int getLayoutId() {
        return R.layout.activity_webview;
    }

    @Override
    protected void init() {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mStatusBar.setVisibility(View.VISIBLE);
            mStatusBar.getLayoutParams().height = SystemUtils.getStatusHeight(this) + 2;
            mStatusBar.setLayoutParams(mStatusBar.getLayoutParams());
        } else {
            mStatusBar.setVisibility(View.GONE);
        }
        toolbar.setBackgroundColor(Color.parseColor(SharedPreferencesUtils.getString(mContext, "themeColor", "#3F51B5")));
        mStatusBar.setBackgroundColor(Color.parseColor(SharedPreferencesUtils.getString(mContext, "themeColor", "#3F51B5")));

        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");

        setToolBar(toolbar, title);


        progressBar.setMax(100);

        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setSupportZoom(false);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setMediaPlaybackRequiresUserGesture(true);

        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {

                progressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                }

                super.onProgressChanged(view, newProgress);

            }
        });
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                progressBar.setVisibility(View.VISIBLE);
                webview.loadUrl(url);
                return true;
            }
        });

        webview.loadUrl(url);
    }


    @Override
    public void onBackPressed() {
        if (webview.canGoBack()) {
            webview.goBack();
        } else {
            finish();
        }


    }
}
