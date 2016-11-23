package com.loadingpagetesyt;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * Created by Administrator on 2016/7/23 0023.
 */

/**
 * 负责管理界面加载数据的逻辑
 *
 * @author Administrator
 */
public abstract class LoadingPage extends FrameLayout {

    enum PageState {
        STATE_LOADING,//加载中的状态
        STATE_ERROR,//加载失败的状态
        STATE_SUCCESS;//加载成功的状态
    }
    private PageState mState = PageState.STATE_LOADING;//表示界面当前的state，默认是加载中
    private View loadingView;
    private View errorView;
    private View successView;

    public LoadingPage(Context context) {
        this(context, null);
    }

    public LoadingPage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        EventBus.getDefault().register(this);
        initLoadingPage();
    }

    private void initLoadingPage() {
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        if (loadingView == null) {
            loadingView = View.inflate(getContext(), R.layout.page_loading, null);
        }
        addView(loadingView, params);
        if (errorView == null) {
            errorView = View.inflate(getContext(), R.layout.page_error, null);
            Button btn_reload = (Button) errorView.findViewById(R.id.btn_reload);
            btn_reload.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mState = PageState.STATE_LOADING;
                    showPage();
                    //2.重新加载数据，然后刷新Page
                    //loadDataAndRefreshPage();
                }
            });
        }
        addView(errorView, params);
        if (successView == null) {
            successView = createSuccessView();
        }
        if (successView == null) {
            throw new IllegalArgumentException("The method createSuccessView() can not return null!");
        } else {
            addView(successView, params);
        }
        showPage();
        //2.重新加载数据，然后刷新Page
      //  loadDataAndRefreshPage(EventLoadingPage event);
    }

    /**
     * 请求数据，然后根据回来的数据去刷新Page
     */
    @Subscribe (threadMode = ThreadMode.MainThread,sticky = true)
    public void loadDataAndRefreshPage(EventLoadingPage event){
        if (event.isSuccess){
            mState = PageState.STATE_ERROR;
        }else{
            mState = PageState.STATE_SUCCESS;
        }
        showPage();

    }
    private void showPage() {
        errorView.setVisibility(INVISIBLE);
        successView.setVisibility(INVISIBLE);
        loadingView.setVisibility(INVISIBLE);
        switch (mState) {
            case STATE_LOADING://加载中的状态
                loadingView.setVisibility(View.VISIBLE);
                break;
            case STATE_ERROR://加载失败的状态
                errorView.setVisibility(View.VISIBLE);
                break;
            case STATE_SUCCESS://加载成功的状态
                successView.setVisibility(View.VISIBLE);
                break;
        }
    }
    /**
     * 获取successView，由于每个界面的successView都不一样，那么应该由每个界面自己实现
     * @return
     */
    public abstract View createSuccessView();
    /**
     * 加载数据让子类去实现
     *
     * @return
     */
}
