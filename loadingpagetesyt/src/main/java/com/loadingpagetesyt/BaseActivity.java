package com.loadingpagetesyt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public abstract class BaseActivity extends AppCompatActivity {
    protected LoadingPage loadingPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (loadingPage==null){
            loadingPage=new LoadingPage(this) {
                @Override
                public View createSuccessView() {
                    return getSuccessView();
                }

            };
        }
       //setContentView(R.layout.activity_base);
    }
    /**
     * 获取每个子类的successView
     *
     * @return
     */
    protected abstract View getSuccessView();
}
