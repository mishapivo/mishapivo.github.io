package com.audacityit.audacity.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by sourav_aits on 5/26/16.
 */
public class PreferenceUtils {

    public final static String PREFERENCE_NAME = "company_profile";
    public final static String KEY_FIRST_LAUNCH = "first_launch";
    public final static String KEY_MODE_STATUS = "hiw_mode_status";
    public final static String KEY_IMAGE_LOAD = "hiw_image_load";
    public final static String KEY_NOTIFY_USER = "hiw_notify_user";
    public final static String KEY_FONT_SIZE = "hiw_font_size";

    public static PreferenceUtils preferenceUtils;

    public SharedPreferences mPreferences;
    public SharedPreferences.Editor mEditor;

    public static PreferenceUtils getInstance() {

        if(preferenceUtils == null) {

            preferenceUtils = new PreferenceUtils();
        }
        return preferenceUtils;
    }

    public PreferenceUtils() {

    }

    public void initPreferences (Context context) {

        mPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
    }

    public void editStringValue (String key, String value) {

        mEditor.putString(key, value);
        mEditor.commit();
    }

    public String getStringValue (String key, String defaultValue) {

        return mPreferences.getString(key, defaultValue);
    }

    public void editIntegerValue(String key, int value) {

        mEditor.putInt(key, value);
        mEditor.commit();
    }

    public int getIntegerValue (String key, int defaultValue) {

        return mPreferences.getInt(key, defaultValue);
    }

    public void editFloatValue(String key, float value) {

        mEditor.putFloat(key, value);
        mEditor.commit();
    }

    public float getFloatValue (String key, float defaultValue) {

        return mPreferences.getFloat(key, defaultValue);
    }

    public void editBoolenValue(String key, boolean value) {

        mEditor.putBoolean(key, value);
        mEditor.commit();
    }

    public boolean getBooleanValue (String key, boolean defaultValue) {

        return mPreferences.getBoolean(key, defaultValue);
    }
}
