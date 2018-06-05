package com.test.routerdemo;

import android.app.Application;

import com.lzp.router.api.Router;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Router.init(this);
    }
}
