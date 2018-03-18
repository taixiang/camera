package com.qhyccd;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.qhyccd.www.mobilenote2.Globals;

/**
 * Created by tx on 2018/3/17.
 */

public class CustomSurface extends SurfaceView implements SurfaceHolder.Callback,Runnable{

    private SurfaceHolder mHolder;
    private Canvas mCanvas;//绘图的画布
    private boolean mIsDrawing;//控制绘画线程的标志位
    private Bitmap bitmap;


    public CustomSurface(Context context) {
        super(context);
        initView();
    }

    public CustomSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CustomSurface(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIsDrawing = true;
        new Thread(this).start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void run() {
        long start =System.currentTimeMillis();
//        Log.i("》》》》 "," start == "+start);
//        draw();
        while(mIsDrawing){
            draw();
            long end = System.currentTimeMillis();
//            Log.i("》》》》 "," end == "+end);

            if(end-start<100){
                try{
//                    Log.i("》》》》 "," sleep= "+(100-end+start));
                    Thread.sleep(100-end+start);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    private void initView() {
        mHolder = getHolder();//获取SurfaceHolder对象
        mHolder.addCallback(this);//注册SurfaceHolder的回调方法
        setFocusable(true);
        setFocusableInTouchMode(true);
//        this.setKeepScreenOn(true);
    }

    //绘图操作
    private void draw() {
        try {
            mCanvas = mHolder.lockCanvas();
//            mCanvas.drawColor(Color.WHITE);


            if(bitmap != null){

                int k = 0;
                int n = 0;
                for (int j = 0; j < Globals.PicHeight; j++) {
                    for (int i = 0; i < Globals.PicWidth; i++) {
                        Globals.bmpdata[k] = 0xff000000 + Globals.ImgDataX[n++] + (Globals.ImgDataX[n++] << 8) + (Globals.ImgDataX[n++] << 16);
                        k++;
                    }
                }

                bitmap.setPixels(Globals.bmpdata, 0, Globals.PicWidth, 0, 0, Globals.PicWidth, Globals.PicHeight);

                mCanvas.drawBitmap(bitmap,null,mHolder.getSurfaceFrame(),new Paint());
            }

            // draw sth绘制过程
        } catch (Exception e) {
        } finally {
            if (mCanvas != null)
                mHolder.unlockCanvasAndPost(mCanvas);//保证每次都将绘图的内容提交
        }
    }

    public void setData(Bitmap bitmap){

        this.bitmap = bitmap;
//        mCanvas.drawBitmap(bitmap,null,mHolder.getSurfaceFrame(),new Paint());
    }




}
