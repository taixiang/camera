package com.qhyccd;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.qhyccd.www.mobilenote2.Globals;
import com.qhyccd.www.mobilenote2.QhyFunc;
import com.qhyccd.www.mobilenote2.usbfumc;


public class MainForm extends AppCompatActivity {

    private Button BUTTON1;
    private Button BUTTON2;
    private Button BUTTON3;
    private Button BUTTON4;
    private Button BUTTON5;
    private ImageView ivPre;
    private CustomSurface surfaceView;

    private boolean onDisplay = false;
    private boolean onButton = false;

    private CustomImageView iv;

    private Bitmap bmp1 = null;

    private usbfumc mUSBManager;

    QhyFunc cQhyFunc = new QhyFunc();

    //we use a timer to display the image
    CountDownTimer cdt = new CountDownTimer(10000, 20) {

        public void onTick(long millisUntilFinished) {

            //to avoid previous task is not compelete and new task is in
            if (onDisplay == true)
                return;
            onDisplay = true;


            int k = 0;
            int n = 0;
            for (int j = 0; j < Globals.PicHeight; j++) {
                for (int i = 0; i < Globals.PicWidth; i++) {
                    Globals.bmpdata[k] = 0xff000000 + Globals.ImgDataX[n++] + (Globals.ImgDataX[n++] << 8) + (Globals.ImgDataX[n++] << 16);
                    k++;
                }
            }

            //bmp1.copyPixelsFromBuffer (Globals.ImgData);
            bmp1.setPixels(Globals.bmpdata, 0, Globals.PicWidth, 0, 0, Globals.PicWidth, Globals.PicHeight);
            iv.setImageBitmap(bmp1);
//            surfaceView.setData(bmp1);
            onDisplay = false;
        }

        @Override
        public void onFinish() {
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_form);

        //获得各类控件句柄

        BUTTON1 = (Button) findViewById(R.id.button1);
        BUTTON2 = (Button) findViewById(R.id.button2);
        BUTTON3 = (Button) findViewById(R.id.button3);
        BUTTON4 = (Button) findViewById(R.id.button4);
        BUTTON5 = (Button) findViewById(R.id.button5);
        ivPre = (ImageView) findViewById(R.id.iv_preview);
        surfaceView = findViewById(R.id.surface);

        ivPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainForm.this, SecondActivity.class);
                startActivity(intent);
            }
        });


        Globals.ImgDataX = new byte[5000 * 5000 * 3];
        Globals.bmpdata = new int[5000 * 5000];


        Globals.PicWidth = 640;
        Globals.PicHeight = 480;
        Globals.CCDType = 1;


        iv = (CustomImageView) findViewById(R.id.imageView1);
        iv.setMaxHeight(Globals.PicWidth);
        iv.setMaxWidth(Globals.PicHeight);


        bmp1 = Bitmap.createBitmap(Globals.PicWidth, Globals.PicHeight, Bitmap.Config.ARGB_8888);


        mUSBManager = new usbfumc(this);


        setTitle("QHY5L-II Android Capture");


        BUTTON1.setOnClickListener(new View.OnClickListener() {
                                       public void onClick(View v) {
                                           onButton = true;
                                           if (Globals.Operator == -1) {
                                               if (mUSBManager.OpenFCamera(true) == Globals.QHYCCD_SUCCESS) {
                                                   Log.e("QHYCCD download camera ", "OK");
                                                   Log.e("QHYCCD download camera ", "OK");
                                                   Log.e("QHYCCD download camera ", "OK");
                                                   Log.e("QHYCCD download camera ", "OK");
                                                   Log.e("QHYCCD download camera ", "OK");
                                                   Log.e("QHYCCD download camera ", "OK");
                                                   Globals.Operator = 1;
                                               }
                                           }
                                           onButton = false;
                                           Log.e("QHYCCD | 11111", String.valueOf(Globals.Operator));
                                       }
                                   }
        );


        BUTTON2.setOnClickListener(new View.OnClickListener() {
                                       public void onClick(View v) {
                                           if (onButton == false) {
                                               onButton = true;
                                               if (Globals.Operator == 1) {
                                                   if (mUSBManager.OpenFCamera(false) == Globals.QHYCCD_SUCCESS) {
                                                       Log.e("QHYCCD open camera ", "OK");
                                                       Log.e("QHYCCD open camera ", "OK");
                                                       Log.e("QHYCCD open camera ", "OK");
                                                       Log.e("QHYCCD open camera ", "OK");
                                                       Log.e("QHYCCD open camera ", "OK");
                                                       Log.e("QHYCCD open camera ", "OK");
                                                       Globals.Operator = 2;
                                                   }
                                               }

                                               onButton = false;

                                           }

                                       }
                                   }
        );


        BUTTON3.setOnClickListener(new View.OnClickListener() {
                                       public void onClick(View v) {
                                           if (onButton == false)

                                           {
                                               onButton = true;
                                               if ((Globals.Operator == 2) || (Globals.Operator == 4)) {
                                                   cdt.start();
                                                   surfaceView.setData(bmp1);
                                                   cQhyFunc.SetLiveParameter();
                                                   Globals.Operator = 3;
                                               }
                                               onButton = false;
                                           }

                                       }

                                   }
        );


        BUTTON4.setOnClickListener(new View.OnClickListener() {

                                       public void onClick(View v) {
                                           Log.i("x == ", iv.getWidth() + "");
//                                           Log.i("y == ", y + "");


                                           Bitmap map = Bitmap.createBitmap(((BitmapDrawable) ((ImageView) iv).getDrawable()).getBitmap(), 0, 0, 144, 100);
                                           if (map != null) {
                                               ivPre.setImageBitmap(map);
                                           }

//                                           if (onButton == false)
//
//                                           {
//                                               onButton = true;
//                                               if (Globals.Operator == 3) {
//
//                                                   cQhyFunc.UnInitializeLiveMode();
//                                                   Globals.Operator = 4;
//                                               }
//                                               onButton = false;
//                                           }

                                       }


                                   }
        );

        BUTTON5.setOnClickListener(new View.OnClickListener() {


                                       public void onClick(View v) {

                                           cQhyFunc.QHYCCDQuit();

                                       }


                                   }
        );

        final int widthScreen = getWindowManager().getDefaultDisplay().getWidth();


        final int heightScreen = DensityUtil.dpToPx(this, 291);

        Log.i("》》》》 width == ", widthScreen + "");

        Log.i("》》》》 height == ", heightScreen + "");

        iv.setListener(new CustomImageView.OnMoveListener() {
            @Override
            public void getXy(float x, float y) {
                Log.i("》》》 x == ", x + "");
                Log.i("》》》》 y == ", y + "");
                Bitmap sourceBmp = ((BitmapDrawable) (iv).getDrawable()).getBitmap();
                if (x + 10 > sourceBmp.getWidth()) {
                    x = sourceBmp.getWidth() - 10;
                } else if (x < 10) {
                    x = 10;
                }
                if (y + 10 > sourceBmp.getHeight()) {
                    y = sourceBmp.getHeight() - 10;
                } else if (y < 10) {
                    y = 10;
                }
                Bitmap bmp = Bitmap.createBitmap(sourceBmp, (int) x - 10, (int) y - 10, 20, 20);
                ivPre.setImageBitmap(bmp);
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bmp1 != null) {
            bmp1.recycle();
            bmp1 = null;
        }
        if (cdt != null) {
            cdt.cancel();
            cdt = null;
        }
    }

    static {
        if (true) {
            System.loadLibrary("QhyJNI");
        }
    }


}







