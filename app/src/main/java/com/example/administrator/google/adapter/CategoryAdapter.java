package com.example.administrator.google.adapter;


import com.example.administrator.google.holder.BaseHolder;
import com.example.administrator.google.holder.CategoryInfoHolder;
import com.example.administrator.google.holder.CategoryTilteHolder;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/27 0027.
 */
public class CategoryAdapter extends BasicAdapter<Object> {
    public CategoryAdapter(ArrayList<Object> arrayList) {
        super(arrayList);
    }

    private final int ITEM_TITLE = 0;
    private final int ITEM_INFO = 1;

    /**
     * 返回指定的position的条目是什么类型的
     */
    @Override
    public int getItemViewType(int position) {
        Object object = arrayList.get(position);
        if (object instanceof String) {
            return ITEM_TITLE;
        } else {
            return ITEM_INFO;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public BaseHolder<Object> getHolder(int position) {
        BaseHolder<Object> holder = null;
        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case ITEM_TITLE:
                holder = new CategoryTilteHolder();
                break;

            case ITEM_INFO:
                holder = new CategoryInfoHolder();
                break;
        }
        return holder;
    }
}
