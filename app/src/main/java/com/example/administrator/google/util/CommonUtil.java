package com.example.administrator.google.util;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.administrator.google.global.GooglePlayApplication;

/**
 * Created by Administrator on 2016/7/23 0023.
 */
public class CommonUtil {
    public static void runOnuiThread(Runnable runnable) {
        GooglePlayApplication.mainHandler.post(runnable);
    }

    public static Drawable getDrawable(int id) {
        return GooglePlayApplication.context.getResources().getDrawable(id);
    }

    public static String getString(int id) {
        return GooglePlayApplication.context.getResources().getString(id);
    }

    public static String[] getStringArray(int id) {
        return GooglePlayApplication.context.getResources().getStringArray(id);
    }

    public static int getColor(int id) {
        return GooglePlayApplication.context.getResources().getColor(id);
    }

    /**
     * 获取dp资源，并且会自动将dp值转为px值
     *
     * @return
     */

    public static int getDimens(int id) {
        return GooglePlayApplication.context.getResources().getDimensionPixelSize(id);
    }

    public static void removeSelfFromParent(View child) {
        if (child != null) {
            ViewParent parent = child.getParent();
            if (parent != null && parent instanceof ViewGroup) {
                ViewGroup group = (ViewGroup) parent;
                group.removeView(child);
            }
        }
    }
}
