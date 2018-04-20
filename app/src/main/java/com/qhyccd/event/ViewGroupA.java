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
public class ViewGroupA extends LinearLayout {
    public ViewGroupA(Context context) {
        super(context);
    }

    public ViewGroupA(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewGroupA(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("》》》", " ViewGroupA onTouchEvent  ");
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i("》》》", " ViewGroupA onInterceptTouchEvent  ");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("》》》", " ViewGroupA dispatchTouchEvent  ");
        return super.dispatchTouchEvent(ev);
    }
}
