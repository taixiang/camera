package com.qhyccd.tabscroll;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.qhyccd.R;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tx
 * @date 2018/7/18
 */
public class TabRecyclerActivity extends Activity {

    private TabLayout tabLayout;
    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private String[] tabTxt = {"1", "2", "3", "4", "5", "6"};
    private boolean isScroll;
    private int lastPos;
    private ViewTreeObserver.OnGlobalLayoutListener listener;

    private boolean mShouldScroll;
    private int mToPosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_recycler);

        int screenH = getScreenHeight();
        int statusBarH = getStatusBarHeight(this);
        int tabH = 50*3;
        int lastH = screenH - statusBarH - tabH;

        recyclerView = findViewById(R.id.recyclerView);
        tabLayout = findViewById(R.id.tablayout);

        for (int i = 0; i < tabTxt.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(tabTxt[i]));
        }
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new MyAdapter(this, tabTxt,lastH));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                isScroll = false;
//                manager.scrollToPositionWithOffset(pos, 0);
                moveToPosition(manager, recyclerView, pos);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    isScroll = true;
                }
                return false;
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

//                int distance = unlikeVertical();
//                Log.i("》》》》  ", " onscrolled" );
                if(isScroll){
                    int position = manager.findFirstVisibleItemPosition();
                    if(lastPos != position){
                        Log.i("》》》》  ", " isscroll ======  true   " );
                        tabLayout.setScrollPosition(position,0,true);
                    }
                    lastPos = position;
//                Log.i("》》》》》》  ", "》》》》》 pos === " + position);


                }


//                recyclerView.removeOnScrollListener(this);
            }
        });

        listener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(listener);

            }
        };
        recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(listener);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (mShouldScroll) {
                    mShouldScroll = false;
                    moveToPosition(manager,recyclerView, mToPosition);
                }
            }
        });

    }

    private int getScollYDistance() {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int position = layoutManager.findFirstVisibleItemPosition();
        View firstVisiableChildView = layoutManager.findViewByPosition(position);
        int itemHeight = firstVisiableChildView.getHeight();
        return (position) * itemHeight - firstVisiableChildView.getTop();
    }

    private Map<Integer, Integer> mMapList = new HashMap<>();
    private int iposition;

    public int unlikeVertical() {
        int itemWH = 0;
        int itemTR = 0;
        int distance = 0;
        int position = manager.findFirstVisibleItemPosition();
        Log.i("》》》》》》  ", "》》》》》 pos === " + position);
        View firstVisiableChildView = manager.findViewByPosition(position);
        //判断是横着还是竖着，得出宽或高
        itemWH = firstVisiableChildView.getHeight();

        //一层判断mMapList是否为空，若不为空则根据键判断保证不会重复存入
        if (mMapList.size() == 0) {
            mMapList.put(position, itemWH);
        } else {
            if (!mMapList.containsKey(position)) {
                mMapList.put(position, itemWH);

            }
        }
        Log.i("》》》》》 ", mMapList + "   map list");
        //判断是横着还是竖着，得出未滑出屏幕的距离
        itemTR = firstVisiableChildView.getTop();

        Log.i("》》》》》 ", "》》》》 itemtr ====  "+itemTR);
        //position为动态获取，目前屏幕Item位置
        for (int i = 0; i < position; i++) {
            //iposition移出屏幕的距离
            iposition = iposition + mMapList.get(i);
        }
        //根据类型拿iposition减未移出Item部分距离，最后得出滑动距离
        distance = iposition - itemTR;
        //item宽高
//        itemW = firstVisiableChildView.getWidth();
//        itemH = firstVisiableChildView.getHeight();
        //归零
        iposition = 0;
        Log.i("》》》》》 "," 》》》》  distance ===  "+distance);
        return distance;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    public void moveToPosition(LinearLayoutManager manager, RecyclerView mRecyclerView, int n) {
        int firstItem = manager.findFirstVisibleItemPosition();
        int lastItem = manager.findLastVisibleItemPosition();
        Log.i("》》》》  ", " first ==== " + firstItem + " last === " + lastItem + " n===== "+n);

        if (n <= firstItem) {
//            mRecyclerView.scrollToPosition(n);
            mRecyclerView.smoothScrollToPosition(n);
        } else if (n <= lastItem) {
            int top = mRecyclerView.getChildAt(n - firstItem).getTop();
            Log.i("》》》》  ", " top===== " + top);
            mRecyclerView.smoothScrollBy(0, top);
        } else {
            mRecyclerView.smoothScrollToPosition(n);
            mToPosition = n;
            mShouldScroll = true;
        }

//        if(n>firstItem && n<lastItem){
//            int top = mRecyclerView.getChildAt(n - firstItem).getTop();
//            mRecyclerView.smoothScrollBy(0, top);
//        }else {
//            mRecyclerView.smoothScrollToPosition(n);
//        }

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
