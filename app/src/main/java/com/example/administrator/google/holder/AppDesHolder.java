package com.example.administrator.google.holder;

import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.administrator.google.R;
import com.example.administrator.google.anim.HeightAnim;
import com.example.administrator.google.bean.AppInfo;
import com.example.administrator.google.global.GooglePlayApplication;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.nineoldandroids.view.ViewPropertyAnimator;

import butterknife.InjectView;

/**
 * Created by Administrator on 2016/7/30 0030.
 */
public class AppDesHolder extends BaseHolder<AppInfo> implements View.OnClickListener {

    @InjectView(R.id.tv_jiandes)
    TextView tvDes;
    @InjectView(R.id.tv_author)
    TextView tvAuthor;
    @InjectView(R.id.iv_des_arrow)
    ImageView ivDesArrow;
    private ScrollView scrollView;
    private int minHeight;//5行文本的高度
    private int maxHeight;//全部文本的高度
    private boolean isExtend = false;//是否展开了
    private boolean isAniming = false;//是否正在执行动画

    @Override
    public View initHolder() {
        View view = View.inflate(GooglePlayApplication.context, R.layout.layout_detail_app_des, null);
        view.setOnClickListener(this);
        return view;
    }

    public void setScrollView(ScrollView scrollView) {
        this.scrollView = scrollView;
    }


    @Override
    public void bindData(AppInfo date) {
        tvDes.setText(date.getDes());
        tvAuthor.setText(date.getAuthor());
        //1.让描述区域只显示5行的高度
        tvDes.setMaxLines(5);
        tvDes.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                tvDes.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                minHeight = tvDes.getHeight();
                //2.获取描述区域全部的高度,由于改变了tv_des的高度，会引起它重新layout
                tvDes.setMaxLines(Integer.MAX_VALUE);
                tvDes.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        //得到全部文本的高度
                        tvDes.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        maxHeight = tvDes.getHeight();
                        //3.让描述只显示5行的高度,
//						tv_des.setMaxLines(5);//不要使用这种方式
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tvDes.getLayoutParams();
                        params.height = minHeight;
                        tvDes.setLayoutParams(params);
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == holdView) {
            if (isAniming) {
                return;
            }

            HeightAnim anim = null;
            if (isExtend) {
                //执行收缩
                anim = new HeightAnim(tvDes, maxHeight, minHeight);
            } else {
                //执行展开动画
                anim = new HeightAnim(tvDes, minHeight, maxHeight);
            }
            anim.startAniamation(350);

            //设置监听器
            anim.setOnHeightChangeListener(new HeightAnim.OnHeightChangeListener() {
                @Override
                public void onHeightChange(int animatedValue) {
                    //正值其实是向上滑动,
//					scrollView.scrollBy(0,1000);
                    scrollView.scrollBy(0, maxHeight - minHeight);
                }
            });

            //更改标记
            isExtend = !isExtend;

            //让箭头旋转
            ViewPropertyAnimator.animate(ivDesArrow)
                    .rotationBy(180)
                    .setDuration(350)
                    .setListener(new AnimatorListener() {
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
                    })
                    .start();
        }
    }

}
