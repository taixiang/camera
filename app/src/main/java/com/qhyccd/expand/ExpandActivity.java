package com.qhyccd.expand;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ExpandableListView;

import com.qhyccd.R;

/**
 * @author tx
 * @date 2018/6/29
 */
public class ExpandActivity extends Activity {

    private ExpandableListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand);
        listView = findViewById(R.id.expand);
        ExpandAdapter adapter = new ExpandAdapter(this);
        listView.setAdapter(adapter);
        listView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });

    }
}
