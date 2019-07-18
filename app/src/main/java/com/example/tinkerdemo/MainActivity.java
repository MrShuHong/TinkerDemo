package com.example.tinkerdemo;

import android.Manifest;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startLocationOrStorage();
    }

    @AfterPermissionGranted(11)
    private void startLocationOrStorage() {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (!EasyPermissions.hasPermissions(this, perms)) {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, "请开启您的定位和读写文件权限",
                    11, perms);
        }
    }

    public void startTinkerUpdate(View view) {
        File downloadCacheDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

        String path  = downloadCacheDirectory.getAbsolutePath()+File.separator+"patch_signed.apk";
        Log.d(TAG, "startTinkerUpdate: "+path);
        TinkerManager.loadPatch(downloadCacheDirectory.getAbsolutePath()+File.separator+"patch_signed.apk");
    }
}
