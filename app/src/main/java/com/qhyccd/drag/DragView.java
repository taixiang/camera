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

    private int centerX;
    private int centerY;


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
        centerX = w/2;
        centerY = h/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.RED);
//        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4.0f);
//        canvas.rotate(45f,centerX,centerY);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);

//        canvas.restore();

        
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                leftD = getLeft();
                topD = getTop();
                rightD = getRight();
                bottomD = getBottom();

                dragDirection = getDirection((int) event.getX(), (int) event.getY());
                if(dragDirection == 9999){
                    Log.i("》》》》》  ","11111========");
                    return false;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(dragDirection == 9999){
                    Log.i("》》》》》  ","222222========");
                    return false;
                }
                //计算移动的距离
                int offsetX = (int) event.getRawX() - lastX;
                int offsetY = (int) event.getRawY() - lastY;
                switch (dragDirection) {
                    case LEFT:
                        leftD = leftD + offsetX;
                        if (leftD < 150) {
                            leftD = 150;
                        }
                        if (rightD - leftD < 300) {
                            leftD = rightD - 300;
                        }
                        break;
                    case RIGHT:
                        rightD = rightD + offsetX;
                        if (rightD >= screenWidth) {
                            rightD = screenWidth - 150;
                        }
                        break;
                    default:
                        break;
                }

//                leftD = getLeft() + offsetX;
//                rightD = getRight() + offsetX;



                Log.i("》》》》》 ", "leftD === " + leftD + " topD== " + topD + "" + " rightD == " + rightD + "   bottomD == " +   bottomD+"  width=="+getWidth()+"   height==="+getHeight());
//                topD = topD -1;
                layout(leftD, topD, rightD, bottomD);
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
//                int b = getBottom() + offsetY;
//                int r = getRight() + offsetX;
//                int t = getTop() + offsetY;

                break;
            default:
                break;
        }

//        invalidate();
        return true;
    }

    public void invali(){
        invalidate();
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
