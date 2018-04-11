package com.qhyccd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @author tx
 * @date 2018/3/22
 */

public class TestActivity extends AppCompatActivity {

    private RadarView radarView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        radarView = findViewById(R.id.radar_view);
//        radarView.setCount(6);
//        radarView.setLayerCount(6);

    }


}
