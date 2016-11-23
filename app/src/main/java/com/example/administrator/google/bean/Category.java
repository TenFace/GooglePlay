package com.example.administrator.google.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/27 0027.
 */
public class Category {

    /**
     * infos : [{"name1":"休闲","name2":"棋牌","name3":"益智","url1":"image/category_game_0.jpg","url2":"image/category_game_1.jpg","url3":"image/category_game_2.jpg"},{"name1":"射击","name2":"体育","name3":"儿童","url1":"image/category_game_3.jpg","url2":"image/category_game_4.jpg","url3":"image/category_game_5.jpg"},{"name1":"网游","name2":"角色","name3":"策略","url1":"image/category_game_6.jpg","url2":"image/category_game_7.jpg","url3":"image/category_game_8.jpg"},{"name1":"经营","name2":"竞速","name3":"","url1":"image/category_game_9.jpg","url2":"image/category_game_10.jpg","url3":""}]
     * title : 游戏
     */

    private String title;
    /**
     * name1 : 休闲
     * name2 : 棋牌
     * name3 : 益智
     * url1 : image/category_game_0.jpg
     * url2 : image/category_game_1.jpg
     * url3 : image/category_game_2.jpg
     */

    private List<CategoryInfor> infos;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CategoryInfor> getInfos() {
        return infos;
    }

    public void setInfos(List<CategoryInfor> infos) {
        this.infos = infos;
    }


}
