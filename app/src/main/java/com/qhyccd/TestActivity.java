package com.qhyccd;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import github.hellocsl.layoutmanager.gallery.GalleryLayoutManager;

/**
 * @author tx
 * @date 2018/3/22
 */

public class TestActivity extends AppCompatActivity {

    private RecyclerView recycler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        recycler = findViewById(R.id.recycler);
        GalleryLayoutManager manager = new GalleryLayoutManager(GalleryLayoutManager.HORIZONTAL);
        manager.attach(recycler);
        //设置滑动缩放效果
        manager.setItemTransformer(new Transformer());
        recycler.setAdapter(new Adapter(this));

        manager.setOnItemSelectedListener(new GalleryLayoutManager.OnItemSelectedListener() {
            @Override
            public void onItemSelected(RecyclerView recyclerView, View item, int position) {
                //滑动到某一项的position
            }
        });
    }

    private class Adapter extends RecyclerView.Adapter<RecyclerHolder> {

        private Context context;

        private Adapter(Context context) {
            this.context = context;
        }

        @Override
        public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_view, null);
            //自定义view的宽度，控制一屏显示个数
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            int width = context.getResources().getDisplayMetrics().widthPixels;
            params.width = width / 3;
            view.setLayoutParams(params);
            return new RecyclerHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerHolder holder, final int position) {
            holder.getView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recycler.smoothScrollToPosition(position);
                }
            });
        }

        @Override
        public int getItemCount() {
            return 10;
        }
    }

    private class RecyclerHolder extends RecyclerView.ViewHolder {
        private View view;

        public RecyclerHolder(View itemView) {
            super(itemView);
            view = itemView;
        }

        public View getView() {
            return view;
        }
    }


}
