package com.qhyccd.tabscroll;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.qhyccd.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tx
 * @date 2018/8/3
 */
public class AliTestActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private TabLayout realTabLayout;
    private CustomScrollView scrollView;
    private LinearLayout container;
    private String[] tabTxt = {"沙发", "财富管理", "资金往来", "购物娱乐", "教育公益", "第三方服务"};

    private List<AnchorView> anchorList = new ArrayList<>();

    private boolean isScroll;
    private int lastPos; //上一次位置
    private ViewTreeObserver.OnGlobalLayoutListener listener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ali_test);

        tabLayout = findViewById(R.id.tablayout_holder);
        realTabLayout = findViewById(R.id.tablayout_real);
        scrollView = findViewById(R.id.scrollView);
        container = findViewById(R.id.container);


        for (int i = 0; i < tabTxt.length; i++) {
            AnchorView anchorView = new AnchorView(this);
            anchorView.setAnchorTxt(tabTxt[i]);
            anchorView.setContentTxt(tabTxt[i]);
            anchorList.add(anchorView);
            container.addView(anchorView);
        }

        listener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int screenH = getScreenHeight();
                int statusBarH = getStatusBarHeight(AliTestActivity.this);
                int tabH = tabLayout.getHeight();
                int lastH = screenH - statusBarH - tabH - 16 * 3;
                Log.i("》》》》》 ", " screenH = " + screenH + " statusBarH = " + statusBarH +
                        " tabH = " + tabH + " lastH = " + lastH);
                AnchorView anchorView = anchorList.get(anchorList.size() - 1);
                if (anchorView.getHeight() < lastH) {
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    params.height = lastH;
                    anchorView.setLayoutParams(params);
                }
                int translation = Math.max(0, tabLayout.getTop());
                realTabLayout.setTranslationY(translation);
                realTabLayout.setVisibility(View.VISIBLE);
                container.getViewTreeObserver().removeOnGlobalLayoutListener(listener);

            }
        };
        container.getViewTreeObserver().addOnGlobalLayoutListener(listener);

        for (int i = 0; i < tabTxt.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(tabTxt[i]));
            realTabLayout.addTab(realTabLayout.newTab().setText(tabTxt[i]));
        }

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
                int translation = Math.max(y, tabLayout.getTop());
                realTabLayout.setTranslationY(translation);
                Log.i("》》》》  ", "  y===  " + y + "  tablayout===== " + tabLayout.getTop());

                if (isScroll) {
                    for (int i = tabTxt.length - 1; i >= 0; i--) {
                        if (y - 120 * 3 > anchorList.get(i).getTop() - 10) {
                            setScrollPos(i);
                            Log.i("》》》》》  ", " lastPos ===== " + lastPos);
                            break;
                        }
                    }
                }

            }
        });

        realTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.i("》》》》  ", " tab=====");
                isScroll = false;
                int pos = tab.getPosition();
                int top = anchorList.get(pos).getTop();
                Log.i("》》》》  ", "top ===== " + top);
                scrollView.smoothScrollTo(0, top + 120 * 3);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    private void setScrollPos(int newPos) {
        if (lastPos != newPos) {
            realTabLayout.setScrollPosition(newPos, 0, true);
        }
        lastPos = newPos;
    }

    private int getScreenHeight() {
        return getResources().getDisplayMetrics().heightPixels;
    }

    public int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources()
                .getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


}
