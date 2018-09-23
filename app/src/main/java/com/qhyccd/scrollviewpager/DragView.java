package com.qhyccd.scrollviewpager;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.qhyccd.R;

import java.util.ArrayList;
import java.util.List;

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

    private ViewPager viewPager;
    private List<TestFragment> list= new ArrayList<>();

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
        View view = LayoutInflater.from(context).inflate(R.layout.view_drag, this, true);
        viewPager = view.findViewById(R.id.view_pager);

    }


    public void setParentWidth(int parentWidth) {
        this.parentWidth = parentWidth;
    }

    public void setParentHeight(int parentHeight) {
        this.parentHeight = parentHeight;
    }

    public void setData(Context context,FragmentManager fm){
        list.add(new TestFragment());
        list.add(new TestFragment());
        list.add(new TestFragment());
        viewPager.setAdapter(new ScrollAdapter(fm));
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
            case MotionEvent.ACTION_UP:
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) getLayoutParams();
                params.setMargins(getLeft(), getTop(), 0, 0);
                break;
            default:
                break;
        }

        return true;
    }

    private class ScrollAdapter extends PagerAdapter {

        private FragmentManager fm;

        public ScrollAdapter(FragmentManager fm) {
            this.fm = fm;
        }

        @Override
        public int getCount() {
            return list.size();
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            int curPos = position;
            position = position % list.size();
            FragmentTransaction ft = fm.beginTransaction();
            TestFragment frag = list.get(position);

            if (!frag.isAdded()) {
                String name = frag.getClass().getName();
                ft.add(frag, name);
                ft.commitAllowingStateLoss();
                fm.executePendingTransactions();
            }
            View view = frag.getView();
            if (view == null) {
                return null;
            }
            if (view.getParent() == null) {
                container.addView(view);
            } else {
                ((ViewGroup) view.getParent()).removeView(view);
                container.addView(view);
            }
        frag.setData();
            return view;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

    }

}
