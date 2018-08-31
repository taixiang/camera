package com.qhyccd.rain;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.qhyccd.R;

/**
 * @author tx
 * @date 2018/8/23
 */
public class TestActivity extends AppCompatActivity {

    private TestView testView;
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
        testView = findViewById(R.id.testView);
        button =findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testView.setRun(true);
            }
        });


    }
}
