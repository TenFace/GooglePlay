package com.example.administrator.google.holder;

import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.google.R;
import com.example.administrator.google.anim.PaddingTopAnim;
import com.example.administrator.google.bean.AppInfo;
import com.example.administrator.google.bean.SafeInfo;
import com.example.administrator.google.global.GooglePlayApplication;
import com.example.administrator.google.global.ImageLoaderOptions;
import com.example.administrator.google.http.Api;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import butterknife.InjectView;

/**
 * Created by Administrator on 2016/7/29 0029.
 */
public class AppSafeHolder extends BaseHolder<AppInfo> implements View.OnClickListener {
    @InjectView(R.id.iv_safe_image1)
    ImageView ivSafeImage1;
    @InjectView(R.id.iv_safe_image2)
    ImageView ivSafeImage2;
    @InjectView(R.id.iv_safe_image3)
    ImageView ivSafeImage3;
    @InjectView(R.id.iv_safe_arrow)
    ImageView ivSafeArrow;
    @InjectView(R.id.iv_safe_icon1)
    ImageView ivSafeIcon1;
    @InjectView(R.id.tv_safe_des1)
    TextView tvSafeDes1;
    @InjectView(R.id.iv_safe_icon2)
    ImageView ivSafeIcon2;
    @InjectView(R.id.tv_safe_des2)
    TextView tvSafeDes2;
    @InjectView(R.id.ll_safe2)
    LinearLayout llSafe2;
    @InjectView(R.id.iv_safe_icon3)
    ImageView ivSafeIcon3;
    @InjectView(R.id.tv_safe_des3)
    TextView tvSafeDes3;
    @InjectView(R.id.ll_safe3)
    LinearLayout llSafe3;
    @InjectView(R.id.ll_safe_container)
    LinearLayout llSafeContainer;
    private int minPaddingTop;//缩进去后的paddingTop
    private int maxPaddingTop;//最大的paddingTop
    private boolean isExtend = false;//是否是伸展 ，默认是收缩的
    private boolean isAniming = false;//是否正在执行动画

    @Override
    public View initHolder() {
        View view = View.inflate(GooglePlayApplication.context, R.layout.layout_detail_app_safe, null);
        view.setOnClickListener(this);
        return view;
    }

    @Override
    public void bindData(AppInfo info) {
        ArrayList<SafeInfo> safe = info.getSafe();
        SafeInfo safeInfo1 = safe.get(0);
        tvSafeDes1.setText(safeInfo1.getSafeDes());
        ImageLoader.getInstance().displayImage(Api.IMAGE_PREFIX + safeInfo1.getSafeUrl(), ivSafeImage1, ImageLoaderOptions.round_options);
        ImageLoader.getInstance().displayImage(Api.IMAGE_PREFIX + safeInfo1.getSafeDesUrl(), ivSafeIcon1, ImageLoaderOptions.round_options);
        if (safe.size() > 1) {  //说明有第二个
            SafeInfo safeInfo2 = safe.get(1);
            ImageLoader.getInstance().displayImage(Api.IMAGE_PREFIX + safeInfo2.getSafeUrl(), ivSafeImage2, ImageLoaderOptions.round_options);
            ImageLoader.getInstance().displayImage(Api.IMAGE_PREFIX + safeInfo2.getSafeDesUrl(), ivSafeIcon2, ImageLoaderOptions.round_options);
            tvSafeDes2.setText(safeInfo2.getSafeDes());
        } else {
            llSafe2.setVisibility(View.GONE);
        }
        if (safe.size() > 2) {
            //说明有第3个
            SafeInfo safeInfo3 = safe.get(2);
            ImageLoader.getInstance().displayImage(Api.IMAGE_PREFIX + safeInfo3.getSafeUrl(), ivSafeImage3, ImageLoaderOptions.fadein_options);
            ImageLoader.getInstance().displayImage(Api.IMAGE_PREFIX + safeInfo3.getSafeDesUrl(), ivSafeIcon3, ImageLoaderOptions.fadein_options);
            tvSafeDes3.setText(safeInfo3.getSafeDes());
        } else {
            //没有第3个，则隐藏
            llSafe3.setVisibility(View.GONE);
        }
        maxPaddingTop = llSafeContainer.getPaddingTop();
        llSafeContainer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //一般用完立即移除，因为只要该view的宽高改变都会再引起回调该方法
                llSafeContainer.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                minPaddingTop = llSafeContainer.getMeasuredHeight() * -1;
                llSafeContainer.setPadding(llSafeContainer.getPaddingLeft(), minPaddingTop, llSafeContainer.getPaddingRight(), llSafeContainer.getPaddingBottom());
            }
        });
        ViewHelper.setTranslationX(holdView, -1 * holdView.getMeasuredWidth());
        ViewPropertyAnimator.animate(holdView).translationX(0).setDuration(400).
                setInterpolator(new OvershootInterpolator()).setStartDelay(500).start();
    }

    @Override
    public void onClick(View v) {
        if (v == holdView) {
            if (isAniming) {
                return;
            }
        }
        PaddingTopAnim pd = null;
        if (isExtend) {  //默认是收缩的
            pd = new PaddingTopAnim(llSafeContainer, maxPaddingTop, minPaddingTop);
        } else {
            pd = new PaddingTopAnim(llSafeContainer, minPaddingTop, maxPaddingTop);
        }
        pd.startAniamation(350);
        isExtend = !isExtend;
        ViewPropertyAnimator.animate(ivSafeArrow).setDuration(350).rotationBy(180).
                setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                        isAniming = true;
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        isAniming = false;
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                }).start();
    }

}
