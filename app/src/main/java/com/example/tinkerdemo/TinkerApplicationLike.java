package com.example.tinkerdemo;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;

import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.entry.DefaultApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * Copyright (C), 2007-2019, 上海昊沧系统控制技术有限公司
 * Author: shuhong
 * Date: 2019/7/17 17:03
 * Description:
 */
@DefaultLifeCycle(application = ".SampleApplication",
        loaderClass = "com.tencent.tinker.loader.TinkerLoader",
        //loaderClassName, 我们这里使用默认即可!
        flags = ShareConstants.TINKER_ENABLE_ALL,
        loadVerifyFlag = false)
public class TinkerApplicationLike extends DefaultApplicationLike {

    public TinkerApplicationLike(Application application, int tinkerFlags,
                                 boolean tinkerLoadVerifyFlag,
                                 long applicationStartElapsedTime,
                                 long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime,
                applicationStartMillisTime, tinkerResultIntent);
    }

    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);

        //原有的初始化方法

        MultiDex.install(base);
        TinkerManager.installTinker(this);
    }
}
