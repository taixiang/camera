package com.qhyccd.scrollviewpager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import com.qhyccd.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tx
 * @date 2018/7/31
 * 答题
 */
public class AnswerActivity extends AppCompatActivity implements AnswerFragment.AnswerListener, ViewPager.OnPageChangeListener {

    /**
     * 刷题
     */
    public static int TYPE_BRUSH = 0;
    /**
     * 考试
     */
    public static int TYPE_EXAM = 1;

    /**
     * 倒计时
     */
    private CountDownTimer timer;
    private SimpleDateFormat format;
    /**
     * 当前剩余时间
     */
    private long curTime = 10 * 1000;

    /**
     * 0是刷题 1是考试
     */
    private int type = 0;

    private ViewPager viewPager;

    /**
     * 答题
     *
     * @param type 0是刷题 1是考试
     */
    public static void actAnswer(Activity activity, int type, String title) {
        Intent intent = new Intent(activity, AnswerActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("title", title);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        viewPager = findViewById(R.id.viewPager);
        List<AnswerFragment> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            AnswerFragment fragment = new AnswerFragment();
            fragment.setAnswerListener(this);
            list.add(fragment);
        }
        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            dataList.add(getString(R.string.str_test));
        }

        viewPager.setAdapter(new AnswerAdapter(getSupportFragmentManager(), list, dataList));
        viewPager.addOnPageChangeListener(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewPager.removeOnPageChangeListener(this);
    }

    /**
     * 下一题
     */
    @Override
    public void next() {
        int curIndex = viewPager.getCurrentItem() + 1;
        viewPager.setCurrentItem(curIndex);
    }


    /**
     * viewpager滑动
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
