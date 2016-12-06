package com.chensen.gank.ui;

import android.animation.Animator;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.color.ColorChooserDialog;
import com.chensen.gank.R;
import com.chensen.gank.bean.HttpResponse;
import com.chensen.gank.common.SkinChangeEvent;
import com.chensen.gank.common.base.BaseActivity;
import com.chensen.gank.common.base.BaseFragment;
import com.chensen.gank.common.utils.ScreenUtil;
import com.chensen.gank.common.utils.SharedPreferencesUtils;
import com.chensen.gank.common.utils.SystemUtils;
import com.chensen.gank.common.utils.ThemeUtils;
import com.chensen.gank.common.view.ResideLayout;
import com.chensen.gank.mvp.contact.LeftMenuContact;
import com.chensen.gank.mvp.presenter.LeftMenuPresenter;
import com.chensen.gank.theme.ColorUiUtil;
import com.chensen.gank.theme.ColorView;
import com.chensen.gank.theme.Theme;
import com.chensen.gank.ui.fragment.AllFragment;
import com.chensen.gank.ui.fragment.AndroidFragment;
import com.chensen.gank.ui.fragment.AppFragment;
import com.chensen.gank.ui.fragment.FrontFragment;
import com.chensen.gank.ui.fragment.FuLiFragment;
import com.chensen.gank.ui.fragment.IosFragment;
import com.chensen.gank.ui.fragment.RecommendFragment;
import com.chensen.gank.ui.fragment.ResourceFragment;
import com.chensen.gank.ui.fragment.VideoFragment;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.foundation_icons_typeface_library.FoundationIcons;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.typeface.IIcon;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

import static com.chensen.gank.R.color.colorIndigoPrimary;
import static com.chensen.gank.R.color.colorOrangePrimary;

public class MainActivity extends BaseActivity implements ColorChooserDialog.ColorCallback, LeftMenuContact.View {


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
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.rl_content)
    FrameLayout rlContent;
    @BindView(R.id.activity_main)
    ResideLayout resideLayout;
    @BindView(R.id.status_bar)
    ColorView mStatusBar;


    private BaseFragment currentFragment;
    private LeftMenuPresenter mPresenter;

    private int lastTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

    }

    @Override
    public int getLayoutId() {

        return R.layout.activity_main;
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

        mPresenter = new LeftMenuPresenter(this);
        mPresenter.getPic();
        mPresenter.getWords();


        ivIcon.setImageDrawable(new IconicsDrawable(this).color(Color.WHITE).icon(MaterialDesignIconic.Icon.gmi_view_comfy).sizeDp(20));
        tvTitle.setText("干货集中营");
        currentFragment = new AllFragment(mContext);
        switchFragment(currentFragment);

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


    @OnClick({R.id.tv_all, R.id.tv_fuli, R.id.tv_android, R.id.tv_ios, R.id.tv_video, R.id.tv_front, R.id.tv_resource, R.id.tv_app, R.id.tv_more, R.id.tv_about, R.id.tv_theme, R.id.rl_title})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_all:
                resideLayout.closePane();
                ivIcon.setImageDrawable(new IconicsDrawable(this).color(Color.WHITE).icon(MaterialDesignIconic.Icon.gmi_view_comfy).sizeDp(20));
                tvTitle.setText("干货集中营");
                currentFragment = new AllFragment(this);
                switchFragment(currentFragment);

                break;
            case R.id.tv_fuli:
                resideLayout.closePane();
                ivIcon.setImageDrawable(new IconicsDrawable(this).color(Color.WHITE).icon(MaterialDesignIconic.Icon.gmi_mood).sizeDp(20));
                tvTitle.setText(R.string.fuli);
                currentFragment = new FuLiFragment(this);
                switchFragment(currentFragment);
                break;
            case R.id.tv_android:
                resideLayout.closePane();
                ivIcon.setImageDrawable(new IconicsDrawable(this).color(Color.WHITE).icon(MaterialDesignIconic.Icon.gmi_android).sizeDp(20));
                tvTitle.setText(R.string.android);
                currentFragment = new AndroidFragment(this);
                switchFragment(currentFragment);
                break;
            case R.id.tv_ios:
                resideLayout.closePane();
                ivIcon.setImageDrawable(new IconicsDrawable(this).color(Color.WHITE).icon(MaterialDesignIconic.Icon.gmi_apple).sizeDp(20));
                tvTitle.setText(R.string.ios);
                currentFragment = new IosFragment(this);
                switchFragment(currentFragment);
                break;
            case R.id.tv_video:
                resideLayout.closePane();
                ivIcon.setImageDrawable(new IconicsDrawable(this).color(Color.WHITE).icon(MaterialDesignIconic.Icon.gmi_collection_video).sizeDp(20));
                tvTitle.setText(R.string.video);
                currentFragment = new VideoFragment(this);
                switchFragment(currentFragment);
                break;
            case R.id.tv_front:
                resideLayout.closePane();
                ivIcon.setImageDrawable(new IconicsDrawable(this).color(Color.WHITE).icon(MaterialDesignIconic.Icon.gmi_language_javascript).sizeDp(20));
                tvTitle.setText(R.string.front);
                currentFragment = new FrontFragment(this);
                switchFragment(currentFragment);
                break;
            case R.id.tv_resource:
                resideLayout.closePane();
                ivIcon.setImageDrawable(new IconicsDrawable(this).color(Color.WHITE).icon(FontAwesome.Icon.faw_location_arrow).sizeDp(20));
                tvTitle.setText(R.string.resource);
                currentFragment = new ResourceFragment(this);
                switchFragment(currentFragment);
                break;
            case R.id.tv_app:
                resideLayout.closePane();
                ivIcon.setImageDrawable(new IconicsDrawable(this).color(Color.WHITE).icon(MaterialDesignIconic.Icon.gmi_apps).sizeDp(20));
                tvTitle.setText(R.string.app);
                currentFragment = new AppFragment(this);
                switchFragment(currentFragment);
                break;
            case R.id.tv_more:
                resideLayout.closePane();
                ivIcon.setImageDrawable(new IconicsDrawable(this).color(Color.WHITE).icon(MaterialDesignIconic.Icon.gmi_more).sizeDp(20));
                tvTitle.setText(R.string.more);
                currentFragment = new RecommendFragment(this);
                switchFragment(currentFragment);

                break;
            case R.id.tv_about:
                new MaterialDialog.Builder(this)
                        .title(R.string.about)
                        .icon(new IconicsDrawable(this)
                                .color(ThemeUtils.getThemeColor(this, R.attr.colorPrimary))
                                .icon(MaterialDesignIconic.Icon.gmi_account)
                                .sizeDp(20))
                        .content(R.string.about_me)
                        .positiveText(R.string.close)
                        .show();
                break;
            case R.id.tv_theme:
                new ColorChooserDialog.Builder(this, R.string.theme)
                        .customColors(R.array.colors, null)
                        .doneButton(R.string.done)
                        .cancelButton(R.string.cancel)
                        .allowUserColorInput(false)
                        .allowUserColorInputAlpha(false)
                        .show();
                break;
            case R.id.rl_title:
                resideLayout.openPane();
                break;
        }
    }

    @Override
    public void onColorSelection(@NonNull ColorChooserDialog dialog, @ColorInt int selectedColor) {
        if (selectedColor == ThemeUtils.getThemeColor(this, R.attr.colorPrimary))
            return;
        EventBus.getDefault().post(new SkinChangeEvent());

        if (selectedColor == getResources().getColor(R.color.colorBluePrimary)) {
            setTheme(R.style.BlueTheme);
            SharedPreferencesUtils.setCurrentTheme(this, Theme.Blue);
            SharedPreferencesUtils.putString(mContext, "themeColor", "#2196f3");
            currentFragment.setTheme(Color.parseColor("#2196f3"));

        } else if (selectedColor == getResources().getColor(R.color.colorRedPrimary)) {
            setTheme(R.style.RedTheme);
            SharedPreferencesUtils.setCurrentTheme(this, Theme.Red);
            SharedPreferencesUtils.putString(mContext, "themeColor", "#f44336");
            currentFragment.setTheme(Color.parseColor("#f44336"));

        } else if (selectedColor == getResources().getColor(R.color.colorBrownPrimary)) {
            setTheme(R.style.BrownTheme);
            SharedPreferencesUtils.setCurrentTheme(this, Theme.Brown);
            SharedPreferencesUtils.putString(mContext, "themeColor", "#795548");
            currentFragment.setTheme(Color.parseColor("#795548"));

        } else if (selectedColor == getResources().getColor(R.color.colorGreenPrimary)) {
            setTheme(R.style.GreenTheme);
            SharedPreferencesUtils.setCurrentTheme(this, Theme.Green);
            SharedPreferencesUtils.putString(mContext, "themeColor", "#4caf50");
            currentFragment.setTheme(Color.parseColor("#4caf50"));


        } else if (selectedColor == getResources().getColor(R.color.colorPurplePrimary)) {
            setTheme(R.style.PurpleTheme);
            SharedPreferencesUtils.setCurrentTheme(this, Theme.Purple);
            SharedPreferencesUtils.putString(mContext, "themeColor", "#9c27b0");
            currentFragment.setTheme(Color.parseColor("#9c27b0"));

        } else if (selectedColor == getResources().getColor(R.color.colorTealPrimary)) {
            setTheme(R.style.TealTheme);
            SharedPreferencesUtils.setCurrentTheme(this, Theme.Teal);
            SharedPreferencesUtils.putString(mContext, "themeColor", "#009688");
            currentFragment.setTheme(Color.parseColor("#009688"));

        } else if (selectedColor == getResources().getColor(R.color.colorPinkPrimary)) {
            setTheme(R.style.PinkTheme);
            SharedPreferencesUtils.setCurrentTheme(this, Theme.Pink);
            SharedPreferencesUtils.putString(mContext, "themeColor", "#e91e63");
            currentFragment.setTheme(Color.parseColor("#e91e63"));

        } else if (selectedColor == getResources().getColor(R.color.colorDeepPurplePrimary)) {
            setTheme(R.style.DeepPurpleTheme);
            SharedPreferencesUtils.setCurrentTheme(this, Theme.DeepPurple);
            SharedPreferencesUtils.putString(mContext, "themeColor", "#673ab7");
            currentFragment.setTheme(Color.parseColor("#673ab7"));

        } else if (selectedColor == getResources().getColor(colorOrangePrimary)) {
            setTheme(R.style.OrangeTheme);
            SharedPreferencesUtils.setCurrentTheme(this, Theme.Orange);
            SharedPreferencesUtils.putString(mContext, "themeColor", "#ff9800");
            currentFragment.setTheme(Color.parseColor("#ff9800"));

        } else if (selectedColor == getResources().getColor(colorIndigoPrimary)) {
            setTheme(R.style.IndigoTheme);
            SharedPreferencesUtils.setCurrentTheme(this, Theme.Indigo);
            SharedPreferencesUtils.putString(mContext, "themeColor", "#3f51b5");
            currentFragment.setTheme(Color.parseColor("#3f51b5"));

        } else if (selectedColor == getResources().getColor(R.color.colorLightGreenPrimary)) {
            setTheme(R.style.LightGreenTheme);
            SharedPreferencesUtils.setCurrentTheme(this, Theme.LightGreen);
            SharedPreferencesUtils.putString(mContext, "themeColor", "#8bc34a");
            currentFragment.setTheme(Color.parseColor("#8bc34a"));

        } else if (selectedColor == getResources().getColor(R.color.colorDeepOrangePrimary)) {
            setTheme(R.style.DeepOrangeTheme);
            SharedPreferencesUtils.setCurrentTheme(this, Theme.DeepOrange);
            SharedPreferencesUtils.putString(mContext, "themeColor", "#ff5722");
            currentFragment.setTheme(Color.parseColor("#ff5722"));

        } else if (selectedColor == getResources().getColor(R.color.colorLimePrimary)) {
            setTheme(R.style.LimeTheme);
            SharedPreferencesUtils.setCurrentTheme(this, Theme.Lime);
            SharedPreferencesUtils.putString(mContext, "themeColor", "#cddc39");
            currentFragment.setTheme(Color.parseColor("#cddc39"));

        } else if (selectedColor == getResources().getColor(R.color.colorBlueGreyPrimary)) {
            setTheme(R.style.BlueGreyTheme);
            SharedPreferencesUtils.setCurrentTheme(this, Theme.BlueGrey);
            SharedPreferencesUtils.putString(mContext, "themeColor", "#607d8b");
            currentFragment.setTheme(Color.parseColor("#607d8b"));

        } else if (selectedColor == getResources().getColor(R.color.colorCyanPrimary)) {
            setTheme(R.style.CyanTheme);
            SharedPreferencesUtils.setCurrentTheme(this, Theme.Cyan);
            SharedPreferencesUtils.putString(mContext, "themeColor", "#00bcd4");
            currentFragment.setTheme(Color.parseColor("#00bcd4"));
        }


        final View rootView = getWindow().getDecorView();
        rootView.setDrawingCacheEnabled(true);
        rootView.buildDrawingCache(true);

        final Bitmap localBitmap = Bitmap.createBitmap(rootView.getDrawingCache());
        rootView.setDrawingCacheEnabled(false);
        if (null != localBitmap && rootView instanceof ViewGroup) {
            final View tmpView = new View(getApplicationContext());
            tmpView.setBackgroundDrawable(new BitmapDrawable(getResources(), localBitmap));
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            ((ViewGroup) rootView).addView(tmpView, params);
            tmpView.animate().alpha(0).setDuration(400).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    ColorUiUtil.changeTheme(rootView, getTheme());
                    System.gc();
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    ((ViewGroup) rootView).removeView(tmpView);
                    localBitmap.recycle();
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            }).start();


        }


    }

    @Override
    public void onBackPressed() {
        if (resideLayout.isOpen()) {
            resideLayout.closePane();

        } else {
            new AlertDialog.Builder(mContext)
                    .setTitle("提示")
                    .setMessage("您确定要退出吗？")
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();

                        }
                    })
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            finish();

                        }
                    })
                    .create().show();


        }
    }


    @Override
    public void showFail(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void DismissLoading() {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public void showPic(HttpResponse data) {
        Picasso.with(MainActivity.this)
                .load(data.getResults().get(0).getUrl())
                .placeholder(new IconicsDrawable(MainActivity.this)
                        .icon(FoundationIcons.Icon.fou_photo)
                        .color(Color.GRAY)
                        .backgroundColor(Color.WHITE)
                        .roundedCornersDp(40)
                        .paddingDp(15)
                        .sizeDp(75))
                .transform(new CropCircleTransformation())
                .into(ivHeader);

    }

    @Override
    public void showWords(HttpResponse data) {
        tvDesc.setText(data.getResults().get(0).getDesc());
    }
}
