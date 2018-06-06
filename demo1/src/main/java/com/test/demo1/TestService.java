package com.test.demo1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.lzp.router.annotation.Router;

/**
 * Created by lillian on 2018/6/6.
 */

@Router(path = "/demo1/service")
public class TestService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("Test", "onBind");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("Test", "onCreate");
    }
}
