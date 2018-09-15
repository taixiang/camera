package com.qhyccd.loading;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.qhyccd.R;

/**
 * @author tx
 * @date 2018/9/15
 */
public class LoadingView extends LinearLayout {

    private ProgressBar progressBar;
    private TextView tv;
    private ImageView iv;
    private Context context;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(context).inflate(R.layout.loading_view, this, true);
        progressBar = view.findViewById(R.id.progressBar);
        tv = findViewById(R.id.tv);
        iv = findViewById(R.id.iv);
    }

    /**
     * loading
     */
    public void showLoading() {
        iv.setVisibility(GONE);
        progressBar.setVisibility(VISIBLE);
    }

    /**
     * 成功
     */
    public void showSuccess() {
        iv.setImageResource(R.mipmap.load_success);
        iv.setVisibility(View.VISIBLE);
        progressBar.setVisibility(GONE);
    }

    /**
     *失败
     */
    public void showFail() {
        iv.setImageResource(R.mipmap.load_fail);
        iv.setVisibility(View.VISIBLE);
        progressBar.setVisibility(GONE);
    }

    /**
     * 提示文字
     *
     * @param txt string
     */
    public void setText(String txt) {
        tv.setText(txt);
    }

    /**
     * 提示文字
     */
    public void setText(@StringRes int txtId) {
        tv.setText(txtId);
    }
}
