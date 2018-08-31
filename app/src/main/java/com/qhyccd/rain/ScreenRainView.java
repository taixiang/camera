package com.qhyccd.rain;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.qhyccd.R;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by LiShen on 2018/4/19.
 * Screen full of RAINDROPS(image)
 */

public class ScreenRainView extends View {
        private float maxScale = 1.0f;
    private float minScale = 1.0f;
    private int startPadding = 0;
    private int rainDuration = 2500;
    private int appearAllDuration = 2000;
    private int appearInterval = 250;
    private boolean destroyAfterRainEnds = true;

    /**
     * 设置图片最大放大倍数
     *
     * @param maxScale 放大倍数 默认1 不放大
     */
    public void setRainMaxScale(float maxScale) {
        if (maxScale >= 1.0f && maxScale <= 10.0f) {
            this.maxScale = maxScale;
        }
    }

    /**
     * 设置图片最小缩小倍数
     *
     * @param minScale 默认1 不缩小
     */
    public void setRainMinScale(float minScale) {
        if (minScale >= 0.1f && minScale <= 10.0f) {
            this.minScale = minScale;
        }
    }

    /**
     * 设置下落图片在顶部Padding 越大越靠近中间
     *
     * @param startPadding 单位为DP
     */
    public void setRainStartPadding(int startPadding) {
        if (startPadding >= 0 && startPadding <= 150) {
            this.startPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, startPadding, getContext().getResources().getDisplayMetrics());
        }
    }

    /**
     * 设置下落图片出现的间隔 间隔越大图片越少
     *
     * @param appearInterval 间隔
     */
    public void setRainAppearInterval(int appearInterval) {
        if (appearInterval < 500 && appearInterval >= 50) {
            this.appearInterval = appearInterval;
        }
    }

    /**
     * 设置图片出现的总时间 越长图片越多 不要超过 rainDuration
     *
     * @param appearAllDuration 总时间
     */
    public void setRainAppearDuration(int appearAllDuration) {
        if (appearAllDuration >= 500 && appearAllDuration <= 60000) {
            this.appearAllDuration = appearAllDuration;
        }
    }

    /**
     * 设置下落动画总时间
     *
     * @param rainDuration 总时间
     */
    public void setRainDuration(int rainDuration) {
        if (rainDuration >= 500 && rainDuration <= 60000) {
            this.rainDuration = rainDuration;
        }
    }

    /**
     * 设置动画结束后是否摧毁 默认摧毁（只能播放一次）
     *
     * @param destroyAfterRainEnds 是否摧毁
     */
    public void setRainDestroyAfterEnds(boolean destroyAfterRainEnds) {
        this.destroyAfterRainEnds = destroyAfterRainEnds;
    }

    public void addRaindropImages(@DrawableRes int... images) {
        for (int image : images) {
            addRaindropImage(image);
        }
    }

    public void addRaindropImage(@DrawableRes int image) {
        addRaindropImage(BitmapFactory.decodeResource(getResources(), image));
    }

    /**
     * 加入图片库
     *
     * @param image 图片的bitmap
     */
    public void addRaindropImage(Bitmap image) {
        raindropImages.add(image);
    }

    private Random random;
    private Matrix matrix;
    private Matrix matrix2;
    private Paint paint;
    private boolean RAINING;
    private long startTime;
    private List<Bitmap> raindropImages;
    private List<RainDrop> raindrops;

    private Bitmap bitmap;
    public ScreenRainView(Context context) {
        super(context);
        init();
    }

    public ScreenRainView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScreenRainView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (RAINING) {
            boolean rainSuccess = false;
            long totalRainTime = System.currentTimeMillis() - startTime;
            if (raindrops.size() > 0) {
                for (RainDrop rainDrop : raindrops) {
//                    Log.i("》》》》》》》》》》 ", " totalRainTime ==" + totalRainTime + " rainDrop.appearTime== " + rainDrop.appearTime + " matrix.reset() ====  " + rainDrop.id);
                    if (rainDrop.image.isRecycled()
                            || rainDrop.y > getHeight()
                            || totalRainTime < rainDrop.appearTime) {
//                        Log.i("》》》》》》》》》》 ", " continue ====  ");
                        continue;
                    }
//                    Log.i("》》》》》》》》》》 ", " matrix.reset() ====  " + rainDrop.id);
                    matrix.reset();
                    matrix.setScale(rainDrop.scale, rainDrop.scale);
                    rainDrop.x = (rainDrop.x + rainDrop.velocityX);
                    rainDrop.y = (rainDrop.y + rainDrop.velocityY);
                    Log.i("》》》》》》》》》》 ", " rainDrop.scale ====  " + rainDrop.scale + " id====== " + rainDrop.id );
                    matrix.postTranslate(rainDrop.x, rainDrop.y);
                    canvas.drawBitmap(rainDrop.image, matrix, paint);
                    rainSuccess = true;

//                    matrix2.reset();
//                    matrix2.postTranslate(0, 10 * rainDrop.id);
//                    canvas.drawBitmap(bitmap, matrix2, paint);

                }
            }
//            Log.i("》》》》》》》》》》 ", " rainSuccess ====  " + rainSuccess);
            if (!rainSuccess) {
                if (destroyAfterRainEnds) {
//                    Log.i("》》》》  ", "destroy  ");
                    destroy();
                } else {
//                    Log.i("》》》》  ", "stop  ");
                    stop();
                }
            } else {
//                Log.i("》》》》》》》》》》 ", " postInvalidate ====  ");
                postInvalidate();
            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.i("》》》》  ", "onDetachedFromWindow  ");
        destroy();
    }

    private void init() {
        setVisibility(GONE);
        setWillNotDraw(false);
        setFocusable(false);

        random = new Random();
        matrix = new Matrix();
        matrix2 = new Matrix();
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        raindrops = new ArrayList<>();
        raindropImages = new ArrayList<>();

        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

    }

    public void start() {
        stop();
        setVisibility(VISIBLE);

        post(new Runnable() {
            @Override
            public void run() {
                prepareRaindrops();
                RAINING = true;
                invalidate();
            }
        });
    }

    public void stop() {
        RAINING = false;
        setVisibility(GONE);
    }

    public void destroy() {
        stop();
        for (Bitmap image : raindropImages) {
            if (!image.isRecycled()) {
                image.recycle();
            }
        }
        raindropImages.clear();
    }

    private void prepareRaindrops() {
        startTime = System.currentTimeMillis();
        raindrops.clear();
        if (appearAllDuration > rainDuration) {
            appearAllDuration = rainDuration;
        }
        int currentDuration = 0;
        float range = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1.5f, getResources().getDisplayMetrics());
        int type = raindropImages.size();
        int i = 0;
        while (currentDuration < appearAllDuration && raindropImages.size() > 0) {
            RainDrop rainDrop = new RainDrop();
            rainDrop.image = raindropImages.get(i % type);
            float scale = 1f;
            if (!(minScale == maxScale && minScale == 1.0f)) {
                if (maxScale > minScale) {
                    BigDecimal maxPercent = BigDecimal.valueOf((maxScale * 100f));
                    BigDecimal minPercent = BigDecimal.valueOf((minScale * 100f));
                    BigDecimal scaleSeed = BigDecimal.valueOf(random.nextInt(maxPercent.subtract(minPercent).intValue()));
                    scale = minPercent.add(scaleSeed).divide(new BigDecimal(100), 1, BigDecimal.ROUND_HALF_UP).floatValue();
                }
            }
            rainDrop.scale = scale;
            int randomXDeviation = 1;
            try {
                randomXDeviation = random.nextInt(getWidth() - (int) (rainDrop.image.getWidth() * scale) - startPadding * 2);
            } catch (Exception ignore) {
            }
            rainDrop.x = randomXDeviation + startPadding;
            rainDrop.y = (int) -Math.ceil(rainDrop.image.getHeight() * scale);
            float height = getHeight() + (-rainDrop.y);
            int velocityY = (int) (height * 16 / (float) rainDuration);
            rainDrop.velocityY = velocityY == 0 ? 1 : velocityY;
            rainDrop.velocityX = Math.round(random.nextFloat() * range - range / 2f);
            rainDrop.appearTime = currentDuration;
            rainDrop.id = i;
            raindrops.add(rainDrop);
//            Log.i("》》》》》  ", "  rainDrop.x ===  " + rainDrop.x + "  rainDrop.y===  " + rainDrop.y +
//                    "  rainDrop.velocityX=== " + rainDrop.velocityX + "  rainDrop.velocityY===  " + rainDrop.velocityY);
//            Log.i("》》》》》  ", "  size ===  " + raindrops.size());
            currentDuration += random.nextInt(appearInterval);
//            Log.i("》》》》》  ", "  currentDuration ===  " + currentDuration);
            i++;
        }
    }

    private class RainDrop {
        private int id;
        private int appearTime;
        private Bitmap image;
        private float scale;
        private int x;
        private int y;
        private int velocityX;
        private int velocityY;
    }
}