package com.qhyccd.screenshot;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.qhyccd.R;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author tx
 * @date 2018/8/27
 */
public class ScreenshotActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnCommon;
    private Button btnScroll;
    private Button btnInflate;
    private RelativeLayout container;
    private LinearLayout part;
    private ScrollView scrollView;
//    private LinearLayout remain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screenshot);
        btnCommon = findViewById(R.id.btn_common);
        btnScroll = findViewById(R.id.btn_scroll);
        btnInflate = findViewById(R.id.btn_inflate);
        container = findViewById(R.id.container);
        scrollView = findViewById(R.id.scrollView);
        part = findViewById(R.id.part);
//        remain = findViewById(R.id.remain);
        btnCommon.setOnClickListener(this);
        btnScroll.setOnClickListener(this);
        btnInflate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_common:
                createBitmap(container);
                break;
            case R.id.btn_scroll:
                createBitmap2(part);
                break;
            case R.id.btn_inflate:
                View view = LayoutInflater.from(this).inflate(R.layout.view_inflate, null, false);
                createBitmap3(view, getScreenWidth(), getScreenHeight());
                break;
            default:
                break;
        }
    }

    public int getScreenHeight() {
        return getResources().getDisplayMetrics().heightPixels;
    }

    public int getScreenWidth() {
        return getResources().getDisplayMetrics().widthPixels;
    }

    private Bitmap createBitmap(View view) {
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        saveBitmap(bitmap);
        return bitmap;
    }

    public Bitmap createBitmap2(View v) {
        Bitmap bmp = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);
        c.drawColor(Color.WHITE);
        v.draw(c);
        saveBitmap(bmp);
        return bmp;
    }

    public Bitmap createBitmap3(View v, int width, int height) {
        int measuredWidth = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
        int measuredHeight = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);
        v.measure(measuredWidth, measuredHeight);
        v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
        Bitmap bmp = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);
        c.drawColor(Color.WHITE);
        v.draw(c);
        saveBitmap(bmp);
        return bmp;
    }

    public Bitmap getScrollViewBitmap(ScrollView scrollView) {
        int h = 0;
        Bitmap bitmap;
        for (int i = 0; i < scrollView.getChildCount(); i++) {
            h += scrollView.getChildAt(i).getHeight();
        }
        // 创建对应大小的bitmap
        bitmap = Bitmap.createBitmap(scrollView.getWidth(), h,
                Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        scrollView.draw(canvas);
        saveBitmap(bitmap);
        return bitmap;
    }

    private void saveBitmap(Bitmap bitmap) {
        FileOutputStream fos;
        try {
            File root = Environment.getExternalStorageDirectory();
            File file = new File(root, "test.png");
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void bitmapToFile(Bitmap bitmap) {
        FileOutputStream fos;
        try {
            boolean isHasSDCard = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
            if (isHasSDCard) {
                File root = Environment.getExternalStorageDirectory();
                File file = new File(root, "test.png");
                fos = new FileOutputStream(file);
            } else {
                throw new Exception("创建文件失败!");
            }
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
