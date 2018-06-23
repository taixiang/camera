package com.qhyccd.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qhyccd.R;

/**
 * @author tx
 * @date 2018/6/23
 */
public class IncludeActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_include);
        View view = findViewById(R.id.container2);
        TextView textView = view.findViewById(R.id.tv);
        textView.setText("这里是来自 第二个 include布局");
    }
}
