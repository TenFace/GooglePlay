package com.example.administrator.google.holder;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.google.R;
import com.example.administrator.google.bean.CategoryInfor;
import com.example.administrator.google.global.GooglePlayApplication;
import com.example.administrator.google.global.ImageLoaderOptions;
import com.example.administrator.google.http.Api;
import com.example.administrator.google.util.ToastUtiles;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by Administrator on 2016/7/27 0027.
 */
public class CategoryInfoHolder extends BaseHolder<Object> implements View.OnClickListener {
    private ImageView iv_image1, iv_image2, iv_image3;
    private TextView tv_name1, tv_name2, tv_name3;
    private LinearLayout layout1;
    private LinearLayout layout2;
    private CategoryInfor infor;
    private View layout3;

    @Override
    public View initHolder() {
        View view = View.inflate(GooglePlayApplication.context, R.layout.adapter_category_info, null);
        iv_image1 = (ImageView) view.findViewById(R.id.iv_image1);
        iv_image2 = (ImageView) view.findViewById(R.id.iv_image2);
        iv_image3 = (ImageView) view.findViewById(R.id.iv_image3);
        tv_name1 = (TextView) view.findViewById(R.id.tv_name1);
        tv_name2 = (TextView) view.findViewById(R.id.tv_name2);
        tv_name3 = (TextView) view.findViewById(R.id.tv_name3);
        layout1 = (LinearLayout) view.findViewById(R.id.ll_1);
        layout2 = (LinearLayout) view.findViewById(R.id.ll_2);
        layout3 = (LinearLayout) view.findViewById(R.id.ll_3);
        layout1.setOnClickListener(this);
        layout2.setOnClickListener(this);
        layout3.setOnClickListener(this);
        return view;
    }

    @Override
    public void bindData(Object date) {
        infor = (CategoryInfor) date;
        //显示第一个方格的数据
        tv_name1.setText(infor.getName1());
        ImageLoader.getInstance().displayImage(Api.IMAGE_PREFIX + infor.getUrl1(), iv_image1, ImageLoaderOptions.round_options);
        //由于第2个和第3个可能木有，所以需要判断
        if (!TextUtils.isEmpty(infor.getName2())) {
            ((ViewGroup) tv_name2.getParent()).setVisibility(View.VISIBLE);
            tv_name2.setText(infor.getName2());
            ImageLoader.getInstance().displayImage(Api.IMAGE_PREFIX + infor.getUrl2(), iv_image2, ImageLoaderOptions.round_options);
        } else {
            ((ViewGroup) tv_name2.getParent()).setVisibility(View.INVISIBLE);
        }
        if (!TextUtils.isEmpty(infor.getName3())) {
            ((ViewGroup) tv_name3.getParent()).setVisibility(View.VISIBLE);
            tv_name3.setText(infor.getName3());
            ImageLoader.getInstance().displayImage(Api.IMAGE_PREFIX + infor.getUrl3(), iv_image3, ImageLoaderOptions.round_options);
        } else {
            ((ViewGroup) tv_name3.getParent()).setVisibility(View.INVISIBLE);
        }
        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtiles.showToast(infor.getName1());
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_1:
                ToastUtiles.showToast(infor.getName1());
                break;
            case R.id.ll_2:
                ToastUtiles.showToast(infor.getName2());
                break;
            case R.id.ll_3:
                ToastUtiles.showToast(infor.getName3());
                break;
        }
    }
}
