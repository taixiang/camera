package com.qhyccd;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tx
 * @date 2018/3/16
 */

public class CircleView extends View {

    private Paint paint = new Paint();
    private int mWidth;
    private int mHeight;
    private int radius;
    private List<Integer> dataList = new ArrayList<>();

    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        paint.setAntiAlias(true);
        paint.setColor(ContextCompat.getColor(context, R.color.colorBlack));
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //获取布局宽高
        mWidth = w;
        mHeight = h;
        Log.i("》》》》 ", "w=" + w + " h=" + h + "  oldw=" + oldw + "  oldh=" + oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //将坐标原点移至中心
        canvas.translate(mWidth / 2, mHeight / 2);

        drawAxes(canvas);

        if (dataList.size() <= 0) {
            return;
        }

        for (int i = 0; i < dataList.size(); i++) {
            canvas.drawCircle(0, 0, dataList.get(i), paint);
        }

    }


    /**
     * 坐标轴
     *
     * @param canvas
     */
    private void drawAxes(Canvas canvas) {
        canvas.drawLine(-mWidth / 2, 0, mWidth / 2, 0, paint);
        canvas.drawLine(0, -mHeight / 2, 0, mHeight / 2, paint);
        for (int i = 1; i < mWidth / 2 / 30; i++) {
            canvas.drawLine(30 * i, 0, 30 * i, -8, paint);
            canvas.drawLine(-30 * i, 0, -30 * i, -8, paint);
        }
        for (int i = 1; i < mHeight / 2 / 30; i++) {
            canvas.drawLine(0, 30 * i, -8, 30 * i, paint);
            canvas.drawLine(0, -30 * i, -8, -30 * i, paint);
        }
    }

    public void drawCircle(int radius) {
        this.radius = radius;
        dataList.add(radius);
        invalidate();
    }

}
