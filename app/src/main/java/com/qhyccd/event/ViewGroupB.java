package com.qhyccd.event;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * @author tx
 * @date 2018/4/18
 */
public class ViewGroupB extends LinearLayout {

    private int lastX;
    private int lastY;


    public ViewGroupB(Context context) {
        super(context);
    }

    public ViewGroupB(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewGroupB(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("》》》", " ViewGroupB onTouchEvent  ");

        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                //计算移动的距离
                int offsetX = x - lastX;
                int offsetY = y - lastY;

                int l = getLeft() + offsetX;
                int b = getBottom() + offsetY;
                int r = getRight() + offsetX;
                int t = getTop() + offsetY;
                //重新确认位置
                layout(l, t, r, b);

                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i("》》》", " ViewGroupB onInterceptTouchEvent  ");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("333》》》", " ViewGroupB dispatchTouchEvent  ");
        return super.dispatchTouchEvent(ev);
    }

}
