package com.qhyccd.life;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by tx on 2018/5/19.
 */

public class BActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("》》》B"," onCreate ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("》》》B"," onRestart ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("》》》B"," onStart ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("》》》B"," onResume ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("》》》B"," onPause ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("》》》B"," onStop ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("》》》B"," onDestroy ");
    }

}
