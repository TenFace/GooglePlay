package com.example.administrator.google.ui.fragment;

import com.example.administrator.google.adapter.BasicAdapter;
import com.example.administrator.google.adapter.HomeAdapter;
import com.example.administrator.google.bean.AppInfo;
import com.example.administrator.google.http.Api;
import com.example.administrator.google.http.HttpUtil;
import com.example.administrator.google.util.CommonUtil;
import com.example.administrator.google.util.JsonUtil;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/23 0023.
 */
public class AppFragment extends BaseListFragment<AppInfo> {

    @Override
    protected BasicAdapter<AppInfo> getAdapter() {

        return new HomeAdapter(infosList);
    }

    @Override
    protected Object requestData() {
        String result = HttpUtil.get(Api.App + infosList.size());
        ArrayList<AppInfo> appInfos = (ArrayList<AppInfo>) JsonUtil.parseJsonToList(result, new TypeToken<List<AppInfo>>() {
        }.getType());
        if (appInfos != null) {
            infosList.addAll(appInfos);//更新数据
            CommonUtil.runOnuiThread(new Runnable() {
                @Override
                public void run() {
                    //更新UI
                    adapter.notifyDataSetChanged();
                    //完成刷新
                    mPullRefreshListView.onRefreshComplete();
                }
            });
        }
        return appInfos;
    }
}
