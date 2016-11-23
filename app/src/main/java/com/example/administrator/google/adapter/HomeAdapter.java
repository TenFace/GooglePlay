package com.example.administrator.google.adapter;

import com.example.administrator.google.bean.AppInfo;
import com.example.administrator.google.holder.BaseHolder;
import com.example.administrator.google.holder.HomeHolder;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/24 0024.
 */
public class HomeAdapter extends BasicAdapter<AppInfo> {
    public HomeAdapter(ArrayList<AppInfo> arrayList) {
        super(arrayList);
    }

    @Override
    public BaseHolder<AppInfo> getHolder(int position) {
        return new HomeHolder();
    }

}
