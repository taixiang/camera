package com.qhyccd;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * @author tx
 * @date 2018/3/7
 */

public class CustomImageView extends ImageView {

    interface OnMoveListener {
        void getXy(float x, float y);
    }

    OnMoveListener listener;

    public void setListener(OnMoveListener listener) {
        this.listener = listener;
    }

    public CustomImageView(Context context) {
        super(context);
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
            float x = event.getX();
            float y = event.getY();
            if (x > getWidth() || y > getHeight()) {
                return true;
            }
            listener.getXy(x, y);
        }

        return true;

    }
}
