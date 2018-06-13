package com.qhyccd.drag;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.almeros.android.multitouch.RotateGestureDetector;
import com.qhyccd.R;

import static android.content.ContentValues.TAG;

/**
 * Created by tx on 2018/6/12.
 */

public class DragActivity extends Activity {

    private DragView view;
    private RotateGestureDetector mRotateGestureDetector;
    private float mRotationDegrees = 0.f;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag);
        view = findViewById(R.id.dragView);
        mRotateGestureDetector = new RotateGestureDetector(this,mSimpleOnRotateGestureListener);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mRotateGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private RotateGestureDetector.SimpleOnRotateGestureListener mSimpleOnRotateGestureListener = new RotateGestureDetector.SimpleOnRotateGestureListener(){

        @Override
        public boolean onRotate(RotateGestureDetector detector) {
            mRotationDegrees = -detector.getRotationDegreesDelta()+mRotationDegrees;
            mRotationDegrees = mRotationDegrees % 360;
            view.setRotation(mRotationDegrees);
            view.invali();
            Log.i("》》》》》》  ", "onRotate: "+detector.getRotationDegreesDelta()+"----"+mRotationDegrees);
            return true;
        }
    };
}
