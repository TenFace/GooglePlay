package com.example.administrator.google.holder;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.google.R;
import com.example.administrator.google.bean.AppInfo;
import com.example.administrator.google.global.GooglePlayApplication;
import com.example.administrator.google.global.ImageLoaderOptions;
import com.example.administrator.google.http.Api;
import com.example.administrator.google.ui.activity.ImageScaleActivity;
import com.example.administrator.google.util.CommonUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/29 0029.
 */
public class AppScreenHolder extends BaseHolder<AppInfo> {
    private Activity activity;
    private LinearLayout ll_screen;

    @Override
    public View initHolder() {
        View view = View.inflate(GooglePlayApplication.context, R.layout.layout_detail_app_screen, null);
        ll_screen = (LinearLayout) view.findViewById(R.id.ll_screen);
        return view;
    }

    /**
     * 绑定Activity
     */
    public void attachActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void bindData(AppInfo date) {
        int width = CommonUtil.getDimens(R.dimen.dp90);
        int height = CommonUtil.getDimens(R.dimen.dp150);
        int margin = CommonUtil.getDimens(R.dimen.dp8);
        final ArrayList<String> screen = date.getScreen();
        for (int i = 0; i < screen.size(); i++) {
            ImageView imageView = new ImageView(GooglePlayApplication.context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
            params.leftMargin = (i == 0 ? 0 : margin);
            imageView.setLayoutParams(params);
            ImageLoader.getInstance().displayImage(Api.IMAGE_PREFIX + screen.get(i), imageView, ImageLoaderOptions.fadein_options);
            ll_screen.addView(imageView);
            final int temp = i;
            //添加点击事件
            imageView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    //开启缩放大图的界面
//					Intent intent = new Intent(GooglePlayApplication.context,ImageScaleActivity.class);
//					//如果使用不是Activity的Context来开启Activity，那么需要该标记
//					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//					intent.putStringArrayListExtra("urlList", screen);
//					GooglePlayApplication.context.startActivity(intent);

                    //第二种方案，使用Activity来开启
                    Intent intent = new Intent(activity,ImageScaleActivity.class);
                    intent.putStringArrayListExtra("urlList", screen);
                    intent.putExtra("currentItem",temp);
                    activity.startActivity(intent);
                }
            });
        }
    }
}
