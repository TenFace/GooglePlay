package com.example.administrator.google.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrator.google.R;
import com.example.administrator.google.ui.fragment.FragmentFactory;
import com.example.administrator.google.util.CommonUtil;

/**6.填充ViewPager和绑定Indicator
 * Created by Administrator on 2016/7/23 0023.
 */
public class MainPagerAdapter extends FragmentPagerAdapter{
    private String[] tabs;
    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
        tabs= CommonUtil.getStringArray(R.array.tab_names);

    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
      Fragment fragment = FragmentFactory.create(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
    /**
     * 一定要重写
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }
}
