package com.qhyccd.drag;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by tx on 2018/6/11.
 */

public class DragView extends View {
    protected int screenWidth;
    protected int screenHeight;
    private Paint paint;
    private int lastX;
    private int lastY;

    private int leftD;
    private int topD;
    private int rightD;
    private int bottomD;

    private int dragDirection;
    private static final int LEFT = 0x10;
    private static final int RIGHT = 0x11;


    public DragView(Context context) {
        this(context, null);
    }

    public DragView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2f);
        initScreenW_H();
    }

    /**
     * 初始化获取屏幕宽高
     */
    protected void initScreenW_H() {
        screenHeight = getResources().getDisplayMetrics().heightPixels - 40;
        screenWidth = getResources().getDisplayMetrics().widthPixels;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                leftD = getLeft();
                topD = getTop();
                rightD = getRight();
                bottomD = getBottom();

                dragDirection = getDirection(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                //计算移动的距离
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                switch (dragDirection) {
                    case LEFT:
                        Log.i("》》》》  ", "》》》 x=== " + x + "   lastX=====" + lastX + " offsetX=== " + offsetX);
                        leftD = getLeft() + offsetX;
                        if (leftD < 150) {
                            leftD = 150;
                        }
                        if (rightD - leftD < 300) {
                            leftD = rightD - 300;
                        }
                        break;
                    case RIGHT:
                        Log.i("》》》》  ", "》》》 x=== " + x + "   lastX=====" + lastX + " offsetX=== " + offsetX);
                        rightD = getRight() + offsetX;
                        if (rightD >= screenWidth) {
                            rightD = screenWidth - 150;
                        }
                        break;
                    default:
                        break;
                }

//                leftD = getLeft() + offsetX;
//                rightD = getRight() + offsetX;

                Log.i("》》》》》 ", "leftD === " + leftD + " topD== " + topD + "" + " rightD == " + rightD + " bottomD == " + bottomD);
                layout(leftD, topD, rightD, bottomD);

//                int b = getBottom() + offsetY;
//                int r = getRight() + offsetX;
//                int t = getTop() + offsetY;

                break;
            default:
                break;
        }


        return true;
    }

    private int getDirection(int x, int y) {
        int left = getLeft();
        int right = getRight();
        int top = getTop();
        int bottom = getBottom();
        int width = getWidth();
        int height = getHeight();

        if (x <= 50 && y >= 50 && y <= bottom - top - 50) {
            return LEFT;
        }
        if (right - left - x <= 50 && y <= bottom - top - 50) {
            return RIGHT;
        }
        return 9999;
    }

}
