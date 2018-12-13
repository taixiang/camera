package com.qhyccd.ad;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.qhyccd.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tx
 * @date 2018/12/11
 */
public class AdActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private ImageView iv_bg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);
        recyclerView = findViewById(R.id.recyclerView);
        iv_bg = findViewById(R.id.iv_bg);
        final int screenHeight = getResources().getDisplayMetrics().heightPixels;
        Log.i("》》》》  ", "  screenHeight ====  " + screenHeight);
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            data.add(i + "");
        }
        iv_bg.scrollTo(0, -screenHeight);
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new AdAdapter(this, data));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int fPos = manager.findFirstVisibleItemPosition();
                int lPos = manager.findLastVisibleItemPosition();
                View view = manager.findViewByPosition(fPos);
                int height = view.getHeight();
                int top = view.getTop();
                int ipos = fPos * height;
                int dis = ipos - top;
                Log.i("》》》》 ", "  dy ======  " + dy + "  fPos ==== " + fPos + "  lPos ==== " + lPos + "  dis ====  " + dis);

//                if(dy > 0){
//                    iv_bg.scrollBy(0, dy+5);
//                }else {
//                    iv_bg.scrollBy(0, dy-5);
//                }

                boolean isIn =true;

                for (int i = fPos; i <= lPos; i++) {
                    View v = manager.findViewByPosition(i);
                    LinearLayout layout = v.findViewById(R.id.linearLayout);
                    if (layout.getVisibility() == View.GONE) {
                        Log.i("》》》》》  ", "  i ============  " + i);
//                        iv_bg.scrollBy(0,dy);
                        if(dy > 0){
                            iv_bg.scrollBy(0,dy+5);
                        }else {
                            iv_bg.scrollBy(0,dy-5);
                        }
                        isIn = false;
                    }
                }

                if(isIn){
                    Log.i("》》》》》  ", "  scrollTo  ");
                    iv_bg.scrollTo(0, -screenHeight);
                }
                isIn = true;
//                ObjectAnimator y = ObjectAnimator.ofFloat(iv_bg, "translationY",0,50);
//                AnimatorSet animatorSet = new AnimatorSet();
//                animatorSet.play(y);
//                animatorSet.start();
            }
        });

    }
}
