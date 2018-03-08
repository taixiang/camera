
/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.qhyccd.www.mobilenote2;

import java.nio.ByteBuffer;


public class Globals
  {

    public static int Operator = -1;
    public static int CCDType;

    public static byte[] ImgDataX;
    public static int[] bmpdata;

    static ByteBuffer ImgData = null;
    public static int PicWidth;
    public static int PicHeight;
    static int IsBuffer;



    static final int BAYER_GB = 1;
    static final int BAYER_GR = 2;
    static final int BAYER_BG = 3;
    static final int BAYER_RG = 4;

    static final int QHYCCD_READ_DIRECTLY = 0x2001;

    static final int QHYCCD_DELAY_200MS = 0x2000;


    /**
     * It means the camera using GiGaE transfer data */
    static final int QHYCCD_QGIGAE = 7;

    /**
     * It means the camera using usb sync transfer data */
    static final int QHYCCD_USBSYNC = 6;

    /**
     * It means the camera using usb async transfer data */
    static final int QHYCCD_USBASYNC = 5;

    /**
     * It means the camera is color one */
    static final int QHYCCD_COLOR = 4;

    /**
     * It means the camera is mono one*/
    static final int QHYCCD_MONO = 3;

    /**
     * It means the camera has cool function */
    static final int QHYCCD_COOL = 2;

    /**
     * It means the camera do not have cool function */
    static final int QHYCCD_NOTCOOL = 1;

    /**
     * camera works well */
    public static final int QHYCCD_SUCCESS = 0;

    /**
     * Other error */
    static final int QHYCCD_ERROR = 0xFFFFFFFF;


    static final int CONTROL_BRIGHTNESS = 0; //!< image brightness
    static final int CONTROL_CONTRAST = 1;       //!< image contrast
    static final int CONTROL_WBR = 2;          //!< red of white balance
    static final int CONTROL_WBB = 3;           //!< blue of white balance
    static final int CONTROL_WBG = 4;          //!< the green of white balance
    static final int CONTROL_GAMMA = 5;         //!< screen gamma
    static final int CONTROL_GAIN = 6;           //!< camera gain
    static final int CONTROL_OFFSET = 7;         //!< camera offset
    static final int CONTROL_EXPOSURE = 8;       //!< expose time (us)
    static final int CONTROL_SPEED = 9;          //!< transfer speed
    static final int CONTROL_TRANSFERBIT = 10;    //!< image depth bits
    static final int CONTROL_CHANNELS = 11;       //!< image channels
    static final int CONTROL_USBTRAFFIC = 12;    //!< hblank
    static final int CONTROL_ROWNOISERE = 13;     //!< row denoise
    static final int CONTROL_CURTEMP = 14;        //!< current cmos or ccd temprature
    static final int CONTROL_CURPWM = 15;        //!< current cool pwm
    static final int CONTROL_MANULPWM = 16;      //!< set the cool pwm
    static final int CONTROL_CFWPORT = 17;       //!< control camera color filter wheel port
    static final int CONTROL_COOLER = 18;        //!< check if camera has cooler
    static final int CONTROL_ST4PORT = 19;        //!< check if camera has st4port
    static final int CAM_COLOR = 20;
    static final int CAM_BIN1X1MODE = 21;        //!< check if camera has bin1x1 mode
    static final int CAM_BIN2X2MODE = 22;       //!< check if camera has bin2x2 mode
    static final int CAM_BIN3X3MODE = 23;        //!< check if camera has bin3x3 mode
    static final int CAM_BIN4X4MODE = 24;         //!< check if camera has bin4x4 mode
    static final int CAM_MECHANICALSHUTTER = 25;                  //!< mechanical shutter
    static final int CAM_TRIGER_INTERFACE = 26;                   //!< triger
    static final int CAM_TECOVERPROTECT_INTERFACE = 27;            //!< tec overprotect
    static final int CAM_SINGNALCLAMP_INTERFACE = 28;              //!< singnal clamp
    static final int CAM_FINETONE_INTERFACE = 29;                 //!< fine tone
    static final int CAM_SHUTTERMOTORHEATING_INTERFACE = 30;      //!< shutter motor heating
    static final int CAM_CALIBRATEFPN_INTERFACE = 31;              //!< calibrated frame
    static final int CAM_CHIPTEMPERATURESENSOR_INTERFACE = 32;     //!< chip temperaure sensor
    static final int CAM_USBREADOUTSLOWEST_INTERFACE = 33;        //!< usb readout slowest

    static final int CAM_8BITS = 34;                              //!< 8bit depth
    static final int CAM_16BITS = 35;                             //!< 16bit depth
    static final int CAM_GPS = 36;                               //!< check if camera has gps

    static final int CAM_IGNOREOVERSCAN_INTERFACE = 37;           //!< ignore overscan area

    static final int QHYCCD_3A_AUTOBALANCE = 38;
    static final int QHYCCD_3A_AUTOEXPOSURE = 39;
    static final int QHYCCD_3A_AUTOFOCUS = 40;
    static final int CONTROL_AMPV = 41;                            //!< ccd or cmos ampv
    static final int CONTROL_VCAM = 42;                          //!< Virtual Camera on off
    static final int CAM_VIEW_MODE = 43;

    static final int CONTROL_CFWSLOTSNUM = 44;         //!< check CFW slots number
    static final int IS_EXPOSING_DONE = 45;
    static final int ScreenStretchB = 46;
    static final int ScreenStretchW = 47;
    static final int CONTROL_DDR = 48;
    static final int CAM_LIGHT_PERFORMANCE_MODE = 49;

    static final int CAM_QHY5II_GUIDE_MODE = 50;
    static final int DDR_BUFFER_CAPACITY = 51;
    static final int DDR_BUFFER_READ_THRESHOLD = 52;


  }
