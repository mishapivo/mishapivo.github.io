package com.audacityit.audacity.util;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;

/**
 * Created by Tushar Saha on 11/23/15.
 * Audacity IT Solutions Ltd.
 */
public class ColorUtil {

    // design color set as default color. need to be changed to a unified color
    private static final String SPLASH_SCREEN_COLOR = "#13153E";
    private static final String NAV_TEXT_COLOR_NORMAL = "#99A4B9";
    private static final String NAV_TEXT_COLOR_CHECKED = "#0094D7";
    private static final String NAV_ICON_COLOR_NORMAL = "#99A4B9";
    private static final String NAV_ICON_COLOR_CHECKED = "#0094D7";

    public static final int getSplashScreenColor(Context context) {
        return Color.parseColor(SPLASH_SCREEN_COLOR);
    }

    private static ColorStateList createColorStateList(int checked, int normal) {
        int[] colors = new int[]{checked, normal};
        int[][] states = new int[2][];
        states[0] = new int[]{android.R.attr.state_checked, android.R.attr.state_enabled};
        states[1] = new int[]{};
        return new ColorStateList(states, colors);
    }


    private static ColorStateList createColorStateList(String b, int pressed, int normal) {
        int[] colors = new int[]{pressed, normal};
        int[][] states = new int[2][];
        states[0] = new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled};
        states[1] = new int[]{};
        return new ColorStateList(states, colors);
    }


    public static ColorStateList getNavTextColorState() {
        return createColorStateList(Color.parseColor(NAV_TEXT_COLOR_CHECKED),
                Color.parseColor(NAV_TEXT_COLOR_NORMAL));
    }

    public static ColorStateList getNavIconColorState() {
        return createColorStateList(Color.parseColor(NAV_ICON_COLOR_CHECKED),
                Color.parseColor(NAV_ICON_COLOR_NORMAL));
    }

    public static ColorStateList getSocialButtonColorState(String color) {
        return createColorStateList("", Color.parseColor(addAlpha(color, 0.75)),
                Color.parseColor(color));
    }

    /**
     * @param originalColor color, without alpha
     * @param alpha         from 0.0 to 1.0
     * @return
     */
    public static String addAlpha(String originalColor, double alpha) {
        long alphaFixed = Math.round(alpha * 255);
        String alphaHex = Long.toHexString(alphaFixed);
        if (alphaHex.length() == 1) {
            alphaHex = "0" + alphaHex;
        }
        originalColor = originalColor.replace("#", "#" + alphaHex);


        return originalColor;
    }
}
