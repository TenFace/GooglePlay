package com.example.administrator.google.ui.fragment;

import android.view.View;
import android.widget.AdapterView;

import com.example.administrator.google.adapter.BasicAdapter;
import com.example.administrator.google.adapter.SubjectAdapter;
import com.example.administrator.google.bean.Subject;
import com.example.administrator.google.http.Api;
import com.example.administrator.google.http.HttpUtil;
import com.example.administrator.google.util.CommonUtil;
import com.example.administrator.google.util.JsonUtil;
import com.example.administrator.google.util.LogUtil;
import com.example.administrator.google.util.ToastUtiles;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;


public class SubjectFragment extends BaseListFragment<Subject> {

    @Override
    protected BasicAdapter<Subject> getAdapter() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LogUtil.e("position ",+(position-1)+" "+infosList.get(position-1).getDes());
                ToastUtiles.showToast(infosList.get(position-1).getDes());
            }
        });
        return new SubjectAdapter(infosList);
    }

    @Override
    protected Object requestData() {
        String result = HttpUtil.get(Api.Subject + infosList.size());
         ArrayList<Subject> subjects =  (ArrayList<Subject>) JsonUtil.parseJsonToList(result, new TypeToken<List<Subject>>() {
        }.getType());

        if (subjects != null) {
            infosList.addAll(subjects);//更新数据
            CommonUtil.runOnuiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                    mPullRefreshListView.onRefreshComplete();
                }
            });
        }
        return subjects;
    }
}


