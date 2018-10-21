package com.qhyccd.svg;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.qhyccd.R;

/**
 * Created by tx on 2018/10/19.
 */

public class SVGActivity extends AppCompatActivity {


    private ImageView iv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svg);
        iv = findViewById(R.id.img);

        String url = "http://equation.kaoyanvip.cn/?mml=%3Cmath%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F1998%2FMath%2FMathML%22%3E%3Cmi%3Eu%3C%2Fmi%3E%3Cmo%3E%2C%3C%2Fmo%3E%3Cmsup%3E%3Cmi%3Eo%3C%2Fmi%3E%3Cmn%3E2%3C%2Fmn%3E%3C%2Fmsup%3E%3C%2Fmath%3E";

        String url2 = "http://www.clker.com/cliparts/u/Z/2/b/a/6/android-toy-h.svg";
        Uri uri = Uri.parse(url);

        GlideToVectorYou
                .init()
                .with(this)
                .load(Uri.parse(url), iv);

//        Glide.with(this).load("https://user-gold-cdn.xitu.io/2018/10/8/166542172d8f837d?imageView2/0/w/1280/h/960/format/webp/ignore-error/1").into(iv);
    }
}
