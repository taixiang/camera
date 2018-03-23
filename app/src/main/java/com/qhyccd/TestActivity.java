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

    public static void actTest(AppCompatActivity appCompatActivity) {
        Intent intent = new Intent(appCompatActivity, TestActivity.class);
        appCompatActivity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }
}
