package com.qhyccd.ad;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);
        recyclerView = findViewById(R.id.recyclerView);
        List<String> data = new ArrayList<>();
        for (int i=0;i<100;i++){
            data.add(i+"");
        }
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new AdAdapter(this,data));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int fPos = manager.findFirstVisibleItemPosition();
                int lPos = manager.findLastVisibleItemPosition();
                View view = manager.findViewByPosition(fPos);
                int height  = view.getHeight();
                int top = view.getTop();
                int ipos = fPos * height;
                int dis = ipos - top;
                Log.i("》》》》 ","  dy ======  "+dy + "  fPos ==== "+ fPos + "  lPos ==== " +lPos + "  dis ====  "+dis);

            }
        });

    }
}
