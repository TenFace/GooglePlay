package com.example.administrator.google.adapter;

import com.example.administrator.google.bean.Subject;
import com.example.administrator.google.holder.BaseHolder;
import com.example.administrator.google.holder.SubjectHolder;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/26 0026.
 */
public class SubjectAdapter extends BasicAdapter<Subject> {
    public SubjectAdapter(ArrayList<Subject> arrayList) {
        super(arrayList);
    }

    @Override
    public BaseHolder<Subject> getHolder(int position) {
        return new SubjectHolder();
    }


}
