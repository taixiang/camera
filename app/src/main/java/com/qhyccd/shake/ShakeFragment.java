package com.qhyccd.shake;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qhyccd.R;

import java.io.IOException;
import java.util.List;

public class ShakeFragment extends Fragment
{
    private SensorManager sensorManager;
    private SensorEventListener shakeListener;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog mAlertDialog;
    private boolean isRefresh = false;
    public static final String ARG_ACTIVITY_ID = "activity_id";
    private String id;
    private String description;
    private String leftTimes;

    /**
     * 摇一摇活动信息
     */
    public static final String REQUEST_GET_SHAKE_MSG_TAG = "get_shake_msg";

    /**
     * 摇一摇
     */
    public static final String REQUEST_GET_SHAKE_TAG = "get_shake";

    public static final String REQUEST_GET_SHAKE_AGAIN_TAG = "get_shake_again";

    int leftChoice;

    RelativeLayout rlyBack;

    ImageView imgHand;

    TextView tvChoice;

    /**
     * 设置旋转动画
     */
    final RotateAnimation animation = new RotateAnimation(0f, 45f, Animation.RELATIVE_TO_SELF,
                                                          0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

    final RotateAnimation animation2 = new RotateAnimation(0.5f, 45f, Animation.RELATIVE_TO_PARENT,
                                                           0f, Animation.RELATIVE_TO_PARENT, 0f);

    public static ShakeFragment newInstance(String id){
        ShakeFragment fragment = new ShakeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_ACTIVITY_ID, id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.activity_shake,null);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        id = getArguments().getString(ARG_ACTIVITY_ID);
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        shakeListener = new ShakeSensorListener();
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animation2.startNow();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        doGetShakeMsg();
        animation.setDuration(500);//设置动画持续时间
        animation.setRepeatCount(Animation.INFINITE);
        animation2.setDuration(500);//设置动画持续时间
        imgHand.setAnimation(animation);
    }

    private void showChoice(String num){
        String totalChoice = String.format("您还有%1$s次机会",num);
        tvChoice.setText(totalChoice);
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(totalChoice);
        stringBuilder.setSpan(new ForegroundColorSpan(0xFFe22828),3,3+num.length(),Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        tvChoice.setText(stringBuilder);
        tvChoice.setVisibility(View.VISIBLE);
    }

    void rlyBack(){
    }

    void showTip(){
        onShakeTipClcik();
    }

    void onShakeTipClcik() {
        try {
            isRefresh = true;
            mAlertDialog = new AlertDialog.Builder(getActivity()).show();
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_shake_tip_dialog, null);
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
            TextView txtDescription = (TextView) view.findViewById(R.id.tvContext);
            txtDescription.setText(description);
        } catch (Exception e) {

        }
    }
     //活动结束
    private static final String ACTIVITY_END = "get_shake_end";

    //啥都没摇到
    private static final String SHAKE_NONE = "get_shake_none";

    //次数用完
    private static final String CHOICE_NONE = "get_left_none";

    //人数过多
    private static final String TRY_AGAIN = "try_again";

    /**
     * 单个红包或者没有的情况
     */
    private void onShakeSingleCardClick(String award,String message) {
        try
        {
            if(mAlertDialog != null){
                mAlertDialog = null;
            }
            mAlertDialog = new AlertDialog.Builder(getActivity()).show();
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_single_card_dialog,null);
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
            TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            ImageView ivCard = (ImageView) view.findViewById(R.id.iv_card);
            TextView tvAward = (TextView) view.findViewById(R.id.tv_award);
            TextView txtTip = (TextView) view.findViewById(R.id.txt);
            RelativeLayout popButton = (RelativeLayout) view.findViewById(R.id.pop_btn);
            if(ACTIVITY_END.equals(award)){
                //"活动已结束！等下次再来吧"
                tvTitle.setText(message);
                ivCard.setImageResource(R.mipmap.shake_pop_card_end);
            }else if(SHAKE_NONE.equals(award)){
                //"啥都没摇到！再接再厉吧！"
                tvTitle.setText(message);
                ivCard.setImageResource(R.mipmap.shake_pop_card_none);
            }else if(CHOICE_NONE.equals(award)){
                //"已经没有次数了，明天再来吧！"
                tvTitle.setText(message);
                ivCard.setImageResource(R.mipmap.shake_pop_card_numend);
            }else if(TRY_AGAIN.equals(award)){
                tvTitle.setText("sorry,摇一摇人数过多,请重新尝试!");
                ivCard.setImageResource(R.mipmap.shake_pop_card_people);
            }else {
                txtTip.setVisibility(View.VISIBLE);
                tvTitle.setText(message);
//                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(message);
//                spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.RED), 6,6+award.length() , Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//                tvTitle.setText(spannableStringBuilder);
                String txtAward = award + "元";
                tvAward.setText(txtAward);
                tvAward.setVisibility(View.VISIBLE);
            }
            popButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    cancelAndReShake();
                }
            });
        }  catch (Exception e){

        }
    }

    /**
     * 同时获得两个红包
     */
    void onShakeBothCardClick(List<ShakeMsgItem.AwardItem> awardItems){
        try
        {
            if(mAlertDialog != null){
                mAlertDialog = null;
            }
            mAlertDialog = new AlertDialog.Builder(getActivity()).show();
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_both_card_dialog,null);
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
            RelativeLayout popButton = (RelativeLayout) view.findViewById(R.id.pop_btn);
            TextView tvFirst = (TextView) view.findViewById(R.id.tvFirst);
            TextView tvSecond = (TextView) view.findViewById(R.id.tvSecond);
            tvFirst.setText(awardItems.get(0).awardValue+"元");
            tvFirst.setText(awardItems.get(1).awardValue+"元");
            popButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    cancelAndReShake();
                }
            });
        }  catch (Exception e){

        }
    }

    private void cancelAndReShake(){
        isRefresh = false;
        if(mAlertDialog != null){
          mAlertDialog.cancel();
        }
        animation.cancel();
        animation2.cancel();
    }

    @Override
    public void onResume()
    {
        sensorManager.registerListener(shakeListener,
                                       sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                                       SensorManager.SENSOR_DELAY_FASTEST);
        super.onResume();
    }

    @Override
    public void onPause()
    {
        sensorManager.unregisterListener(shakeListener);
        super.onPause();
    }

    /**
     * 获取摇一摇信息
     */
    private void doGetShakeMsg(){
    }

    private void doGetShakeMsgTest(){
    }


    /**
     * 摇一摇
     */
    private void doShake(){
    }

    private class ShakeSensorListener implements SensorEventListener {
        private static final int ACCELERATE_VALUE = 19;

        @Override
        public void onSensorChanged(SensorEvent event) {

            // Log.e("zhengyi.wzy", "type is :" + event.sensor.getType());

            // 判断是否处于刷新状态(例如微信中的查找附近人)
            if (isRefresh) {
                return;
            }

            float[] values = event.values;

            /**
             * 一般在这三个方向的重力加速度达到20就达到了摇晃手机的状态 x : x轴方向的重力加速度，向右为正 y :
             * y轴方向的重力加速度，向前为正 z : z轴方向的重力加速度，向上为正
             */
            float x = Math.abs(values[0]);
            float y = Math.abs(values[1]);
            // float z = Math.abs(values[2]);
            float z = 0;

//            Log.e("Davis", "x is :" + x + " y is :" + y + " z is :" + z);
            /** 开始动画 */
            animation.startNow();


            if (x >= ACCELERATE_VALUE || y >= ACCELERATE_VALUE
                    || z >= ACCELERATE_VALUE) {
                doGetShakeMsgTest();
                playSound(getActivity());
                VibratorHelper.Vibrate(getActivity(), 500);
                isRefresh = true;
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    }

    // 播放自定义的声音
    MediaPlayer player = null;

    public void playSound(Context context) {
        try {
            if (player == null)
            {
                player = new MediaPlayer();
            }
            else {
                try {
                    player.release();
                    player = null;
                    player = new MediaPlayer();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            String uri = "android.resource://" + context.getPackageName() + "/"
                    + R.raw.shake_sound_male;
            Uri no = Uri.parse(uri);
            player.setDataSource(context, no);
            player.prepare();
            player.start();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
        }
    }
}
