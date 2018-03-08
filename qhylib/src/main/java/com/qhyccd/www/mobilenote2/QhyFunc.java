package com.qhyccd.www.mobilenote2;

import android.util.Log;

import java.nio.ByteBuffer;

import static android.os.SystemClock.sleep;

public class QhyFunc
  {

    private static final String TAG = "QhyFunc";
    int num = 0;

    String id;

    int get_w;
    int get_h;
    int get_bpp;
    int set_w = 0;
    int set_h = 0;
    int set_bpp = 8;
    int channels;

    int camtime = 100000;
    int camgain = 0;
    int camoffset = 140;
    int camspeed = 0;
    int cambinx = 1;
    int cambiny = 1;
    double chipw = 0.0;
    double chiph = 0.0;
    double pixelw = 0.0;
    double pixelh = 0.0;
    boolean stop_capture = false;

    boolean BIN1X1MODE = true;
    boolean BIN2X2MODE = false;
    boolean BIN3X3MODE = false;
    boolean BIN4X4MODE = false;



    public void FlipPictrue ()
    {

      Globals.ImgData.position (0);
      Log.e("QHYCCD | END","1111111111111");
      Globals.ImgData.get(Globals.ImgDataX,0, Globals.PicWidth* Globals.PicHeight*3);
      //bmp1.copyPixelsFromBuffer (Globals.ImgData);
      //iv.setImageBitmap(bmp1);

      Log.e("QHYCCD | END","222222222222");
    }


    public  QhyFunc()
    {
      QHYCCDInit();

      stop_capture = false;
    }


    void StopCapture ()
    {
      stop_capture = true;
    }

    void InitImageBuffer (ByteBuffer paramByteBuffer)
    {
      Globals.ImgData = paramByteBuffer;
      Log.e(TAG,"InitImageBuffer (ByteBuffer paramByteBuffer)");
    }


    void ReleaseImageBuffer ()
    {
      Globals.ImgData = null;
    }

    void GetImageParamter (double Chipw,double Chiph,int w,int h,double Pixelw,
                           double Pixelh,int bpp,int Channels)
    {
      chipw = Chipw;
      chiph = Chiph;
      get_w = w;
      get_h = h;
      pixelw = Pixelw;
      pixelh = Pixelh;
      get_bpp = bpp;
      channels = Channels;
      Log.v(TAG,"succeess" + get_w + get_h + get_bpp + channels);
    }

    void getQHYCCDId(String Id)
    {
      id = Id;
    }

    public int DownFirmware()
    {
      int ret = Globals.QHYCCD_ERROR;
      String path = "/mnt/sdcard/";

      ret = InitQHYCCDResource();

      if(ret == Globals.QHYCCD_SUCCESS)
        {
          Log.v(TAG,"Init SDK success!");
        }
      else
        {
          Log.e(TAG,"Init SDK failed!");
          return ret;
        }

      ret = OSXInitQHYCCDFirmware(path);
      if(ret == Globals.QHYCCD_SUCCESS)
        {
          Log.v(TAG,"Download firmware successed!");
        }
      else
        {
          Log.e(TAG,"Download firmware failed!");
        }
      return ret;
    }

    public int DownFirmwareArray()
    {
      int ret = Globals.QHYCCD_ERROR;


      ret = InitQHYCCDResource();

      if(ret == Globals.QHYCCD_SUCCESS)
        {
          Log.v(TAG,"Init SDK success!");
        }
      else
        {
          Log.e(TAG,"Init SDK failed!");
          return ret;
        }

      ret = OSXInitQHYCCDFirmwareMem();
      if(ret == Globals.QHYCCD_SUCCESS)
        {
          Log.v(TAG,"Download firmware successed!");
        }
      else
        {
          Log.e(TAG,"Download firmware failed!");
        }
      return ret;
    }

    public int InitializeQHYCCD()
    {
      int ret = Globals.QHYCCD_ERROR;
      int found = 0;
      stop_capture = false;
      ret = InitQHYCCDResource();

      if(ret == Globals.QHYCCD_SUCCESS)
        {
          Log.v(TAG,"Init SDK success!");
        }
      else
        {
          Log.e(TAG,"Open QHYCCD failed");
          return ret;
        }
      num = ScanQHYCCD();
      if(num > 0)
        {
          Log.v(TAG,"Yes!Found QHYCCD,the num is " + num);
        }
      else
        {
          Log.e(TAG,"Not Found QHYCCD,please check the usblink or the power");
          return ret;
        }

      for(int i = 0; i < num; i++)
        {
          ret = GetQHYCCDId(i,id);
          if(ret == Globals.QHYCCD_SUCCESS)
            {
              Log.v(TAG,"connected to the first camera from the list,id is " + id);
              found = 1;
              break;
            }
        }

      if(found == 1)
        {
          ret = OpenQHYCCD(id);
          if(ret == Globals.QHYCCD_SUCCESS)
            {
              Log.v(TAG,"Open QHYCCD success!");
            }
          else
            {
              Log.e(TAG,"Open QHYCCD failed");
              return ret;
            }
        }
      else
        {
          return Globals.QHYCCD_ERROR;
        }

      Log.e(TAG,"InitializeQHYCCD OK!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
      return ret;
    }


    public int SetLiveParameter()
    {
      int ret = Globals.QHYCCD_ERROR;
      char c = 1;

      if (InitializeQHYCCD() == Globals.QHYCCD_ERROR)
        {
          return ret;
        }

      ret = SetQHYCCDStreamMode(c);


      ret = InitQHYCCD();
      if(ret == Globals.QHYCCD_SUCCESS)
        {
          Log.v(TAG,"Init QHYCCD success!");
        }
      else
        {
          Log.e(TAG,"Init QHYCCD fail code:" + ret);
          return ret;
        }

      ret = GetQHYCCDChipInfo(chipw,chiph,get_w,get_h,pixelw,pixelh,get_bpp);
      if(ret == Globals.QHYCCD_SUCCESS)
        {
          Log.v(TAG,"GetQHYCCDChipInfo success!");
          Log.v(TAG,"CCD/CMOS chip information:");
          Log.v(TAG,"Chip width" + chipw);
          Log.v(TAG,"Chip height" + chiph);
          Log.v(TAG,"Chip pixel width" + pixelw);
          Log.v(TAG,"Chip pixel height" + pixelh);
          Log.v(TAG,"Chip Max Resolution is w = " + get_w);
          Log.v(TAG,"Chip Max Resolution is h = " + get_h);
          Log.v(TAG,"Chip Max Resolution is bpp = " + get_bpp);
        }
      else
        {
          Log.e(TAG,"GetQHYCCDChipInfo fail");
          return ret;
        }

      ret = IsQHYCCDControlAvailable(Globals.CAM_COLOR);
      if(ret == Globals.BAYER_GB || ret == Globals.BAYER_GR ||
          ret == Globals.BAYER_BG || ret == Globals.BAYER_RG)
        {
          Log.v(TAG,"System Information::This QHYCCD device is a color camera!");
          SetQHYCCDDebayerOnOff(true);
          //set camera param by definition
          SetQHYCCDParam(Globals.CONTROL_WBR,64);
          SetQHYCCDParam(Globals.CONTROL_WBG,64);
          SetQHYCCDParam(Globals.CONTROL_WBB,64);
        }
      else
        {
          Log.v(TAG,"System Information::This QHYCCD device is not a color camera!");
        }

      ret = IsQHYCCDControlAvailable(Globals.CONTROL_DDR);
      if(ret == Globals.QHYCCD_SUCCESS)
        {
          Log.v(TAG,"System Information::This QHYCCD device has DDR!");
          ret = SetQHYCCDParam(Globals.CONTROL_DDR,1);
          if(ret == Globals.QHYCCD_SUCCESS)
            {
              Log.v(TAG,"System Information::Open QHYCCD device DDR success!");
            }
          else
            {
              Log.v(TAG,"System Information::Open QHYCCD device DDR failed! " + ret);
            }
        }
      else
        {
          Log.v(TAG,"System Information::This QHYCCD device doesn't have DDR!");
        }



      ret = IsQHYCCDControlAvailable(Globals.CONTROL_TRANSFERBIT);
      if(ret == Globals.QHYCCD_SUCCESS)
        {
          ret = SetQHYCCDBitsMode(8);
          if(ret != Globals.QHYCCD_SUCCESS)
            {
              Log.e(TAG,"SetQHYCCDParam CONTROL_GAIN failed");
              return ret;
            }
        }



      ret = IsQHYCCDControlAvailable(Globals.CONTROL_OFFSET);
      if(ret == Globals.QHYCCD_SUCCESS)
        {
          Log.e(TAG,"System Information::Can set this QHYCCD device offset.");
          ret = SetQHYCCDParam(Globals.CONTROL_OFFSET,50);
          if(ret == Globals.QHYCCD_SUCCESS)
            {
              Log.e(TAG,"System Information::Set QHYCCD device offset success!");
            }
          else
            {
              Log.e(TAG,"System Information::Set QHYCCD device offset failed! "+ret);
            }
        }
      else
        {
          Log.e(TAG,"System Information::Can't set this QHYCCD device offset!");
        }

      ret = IsQHYCCDControlAvailable(Globals.CONTROL_GAIN);
      if(ret == Globals.QHYCCD_SUCCESS)
        {
          Log.e(TAG,"System Information::Can set this QHYCCD device gain.");
          ret = SetQHYCCDParam(Globals.CONTROL_GAIN,50);
          if(ret == Globals.QHYCCD_SUCCESS)
            {
              Log.e(TAG,"System Information::Set QHYCCD device gain success!");
            }
          else
            {
              Log.e(TAG,"System Information::Set QHYCCD device gain failed! "+ret);
            }
        }
      else
        {
          Log.e(TAG,"System Information::Can't set this QHYCCD device gain.");
        }

      ret = IsQHYCCDControlAvailable(Globals.CONTROL_USBTRAFFIC);
      if(ret == Globals.QHYCCD_SUCCESS)
        {
          Log.e(TAG,"System Information::Can set this QHYCCD device USBTraffic!");
          ret = SetQHYCCDParam(Globals.CONTROL_USBTRAFFIC,50);
          if(ret == Globals.QHYCCD_SUCCESS)
            {
              Log.e(TAG,"System Information::Set QHYCCD device USBTraffic success!");
            }
          else
            {
              Log.e(TAG,"System Information::Set QHYCCD device USBTraffic failed! "+ret);
              return ret;
            }
        }
      else
        {
          Log.e(TAG,"System Information::Can't set this QHYCCD device USBTraffic!\n");
        }

      int exp_time = 0;
      ret = IsQHYCCDControlAvailable(Globals.CONTROL_EXPOSURE);
      if(ret == Globals.QHYCCD_SUCCESS)
        {
          Log.e(TAG,"System Information::Can set this QHYCCD device exposure time. ");
          exp_time = GetQHYCCDParam(Globals.CONTROL_EXPOSURE);
          if(exp_time > 0)
            Log.e(TAG,"System Information::QHYCCD device exposure time is %3d ms. "+exp_time/1000);
          else
            Log.e(TAG,"System Information::Get QHYCCD device exposure time failed! "+ret);

          ret = SetQHYCCDParam(Globals.CONTROL_EXPOSURE,20*1000);
          if(ret == Globals.QHYCCD_SUCCESS)
            {
              Log.e(TAG,"System Information::Set QHYCCD device exposure time success!");
              exp_time = GetQHYCCDParam(Globals.CONTROL_EXPOSURE);
              if(exp_time > 0)
                Log.e(TAG,"System Information::QHYCCD device exposure time is %3d ms. "+exp_time/1000);
              else
                Log.e(TAG,"System Information::Get QHYCCD device exposure time failed! "+ret);
            }
          else
            {
              Log.e(TAG,"System Information::Set QHYCCD device exposure time failed! "+ret);
            }
        }

      ret = IsQHYCCDControlAvailable(Globals.CONTROL_SPEED);
      if(ret == Globals.QHYCCD_SUCCESS)
        {
          Log.e(TAG,"System Information::Can set this QHYCCD device speed!");
          ret = SetQHYCCDParam(Globals.CONTROL_SPEED,2);
          if(ret == Globals.QHYCCD_SUCCESS)
            {
              Log.e(TAG,"System Information::Set QHYCCD device speed succeed!");
            }
          else
            {
              Log.e(TAG,"System Information::Set QHYCCD device speed failed! "+ret);
              return ret;
            }
        }
      else
        {
          Log.e(TAG,"System Information::Can't set this QHYCCD device speed!");
        }

      if ((set_w == 0)||(set_h == 0))
        {
          set_w = get_w;
          set_h = get_h;
        }
      set_w = Globals.PicWidth;
      set_h = Globals.PicHeight;
      if (BIN1X1MODE)
        {
          ret = IsQHYCCDControlAvailable(Globals.CAM_BIN1X1MODE);
          if(ret == Globals.QHYCCD_SUCCESS)
            {
              Log.e(TAG,"Can set this camera 1X1 bin mode.");
              ret = SetQHYCCDBinMode(1,1);
              if(ret == Globals.QHYCCD_SUCCESS)
                {
                  Log.e(TAG,"Set camera 1X1 bin mode success!");
                  ret = SetQHYCCDResolution(0,0,set_w,set_h);
                  if(ret == Globals.QHYCCD_SUCCESS)
                    {
                      Log.e(TAG,"Set camera resolution success!");
                    }
                  else
                    {
                      Log.e(TAG,"Set camera resolution failed! "+ret);
                      return ret;
                    }
                }
              else
                {
                  Log.e(TAG,"Set camera 1X1 bin mode failed! "+ret);
                }
            }
        }
      if (BIN2X2MODE)
        {
          ret = IsQHYCCDControlAvailable(Globals.CAM_BIN2X2MODE);
          if(ret == Globals.QHYCCD_SUCCESS)
            {
              Log.e(TAG,"Can set this camera 2X2 bin mode.");
              ret = SetQHYCCDBinMode(2,2);
              if(ret == Globals.QHYCCD_SUCCESS)
                {
                  Log.e(TAG,"Set camera 2X2 bin mode success!");
                  ret = SetQHYCCDResolution(0,0,set_w/2,set_h/2);
                  if(ret == Globals.QHYCCD_SUCCESS)
                    {
                      Log.e(TAG,"Set camera resolution success!");
                    }
                  else
                    {
                      Log.e(TAG,"Set camera resolution failed! "+ret);
                      return ret;
                    }
                }
              else
                {
                  Log.e(TAG,"Set camera 2X2 bin mode failed! "+ret);
                }
            }
        }
      if (BIN3X3MODE)
        {
          ret = IsQHYCCDControlAvailable(Globals.CAM_BIN3X3MODE);
          if(ret == Globals.QHYCCD_SUCCESS)
            {
              Log.e(TAG,"Can set this camera 3X3 bin mode.");
              ret = SetQHYCCDBinMode(3,3);
              if(ret == Globals.QHYCCD_SUCCESS)
                {
                  Log.e(TAG,"Set camera 3X3 bin mode success!");
                  ret = SetQHYCCDResolution(0,0,set_w/3,set_h/3);
                  if(ret == Globals.QHYCCD_SUCCESS)
                    {
                      Log.e(TAG,"Set camera resolution success!");
                    }
                  else
                    {
                      Log.e(TAG,"Set camera resolution failed! "+ret);
                      return ret;
                    }
                }
              else
                {
                  Log.e(TAG,"Set camera 3X3 bin mode failed! "+ret);
                }
            }
        }
      if (BIN4X4MODE)
        {
          ret = IsQHYCCDControlAvailable(Globals.CAM_BIN4X4MODE);
          if(ret == Globals.QHYCCD_SUCCESS)
            {
              Log.e(TAG,"Can set this camera 4X4 bin mode.");
              ret = SetQHYCCDBinMode(4,4);
              if(ret == Globals.QHYCCD_SUCCESS)
                {
                  Log.e(TAG,"Set camera 4X4 bin mode success!");
                  ret = SetQHYCCDResolution(0,0,set_w/4,set_h/4);
                  if(ret == Globals.QHYCCD_SUCCESS)
                    {
                      Log.e(TAG,"Set camera resolution success!");
                    }
                  else
                    {
                      Log.e(TAG,"Set camera resolution failed! "+ret);
                      return ret;
                    }
                }
              else
                {
                  Log.e(TAG,"Set camera 1X1 bin mode failed! "+ret);
                  return ret;
                }
            }
        }





      if (false)
        {

          if ((set_w == 0)||(set_h == 0))
            {
              set_w = get_w;
              set_h = get_h;
            }

          ret = SetQHYCCDResolution(0,0,set_w,set_h);
          Globals.PicWidth = set_w;
          Globals.PicHeight = set_h;
          if(ret == Globals.QHYCCD_SUCCESS)
            {
              Log.v(TAG,"SetQHYCCDResolution success!");
            }
          else
            {
              Log.e(TAG,"SetQHYCCDResolution fail");
              return ret;
            }
        }

      int length = GetQHYCCDMemLength();

      if((length > 0)&&(Globals.ImgData != null))
        {
          Log.e(TAG,"Get the min memory space length = "+length);
        }
      else
        {
          Log.e(TAG,"Get the min memory space length failure");
          return Globals.QHYCCD_ERROR;
        }

      sleep(10);
      ret = BeginQHYCCDLive();
      if(ret == Globals.QHYCCD_SUCCESS)
        {
          Log.v(TAG,"BeginQHYCCDLive success!");

        }
      else
        {
          Log.e(TAG,"BeginQHYCCDLive failed");
          return ret;
        }


      ret = GetQHYCCDLiveFrame(set_w,set_h,set_bpp,channels);
      Log.e(TAG,"GetQHYCCDLiveFrame return value is :" + ret);


      return ret;



    }


    public int SetSingleParameter()
    {
      int ret = Globals.QHYCCD_ERROR;
      char c = 0;
      ret = SetQHYCCDStreamMode(c);
      if(ret == Globals.QHYCCD_SUCCESS)
        {
          Log.v(TAG,"SetQHYCCDStreamMode success!");
        }
      else
        {
          Log.e(TAG,"SetQHYCCDStreamMode code: " + ret);
          return ret;
        }

      ret = InitQHYCCD();
      if(ret == Globals.QHYCCD_SUCCESS)
        {
          Log.v(TAG,"Init QHYCCD success!\n");
        }
      else
        {
          Log.e(TAG,"Init QHYCCD fail code:" + ret);
          return ret;
        }


      ret = GetQHYCCDChipInfo(chipw,chiph,get_w,get_h,pixelw,pixelh,get_bpp);
      if(ret == Globals.QHYCCD_SUCCESS)
        {
          Log.v(TAG,"GetQHYCCDChipInfo success!");
          Log.v(TAG,"CCD/CMOS chip information:");
          Log.v(TAG,"Chip width" + chipw);
          Log.v(TAG,"Chip height" + chiph);
          Log.v(TAG,"Chip pixel width" + pixelw);
          Log.v(TAG,"Chip pixel height" + pixelh);
          Log.v(TAG,"Chip Max Resolution is w = " + get_w);
          Log.v(TAG,"Chip Max Resolution is h = " + get_h);
          Log.v(TAG,"Chip Max Resolution is bpp = " + get_bpp);
        }
      else
        {
          Log.e(TAG,"GetQHYCCDChipInfo fail\n");
          return ret;
        }

      ret = IsQHYCCDControlAvailable(Globals.CAM_COLOR);
      if(ret == Globals.BAYER_GB || ret == Globals.BAYER_GR ||
          ret == Globals.BAYER_BG || ret == Globals.BAYER_RG)
        {
          Log.v(TAG,"This is a Color Cam");
          SetQHYCCDDebayerOnOff(true);
          SetQHYCCDParam(Globals.CONTROL_WBR,20);
          SetQHYCCDParam(Globals.CONTROL_WBG,20);
          SetQHYCCDParam(Globals.CONTROL_WBB,20);
        }

      ret = IsQHYCCDControlAvailable(Globals.CONTROL_USBTRAFFIC);
      if(ret == Globals.QHYCCD_SUCCESS)
        {
          ret = SetQHYCCDParam(Globals.CONTROL_USBTRAFFIC,30);
          if(ret != Globals.QHYCCD_SUCCESS)
            {
              Log.e(TAG,"SetQHYCCDParam CONTROL_USBTRAFFIC failed");
              return ret;
            }
        }

      ret = IsQHYCCDControlAvailable(Globals.CONTROL_GAIN);
      if(ret == Globals.QHYCCD_SUCCESS)
        {
          ret = SetQHYCCDParam(Globals.CONTROL_GAIN,30);
          if(ret != Globals.QHYCCD_SUCCESS)
            {
              Log.e(TAG,"SetQHYCCDParam CONTROL_GAIN failed");
              return ret;
            }
        }

      ret = IsQHYCCDControlAvailable(Globals.CONTROL_OFFSET);
      if(ret == Globals.QHYCCD_SUCCESS)
        {
          ret = SetQHYCCDParam(Globals.CONTROL_OFFSET,140);
          if(ret != Globals.QHYCCD_SUCCESS)
            {
              Log.e(TAG,"SetQHYCCDParam CONTROL_GAIN failed");
              return ret;
            }
        }

      ret = SetQHYCCDParam(Globals.CONTROL_EXPOSURE,20000);//170000000);
      if(ret != Globals.QHYCCD_SUCCESS)
        {
          Log.e(TAG,"SetQHYCCDParam CONTROL_EXPOSURE failed");
          return ret;
        }

      if ((set_w == 0)||(set_h == 0))
        {
          set_w = get_w;
          set_h = get_h;
        }

      ret = SetQHYCCDResolution(0,0,set_w,set_h);
      if(ret == Globals.QHYCCD_SUCCESS)
        {
          Log.e(TAG,"SetQHYCCDResolution success!");
        }
      else
        {
          Log.e(TAG,"SetQHYCCDResolution fail");
          return ret;
        }

      ret = SetQHYCCDBinMode(cambinx,cambiny);
      if(ret == Globals.QHYCCD_SUCCESS)
        {
          Log.e(TAG,"SetQHYCCDBinMode success!");
        }
      else
        {
          Log.e(TAG,"SetQHYCCDBinMode fail");
          return ret;
        }

      ret = IsQHYCCDControlAvailable(Globals.CONTROL_TRANSFERBIT);
      if(ret == Globals.QHYCCD_SUCCESS)
        {
          ret = SetQHYCCDBitsMode(set_bpp);
          if(ret != Globals.QHYCCD_SUCCESS)
            {
              Log.e(TAG,"SetQHYCCDParam CONTROL_GAIN failed");
              return ret;
            }
        }

      ret = ExpQHYCCDSingleFrame();
      if( ret != Globals.QHYCCD_ERROR )
        {
          Log.e(TAG,"ExpQHYCCDSingleFrame success!");
          if( ret != Globals.QHYCCD_READ_DIRECTLY )
            {
              sleep(1);
            }
        }
      else
        {
          Log.e(TAG,"ExpQHYCCDSingleFrame fail");
          return ret;
        }

      int length = GetQHYCCDMemLength();

      if((length > 0)&&(Globals.ImgData != null))
        {
          Log.e(TAG,"Get the min memory space length = "+length);
          return Globals.QHYCCD_SUCCESS;
        }
      else
        {
          Log.e(TAG,"Get the min memory space length failure");
          return Globals.QHYCCD_ERROR;
        }

    }


    public int StartSingleCapture()

    {
      int ret = Globals.QHYCCD_ERROR;
      //while(stop_capture == false)
      {
        ret = GetQHYCCDSingleFrame(set_w,set_h,set_bpp,channels);
        if(ret == Globals.QHYCCD_SUCCESS)
          {
            Log.v(TAG,"GetQHYCCDSingleFrame succeess" + get_w + get_h + get_bpp + channels);
            //to do anything you like:
            //这里加入绘图函数

          }
        else
          {
            Log.e(TAG,"GetQHYCCDSingleFrame fail:" + ret);
          }
      }
      return ret;
    }


    public int StartLiveCapture()

    {
      int ret = Globals.QHYCCD_ERROR;


      return ret;
    }


    public int UnInitializeSingleMode()
    {
      int ret = Globals.QHYCCD_ERROR;

      ret = CancelQHYCCDExposingAndReadout();
      if(ret == Globals.QHYCCD_SUCCESS)
        {
          Log.v(TAG,"CancelQHYCCDExposingAndReadout success!");
        }
      else
        {
          Log.e(TAG,"CancelQHYCCDExposingAndReadout fail");
          return ret;
        }

      ret = CloseQHYCCD();
      if(ret == Globals.QHYCCD_SUCCESS)
        {
          Log.v(TAG,"Close QHYCCD success!");
        }
      else
        {
          Log.e(TAG,"Close QHYCCD failed!");
          return ret;
        }

      ret = ReleaseQHYCCDResource();
      if(ret == Globals.QHYCCD_SUCCESS)
        {
          Log.v(TAG,"Rlease SDK Resource  success!");
        }


      return ret;
    }



    public int UnInitializeLiveMode()
    {
      int ret = Globals.QHYCCD_ERROR;

      StopQHYCCDLive();
      ReleaseQHYCCDBuffer();
      ReleaseImageBuffer ();

      /*
            ret = CloseQHYCCD();
            if(ret == Globals.QHYCCD_SUCCESS)
              {
                Log.v(TAG,"Close QHYCCD success!");
              }
            else
              {
                Log.e(TAG,"Close QHYCCD failed!");
                return ret;
              }
       
       
            ret = ReleaseQHYCCDResource();
            if(ret == Globals.QHYCCD_SUCCESS)
              {
                Log.v(TAG,"Rlease SDK Resource  success!");
              }
      */


      return Globals.QHYCCD_SUCCESS;
    }


    public native void
    QHYCCDQuit();

    public native void
    QHYCCDInit();


    public native int
    InitQHYCCDResource();

    public native int
    ReleaseQHYCCDBuffer();

    public native int
    ReleaseQHYCCDResource();


    public native int
    GetQHYCCDId(int index,String id);

    public native int
    GetQHYCCDModel(String id, String model);

    public native int
    OpenQHYCCD(String id);

    public native int
    CloseQHYCCD();

    public native int
    SetQHYCCDStreamMode(char mode);

    public native int
    InitQHYCCD();

    public native int
    IsQHYCCDControlAvailable(int controlId);

    public native int
    SetQHYCCDParam(int controlId,double value);

    public native int
    GetQHYCCDParam (int controlId);

    public native int
    GetQHYCCDParamMinMaxStep (int controlId,
                              double min,double max,double step);

    public native int
    SetQHYCCDResolution (int x,
                         int y,int xsize,int ysize);

    public native int
    GetQHYCCDMemLength();

    public native int
    ExpQHYCCDSingleFrame();

    public native int
    GetQHYCCDSingleFrame (int w,
                          int h,int bpp,int channels);

    public native int
    CancelQHYCCDExposing();

    public native int
    CancelQHYCCDExposingAndReadout();

    public native int
    BeginQHYCCDLive();

    public native int
    GetQHYCCDLiveFrame (int w,int h,
                        int bpp,int channels);

    public native int
    StopQHYCCDLive();

    public native int
    SetQHYCCDBinMode (int wbin,int hbin);

    public native int
    SetQHYCCDBitsMode (int bits );

    public native int
    ControlQHYCCDTemp (double targettem );

    public native int
    ControlQHYCCDGuide (int direction,short duration);

    public native int
    SendOrder2QHYCCDCFW (String order, int length);

    public native int
    GetQHYCCDCFWStatus (String status);

    public native int
    IsQHYCCDCFWPlugged();

    public native int
    SetQHYCCDTrigerMode (int trigerMode);

    public native  void
    Bits16ToBits8 (String InputData16,
                   String OutputData8, int imageX,
                   int imageY, short B, short W);

    public native  void
    HistInfo192x130 (int x,
                     int y, String InBuf, String OutBuf);

    public native int
    GetQHYCCDChipInfo (double chipw,
                       double chiph,int imagew,int imageh,double pixelw,
                       double pixelh,int bpp);

    public native int
    GetQHYCCDEffectiveArea (int startX,
                            int startY, int sizeX, int sizeY);

    public native int
    GetQHYCCDOverScanArea (int startX,
                           int startY, int sizeX, int sizeY);

    public native int
    SetQHYCCDFocusSetting (int focusCenterX,
                           int focusCenterY);

    public native int
    GetQHYCCDExposureRemaining();

    public native int
    GetQHYCCDFWVersion (String buf);

    public native int
    SetQHYCCDInterCamSerialParam (int opt);

    public native int
    QHYCCDInterCamSerialTX (String buf, int length);

    public native int
    QHYCCDInterCamSerialRX (String buf);

    public native int
    QHYCCDInterCamOledOnOff (char onoff);

    public native int
    SetQHYCCDInterCamOledBrightness (char brightness);

    public native int
    SendFourLine2QHYCCDInterCamOled (
            String messagetemp, String messageinfo, String messagetime, String messagemode);

    public native int
    SendTwoLine2QHYCCDInterCamOled (
            String messageTop, String messageBottom);

    public native int
    SendOneLine2QHYCCDInterCamOled (String messageTop);

    public native int
    GetQHYCCDCameraStatus (String buf);


    public native int
    GetQHYCCDShutterStatus();

    public native int
    ControlQHYCCDShutter (char status);

    public native int
    GetQHYCCDHumidity (double hd);

    public native int
    QHYCCDI2CTwoWrite (short addr,short value);

    public native  double
    QHYCCDI2CTwoRead (short addr);

    public native  double
    GetQHYCCDReadingProgress();

    public native void
    SetQHYCCDLogLevel (
      char logLevel);

    public native int
    TestQHYCCDPIDParas (
      double p, double i, double d);

    public native int
    SetQHYCCDTrigerFunction (boolean value);

    public native int
    DownloadFX3FirmWare (short vid,short pid,
                         String imgpath);

    public native int
    GetQHYCCDType();

    public native int
    SetQHYCCDDebayerOnOff (boolean onoff);

    public native int
    SetQHYCCDFineTone (char setshporshd,
                       char shdloc,char shploc,char shwidth);

    public native int
    SetQHYCCDGPSVCOXFreq  (
      short i);

    public native int
    SetQHYCCDGPSLedCalMode	(
      char i);

    public native void
    SetQHYCCDGPSLedCal	(
      int pos,char width);

    public native  void
    SetQHYCCDGPSPOSA  (char is_slave,
                       int pos,char width);

    public native int
    SetQHYCCDGPSMasterSlave  (char i);


    public native void
    SetQHYCCDGPSSlaveModeParameter	(int target_sec,
                                    int target_us,
                                    int deltaT_sec,int deltaT_us,int expTime);

    public native int
    QHYCCDVendRequestWrite	(char req,short value,
                            short index1,int length,String data);

    public native int
    QHYCCDReadUSB_SYNC	(char endpoint, int length,
                           String data, int timeout);

    public native int
    QHYCCDLibusbBulkTransfer  (char endpoint,
                               String data, int length, int transferred, int timeout);

    public native int
    GetQHYCCDSDKVersion  (int year,
                          int month,int day,int subday);

    public native int
    OSXInitQHYCCDFirmware(String path);

    public native int
    OSXInitQHYCCDFirmwareMem();

    public native int
    ScanQHYCCD();

    public native int
    ScanQHYCCD(String id);




  }














