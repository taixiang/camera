package com.qhyccd.scrollviewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.qhyccd.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tx
 * @date 2018/7/31
 */
public class AnswerFragment extends Fragment {

    interface AnswerListener {
        //下一题
        void next();
    }

    private RelativeLayout container;
    private DragView dragView;
    private LinearLayout llOptions;

    /**
     * 选项
     */
    private List<OptionView> listOpts = new ArrayList<>();

    /**
     * 当前题目序号
     */
    private int curIndex;

    private AnswerListener answerListener;

    public void setAnswerListener(AnswerListener answerListener) {
        this.answerListener = answerListener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup vg, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_answer, vg, false);
        container = view.findViewById(R.id.container);
        dragView = view.findViewById(R.id.dragView);
        llOptions = view.findViewById(R.id.ll_opts);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        llOptions.removeAllViews();
        for (int i = 0; i < 4; i++) {
            OptionView view = new OptionView(getActivity());
            listOpts.add(view);
            llOptions.addView(view);
        }
        container.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int width = container.getWidth();
                int height = container.getHeight();
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) dragView.getLayoutParams();
                params.height = height * 4 / 5;
                params.bottomMargin = -params.height * 4 / 5;
                dragView.setLayoutParams(params);
                dragView.setParentWidth(width);
                dragView.setParentHeight(height);
                dragView.setData(getActivity(),getChildFragmentManager());
                container.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

}
