package com.example.administrator.google.anim;

import android.view.View;

import com.nineoldandroids.animation.ValueAnimator;

/**
 * Created by Administrator on 2016/7/29 0029.
 */
public abstract class BaseAnim {
    protected ValueAnimator animator;
    protected View target;

    public BaseAnim(View target, int startValue, int endVlaue) {
        this.target = target;
        animator = ValueAnimator.ofInt(startValue, endVlaue);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int animatedValue = (Integer) valueAnimator.getAnimatedValue();
                //执行具体的动画逻辑
                doAnimation(animatedValue);
            }
        });
    }

    /**
     * 开启动画
     *
     * @param duration
     */
    public void startAniamation(int duration) {
        animator.setDuration(duration).start();
    }

    protected abstract void doAnimation(int animatedValue);

}
