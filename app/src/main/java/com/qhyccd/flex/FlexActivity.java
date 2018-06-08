package com.qhyccd.flex;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.qhyccd.R;

/**
 * @author tx
 * @date 2018/6/4
 */
public class FlexActivity extends Activity implements View.OnClickListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flex);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.flexDirection:
                Intent intent1 = new Intent(this, FlexDirectionActivity.class);
                startActivity(intent1);
                break;
            case R.id.flexWrap:
                Intent intent2 = new Intent(this, FlexWrapActivity.class);
                startActivity(intent2);
                break;
            case R.id.justifyContent:
                Intent intent3 = new Intent(this, JustifyContentActivity.class);
                startActivity(intent3);
                break;
            case R.id.alignItems:
                Intent intent4 = new Intent(this, AlignItemsActivity.class);
                startActivity(intent4);
                break;
            case R.id.alignContent:
                Intent intent5 = new Intent(this, AlignContentActivity.class);
                startActivity(intent5);
                break;
            default:
                break;
        }
    }
}
