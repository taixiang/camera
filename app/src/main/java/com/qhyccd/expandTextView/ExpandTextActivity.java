package com.qhyccd.expandTextView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.qhyccd.R;

import cn.jpush.android.api.JPushInterface;

/**
 * @author tx
 * @date 2018/9/4
 */
public class ExpandTextActivity extends AppCompatActivity {


    private Button btn1;
    private Button btn2;
    private int s;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_text);
        btn1= findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s++;
                JPushInterface.setAlias(ExpandTextActivity.this,s,"1111111");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s=1;
                JPushInterface.deleteAlias(ExpandTextActivity.this,s);
            }
        });


    }

}
