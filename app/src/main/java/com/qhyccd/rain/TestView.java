package com.qhyccd.rain;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.qhyccd.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author tx
 * @date 2018/8/23
 */
public class TestView extends View {

    private Paint paint;
    private Matrix matrix;
    private Bitmap bitmap;
    private Random random;

    private boolean isRun;
    private List<ItemImg> bitmapList;

    public TestView(Context context) {
        this(context, null);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        matrix = new Matrix();
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        random = new Random();
        bitmapList = new ArrayList<>();
        Log.i("》》》》》  ", " getWidth====  " + getWidth());

    }

    private int count = 1;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isRun) {
            boolean isInScreen = false;
            for (int i = 0; i < bitmapList.size(); i++) {
                matrix.reset();
                bitmapList.get(i).startX = bitmapList.get(i).startX + bitmapList.get(i).speedX;
                bitmapList.get(i).startY = bitmapList.get(i).startY + bitmapList.get(i).speedY;
                Log.i("》》》》》》》》》  ", "x===  " + bitmapList.get(i).startX + "   y===  " + bitmapList.get(i).startY);
                if (bitmapList.get(i).startY < getHeight()) {
                    isInScreen = true;
                }
                matrix.postTranslate(bitmapList.get(i).startX, bitmapList.get(i).startY);
                canvas.drawBitmap(bitmapList.get(i).bitmap, matrix, paint);
            }
            if (isInScreen) {
                postInvalidate();
            }

        }
    }

    public void setRun(boolean isRun) {
        this.isRun = isRun;
        initData();
        postInvalidate();
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            ItemImg itemImg = new ItemImg();
            itemImg.bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
            itemImg.startX = random.nextInt(getWidth() - 100) + 50;
            itemImg.startY = -random.nextInt(getHeight());
            itemImg.speedX = random.nextInt(4) - 2;
            itemImg.speedY = 12;
            bitmapList.add(itemImg);
        }


    }

    private class ItemImg {
        private int startX;
        private int startY;
        private int speedX;
        private int speedY;
        private Bitmap bitmap;
        private boolean isOutScreen;
    }


}
