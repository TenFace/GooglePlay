package com.example.administrator.google.ui.activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.administrator.google.R;
import com.example.administrator.google.adapter.ImageScaleAdapter;
import com.example.administrator.google.ui.widget.HackyViewPager;

import java.util.ArrayList;

public class ImageScaleActivity extends Activity{
	private HackyViewPager viewPager;//骇客
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_scale);
		viewPager = (HackyViewPager) findViewById(R.id.hackyViewPager);
		//1.获取集合
		ArrayList<String> urlList = getIntent().getStringArrayListExtra("urlList");
		int currentItem = getIntent().getIntExtra("currentItem", 0);
		//2.填充数据
		ImageScaleAdapter adapter = new ImageScaleAdapter(urlList);
		viewPager.setAdapter(adapter);
		//设置默认选中的页
		viewPager.setCurrentItem(currentItem);
	}
}
