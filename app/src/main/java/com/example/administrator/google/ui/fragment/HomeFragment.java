package com.example.administrator.google.ui.fragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.google.R;
import com.example.administrator.google.adapter.BasicAdapter;
import com.example.administrator.google.adapter.HomeAdapter;
import com.example.administrator.google.adapter.HomeHeaderAdapter;
import com.example.administrator.google.bean.AppInfo;
import com.example.administrator.google.bean.Home;
import com.example.administrator.google.http.Api;
import com.example.administrator.google.http.HttpUtil;
import com.example.administrator.google.ui.activity.AppDetailActivity;
import com.example.administrator.google.util.CommonUtil;
import com.example.administrator.google.util.ImageDefaUtil;
import com.example.administrator.google.util.JsonUtil;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

/**
 * Created by Administrator on 2016/7/23 0023.
 */
public class HomeFragment extends BaseListFragment<AppInfo> {
    // ArrayList<AppInfo> infosList = new ArrayList<>();
    private ViewPager viewPager;
    private boolean mIsShowPoint = true;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            handler.sendEmptyMessageDelayed(0, 2500);
        }
    };
    private String[] descs = {
            "十亿红包天天抢", "你想看的这里都有", "导师大揭秘", "因为热爱", "一分钱领五元红包", "爱奇艺视频", "赢钱才是正经事", "世界杯天天竞猜"
    };
    private LinearLayout ll_dots;
    private TextView tv_desc;
    private Home home;
    private HomeHeaderAdapter homeHeaderAdapter;

    //    @Override
//    protected View getSuccessView() {
//        mPullRefreshListView = (PullToRefreshListView) View.inflate(getActivity(), R.layout.ptr_refresh, null);
//        homeAdapter = new HomeAdapter(infosList);
//        mPullRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);//设置两边都可以刷新
//
//        /**
//         * 下拉刷新和上拉加载更多都会执行该方法，
//         * @param refreshView
//         */
//        mPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
//            @Override
//            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
//                loadingPage.loadDataAndRefreshPage();
//            }
//        });
//        listView = mPullRefreshListView.getRefreshableView();
//        listView.setDividerHeight(0);//listview的下划线
//        listView.setSelector(android.R.color.transparent);//去掉点击后的背景色
//
//        addHeader();
//        listView.setAdapter(homeAdapter);

//
//        return mPullRefreshListView;
//    }
//

    @Override
    protected BasicAdapter<AppInfo> getAdapter() {
        return new HomeAdapter(infosList);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), AppDetailActivity.class);
        intent.putExtra("packageName", infosList.get(position - 2).getPackageName());
        startActivity(intent);
    }

    /**
     * 当点击广告条的时候,使广告条停止滚动
     */
    View.OnTouchListener myOntouch = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    handler.removeCallbacksAndMessages(null);
                    break;

                case MotionEvent.ACTION_UP:
                    handler.sendEmptyMessageDelayed(0, 2500);
                    break;
            }
            return false;
        }
    };
    /**
     * 给viewpage添加监听事件,使文字和圆点能够同步
     */
    ViewPager.OnPageChangeListener PageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            changeDescAndDot(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    //当被选择的时候改变文字描述和点的状态
    private void changeDescAndDot(int position) {
        position = position % ll_dots.getChildCount();
        tv_desc.setText(descs[position]);
        for (int i = 0; i < ll_dots.getChildCount(); i++) {
            ll_dots.getChildAt(i).setSelected(i == position);
        }
    }

    protected void addHeader() {
        View headerView = View.inflate(getActivity(), R.layout.layout_home_header, null);
        viewPager = (ViewPager) headerView.findViewById(R.id.hoem_viewPager);
        //根据图片的宽高比，去动态设定viewPager的高度，让它的宽高比和图片能保持一致
        //获取屏幕的宽   做了一下适配来动态的获取图片的高
        //  int width = getActivity().getWindowManager().getDefaultDisplay().getWidth();
        //2.根据图片的宽高比获取对应的高度,(宽高比是2.65)
        // float height = (float) width / 2.65f;
        //3.将高度设置给viewPager
        //  ViewGroup.LayoutParams params = viewPager.getLayoutParams();
        //  params.height = (int) height;
        //  viewPager.setLayoutParams(params);
        ImageDefaUtil.ivUtil(viewPager, 2.65f);  //写了一个来设置图片的方法
        ll_dots = (LinearLayout) headerView.findViewById(R.id.ll_dots);
        tv_desc = (TextView) headerView.findViewById(R.id.tv_desc);
        listView.addHeaderView(headerView);
        viewPager.setOnPageChangeListener(PageChangeListener);
        viewPager.setOnTouchListener(myOntouch);
    }

    /**
     * 初始化圆点
     */
    private void initDots() {
        for (int i = 0; i < home.getPicture().size(); i++) {
            int _dp = ImageDefaUtil.dp2px(6);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(_dp, _dp);
            params.leftMargin = _dp;
            View dote = new View(getActivity());
            dote.setLayoutParams(params);
            dote.setBackgroundResource(R.drawable.selector_dot);
            ll_dots.addView(dote);
        }
    }

    @Override
    protected Object requestData() {
        checkPullFromStart();
        String string = HttpUtil.get(Api.Home + infosList.size());
        if (string != null) {
            home = JsonUtil.parseJsonToBean(string, Home.class);

            if (home != null) {
                infosList.addAll(home.getList());
                CommonUtil.runOnuiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mIsShowPoint) {
                            initDots();
                        }
                        if (home.getPicture() != null && home.getPicture().size() > 0) {
                            if (homeHeaderAdapter == null) {
                                homeHeaderAdapter = new HomeHeaderAdapter(home.getPicture());
                                viewPager.setAdapter(homeHeaderAdapter);
                                viewPager.setCurrentItem(home.getPicture().size() * 100000);
                            }
                        }
                        adapter.notifyDataSetChanged();
                        mPullRefreshListView.onRefreshComplete();
                        mIsShowPoint = false;
                    }
                });

            }
        }
        return home;
    }


    protected void checkPullFromStart() {
        if (mPullRefreshListView.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_START) {
            infosList.clear();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        handler.sendEmptyMessageDelayed(0, 2500);
    }

    @Override
    public void onStop() {
        super.onStop();
        handler.removeMessages(0);
    }


}
