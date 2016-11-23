package com.example.administrator.google.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.administrator.google.global.GooglePlayApplication;

/**
 * Created by Administrator on 2016/7/27 0027.
 */
public class ImageDefaUtil {
    /**
     * 图片不填充父容器
     * 根据图片的宽高比例获取对应的高
     *
     * @param imageView      imageView对象
     * @param proportion     比例
     * @param diversityValue 如果图片在布局中距离屏幕真实的距离 dp值
     */
    public static void ivUtil(View imageView, float proportion, int diversityValue) {
        if (imageView != null && proportion != 0) {
            int width = getWidth();
            int dimens = dp2px(diversityValue);
            int tuWidth = width - dimens;
            init(imageView, proportion, tuWidth);
        }
    }


    public static void ivUtil(View imageView, float proportion) {
        if (imageView != null && proportion != 0) {
            int width = getWidth();
            init(imageView, proportion, width);
        }
    }

    private static void init(View imageView, float proportion, int TuWidth) {
        float height = TuWidth / proportion;
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.height = (int) height;
        imageView.setLayoutParams(layoutParams);
    }

    private static int getWidth() {
        WindowManager systemService = (WindowManager) GooglePlayApplication.context.getSystemService(Context.WINDOW_SERVICE);
        return systemService.getDefaultDisplay().getWidth();
    }

    public static int dp2px(int dp) {
        float density = GooglePlayApplication.context.getResources().getDisplayMetrics().density;
        return (int) (density * dp + 0.5f);
    }
}
