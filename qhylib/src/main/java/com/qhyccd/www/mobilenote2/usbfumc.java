package com.qhyccd.www.mobilenote2;


import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.text.TextUtils;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;


public class usbfumc
  {


    private UsbManager qusbmanager;
    private UsbDeviceConnection qconnection;

    private final WeakReference<Context> mWeakContext;

    private final int MAXDEVICES = 63;
    private static final String DEFAULT_USBFS = "/dev/bus/usb";
    //static private final Context mContext;

    /* Global struct for camera'io product id */
    int[] campid ={
                    0x0921, 0x8311, 0x6741, 0x6941, 0x6005, 0x1001, 0x1201, 0x8301, 0x6003,
                    0x1111, 0x8141, 0x2851, 0x025a, 0x6001, 0x0931, 0x1611, 0x296d, 0x4023,
                    0x2971, 0xa618, 0x1501, 0x1651, 0x8321, 0x1621, 0x1671, 0x8303, 0x1631,
                    0x2951, 0x00f1, 0x296d, 0x0941, 0x0175, 0x8323, 0x0179, 0x1623, 0x0237,
                    0x0186, 0x6953, 0x8614, 0x1601, 0x1633, 0xC401, 0x0225, 0xC175, 0x0291,
                    0xC179, 0xC225, 0xC291, 0xC164, 0xC166, 0xC368, 0xC184, 0x8614, 0xF368,
                    0xA815, 0x5301, 0x1633, 0xC248, 0xC168, 0xC129, 0x9001, 0x0000, 0x0000
                  };

    /* Global struct for camera'firmware product id */
    int[] fpid ={
                  0x0920, 0x8310, 0x6740, 0x6940, 0x6004, 0x1000, 0x1200, 0x8300, 0x6002,
                  0x1110, 0x8140, 0x2850, 0x0259, 0x6000, 0x0930, 0x1610, 0x0901, 0x4022,
                  0x2970, 0xb618, 0x1500, 0x1650, 0x8320, 0x1620, 0x1670, 0x8302, 0x1630,
                  0x2950, 0x00f1, 0x0901, 0x0940, 0x0174, 0x8322, 0x0178, 0x1622, 0x0236,
                  0x0185, 0x6952, 0x8613, 0x1600, 0x1632, 0xC400, 0x0224, 0xC174, 0x0290,
                  0xC178, 0xC224, 0xC290, 0xC163, 0xC165, 0xC367, 0xC183, 0x8613, 0xF367,
                  0xA814, 0x5300, 0x1632, 0xC247, 0xC167, 0xC128, 0x9000, 0x0000, 0x0000
                };






    private final native int nativeConnect(int venderId, int productId,
                                           int fileDescriptor, int busNum,
                                           int devAddr, String usbfs, boolean downfirmware);

    public class CameraList
      {
        public int totalUSBDevice;
        public int totalQHYCamera;
        public String cameraName;
        public int fvid;
        public int fpid;
        public int cpid;
        public int mBusNum;
        public int mDevNum;
        public int mUSBfd;
        public boolean found;
        public UsbDevice device;
        public boolean hasPermission;
        public boolean downfirmware;
      }


    CameraList cl=new CameraList();  //global

    public  usbfumc(final Context context)
    {
      mWeakContext = new WeakReference<Context>(context);
      cl.totalUSBDevice = 0;
      cl.totalQHYCamera = 0;
      cl.cameraName = "";
      cl.fvid = 0;
      cl.fpid = 0;
      cl.cpid = 0;
      cl.mBusNum = 0;
      cl.mDevNum = 0;
      cl.mUSBfd = 0;
      cl.found = false;
      cl.device = null;
      cl.hasPermission = false;
      cl.downfirmware = false;


    }



    boolean scanCamera(final CameraList cl)
    {
      int i;
      final Context context = mWeakContext.get();

      if (context != null)
        {

          qusbmanager = (UsbManager)context.getSystemService(Context.USB_SERVICE);
          HashMap<String, UsbDevice> deviceList = qusbmanager.getDeviceList();

          cl.totalUSBDevice=deviceList.size();


          //BUTTON1.setText("dev:"+String.valueOf(cl.totalUSBDevice));
          Log.e("QHYCCD | USB Devices Found", String.valueOf(cl.totalUSBDevice));


          cl.cameraName="";   //clear camera list name table
          cl.totalQHYCamera=0;
          cl.found =false;





          if(cl.totalUSBDevice>0)
            {

              Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();
              // ArrayList<String> USBDeviceList = new ArrayList<String>(); // 存放USB设备的数量

              int index = 0;
              int vid,pid;


              //check all device and put the QHYCCD device into the CameraList (cl)
              while (deviceIterator.hasNext())
                {


                  UsbDevice device = deviceIterator.next();


                  //USBDeviceList.add(String.valueOf(device.getVendorId()));
                  //USBDeviceList.add(String.valueOf(device.getProductId()));

                  vid = device.getVendorId();
                  pid = device.getProductId();
                  Log.e("QHYCCD | USB Devices Found", String.valueOf(pid));
                  Log.e("QHYCCD | USB Devices Found", String.valueOf(vid));

                  if ((vid == 0x1618) || (vid == 0x16c0))
                    {
                      for (i = 0;i < MAXDEVICES;i ++)
                        {

                          if ((pid == campid[i])||(pid == fpid[i]))
                            {
                              cl.fvid=vid;
                              cl.fpid=pid;
                              cl.totalQHYCamera++;
                              cl.device=device;
                              cl.hasPermission=qusbmanager.hasPermission(device);





                              final String name = device.getDeviceName();
                              final String[] v = !TextUtils.isEmpty(name) ? name.split("/") : null;
                              int busnum = 0;
                              int devnum = 0;
                              if (v != null)
                                {
                                  busnum = Integer.parseInt(v[v.length-2]);
                                  devnum = Integer.parseInt(v[v.length-1]);
                                }
                              cl.mBusNum = busnum;
                              cl.mDevNum = devnum;

                              cl.found = true;
                              qconnection = null;
                              qconnection = qusbmanager.openDevice(cl.device);

                              cl.cameraName = getUSBFSName(cl.device);
                              cl.mUSBfd = qconnection.getFileDescriptor();
                              break;
                            }

                        }
                    }

                  if (cl.found == true)
                    {
                      Log.e("QHYCCD camera found","OK");
                      return true;
                    }
                  else
                    {
                      Log.e("QHYCCD camera found","ERROR");
                      return false;
                    }

                }



            }

        }

      return false;

    }


    public int OpenFCamera(boolean downfirmware)
    {
      int ret = Globals.QHYCCD_ERROR;
      scanCamera(cl);
      if (cl.found == false)
        {
          return ret;
        }

      ret = nativeConnect(cl.fvid,cl.fpid,cl.mUSBfd,cl.mBusNum,cl.mDevNum,cl.cameraName,downfirmware);

      return ret;
    }











    /**
     * get vendor id
     * @return
     */
    public int getVenderId()
    {
      return cl.fvid;
    }


    /**
     * get product id
     * @return
     */
    public int getFProductId()
    {
      return  cl.fpid;
    }


    /**
     * get product id
     * @return
     */
    public int getCProductId()
    {
      return  cl.cpid;
    }


    /**
     * get file descriptor to access USB device
     * @return
     * @throws IllegalStateException
     */
    public synchronized int getFileDescriptor()
    throws IllegalStateException
      {
        if (qconnection == null)
          {
            return 0;
          }
        else
          {
            return qconnection.getFileDescriptor();
          }
      }



    public int getBusNum()
    {
      return cl.mBusNum;
    }

    public int getDevNum()
    {
      return cl.mDevNum;
    }



    private final String getUSBFSName(final UsbDevice device)
    {
      String result = null;
      final String name = device.getDeviceName();
      final String[] v = !TextUtils.isEmpty(name) ? name.split("/") : null;

      if ((v != null) && (v.length > 2))
        {
          final StringBuilder sb = new StringBuilder(v[0]);
          for (int i = 1; i < v.length - 2; i++)
            {
              sb.append("/").append(v[i]);
            }
          result = sb.toString();
        }

      if (TextUtils.isEmpty(result))
        {
          Log.v("failed to get USBFS path, try to use default path:" ,name);
          result = DEFAULT_USBFS;
        }

      return result;
    }



  }







