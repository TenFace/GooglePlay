package com.example.administrator.google.holder;

import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.administrator.google.R;
import com.example.administrator.google.bean.AppInfo;
import com.example.administrator.google.global.GooglePlayApplication;
import com.example.administrator.google.global.ImageLoaderOptions;
import com.example.administrator.google.http.Api;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * /**
 * 初始化Holder和布局的封装到viewHODER
 * y又把每个fragment的共性抽取到BaseHolder中
 */
public class HomeHolder extends BaseHolder<AppInfo> {
    //    @InjectView(R.id.iv_icon)
//    ImageView ivIcon;
//    @InjectView(R.id.tv_name)
//    TextView tvName;
//    @InjectView(R.id.rb_star)
//    RatingBar rbStar;
//    @InjectView(R.id.tv_size)
//    TextView tvSize;
//    @InjectView(R.id.tv_des)
//    TextView tvDes;
    ImageView iv_icon;
    RatingBar rb_star;
    TextView tv_name, tv_size, tv_des;


    @Override
    public View initHolder() {
        View view = View.inflate(GooglePlayApplication.context, R.layout.home_item, null);
        iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
        rb_star = (RatingBar) view.findViewById(R.id.rb_star);
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        tv_size = (TextView) view.findViewById(R.id.tv_size);
        tv_des = (TextView) view.findViewById(R.id.tv_des);

//        ButterKnife.inject(view);
        return view;
    }

    @Override
    public void bindData(AppInfo appInfo) {
//        tvName.setText(appinfo.getName());
//        rbStar.setRating(appinfo.getStars());
//        tvDes.setText(appinfo.getDes());
//        tvSize.setText(Formatter.formatFileSize(GooglePlayApplication.context, appinfo.getSize()));
        tv_name.setText(appInfo.getName());
        rb_star.setRating(appInfo.getStars());
        tv_size.setText(Formatter.formatFileSize(GooglePlayApplication.context, appInfo.getSize()));
        tv_des.setText(appInfo.getDes());
        ImageLoader.getInstance().displayImage(Api.IMAGE_PREFIX+appInfo.getIconUrl(), iv_icon,ImageLoaderOptions.round_options);
    }

}
