package com.qhyccd.tabscroll;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.qhyccd.R;

/**
 * @author tx
 * @date 2018/7/10
 */
public class TabScrollActivity extends Activity {

    private TabLayout tabLayout;
    private CustomScrollView scrollView;
    private String[] tabTxt = {"沙发", "财富管理", "资金往来", "购物娱乐", "教育公益", "第三方服务"};
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;
    private FlexboxLayout flexLayout;
    private boolean isScroll;
    private ViewTreeObserver.OnGlobalLayoutListener listener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_scroll);
        tabLayout = findViewById(R.id.tablayout);
        scrollView = findViewById(R.id.scrollView);

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        tv5 = findViewById(R.id.tv5);
        tv6 = findViewById(R.id.tv6);
        flexLayout = findViewById(R.id.six_layout);

        listener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int height = flexLayout.getHeight();
                Log.i("》》》》  ", "》》》》》  " + height);
                if (height < 1500) {
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    params.height = 1500;
                    flexLayout.setLayoutParams(params);
                }
            }
        };

        flexLayout.getViewTreeObserver().addOnGlobalLayoutListener(listener);


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
                    case 0:
                        top = tv1.getTop();
                        break;
                    case 1:
                        top = tv2.getTop();
                        break;
                    case 2:
                        top = tv3.getTop();
                        break;
                    case 3:
                        top = tv4.getTop();
                        break;
                    case 4:
                        top = tv5.getTop();
                        break;
                    case 5:
                        top = tv6.getTop();
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
//                Log.i("》》》》  ", " y ==== " + y);
//                Log.i("》》》》  ", " tv1 === " + tv1.getTop());
//                Log.i("》》》》  ", " tv2 === " + tv2.getTop());
//                Log.i("》》》》  ", " tv3 === " + tv3.getTop());
//                Log.i("》》》》  ", " tv4 === " + tv4.getTop());
//                Log.i("》》》》  ", " tv5 === " + tv5.getTop());
//                Log.i("》》》》  ", " tv6 === " + tv6.getTop());

                if (isScroll) {
                    if (y > tv6.getTop()) {
                        tabLayout.setScrollPosition(5, 0, true);
                    } else if (y > tv5.getTop()) {
                        tabLayout.setScrollPosition(4, 0, true);
                    } else if (y > tv4.getTop()) {
                        tabLayout.setScrollPosition(3, 0, true);
                    } else if (y > tv3.getTop()) {
                        tabLayout.setScrollPosition(2, 0, true);
                    } else if (y > tv2.getTop()) {
                        tabLayout.setScrollPosition(1, 0, true);
                    } else if (y > tv1.getTop()) {
                        tabLayout.setScrollPosition(0, 0, true);
                    }
                }


            }

            @Override
            public void onTouchUp() {

            }

            @Override
            public void onTouchDown() {

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        flexLayout.getViewTreeObserver().removeOnGlobalLayoutListener(listener);
    }

    private int getScreenHeight() {
        return getResources().getDisplayMetrics().heightPixels;
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources()
                .getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

}
