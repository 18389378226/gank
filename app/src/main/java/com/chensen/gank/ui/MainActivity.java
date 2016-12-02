package com.chensen.gank.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chensen.gank.R;
import com.chensen.gank.common.base.BaseActivity;
import com.chensen.gank.common.utils.ScreenUtil;
import com.chensen.gank.common.view.ResideLayout;
import com.chensen.gank.ui.fragment.AllFragment;
import com.chensen.gank.ui.fragment.FuLiFragment;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.foundation_icons_typeface_library.FoundationIcons;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.typeface.IIcon;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class MainActivity extends BaseActivity {


    @BindView(R.id.iv_header)
    ImageView ivHeader;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.ll_topmenu)
    LinearLayout llTopmenu;
    @BindView(R.id.tv_all)
    TextView tvAll;
    @BindView(R.id.tv_fuli)
    TextView tvFuli;
    @BindView(R.id.tv_android)
    TextView tvAndroid;
    @BindView(R.id.tv_ios)
    TextView tvIos;
    @BindView(R.id.tv_video)
    TextView tvVideo;
    @BindView(R.id.tv_front)
    TextView tvFront;
    @BindView(R.id.tv_resource)
    TextView tvResource;
    @BindView(R.id.tv_app)
    TextView tvApp;
    @BindView(R.id.tv_more)
    TextView tvMore;
    @BindView(R.id.tv_about)
    TextView tvAbout;
    @BindView(R.id.tv_theme)
    TextView tvTheme;
    @BindView(R.id.ll_bottommenu)
    LinearLayout llBottommenu;
    @BindView(R.id.icon)
    ImageView ivIcon;
    @BindView(R.id.title)
    TextView tvTitle;
    @BindView(R.id.rl_content)
    FrameLayout rlContent;
    @BindView(R.id.activity_main)
    ResideLayout resideLayout;


    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

    }

    @Override
    public int getLayoutId() {

        return R.layout.activity_main;
    }

    @Override
    protected void init() {

        switchFragment(new AllFragment(mContext));

        setIconDrawable(tvAll, MaterialDesignIconic.Icon.gmi_view_comfy);
        setIconDrawable(tvFuli, MaterialDesignIconic.Icon.gmi_mood);
        setIconDrawable(tvAndroid, MaterialDesignIconic.Icon.gmi_android);
        setIconDrawable(tvIos, MaterialDesignIconic.Icon.gmi_apple);
        setIconDrawable(tvVideo, MaterialDesignIconic.Icon.gmi_collection_video);
        setIconDrawable(tvFront, MaterialDesignIconic.Icon.gmi_language_javascript);
        setIconDrawable(tvResource, FontAwesome.Icon.faw_location_arrow);
        setIconDrawable(tvApp, MaterialDesignIconic.Icon.gmi_apps);
        setIconDrawable(tvAbout, MaterialDesignIconic.Icon.gmi_account);
        setIconDrawable(tvTheme, MaterialDesignIconic.Icon.gmi_palette);
        setIconDrawable(tvMore, MaterialDesignIconic.Icon.gmi_more);


        Picasso.with(mContext)
                .load(R.drawable.header)
                .placeholder(new IconicsDrawable(mContext)
                        .icon(FoundationIcons.Icon.fou_photo)
                        .color(Color.GRAY)
                        .backgroundColor(Color.WHITE)
                        .roundedCornersDp(40)
                        .paddingDp(15)
                        .sizeDp(75))
                .transform(new CropCircleTransformation())
                .error(R.drawable.default_img)
                .into(ivHeader);


    }

    private void switchFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rl_content, fragment)
                .commit();
    }

    private void setIconDrawable(TextView view, IIcon icon) {
        view.setCompoundDrawablesWithIntrinsicBounds(new IconicsDrawable(this)
                        .icon(icon)
                        .color(Color.WHITE)
                        .sizeDp(16),
                null, null, null);
        view.setCompoundDrawablePadding(ScreenUtil.dip2px(this, 10));
    }


    @OnClick({R.id.tv_all, R.id.tv_fuli, R.id.tv_android, R.id.tv_ios, R.id.tv_video, R.id.tv_front, R.id.tv_resource, R.id.tv_app, R.id.tv_more, R.id.tv_about, R.id.tv_theme, R.id.icon})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_all:
                resideLayout.closePane();
                ivIcon.setImageDrawable(new IconicsDrawable(this).color(Color.WHITE).icon(MaterialDesignIconic.Icon.gmi_view_comfy).sizeDp(20));
                tvTitle.setText("干货集中营");
                switchFragment(new AllFragment(mContext));
                break;
            case R.id.tv_fuli:
                resideLayout.closePane();
                ivIcon.setImageDrawable(new IconicsDrawable(this).color(Color.WHITE).icon(MaterialDesignIconic.Icon.gmi_mood).sizeDp(20));
                tvTitle.setText(R.string.fuli);
                switchFragment(new FuLiFragment(mContext));
                break;
            case R.id.tv_android:
                break;
            case R.id.tv_ios:
                break;
            case R.id.tv_video:
                break;
            case R.id.tv_front:
                break;
            case R.id.tv_resource:
                break;
            case R.id.tv_app:
                break;
            case R.id.tv_more:
                break;
            case R.id.tv_about:
                break;
            case R.id.tv_theme:
                break;
            case R.id.icon:
                break;
        }
    }
}
