package com.test.routerdemo;

import android.app.Application;

import com.lzp.router.api.Route;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Route.init(this);
    }
}
