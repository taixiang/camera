package com.qhyccd;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import github.hellocsl.layoutmanager.gallery.GalleryLayoutManager;

//滑动过程中的缩放
    public class Transformer implements GalleryLayoutManager.ItemTransformer {

    @Override
    public void transformItem(GalleryLayoutManager layoutManager, View item, float fraction) {
        //以圆心进行缩放
        item.setPivotX(item.getWidth() / 2.0f);
        item.setPivotY(item.getHeight() / 2.0f);
        float scale = 1 - 0.5f * Math.abs(fraction);
        item.setScaleX(scale);
        item.setScaleY(scale);

        int pos = (int) item.getTag();

        Log.i("》》》》  ","  pos ==== "+pos);
        TextView tv1 = item.findViewById(R.id.tv1);
        TextView tv2 = item.findViewById(R.id.tv2);
        TextView tv3 = item.findViewById(R.id.tv3);
        if(scale > 0.95f){ // 中间选中

            tv3.setVisibility(View.INVISIBLE);
            tv1.setVisibility(View.VISIBLE);
            tv2.setVisibility(View.VISIBLE);
            if(pos == 0){
                tv1.setText("起始日");
                tv2.setVisibility(View.INVISIBLE);
            }else if(pos == 13){
                tv1.setText("结束日");
                tv2.setVisibility(View.INVISIBLE);
            }
        }else {
            tv1.setVisibility(View.INVISIBLE);
            tv2.setVisibility(View.INVISIBLE);
            tv3.setVisibility(View.VISIBLE);
        }

    }
}
