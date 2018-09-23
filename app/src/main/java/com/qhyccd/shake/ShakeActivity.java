package com.qhyccd.shake;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qhyccd.R;

/**
 * Created by tx on 2018/9/22.
 */

public class ShakeActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private ShakeSensorListener shakeListener;

    /**
     * 判断一次摇一摇动作
     */
    private boolean isShake = false;

    private ImageView imgHand;

    private ObjectAnimator anim;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);
        imgHand = findViewById(R.id.imgHand);
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        shakeListener = new ShakeSensorListener();
        sensorManager.registerListener(shakeListener,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_FASTEST);

        anim = ObjectAnimator.ofFloat(imgHand,"rotation",0f,45f,-30f,0f);
        anim.setDuration(500);
        anim.setRepeatCount(ValueAnimator.INFINITE);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(shakeListener);
    }

    private AlertDialog mAlertDialog;

    private void onShakeSingleCardClick() {
            if(mAlertDialog != null){
                mAlertDialog = null;
            }
            mAlertDialog = new AlertDialog.Builder(this).show();
            View view = LayoutInflater.from(this).inflate(R.layout.layout_single_card_dialog,null);
            mAlertDialog.setContentView(view);
            mAlertDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
            {
                @Override
                public void onCancel(DialogInterface dialog)
                {
                    cancelAndReShake();
                }
            });
            Window window = mAlertDialog.getWindow();
            window.setBackgroundDrawable(new ColorDrawable(0x00000000));
    }

    private void cancelAndReShake(){
        isShake = false;
        if(mAlertDialog != null){
            mAlertDialog.cancel();
        }
        anim.cancel();
    }


    private class ShakeSensorListener implements SensorEventListener {
        private static final int ACCELERATE_VALUE = 19;

        @Override
        public void onSensorChanged(SensorEvent event) {

            if (isShake) {
                return;
            }
            anim.start();

            float[] values = event.values;

            /**
             * 一般在这三个方向的重力加速度达到20就达到了摇晃手机的状态 x : x轴方向的重力加速度，向右为正 y :
             * y轴方向的重力加速度，向前为正 z : z轴方向的重力加速度，向上为正
             */
            float x = Math.abs(values[0]);
            float y = Math.abs(values[1]);
             float z = Math.abs(values[2]);

            if (x >= ACCELERATE_VALUE || y >= ACCELERATE_VALUE
                    || z >= ACCELERATE_VALUE) {
                playSound(ShakeActivity.this);
                vibrate( 500);
                isShake = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onShakeSingleCardClick();
                    }
                },1000);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    }



    MediaPlayer player = null;

    public void playSound(Context context) {
        try {
            if (player == null)
            {
                player = MediaPlayer.create(context,R.raw.shake_sound_male);
            }
            else {
                try {
                    player.release();
                    player = null;
                    player = MediaPlayer.create(context,R.raw.shake_sound_male);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            player.start();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (Exception e) {
        }
    }

    public void vibrate(long milliseconds) {
        Vibrator vibrator = (Vibrator)getSystemService(Service.VIBRATOR_SERVICE);
        vibrator.vibrate(milliseconds);
    }

}
