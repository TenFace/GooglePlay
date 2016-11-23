package com.example.administrator.google.util;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public class ColorUtil {
    public static int getRandomColor() {
        Random random = new Random();
        int red = random.nextInt(180);//0-190
        int green = random.nextInt(190);//0-190
        int blue = random.nextInt(170);//0-190
        return Color.rgb(red, green, blue);
    }

}
