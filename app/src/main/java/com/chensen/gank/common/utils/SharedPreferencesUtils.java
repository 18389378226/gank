package com.chensen.gank.common.utils;


import android.content.Context;
import android.content.SharedPreferences;

import com.chensen.gank.theme.Theme;


public class SharedPreferencesUtils {


    private static SharedPreferences getSharedPreferences(final Context context) {
        return android.preference.PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static boolean isFirstTime(Context context, String key) {
        if (getBoolean(context, key, false)) {
            return false;
        } else {
            putBoolean(context, key, true);
            return true;
        }
    }

    public static boolean contains(Context context, String key) {
        return SharedPreferencesUtils.getSharedPreferences(context).contains(key);
    }

    public static int getInt(final Context context, final String key, final int defaultValue) {
        return SharedPreferencesUtils.getSharedPreferences(context).getInt(key, defaultValue);
    }

    public static boolean putInt(final Context context, final String key, final int pValue) {
        final SharedPreferences.Editor editor = SharedPreferencesUtils.getSharedPreferences(context).edit();

        editor.putInt(key, pValue);

        return editor.commit();
    }

    public static long getLong(final Context context, final String key, final long defaultValue) {
        return SharedPreferencesUtils.getSharedPreferences(context).getLong(key, defaultValue);
    }

    public static Long getLong(final Context context, final String key, final Long defaultValue) {
        if (SharedPreferencesUtils.getSharedPreferences(context).contains(key)) {
            return SharedPreferencesUtils.getSharedPreferences(context).getLong(key, 0);
        } else {
            return null;
        }
    }


    public static boolean putLong(final Context context, final String key, final long pValue) {
        final SharedPreferences.Editor editor = SharedPreferencesUtils.getSharedPreferences(context).edit();

        editor.putLong(key, pValue);

        return editor.commit();
    }

    public static boolean getBoolean(final Context context, final String key, final boolean defaultValue) {
        return SharedPreferencesUtils.getSharedPreferences(context).getBoolean(key, defaultValue);
    }

    public static boolean putBoolean(final Context context, final String key, final boolean pValue) {
        final SharedPreferences.Editor editor = SharedPreferencesUtils.getSharedPreferences(context).edit();

        editor.putBoolean(key, pValue);

        return editor.commit();
    }

    public static String getString(final Context context, final String key, final String defaultValue) {
        return SharedPreferencesUtils.getSharedPreferences(context).getString(key, defaultValue);
    }

    public static boolean putString(final Context context, final String key, final String pValue) {
        final SharedPreferences.Editor editor = SharedPreferencesUtils.getSharedPreferences(context).edit();

        editor.putString(key, pValue);

        return editor.commit();
    }


    public static boolean remove(final Context context, final String key) {
        final SharedPreferences.Editor editor = SharedPreferencesUtils.getSharedPreferences(context).edit();

        editor.remove(key);

        return editor.commit();
    }

    public static Theme getCurrentTheme(Context context) {
        return Theme.valueOf(SharedPreferencesUtils.getString(context, "app_theme", Theme.Brown.name()));
    }

    public static void setCurrentTheme(Context context, Theme currentTheme) {
        SharedPreferencesUtils.putString(context, "app_theme", currentTheme.name());
    }
}