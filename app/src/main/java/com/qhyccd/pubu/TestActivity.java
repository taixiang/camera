package com.qhyccd.pubu;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qhyccd.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.Random;

/**
 * Created by tx on 2018/8/10.
 */

public class TestActivity extends AppCompatActivity {

    private RecyclerView recyclerView;


    private SmartRefreshLayout refreshLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pubu);
        recyclerView = findViewById(R.id.recyclerView);
        refreshLayout = findViewById(R.id.refresh);
        StaggeredGridLayoutManager recyclerViewLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        recyclerView.setAdapter(new MyAdapter(this));

    }


    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

        private Context context;

        public MyAdapter(Context context) {
            this.context = context;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.adapter_item,parent,false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tv.setText(position+"");
            Random random = new Random();
            int r = random.nextInt(256);
            int g = random.nextInt(256);
            int b = random.nextInt(256);
//            holder.iv.setBackgroundColor(Color.rgb(r, g, b));
        }

        @Override
        public int getItemCount() {
            return 11;
        }
    }

    private static class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv;
        private TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
        }
    }

}
