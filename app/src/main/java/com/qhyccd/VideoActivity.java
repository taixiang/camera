package com.qhyccd;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

import com.qhyccd.util.ImageLoader;
import com.qhyccd.util.VideoViewListener;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.model.VideoOptionModel;
import com.shuyu.gsyvideoplayer.utils.GSYVideoType;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * @author tx
 * @date 2018/4/9
 */
public class VideoActivity extends AppCompatActivity {

    private StandardGSYVideoPlayer player;
    private ImageView iv;
    private Timer timer;
    private TimerTask task;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            player.setUp("https://cms.bscwin.com/upload/201803/19/756a7bef-6513-487b-ae9d-c5d7dae644b3.mp4", true, "");
            player.startPlayLogic();
            player.setVisibility(View.VISIBLE);
            iv.setVisibility(View.GONE);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        player = findViewById(R.id.player);
        iv = findViewById(R.id.iv);


        GSYVideoType.setShowType(GSYVideoType.SCREEN_TYPE_DEFAULT);

        GSYVideoType.enableMediaCodec();
        GSYVideoType.enableMediaCodecTexture();

        VideoOptionModel videoOptionModel =
                new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "framedrop", 27);
        List<VideoOptionModel> list = new ArrayList<>();
        list.add(videoOptionModel);
        GSYVideoManager.instance().setOptionModelList(list);

        player.setUp("https://cms.bscwin.com/upload/201803/19/756a7bef-6513-487b-ae9d-c5d7dae644b3.mp4", true, "");
        player.startPlayLogic();
        timer = new Timer();
        player.setStandardVideoAllCallBack(new VideoViewListener() {
            @Override
            public void onAutoComplete(String url, Object... objects) {
                super.onAutoComplete(url, objects);
                Log.i("》》》", "播放结束");
                ImageLoader.loadImage(VideoActivity.this,"http://www.kaoyanvip.cn/static/img/index/banner2/bg.jpg",iv);
                iv.setVisibility(View.VISIBLE);
                startNewTask();
                player.setVisibility(View.GONE);
            }
        });
    }

    private void startNewTask() {
        if (task != null) {
            task.cancel();
            task = null;
        }
        task = new TimerTask() {
            @Override
            public void run() {
                Log.i("》》》", "重新播放");
                handler.sendEmptyMessage(0);
            }
        };
        timer.schedule(task, 3000);
    }

    @Override
    protected void onResume() {
        getCurPlay().onVideoResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        getCurPlay().release();
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
        if (task != null) {
            task.cancel();
        }
    }

    private GSYVideoPlayer getCurPlay() {
        if (player.getFullWindowPlayer() != null) {
            return player.getFullWindowPlayer();
        }
        return player;
    }


}
