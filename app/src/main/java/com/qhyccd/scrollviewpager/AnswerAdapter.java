package com.qhyccd.scrollviewpager;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

/**
 * @author tx
 * @date 2018/7/31
 */
public class AnswerAdapter extends PagerAdapter {

    private List<AnswerFragment> list;
    private FragmentManager fm;
    private List<String> dataList;

    public AnswerAdapter(FragmentManager fm, List<AnswerFragment> list, List<String> dataList) {
        this.fm = fm;
        this.list = list;
        this.dataList = dataList;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int curPos = position;
        position = position % list.size();
        FragmentTransaction ft = fm.beginTransaction();
        AnswerFragment frag = list.get(position);

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
//        frag.setData(curPos, dataList.get(curPos));
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
    }

    //    @Override
//    public Fragment getItem(int position) {
//        return list.get(position);
//    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}
