package com.example.administrator.google.holder;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.google.R;
import com.example.administrator.google.bean.Category;
import com.example.administrator.google.bean.CategoryInfor;
import com.example.administrator.google.bean.CategoryInfor2;
import com.example.administrator.google.global.GooglePlayApplication;
import com.example.administrator.google.global.ImageLoaderOptions;
import com.example.administrator.google.http.Api;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import butterknife.InjectView;

/**
 * d动态生成
 * Created by Administrator on 2016/7/31 0031.
 */
public class CategoryHolder2 extends BaseHolder<Category> {
    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.infos)
    LinearLayout infos;

    @Override
    public View initHolder() {
        LinearLayout view = (LinearLayout) View.inflate(GooglePlayApplication.context,
                R.layout.adapter_categroy2, null);
        return view;
    }

    @Override
    public void bindData(Category date) {
        title.setText(date.getTitle());
        infos.removeAllViews();
        ArrayList<CategoryInfor2> categoryInfors = new ArrayList<>();

        for (int i = 0; i < date.getInfos().size(); i++) {
            fillLineData(date.getInfos().get(i), categoryInfors);
        }
        //行数据
        LinearLayout line = (LinearLayout) View.inflate(GooglePlayApplication.context, R.layout.categroy_line, null);
        //行中元素
        LinearLayout item;
        ImageView itemImage;
        TextView itemText;
        String imageUrl;
        int weightSum = (int) line.getWeightSum();
        //动态添加布局
        for (int i = 0; i < categoryInfors.size(); i++) {
            if (i !=0 && i % weightSum == 0) {
                infos.addView(line);
                line = (LinearLayout) View.inflate(GooglePlayApplication.context, R.layout.categroy_line, null);
            }
            item = (LinearLayout) View.inflate(GooglePlayApplication.context, R.layout.category_item, null);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
            item.setLayoutParams(params);
            item.setClickable(true);
            itemText = (TextView) item.findViewById(R.id.tv_item_text);
            itemText.setText(categoryInfors.get(i).name);
            itemImage = (ImageView) item.findViewById(R.id.iv_item_image);
            imageUrl = Api.IMAGE_PREFIX + categoryInfors.get(i).url;
            ImageLoader.getInstance().displayImage(imageUrl, itemImage, ImageLoaderOptions.round_options);
            line.addView(item);
        }
        //将最后一行添加到主布局中
        infos.addView(line);
    }

    private void fillLineData(CategoryInfor categoryInfor, ArrayList<CategoryInfor2> categoryInfors) {
        categoryInfors.add(new CategoryInfor2(categoryInfor.getName1(), categoryInfor.getUrl1()));

        //第二列有数据
        if (!TextUtils.isEmpty(categoryInfor.getName2())) {
            categoryInfors.add(new CategoryInfor2(categoryInfor.getName2(), categoryInfor.getUrl2()));
        }
        //第三列有数据
        if (!TextUtils.isEmpty(categoryInfor.getName3())) {
            categoryInfors.add(new CategoryInfor2(categoryInfor.getName3(), categoryInfor.getUrl3()));
        }
    }
}
