package com.example.tinkerdemo;

import android.content.Context;

import com.tencent.tinker.entry.ApplicationLike;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;

/**
 * Copyright (C), 2007-2019, 上海昊沧系统控制技术有限公司
 * Author: shuhong
 * Date: 2019/7/17 17:02
 * Description:
 * 根据这篇文章进行配置  https://www.jianshu.com/p/9a107ee09006
 */
public class TinkerManager {

    //标记是否安装过Tinker
    private static boolean isInstalled =  false;

    private static ApplicationLike mAppLike;

    /**
     * 完成tinker的初始化
     * @param applicationLike
     */
    public static void installTinker(TinkerApplicationLike applicationLike) {
        mAppLike = applicationLike;
        if (isInstalled){
            return;
        }

        //完成Tinker的初始化
        TinkerInstaller.install(mAppLike);
        isInstalled = true;
    }

    /**
     * 完成patch文件的加载
     * @param path
     */
    public static void loadPatch(String path){
        if (Tinker.isTinkerInstalled()){
            TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(),path);
        }
    }

    private static Context getApplicationContext(){
        if (mAppLike != null){
            return mAppLike.getApplication().getApplicationContext();
        }
        return null;
    }
}
