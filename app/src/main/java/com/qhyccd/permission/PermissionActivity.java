package com.qhyccd.permission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.qhyccd.R;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * @author tx
 * @date 2018/12/13
 */
public class PermissionActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    /**
     * 获取权限使用的 RequestCode
     */
    private static final int PERMISSIONS_REQUEST_CODE = 1001;

    private Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermission();
            }
        });
    }

    @AfterPermissionGranted(PERMISSIONS_REQUEST_CODE)
    private void requestPermission(){
        String[] perms = {Manifest.permission.CAMERA};
        //权限判断，第一次弹出系统的授权提示框
        if (EasyPermissions.hasPermissions(this, perms)) {
            //有权限直接执行 todo...
            Log.i("》》》》》  ","  requestPermission  requestPermission ");

            takePhoto();
        }else {
            Log.i("》》》》》  ","  requestPermission  requestPermission0000000 ");
            //没有权限的话，先提示，点确定后弹出系统的授权提示框
            EasyPermissions.requestPermissions(this, "拍照过程需要用到相机权限",
                    PERMISSIONS_REQUEST_CODE, perms);
        }
    }

    private void requestPermission1() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            //权限判断，没有就去请求所需权限，传参 需要申请的权限(可以多个)， requestCode请求码用于结果回调里判断
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSIONS_REQUEST_CODE);
        }else {
            //有权限直接拍照，6.0以下的手机拍照走这里
            takePhoto();
        }
    }

    private void takePhoto(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    /**
     * 允许权限成功后触发
     */
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
    }

    /**
     * 禁止权限后触发
     */
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            //在权限弹窗中，用户勾选了'不在提示'且拒绝权限的情况触发，可以进行相关提示。
        }
    }

}
