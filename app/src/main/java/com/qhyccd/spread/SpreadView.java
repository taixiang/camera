package com.qhyccd.spread;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;


import com.qhyccd.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tx
 * @date 2018/4/2
 */

public class SpreadView extends View {

    private Paint circlePaint; //圆paint
    private float radius = 100; //半径
    private Paint spreadPaint; //扩散paint
    private float centerX;//圆心x
    private float centerY;//圆心y

    private List<Integer> alphas = new ArrayList<>();
    private List<Float> levelWidths = new ArrayList<>();


    public SpreadView(Context context) {
        this(context, null, 0);
    }

    public SpreadView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpreadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        circlePaint = new Paint();
        circlePaint.setColor(ContextCompat.getColor(context, R.color.colorAccent));
        circlePaint.setAntiAlias(true);

        alphas.add(255);
        levelWidths.add(0f);
        spreadPaint = new Paint();
        spreadPaint.setAntiAlias(true);
        spreadPaint.setAlpha(255);
        spreadPaint.setColor(ContextCompat.getColor(context, R.color.colorRed));

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        int wSpecSize = MeasureSpec.getSize(widthMeasureSpec);
//        int hSpecSize = MeasureSpec.getSize(heightMeasureSpec);
//        setMeasuredDimension(Math.min(wSpecSize, hSpecSize), Math.min(wSpecSize, hSpecSize));
//    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < levelWidths.size(); i++) {
            int alpha = alphas.get(i);
            spreadPaint.setAlpha(alpha);
            float width = levelWidths.get(i);
            //扩散的圆
            canvas.drawCircle(centerX, centerY, radius + width, spreadPaint);

            if (alpha > 0 && width < 300) {
                alpha = alpha > 0 ? alpha - 5 : 1;
                alphas.set(i, alpha);
                levelWidths.set(i, width + 5);
            }
        }

        //中间的圆
        canvas.drawCircle(centerX, centerY, radius, circlePaint);

//        levelWidths.set(0, 10f);
//        alphas.set(0, 250);


        //当扩散圆扩散到指定宽度时添加新扩散圆
        if (levelWidths.get(levelWidths.size() - 1) > 100) {
            levelWidths.add(0f);
            alphas.add(255);
        }

        if (levelWidths.size() > 10) {

            alphas.remove(0);
            levelWidths.remove(0);
        }


        postInvalidateDelayed(100);
    }

    public void start() {
        invalidate();
    }
}
