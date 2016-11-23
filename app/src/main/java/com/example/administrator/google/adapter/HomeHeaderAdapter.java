package com.example.administrator.google.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.google.global.GooglePlayApplication;
import com.example.administrator.google.global.ImageLoaderOptions;
import com.example.administrator.google.http.Api;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/25 0025.
 */
public class HomeHeaderAdapter extends BasePageAdapter {
    public HomeHeaderAdapter(ArrayList<String> list) {
        super(list);
    }
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(GooglePlayApplication.context);
        ImageLoader.getInstance().displayImage(Api.IMAGE_PREFIX + list.get(position&list.size()-1), imageView, ImageLoaderOptions.fadein_options);
        container.addView(imageView);
        return imageView;
    }

}
