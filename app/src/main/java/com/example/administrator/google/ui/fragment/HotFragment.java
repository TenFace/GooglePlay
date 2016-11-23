package com.example.administrator.google.ui.fragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.administrator.google.R;
import com.example.administrator.google.global.GooglePlayApplication;
import com.example.administrator.google.http.Api;
import com.example.administrator.google.http.HttpUtil;
import com.example.administrator.google.ui.widget.FlowLayout;
import com.example.administrator.google.util.ColorUtil;
import com.example.administrator.google.util.CommonUtil;
import com.example.administrator.google.util.DrawableUtil;
import com.example.administrator.google.util.JsonUtil;
import com.example.administrator.google.util.ToastUtiles;
import com.google.gson.reflect.TypeToken;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/23 0023.
 */
public class HotFragment extends BaseFragment {
    private ScrollView scrollView;
    private FlowLayout flowLayout;
    private int vPadding, hPadding;

    @Override
    protected View getSuccessView() {
        scrollView = new ScrollView(getActivity());
        flowLayout = new FlowLayout(GooglePlayApplication.context);
        vPadding = CommonUtil.getDimens(R.dimen.dp6);
        hPadding = CommonUtil.getDimens(R.dimen.dp9);
        //1.设置padding值
        int padding = CommonUtil.getDimens(R.dimen.dp15);
        flowLayout.setPadding(padding, padding, padding, padding);
        flowLayout.setHorizontalSpacing(padding);
        flowLayout.setVerticalSpacing(padding);
        scrollView.addView(flowLayout);
        return scrollView;
    }

    @Override
    protected Object requestData() {
        String result = HttpUtil.get(Api.Hot);
        final ArrayList<String> list = (ArrayList<String>) JsonUtil.parseJsonToList(result, new TypeToken<List<String>>() {
        }.getType());
        if (list != null) {
            CommonUtil.runOnuiThread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < list.size(); i++) {
                        final TextView textView = new TextView(getActivity());
                        textView.setTextSize(16);
                        textView.setGravity(Gravity.CENTER);
                        textView.setText(list.get(i));
                        textView.setTextColor(Color.WHITE);
                        // Drawable drawable = DrawableUtil.generateDrawable(ColorUtil.getRandomColor(), hPadding);
                        Drawable pressed = DrawableUtil.generateDrawable(ColorUtil.getRandomColor(), hPadding);
                        Drawable normal = DrawableUtil.generateDrawable(ColorUtil.getRandomColor(), hPadding);
                        Drawable drawable = DrawableUtil.generateSelector(pressed, normal);
                        textView.setBackgroundDrawable(drawable);
                        textView.setPadding(hPadding, vPadding, hPadding, vPadding);
                        flowLayout.addView(textView);
                        textView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                AnimatorSet set = new AnimatorSet();
                                set.playTogether(
                                        //ObjectAnimator.ofFloat(textView, "rotationX", 0, 360),
                                        ObjectAnimator.ofFloat(textView, "rotationY", 0, 30, 60, 90, 120, 150, 180, 210, 240, 280, 310, 360),
                                        // ObjectAnimator.ofFloat(textView, "rotation", 0, -90),
                                        // ObjectAnimator.ofFloat(textView, "translationX", 0, 90),
                                        //ObjectAnimator.ofFloat(textView, "translationY", 0, 90),
                                        ObjectAnimator.ofFloat(textView, "scaleX", 0.2f, 0.3f, 0.4f, 0.5f, 1f, 1.4f, 1.2f, 1f),
                                        ObjectAnimator.ofFloat(textView, "scaleY", 0.2f, 0.3f, 0.4f, 0.5f, 1f, 1.4f, 1.2f, 1f),
                                        ObjectAnimator.ofFloat(textView, "alpha", 1, 0.25f, 1)
                                );
                                set.setDuration(1 * 1000).start();
                                ToastUtiles.showToast(textView.getText().toString());
                            }
                        });
                    }
                }
            });
        }
        return list;
    }
}
