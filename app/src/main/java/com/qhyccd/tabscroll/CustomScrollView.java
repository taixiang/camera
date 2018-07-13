package com.qhyccd.tabscroll;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * @author tx
 * @date 2018/7/10
 */
public class CustomScrollView extends ScrollView {

    public Callbacks mCallbacks;


    public CustomScrollView(Context context) {
        super(context);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setCallbacks(Callbacks callbacks) {
        this.mCallbacks = callbacks;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        if (mCallbacks != null) {
            mCallbacks.onScrollChanged(l, t, oldl, oldt);
        }
    }

    public interface Callbacks {
        void onScrollChanged(int x, int y, int oldx, int oldy);

        void onTouchUp();

        void onTouchDown();
    }

}