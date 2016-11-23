package com.example.administrator.google.ui.fragment;

import android.view.View;
import android.widget.ListView;

import com.example.administrator.google.R;
import com.example.administrator.google.adapter.CategoryAdapter;
import com.example.administrator.google.bean.Category;
import com.example.administrator.google.bean.CategoryInfor;
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
public class CategoryFragment extends BaseFragment {

    private ListView listView;
    ArrayList<Object> obList = new ArrayList<>();


    @Override
    protected View getSuccessView() {
        listView = (ListView) View.inflate(getActivity(), R.layout.listview, null);
        return listView;
    }

    @Override
    protected Object requestData() {
        String json = HttpUtil.get(Api.Category);
        ArrayList<Category> categories = (ArrayList<Category>) JsonUtil.parseJsonToList(json, new TypeToken<List<Category>>() {
        }.getType());
        if (categories != null) {
            for (Category category : categories) {
                obList.add(category.getTitle());  //添加的只是作为一个item加上去
                List<CategoryInfor> categoryInfos = category.getInfos();
                obList.addAll(categoryInfos);  //addall添加的是一个集合,此时长度原有长度加上集合的长度

            }
            CommonUtil.runOnuiThread(new Runnable() {
                @Override
                public void run() {
                    listView.setAdapter(new CategoryAdapter(obList));
                }
            });
        }
        return obList;
    }
}
