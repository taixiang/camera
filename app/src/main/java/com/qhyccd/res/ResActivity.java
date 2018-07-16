package com.qhyccd.res;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.qhyccd.R;

/**
 * @author tx
 * @date 2018/7/16
 */
public class ResActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_tool);
    }
}
