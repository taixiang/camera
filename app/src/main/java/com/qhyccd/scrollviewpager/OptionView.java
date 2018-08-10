package com.qhyccd.scrollviewpager;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import com.qhyccd.R;


/**
 * @author tx
 * @date 2018/8/1
 * 选项
 */
public class OptionView extends ConstraintLayout {

    public OptionView(Context context) {
        this(context, null);
    }

    public OptionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OptionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_option, this, true);
    }

}
