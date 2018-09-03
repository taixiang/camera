package com.qhyccd.rain;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.qhyccd.R;

/**
 * @author tx
 * @date 2018/8/23
 */
public class RainActivity extends AppCompatActivity {

    private RainView rainView;
    private Button btnCake;
    private Button btnDog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
        rainView = findViewById(R.id.testView);
        btnCake = findViewById(R.id.btn_cake);
        btnDog = findViewById(R.id.btn_dog);
        btnCake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rainView.setImgResId(R.mipmap.cake);
                rainView.start(true);
            }
        });
        btnDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rainView.setImgResId(R.mipmap.dog);
                rainView.start(true);
            }
        });


    }
}
