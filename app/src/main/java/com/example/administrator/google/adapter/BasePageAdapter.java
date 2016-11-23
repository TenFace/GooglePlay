package com.example.administrator.google.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/25 0025.
 */
public class BasePageAdapter<T> extends PagerAdapter {
    ArrayList<T> list;

    public BasePageAdapter(ArrayList<T> list) {

        this.list = list;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
        container.removeView((View) object);
    }

}
