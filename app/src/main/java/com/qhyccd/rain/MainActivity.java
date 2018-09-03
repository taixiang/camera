package com.qhyccd.rain;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.qhyccd.R;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ScreenRainView srvMain;
    private Button start;
    private Random random = new Random();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rain);
        srvMain = findViewById(R.id.srvMain);
        start = findViewById(R.id.start);
        srvMain.setRainStartPadding(10);
        srvMain.setRainDestroyAfterEnds(false);
        srvMain.setRainMinScale(0.8f);
        srvMain.setRainMaxScale(1.2f);
        srvMain.setRainDuration(2500);
        srvMain.setRainAppearInterval(200);
        srvMain.setRainAppearDuration(1500);
        srvMain.addRaindropImages(R.mipmap.pic_vip_bought_gift_orange,
                R.mipmap.pic_vip_bought_gift_yellow);
        float t = 80f/100f;
        for(int i=0;i<10;i++){
            int q = -random.nextInt(5);
            Log.i("》》》》  ","t ========  "+ q);
        }

    }

    public void onClick(View v) {
        if (v.getId() == R.id.start) {
            srvMain.start();
        } else {
            Intent intent = new Intent(this, RainActivity.class);
            startActivity(intent);
        }

    }
}