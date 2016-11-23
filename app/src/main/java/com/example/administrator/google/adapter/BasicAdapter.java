package com.example.administrator.google.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.BaseAdapter;

import com.example.administrator.google.holder.BaseHolder;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

import java.util.ArrayList;

/**
 * 1,c初始化convertView 2,给holdView添加标签 3,复用holdView 4绑定数据
 * Created by Administrator on 2016/7/24 0024.
 */
public abstract class BasicAdapter<T> extends BaseAdapter {
    public ArrayList<T> arrayList;

    public BasicAdapter(ArrayList<T> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder<T> holder = null;  //通过抽象方法的返回值把convertView 装换成了viewHolder
        if (convertView == null) {
            holder = getHolder(position);  //需要一个不固定的holder 功能1 和功能2
        } else {
            holder = (BaseHolder<T>) convertView.getTag(); //功能3
        }
        holder.bindData(arrayList.get(position));  //功能4  绑定数据

        //继承BasicAdapter添加动画
        ViewHelper.setScaleX(holder.getHoldView(), 0.5f);
        ViewHelper.setScaleY(holder.getHoldView(), 0.5f);
        ViewPropertyAnimator.animate(holder.getHoldView()).scaleX(1.0f).setDuration(400).setInterpolator(new OvershootInterpolator()).start();
        ViewPropertyAnimator.animate(holder.getHoldView()).scaleY(1.0f).setDuration(400).setInterpolator(new OvershootInterpolator()).start();
        return holder.getHoldView(); //相当于   View view = View.inflate(GooglePlayApplication.context, R.layout.home_item, null);4
        //ruturn view;
    }

    public abstract BaseHolder<T> getHolder(int position);

}
