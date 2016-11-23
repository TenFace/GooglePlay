package com.example.administrator.google.util;

import android.widget.Toast;

import com.example.administrator.google.global.GooglePlayApplication;

/**
 * 单例的toast
 * Created by Administrator on 2016/7/23 0023.
 */
public class ToastUtiles {
    private static Toast toast;

    public static void showToast(String text) {
        if (toast == null) {
            toast = Toast.makeText(GooglePlayApplication.context, text, Toast.LENGTH_SHORT);
        } else {
            toast.setText(text);
        }
        toast.show();
    }
}
