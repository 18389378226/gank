package com.chensen.gank.theme;

import android.content.res.Resources;
import android.view.View;

/**
 * 换肤接口
 *
 */
public interface ColorUiInterface {


    View getView();

    void setTheme(Resources.Theme themeId);
}
