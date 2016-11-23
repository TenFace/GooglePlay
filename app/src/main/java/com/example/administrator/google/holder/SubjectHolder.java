package com.example.administrator.google.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.google.R;
import com.example.administrator.google.bean.Subject;
import com.example.administrator.google.global.GooglePlayApplication;
import com.example.administrator.google.global.ImageLoaderOptions;
import com.example.administrator.google.http.Api;
import com.example.administrator.google.util.ImageDefaUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

public class SubjectHolder extends BaseHolder<Subject> {
    private ImageView iv_image;
    private TextView tv_des;
    private View view;

    @Override
    public View initHolder() {
        view = View.inflate(GooglePlayApplication.context, R.layout.adapter_subject, null);
        iv_image = (ImageView) view.findViewById(R.id.iv_image);
        tv_des = (TextView) view.findViewById(R.id.tv_des);
        return view;
    }

    @Override
    public void bindData(Subject data) {
        tv_des.setText(data.getDes());
        //1.获取屏幕的宽度
        //2.根据图片的宽高比计算对应的高度
        //3.将高度设置给ImageView
        ImageDefaUtil.ivUtil(iv_image, 2.42f, 28);
        ImageLoader.getInstance().displayImage(Api.IMAGE_PREFIX + data.getUrl(), iv_image, ImageLoaderOptions.round_options);
    }

//    private void ivUtil() {
//        WindowManager systemService = (WindowManager) GooglePlayApplication.context.getSystemService(Context.WINDOW_SERVICE);
//        int width = systemService.getDefaultDisplay().getWidth();
//        int dimens = dp2px(24);
//        int tuWidth = width - dimens;
//        float height = tuWidth / 2.6f;
//        ViewGroup.LayoutParams layoutParams = iv_image.getLayoutParams();
//        layoutParams.height = (int) height;
//        iv_image.setLayoutParams(layoutParams);
//    }
//
//    private int dp2px(int dp) {
//        float density = GooglePlayApplication.context.getResources().getDisplayMetrics().density;
//        return (int) (density * dp + 0.5f);
//    }
}

