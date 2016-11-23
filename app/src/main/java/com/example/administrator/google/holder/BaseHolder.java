package com.example.administrator.google.holder;

import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/24 0024.
 */
public abstract class BaseHolder<T> {
    public View holdView;

    public BaseHolder() { //初始化holdview
        holdView = initHolder();
        holdView.setTag(this);//为holdview 打上标签
        ButterKnife.inject(this,holdView);
    }

    public abstract View initHolder();

    //提供绑定数据的方法
    public abstract void bindData(T date); //因为每个页面的数据都不一样所所以传一个泛型的参数

    public View getHoldView() {
        return holdView;
    }
}
