package com.example.administrator.google.global;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**获取上下文的对象
 * Created by Administrator on 2016/7/23 0023.
 */
public class GooglePlayApplication extends Application {
    public static Context context;
    public static Handler mainHandler;//主线程的handler
    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        mainHandler=new Handler();
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));
    }
    //初始化ImageLoader


}
