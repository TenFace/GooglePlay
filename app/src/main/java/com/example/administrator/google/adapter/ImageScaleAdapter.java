package com.example.administrator.google.adapter;

import android.view.ViewGroup;

import com.example.administrator.google.global.GooglePlayApplication;
import com.example.administrator.google.global.ImageLoaderOptions;
import com.example.administrator.google.http.Api;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

public class ImageScaleAdapter extends BasePageAdapter<String>{

	public ImageScaleAdapter(ArrayList<String> list) {
		super(list);
	}


	@Override
	public Object instantiateItem(ViewGroup container, int position) {
//		ImageView imageView = new ImageView(GooglePlayApplication.context);
		PhotoView imageView = new PhotoView(GooglePlayApplication.context);
		
		ImageLoader.getInstance().displayImage(Api.IMAGE_PREFIX+list.get(position), imageView, ImageLoaderOptions.fadein_options);
		
		//将ImageView加入到ViewPager中
		container.addView(imageView);
		return imageView;
	}

}
