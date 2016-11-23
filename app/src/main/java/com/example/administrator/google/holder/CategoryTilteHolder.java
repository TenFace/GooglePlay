package com.example.administrator.google.holder;

import android.view.View;
import android.widget.TextView;

import com.example.administrator.google.R;
import com.example.administrator.google.global.GooglePlayApplication;

/**
 * Created by Administrator on 2016/7/27 0027.
 */
public class CategoryTilteHolder extends BaseHolder<Object> {

    private TextView tv_title;

    @Override
    public View initHolder() {
        View view = View.inflate(GooglePlayApplication.context, R.layout.adapter_category_title, null);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        return view;
    }

    @Override
    public void bindData(Object date) {
        tv_title.setText((String) date);
    }
}
