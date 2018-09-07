package com.qhyccd.expandTextView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qhyccd.R;

/**
 * @author tx
 * @date 2018/9/4
 */
public class ExpandTextView extends LinearLayout {

    private TextView tvContent;
    private TextView tvTip;

    public ExpandTextView(Context context) {
        this(context, null);
    }

    public ExpandTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExpandTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.expand_text_view, this, true);
        tvContent = view.findViewById(R.id.tv_content);
        tvTip = view.findViewById(R.id.tv_tip);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int lineCount = tvContent.getLineCount();
        Log.i("》》》》》 ","lineCount === "+lineCount);
        if(lineCount<4){
            return;
        }
        tvTip.setVisibility(VISIBLE);


    }
}
