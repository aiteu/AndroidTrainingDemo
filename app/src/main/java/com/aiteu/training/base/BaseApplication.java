package com.aiteu.training.base;

import android.app.Application;
import android.content.Context;

public class BaseApplication extends Application {

    private static Application sApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }

    public static Context getApp(){
        return sApplication;
    }
}
