package com.audacityit.audacity.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

//import com.audacityit.audacity.model.User;

//import static com.audacityit.audacity.util.Constant.JF_DOB;
//import static com.audacityit.audacity.util.Constant.JF_EMAIL;

/**
 * Created by tusharaits on 9/29/15.
 */
public class PreferenceUtil {

    /**
     * @param context the application context
     * @param key     variable in which the value will be stored to be retrieved later
     * @param value   the value to store
     * @brief save int value with shared preference
     */
    public static void savePreference(Context context, String key, int value) {
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * @param context the application context
     * @param key     variable from which the value will be retrieved
     * @return int
     * @brief retrieve int value from specific key
     */
    public static int getPreferenceInt(Context context, String key) {
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getInt(key, 0);
    }

    /**
     * @param context the application context
     * @param key     variable in which the value will be stored to be retrieved later
     * @param value   the value to store
     * @brief save String value with shared preference
     */
    public static void savePreference(Context context, String key, String value) {
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * @param context the application context
     * @param key     variable from which the value will be retrieved
     * @return Sting
     * @brief retrieve String value from specific key
     */
    public static String getPreferenceString(Context context, String key) {
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(key, "");
    }

//    public static boolean isUserExist(Context context) {
//        return !TextUtils.isEmpty(getPreferenceString(context, Constant.JF_ID)) &&
//                !TextUtils.isEmpty(getPreferenceString(context, Constant.JF_EMAIL));
//    }

//    public static String getUserType(Context context) {
//        return getPreferenceString(context, Constant.JF_TYPE);
//    }

//    public static String getUserName(Context context) {
//        return getPreferenceString(context, Constant.JF_NAME);
//    }

    public static void deleteUser(Context context) {
//        PreferenceUtil.savePreference(context, JF_ID, "");
//        PreferenceUtil.savePreference(context, JF_EMAIL, "");
//        PreferenceUtil.savePreference(context, JF_MOBILE, "");
//        PreferenceUtil.savePreference(context, JF_TYPE, "");
//        PreferenceUtil.savePreference(context, JF_NAME, "");
//        PreferenceUtil.savePreference(context, JF_DOB, "");
//        PreferenceUtil.savePreference(context, JF_GENDER, "");
//        PreferenceUtil.savePreference(context, JF_LOCATION, "");
    }

//    public static User getUser(Context context) {
//        return new User(getPreferenceString(context, Constant.JF_ID),
//                getPreferenceString(context, Constant.JF_EMAIL),
//                getPreferenceString(context, Constant.JF_MOBILE),
//                getPreferenceString(context, Constant.JF_TYPE),
//                getPreferenceString(context, Constant.JF_NAME),
//                getPreferenceString(context, Constant.JF_DOB),
//                getPreferenceString(context, Constant.JF_GENDER),
//                getPreferenceString(context, Constant.JF_LOCATION));
//    }

}
