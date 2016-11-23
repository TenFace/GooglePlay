package com.example.administrator.google.ui.fragment;


import android.support.v4.app.Fragment;

/**
 * Created by Administrator on 2016/7/23 0023.
 */
public class FragmentFactory  {
    /**
     * 根据不同的position生产对应的Fragment对象
     * @param
     * @return
     */
    public static Fragment create(int position){
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new AppFragment();
                break;
            case 2:
                fragment = new GameFragment();
                break;
            case 3:
                fragment = new SubjectFragment();
                break;
            case 4:
                fragment = new RecommendFragment();
                break;
            case 5:
                fragment = new CategoryFragment();
                break;
            case 6:
                fragment = new HotFragment();
                break;
        }
        return  fragment;
    }
}
