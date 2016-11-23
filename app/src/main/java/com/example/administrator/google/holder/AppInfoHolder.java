package com.example.administrator.google.holder;

import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.administrator.google.R;
import com.example.administrator.google.bean.AppInfo;
import com.example.administrator.google.global.GooglePlayApplication;
import com.example.administrator.google.global.ImageLoaderOptions;
import com.example.administrator.google.http.Api;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.InjectView;

/**
 * Created by Administrator on 2016/7/29 0029.
 */
public class AppInfoHolder extends BaseHolder<AppInfo> {
    @InjectView(R.id.iv_icon)
    ImageView ivIcon;
    @InjectView(R.id.tv_name)
    TextView tvName;
    @InjectView(R.id.rb_star)
    RatingBar rbStar;
    @InjectView(R.id.tv_download_num)
    TextView tvDownloadNum;
    @InjectView(R.id.tv_version)
    TextView tvVersion;
    @InjectView(R.id.tv_date)
    TextView tvDate;
    @InjectView(R.id.tv_size)
    TextView tvSize;

    @Override
    public View initHolder() {
        View view = View.inflate(GooglePlayApplication.context, R.layout.layout_detail_app_info, null);
        return view;
    }

    @Override
    public void bindData(AppInfo appInfo) {
        ImageLoader.getInstance().displayImage(Api.IMAGE_PREFIX + appInfo.getIconUrl(), ivIcon, ImageLoaderOptions.fadein_options);
        tvName.setText(appInfo.getName());
        rbStar.setRating(appInfo.getStars());
        tvDownloadNum.setText("下载：" + appInfo.getDownloadNum());
        tvVersion.setText("版本：" + appInfo.getVersion());
        tvDate.setText("日期：" + appInfo.getDate());
        tvSize.setText("大小:" + Formatter.formatFileSize(GooglePlayApplication.context, appInfo.getSize()));

    }
}
