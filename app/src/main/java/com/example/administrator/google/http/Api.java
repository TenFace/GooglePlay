package com.example.administrator.google.http;

/**
 * Created by Administrator on 2016/7/24 0024.
 */
public interface Api {
    //服务器的主机地址
    String SERVER_HOST = "http://127.0.0.1:8090/";
    //图片
    String IMAGE_PREFIX = SERVER_HOST + "image?name=";
    //home页的url地址
    String Home = SERVER_HOST + "home?index=";
    //app页的url地址
    String App = SERVER_HOST + "app?index=";
    //game页的url地址
    String Game = SERVER_HOST + "game?index=";
    //subject页的url地址
    String Subject = SERVER_HOST + "subject?index=";
    //recommend页的url地址
    String Recommend = SERVER_HOST + "recommend?index=0";
    //category页的url地址
    String Category = SERVER_HOST + "category?index=0";
    //hot页的url地址
    String Hot = SERVER_HOST + "hot?index=0";
    //detail页的url地址
    String Detail = SERVER_HOST + "detail?packageName=%s";
}
