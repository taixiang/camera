package com.qhyccd.beis;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

import com.qhyccd.R;

/**
 * @author tx
 * @date 2018/6/2
 */
public class AnimActivity extends Activity {

    private Button button;
    private TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);

        button = findViewById(R.id.btn);
        tv = findViewById(R.id.tv);

        int[] location = new int[2];
        button.getLocationInWindow(location);
        int x1 = location[0];
        int y1 = location[1];

        int[] location2 = new int[2];
        tv.getLocationInWindow(location2);
        int x2 = location2[0];
        int y2 = location2[1];

//        Log.i("》》》》  ","  x2====" + x2 + "  y2===" + y2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] location = new int[2];
                button.getLocationInWindow(location);
                int x1 = location[0];
                int y1 = location[1];
                Log.i("》》》》  ","  x1====" + x1 + "  y1===" + y1);

                int[] location2 = new int[2];
                tv.getLocationInWindow(location2);
                int x2 = location2[0];
                int y2 = location2[1];


                ObjectAnimator translateAnimationX = ObjectAnimator.ofFloat(button, "translationX", 0, x2-x1);
                translateAnimationX.setDuration(800);
                translateAnimationX.setInterpolator(new LinearInterpolator());
//        translateAnimationX.start();
                ObjectAnimator translateAnimationY = ObjectAnimator.ofFloat(button, "translationY", 0, y2 - y1 );
                translateAnimationY.setDuration(800);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(translateAnimationX).with(translateAnimationY);
                animatorSet.start();

            }
        });

    }



}
