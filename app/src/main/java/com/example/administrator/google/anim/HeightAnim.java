package com.example.administrator.google.anim;

import android.view.View;
import android.widget.LinearLayout;

/**
 * 高度变化的动画
 *
 * @author Administrator
 */
public class HeightAnim extends BaseAnim {

    public HeightAnim(View target, int startValue, int endValue) {
        super(target, startValue, endValue);
    }

    @Override
    protected void doAnimation(int animatedValue) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) target.getLayoutParams();
        params.height = animatedValue;
        target.setLayoutParams(params);
        if (listener != null) {
            listener.onHeightChange(animatedValue);
        }
    }

    private OnHeightChangeListener listener;

    public void setOnHeightChangeListener(OnHeightChangeListener listener) {
        this.listener = listener;
    }

    /**
     * 当height改变的监听器
     *
     * @author Administrator
     */
    public interface OnHeightChangeListener {
        void onHeightChange(int animatedValue);
    }

}
