package com.example.administrator.google.anim;

import android.view.View;

/**
 * Created by Administrator on 2016/7/29 0029.
 */
public class PaddingTopAnim extends BaseAnim {
    public PaddingTopAnim(View target, int startValue, int endVlaue) {
        super(target, startValue, endVlaue);
    }
    @Override
    protected void doAnimation(int animatedValue) {
        //将动画的值设置为view的当前的paddingTop
        target.setPadding(target.getPaddingLeft(),animatedValue,target.getPaddingRight()
                ,target.getPaddingBottom());
    }
}
