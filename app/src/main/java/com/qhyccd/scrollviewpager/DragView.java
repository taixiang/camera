package com.qhyccd.scrollviewpager;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.qhyccd.R;

/**
 * @author tx
 * @date 2018/8/2
 */
public class DragView extends LinearLayout {

    private int lastX;
    private int lastY;
    /**
     * 父布局宽高
     */
    private int parentWidth;
    private int parentHeight;

    private int scrollX;
    private int scrollY;

    public DragView(Context context) {
        this(context, null);
    }

    public DragView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_drag, this, true);
    }


    public void setParentWidth(int parentWidth) {
        this.parentWidth = parentWidth;
    }

    public void setParentHeight(int parentHeight) {
        this.parentHeight = parentHeight;
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取到手指处的横坐标和纵坐标
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

                if (t <= parentHeight - getHeight()) { //滑动顶部
                    t = parentHeight - getHeight();
                    b = t + getHeight();
                }

                if (t >= parentHeight - getHeight() / 5) { //滑动底部
                    t = parentHeight - getHeight() / 5;
                    b = t + getHeight();
                }
                //调用layout方法来重新放置它的位置
//                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) getLayoutParams();
//                params.height = parentHeight - t;

                layout(getLeft(), t, getRight(), b);

//                LinearLayout.LayoutParams pa = (LinearLayout.LayoutParams) scrollView.getLayoutParams();
//                pa.height = LayoutParams.MATCH_PARENT;
//                scrollView.setLayoutParams(pa);
//                dragInterface.getHeight(500);

                break;
            default:
                break;
        }

        return true;
    }

}
