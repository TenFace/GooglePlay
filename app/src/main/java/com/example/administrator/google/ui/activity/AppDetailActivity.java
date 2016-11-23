package com.example.administrator.google.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.administrator.google.R;
import com.example.administrator.google.bean.AppInfo;
import com.example.administrator.google.holder.AppDesHolder;
import com.example.administrator.google.holder.AppInfoHolder;
import com.example.administrator.google.holder.AppSafeHolder;
import com.example.administrator.google.holder.AppScreenHolder;
import com.example.administrator.google.http.Api;
import com.example.administrator.google.http.HttpUtil;
import com.example.administrator.google.ui.fragment.LoadingPage;
import com.example.administrator.google.util.CommonUtil;
import com.example.administrator.google.util.JsonUtil;

public class AppDetailActivity extends ActionBarActivity {

    private String packageName;
    private LinearLayout holder_container;
    private ScrollView scrollview;
    private AppInfoHolder appinfoHolder;
    private AppInfo appInfo;
    private AppSafeHolder appSafeHolder;
    private AppScreenHolder appScreenHolder;
    private AppDesHolder appDesHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        packageName = getIntent().getStringExtra("packageName");
        setActionBar();
        LoadingPage loadpage = new LoadingPage(this) {


            @Override
            public View createSuccessView() {
                View view = View.inflate(AppDetailActivity.this, R.layout.activity_app_detail, null);
                holder_container = (LinearLayout) view.findViewById(R.id.holder_container);
                scrollview = (ScrollView) view.findViewById(R.id.scrollview);
                appinfoHolder = new AppInfoHolder();
                holder_container.addView(appinfoHolder.getHoldView());
                appSafeHolder = new AppSafeHolder();
                holder_container.addView(appSafeHolder.getHoldView());
                //3.初始化app screen模块
                appScreenHolder = new AppScreenHolder();
                appScreenHolder.attachActivity(AppDetailActivity.this);
                holder_container.addView(appScreenHolder.getHoldView());
                //4.初始化app des模块
                appDesHolder = new AppDesHolder();
                holder_container.addView(appDesHolder.getHoldView());
                appDesHolder.setScrollView(scrollview);
                return view;
            }

            @Override
            protected Object loadData() {
                return getData();
            }
        };
        setContentView(loadpage);
    }

    private Object getData() {
        String url = String.format(Api.Detail, packageName);
        String json = HttpUtil.get(url);
        appInfo = JsonUtil.parseJsonToBean(json, AppInfo.class);
        if (appInfo != null) {
            CommonUtil.runOnuiThread(new Runnable() {
                @Override
                public void run() {
                    updateUi();
                }
            });
        }
        return appInfo;
    }

    private void updateUi() {
        //1.绑定app info模块的数据
        appinfoHolder.bindData(appInfo);
        //1.绑定app safe模块的数据
        appSafeHolder.bindData(appInfo);
        appScreenHolder.bindData(appInfo);
        appDesHolder.bindData(appInfo);
    }

    private void setActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getString(R.string.app_detail));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
