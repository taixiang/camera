package com.qhyccd.life;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.qhyccd.R;

/**
 * Created by tx on 2018/5/19.
 */

public class AActivity extends Activity {

    private Button btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            String data = savedInstanceState.getString("test");
            Log.i("》》》A"," onCreate 获取的数据："+data);
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("》》》A"," onRestart ");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String data = savedInstanceState.getString("test");
        Log.i("》》》A"," onRestoreInstanceState 获取的数据："+data);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("》》》A"," onStart ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("》》》A"," onResume ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("》》》A"," onPause ");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("test","保存的数据");
        Log.i("》》》A"," onSaveInstanceState 开始保存数据");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("》》》A"," onStop ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("》》》A"," onDestroy ");
    }
}
