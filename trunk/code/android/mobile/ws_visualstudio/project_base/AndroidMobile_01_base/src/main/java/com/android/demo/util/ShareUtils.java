package com.android.demo.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by Kevin.young  on 2015/9/11.
 * Lately modify by Kevin.young  on 2015/9/11.
 * Copyright @ 1996-2015 Kevin Corporation, All Rights Reserved
 * <p/>
 */
public class ShareUtils {
    Context context;
    private static ShareUtils instance;

    protected ShareUtils(Context context) {
        this.context = context;
        init();
    }

    public static ShareUtils instance(Context context) {
        if (null == instance) {
            synchronized (ShareUtils.class) {
                if (null == instance) {
                    instance = new ShareUtils(context);
                }
            }
        }
        return instance;
    }

    public static ShareUtils getInstance() {
        return instance;
    }

    public static final String PREFS_FILE_NAME1 = "PREFS_FILE_NAME1";
    public static final String PREFS_FILE_LOGIN = "PREFS_FILE_LOGIN";

    private void init() {

    }

    public static void setFloat(Context context, String fileName, String key, float value) {
        Editor editor = context.getSharedPreferences(fileName, Activity.MODE_PRIVATE).edit();
        editor.putFloat(key, value).commit();
    }

    public static float getFloat(Context context, String fileName, String key) {
        return context.getSharedPreferences(fileName, Activity.MODE_PRIVATE).getFloat(key, -1f);
    }

    public static void setString(Context context, String fileName, String key, String value) {
        Editor editor = context.getSharedPreferences(fileName, Activity.MODE_PRIVATE).edit();
        editor.putString(key, value).commit();
    }

    public static String getString(Context context, String fileName, String key) {
        return context.getSharedPreferences(fileName, Activity.MODE_PRIVATE).getString(key, "");
    }

    public static void setBoolean(Context context, String fileName, String key, Boolean value) {
        Editor editor = context.getSharedPreferences(fileName, Activity.MODE_PRIVATE).edit();
        editor.putBoolean(key, value).commit();
    }

    public static boolean getBoolean(Context context, String fileName, String key) {
        return context.getSharedPreferences(fileName, Activity.MODE_PRIVATE).getBoolean(key, false);
    }

    public static boolean getBoolean(Context context, String fileName, String key, boolean defaultValue) {
        return context.getSharedPreferences(fileName, Activity.MODE_PRIVATE).getBoolean(key, defaultValue);
    }

    public static void setInt(Context context, String fileName, String key, int value) {
        Editor editor = context.getSharedPreferences(fileName, Activity.MODE_PRIVATE).edit();
        editor.putInt(key, value).commit();
    }

    public static int getInt(Context context, String fileName, String key) {
        return context.getSharedPreferences(fileName, Activity.MODE_PRIVATE).getInt(key, -1);
    }

    public static int getInt(Context context, String fileName, String key, int defaultV) {
        return context.getSharedPreferences(fileName, Activity.MODE_PRIVATE).getInt(key, defaultV);
    }

    public static void removeProperty(Context context, String fileName, String key) {
        SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        Editor editor = preferences.edit();
        editor.remove(key);
        editor.commit();
    }

    public void setFloat(String fileName, String key, float value) {
        setFloat(context, fileName, key, value);
    }

    public float getFloat(String fileName, String key) {
        return getFloat(context, fileName, key);
    }

    public void setString(String fileName, String key, String value) {
        setString(context, fileName, key, value);
    }

    public String getString(String fileName, String key) {
        return getString(context, fileName, key);
    }

    public void setBoolean(String fileName, String key, Boolean value) {
        setBoolean(fileName, key, value);
    }

    public boolean getBoolean(String fileName, String key) {
        return getBoolean(context, fileName, key);
    }

    public boolean getBoolean(String fileName, String key, boolean defaultValue) {
        return getBoolean(context, fileName, key, defaultValue);
    }

    public void setInt(String fileName, String key, int value) {
        setInt(context, fileName, key, value);
    }

    public int getInt(String fileName, String key) {
        return getInt(context, fileName, key);
    }

    public int getInt(String fileName, String key, int defV) {
        return getInt(context, fileName, key, defV);
    }

    public void removeProperty(String fileName, String key) {
        removeProperty(context, fileName, key);
    }
}
