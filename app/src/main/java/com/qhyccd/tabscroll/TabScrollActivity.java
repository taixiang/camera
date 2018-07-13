package com.qhyccd.tabscroll;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.qhyccd.R;

/**
 * @author tx
 * @date 2018/7/10
 */
public class TabScrollActivity extends Activity {

    private TabLayout tabLayout;
    private CustomScrollView scrollView;
    private String[] tabTxt = {"便民生活", "财富管理", "资金往来", "购物娱乐", "教育公益", "第三方服务"};
    private TextView tv2;
    private boolean isScroll;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_scroll);
        tabLayout = findViewById(R.id.tablayout);
        scrollView = findViewById(R.id.scrollView);

        tv2 = findViewById(R.id.tv2);

        for (int i = 0; i < tabTxt.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(tabTxt[i]));
        }
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.i("》》》》  ", " tab=====");
                isScroll = false;
                int pos = tab.getPosition();
                int top = 0;
                switch (pos) {
                    case 1:
                        top = tv2.getTop();
                        break;
                    default:
                        break;
                }
                scrollView.smoothScrollTo(0, top);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    isScroll = true;
                }
                return false;
            }
        });

        scrollView.setCallbacks(new CustomScrollView.Callbacks() {
            @Override
            public void onScrollChanged(int x, int y, int oldx, int oldy) {
                Log.i("》》》》  ", " y ==== " + y);

                Log.i("》》》》  ", " tv2 === " + tv2.getTop());

//                if(isScroll){
                if (y > tv2.getTop()) {
                    tabLayout.setScrollPosition(1, 0, true);
                }
//                }


            }

            @Override
            public void onTouchUp() {

            }

            @Override
            public void onTouchDown() {

            }
        });


    }
}
