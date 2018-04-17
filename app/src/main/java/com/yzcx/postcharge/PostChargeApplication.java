package com.yzcx.postcharge;

import android.app.Application;

import com.yzcx.postcharge.until.Utils;


/**
 * @author liuqingjie
 * @create $date$
 * @description $desc$
 */

public class PostChargeApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化工具类
        Utils.init(this);
    }
}
