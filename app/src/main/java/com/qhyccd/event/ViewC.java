package com.qhyccd.event;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * @author tx
 * @date 2018/4/18
 */
public class ViewC extends View {

    private int lastX;
    private int lastY;

    public ViewC(Context context) {
        super(context);
    }

    public ViewC(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewC(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i("》》》", " ViewC dispatchTouchEvent  ");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("》》》", " ViewC onTouchEvent  ");

        int x = (int) event.getX();
        int y = (int) event.getY();

        Log.i("》》》", " ViewC onTouchEvent  x=="+x+"  y=="+y);

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
            case MotionEvent.ACTION_UP:
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) getLayoutParams();
                params.setMargins(getLeft(), getTop(), 0, 0);
                break;
            default:
                break;
        }
        return true;
    }


}
