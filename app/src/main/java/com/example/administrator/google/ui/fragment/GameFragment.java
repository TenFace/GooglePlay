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
public class GameFragment extends BaseListFragment<AppInfo> {

    @Override
    protected BasicAdapter<AppInfo> getAdapter() {
        return new HomeAdapter(infosList);
    }

    @Override
    protected Object requestData() {
        String result = HttpUtil.get(Api.Game + infosList.size());
        ArrayList<AppInfo> appInfor = (ArrayList<AppInfo>) JsonUtil.parseJsonToList(result, new TypeToken<List<AppInfo>>() {
        }.getType());
        if (appInfor != null) {
            infosList.addAll(appInfor);
            CommonUtil.runOnuiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                    mPullRefreshListView.onRefreshComplete();
                }
            });
        }
        return appInfor;
    }
}
