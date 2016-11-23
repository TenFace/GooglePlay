package com.example.administrator.google.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.google.util.CommonUtil;

/**
 * Created by Administrator on 2016/7/23 0023.
 */
public abstract class BaseFragment extends Fragment {
    protected LoadingPage loadingPage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (loadingPage == null) {
            loadingPage = new LoadingPage(getContext()) {
                @Override
                public View createSuccessView() {
                    return getSuccessView();  //这两个抽象方法的使用真的是太巧妙了!!!!
                }
                @Override
                protected Object loadData() {
                    return requestData();
                }
            };

        }else {
            //需要拿loadingPage的父View（NoSaveStateFramelayout）去移除它自己
            CommonUtil.removeSelfFromParent(loadingPage);
            //但是呢，在Android5.0之后的FragmentManager已经不会在Fragment的view外边包裹一层，这意味着不用移除也不会报错;
        }
        return loadingPage;
    }

    /**
     * 获取每个子类的successView
     *
     * @return
     */
    protected abstract View getSuccessView();

    /**
     * 获取每个子类的数据
     *
     * @return
     */
    protected abstract Object requestData();
}
