package com.loadingpagetesyt;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import de.greenrobot.event.EventBus;

public class MainActivity extends Activity {
    protected LoadingPage loadingPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (loadingPage == null) {
            loadingPage = new LoadingPage(this) {
                @Override
                public View createSuccessView() {
                   // View view = View.inflate(MainActivity.this,R.layout.activity_main, null);
                    TextView textView=new TextView(MainActivity.this);
                    textView.setText("ffdfdf");
                    return textView;
                }
            };
        }
        EventBus.getDefault().postSticky(new EventLoadingPage(false));
        // setContentView(R.layout.activity_main);
    }

}
