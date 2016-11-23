package com.example.administrator.google.adapter;

import com.example.administrator.google.bean.Category;
import com.example.administrator.google.holder.BaseHolder;
import com.example.administrator.google.holder.CategoryHolder2;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/31 0031.
 */
public class CategoryAdapter2 extends BasicAdapter<Category>{
    public CategoryAdapter2(ArrayList arrayList) {
        super(arrayList);
    }

    @Override
    public BaseHolder getHolder(int position) {
        return new CategoryHolder2();
    }
}
