package com.qhyccd.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;

import com.qhyccd.R;

/**
 * @author tx
 * @date 2018/6/23
 */
public class ViewStubActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewstub);
        ViewStub viewstub = findViewById(R.id.viewstub);
        viewstub.inflate();

    }
}
