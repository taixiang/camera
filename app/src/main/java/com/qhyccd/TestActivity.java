package com.qhyccd;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import github.hellocsl.layoutmanager.gallery.GalleryLayoutManager;

/**
 * @author tx
 * @date 2018/3/22
 */

public class TestActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private Adapter adapter = new Adapter(this);
    private GalleryLayoutManager manager;
    private Transformer transformer = new Transformer();
    private int curPos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        recycler = findViewById(R.id.recycler);
        manager = new GalleryLayoutManager(GalleryLayoutManager.HORIZONTAL);
        manager.attach(recycler);
        //设置滑动缩放效果

        manager.setItemTransformer(transformer);
        recycler.setAdapter(adapter);

        manager.setOnItemSelectedListener(new GalleryLayoutManager.OnItemSelectedListener() {
            @Override
            public void onItemSelected(RecyclerView recyclerView, View item, int position) {
                //滑动到某一项的position
                Log.i("》》》 "," onItemSelected  "+position);
                curPos = position;
            }
        });

        recycler.addItemDecoration(new CustomDecoration(20));
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
            params.width = width / 5;
            view.setLayoutParams(params);
            return new RecyclerHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerHolder holder, final int position) {
            TextView tv3= holder.getView().findViewById(R.id.tv3);
            TextView tv1= holder.getView().findViewById(R.id.tv1);
            TextView tv2= holder.getView().findViewById(R.id.tv2);
            tv1.setText(position+"/"+14);
            tv2.setText("本期 "+position);
            tv3.setText(position+"");
            holder.getView().setTag(position);
        }

        @Override
        public int getItemCount() {
            return 14;
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

    private class CustomDecoration extends RecyclerView.ItemDecoration{
        private int space;

        public CustomDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
//            Log.i("》》》》 ","  AdapterPosition== "+parent.getChildAdapterPosition(view));
            if(parent.getChildAdapterPosition(view)-2>=0 && curPos == parent.getChildAdapterPosition(view)-2){
//                Log.i("》》》》 ","  ---------------- ");
                outRect.left = space;
                outRect.right = space;
            }else {
//                Log.i("》》》》 ","  ============== ");
                outRect.left = 0;
                outRect.right = 0;
            }

        }
    }

}
