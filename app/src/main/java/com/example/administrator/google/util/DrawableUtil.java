package com.example.administrator.google.util;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public class DrawableUtil {
    public static Drawable generateDrawable(int rgb, float radius) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setCornerRadius(radius);
        gradientDrawable.setColor(rgb);
        return gradientDrawable;
    }

    /**
     * 用java代码的方式动态生成状态选择器
     *
     * @param pressed
     * @param normal
     * @return
     */
    public static Drawable generateSelector(Drawable normal, Drawable pressed) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, pressed);
        stateListDrawable.addState(new int[]{}, normal);
        //设置状态选择器过度动画
        if (Build.VERSION.SDK_INT > 10) {
            stateListDrawable.setEnterFadeDuration(500);
            stateListDrawable.setExitFadeDuration(500);
        }
        return stateListDrawable;
    }

}
