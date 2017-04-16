package com.audacityit.audacity.util;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by zrfaisal on 12/25/15.
 */
public class PermissionUtil {

    public static boolean hasPermission(Activity activity, String permission) {
        return (ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED);
    }

    public static void requestPermission(Activity activity, String[] permission, int requestId) {
        ActivityCompat.requestPermissions(activity, permission, requestId);
    }
}
