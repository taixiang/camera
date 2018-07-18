package com.qhyccd.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.qhyccd.R;

/**
 * Created by tx on 2018/7/15.
 */

public class TestActivity extends Activity {
    private RelativeLayout rl_mask1;
    private Button button;
    private ImageView iv;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        rl_mask1 = findViewById(R.id.rl_mask1);
        iv = findViewById(R.id.iv);
        button = findViewById(R.id.button);

        rl_mask1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(TestActivity.this,"33333",Toast.LENGTH_SHORT).show();
                rl_mask1.setVisibility(View.GONE);
                return true;
            }
        });

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestActivity.this,"22222",Toast.LENGTH_SHORT).show();

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestActivity.this,"1111",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
