package com.qhyccd;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * @author tx
 * @date 2018/3/15
 */

public class SecondActivity extends AppCompatActivity {

    private TouchView ivTouch;
    private LinearLayout container;
    private CircleView circleView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ivTouch = findViewById(R.id.iv_touch);
        container = findViewById(R.id.container);
        circleView = findViewById(R.id.circleView);

//        ivTouch.setOnTouchListener(shopCarSettleTouch);

        container.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int totalWidth = container.getWidth();
                int totalHeight = container.getHeight();
                Log.i("》》》 totalWidth === ", "" + totalWidth);
                ivTouch.setWidth(totalWidth);
                ivTouch.setHeight(totalHeight);
                container.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        circleView.drawCircle(120);
        circleView.drawCircle(60);
    }


}
