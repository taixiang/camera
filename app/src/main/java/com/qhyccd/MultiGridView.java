package com.qhyccd;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author tx
 * @date 2018/3/20
 */

public class MultiGridView extends View {

    private Paint paint = new Paint();
    private int mWidth;
    private int mHeight;


    public MultiGridView(Context context) {
        super(context);
    }

    public MultiGridView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        paint.setAntiAlias(true);
        paint.setColor(ContextCompat.getColor(context, R.color.colorRed));
    }

    public MultiGridView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //获取布局宽高
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < mHeight / 20; i++) {
            canvas.drawLine(0, 20 * i, mWidth, 20 * i, paint);
        }

        for (int i = 0; i < mWidth / 20; i++) {
            canvas.drawLine(20 * i, 0, 20 * i, mHeight, paint);
        }
    }
}
