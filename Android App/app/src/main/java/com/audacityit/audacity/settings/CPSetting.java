package com.audacityit.audacity.settings;

import android.content.Context;

import com.audacityit.audacity.util.DBHelper;
import com.audacityit.audacity.util.PreferenceUtils;


/**
 * Created by sourav_aits on 9/26/16.
 */
public class CPSetting {
    public int FONT_SIZE_SMALL = 10;
    public int FONT_SIZE_MID = 15;
    public int FONT_SIZE_LARGE = 20;

    private static CPSetting CPSetting;
    private boolean nightModeStatus;
    private boolean imageLoad;
    private boolean notifyUser;
    private Integer fontSize;

    public DBHelper getDbHelper() {
        return dbHelper;
    }

    private DBHelper dbHelper;

    public Integer getFontSize() {
        return fontSize;
    }

    public void setFontSize(Integer fontSize) {
        PreferenceUtils.getInstance().editIntegerValue(PreferenceUtils.KEY_FONT_SIZE, fontSize);
        this.fontSize = fontSize;
    }

    public boolean isImageLoad() {
        return imageLoad;
    }

    public void setImageLoad(boolean imageLoad) {
        PreferenceUtils.getInstance().editBoolenValue(PreferenceUtils.KEY_IMAGE_LOAD, imageLoad);
        this.imageLoad = imageLoad;
    }

    public boolean isNotifyUser() {
        return notifyUser;
    }

    public void setNotifyUser(boolean notifyUser) {
        PreferenceUtils.getInstance().editBoolenValue(PreferenceUtils.KEY_NOTIFY_USER, notifyUser);
        this.notifyUser = notifyUser;
    }

    public boolean isNightModeStatus() {
        return nightModeStatus;
    }

    public void setNightModeStatus(boolean nightModeStatus) {
        PreferenceUtils.getInstance().editBoolenValue(PreferenceUtils.KEY_MODE_STATUS, nightModeStatus);
        this.nightModeStatus = nightModeStatus;
    }

    public static CPSetting getInstance() {
        if(CPSetting == null) {
            CPSetting = new CPSetting();
        }
        return CPSetting;
    }

    public void initAppSettings(Context context) {
        //nightModeStatus = PreferenceUtils.getInstance().getBooleanValue(PreferenceUtils.KEY_MODE_STATUS, false);
        //imageLoad = PreferenceUtils.getInstance().getBooleanValue(PreferenceUtils.KEY_IMAGE_LOAD,true);
        //notifyUser = PreferenceUtils.getInstance().getBooleanValue(PreferenceUtils.KEY_NOTIFY_USER,false);
        //fontSize = PreferenceUtils.getInstance().getIntegerValue(PreferenceUtils.KEY_FONT_SIZE,FONT_SIZE_MID);

        dbHelper = new DBHelper();
        dbHelper.setupDb(context);
    }

}
