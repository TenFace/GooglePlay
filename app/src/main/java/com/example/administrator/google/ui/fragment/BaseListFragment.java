package com.example.administrator.google.ui.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.google.R;
import com.example.administrator.google.adapter.BasicAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

/**
 * 由于多个页面都有刷新的功能所以要抽取一个基类
 * Created by Administrator on 2016/7/26 0026.
 */
public abstract class BaseListFragment<T> extends BaseFragment implements AdapterView.OnItemClickListener {
    protected PullToRefreshListView mPullRefreshListView;
    public ListView listView;
    protected BasicAdapter<T> adapter;
    protected ArrayList<T> infosList = new ArrayList<>();

    @Override
    protected View getSuccessView() {
        mPullRefreshListView = (PullToRefreshListView) View.inflate(getActivity(), R.layout.ptr_refresh, null);
        setRefreshMode();
        /**
         * 下拉刷新和上拉加载更多都会执行该方法，
         * @param refreshView
         */
        mPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                loadingPage.loadDataAndRefreshPage();
            }
        });
        listView = mPullRefreshListView.getRefreshableView();
        setListView();
        addHeader();
        adapter = getAdapter();//需要动态获取一个adapter
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);
        return mPullRefreshListView;
    }

    protected void setListView() {

        listView.setDividerHeight(0);//listview的下划线
        listView.setSelector(android.R.color.transparent);//去掉点击后的背景色
    }

    /**
     * 不同的页面刷新的模式同
     */
    protected void setRefreshMode() {
        mPullRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);//设置两边都可以刷新
    }


    protected abstract BasicAdapter<T> getAdapter();

    protected void checkPullFromStart() {
        if (mPullRefreshListView.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_START) {
            infosList.clear();
        }
    }

    protected void addHeader() {
    }

    @Override
    protected Object requestData() {
        return null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
