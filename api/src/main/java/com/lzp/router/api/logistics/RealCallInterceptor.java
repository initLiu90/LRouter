package com.lzp.router.api.logistics;

import android.app.Activity;
import android.app.Fragment;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;

import com.lzp.router.api.RouterCallback;
import com.lzp.router.api.exception.HandlerException;
import com.lzp.router.api.table.RouterMeta;
import com.lzp.router.api.utils.RLog;

import java.lang.reflect.Constructor;

public class RealCallInterceptor implements Interceptor {
    private static final String TAG = RealCallInterceptor.class.getSimpleName();

    @Override
    public Object intercept(Chain chain) {
        RLog.i(TAG, "intercept");

        Route router = chain.getRouter();
        RouterMeta meta = chain.getRouterMeta();

        RLog.i(TAG, "startActivity action type=" + chain.getRouterMeta().getType().name());
        Object result = null;
        switch (chain.getRouterMeta().getType()) {
            case ACTIVITY:
                try {
                    processActivity(router.getContext(), router.createIntent(meta.getDestination(), true), router.getRequestCode());
                } catch (HandlerException e) {
                    sendErrorCallback(chain.getRouterCallback(), e.toString());
                }
                break;
            case SERVICE:
                result = processService(router.getContext(), router.createIntent(meta.getDestination(), false), router.getSerciceConnection());
                break;
            case FRAGMENT:
            case FRAGMENT_V4:
                try {
                    result = processFragment(meta.getDestination(), router.getExtras());
                } catch (Exception e) {
                    sendErrorCallback(chain.getRouterCallback(), e.toString());
                }
                break;
            default:
                sendErrorCallback(chain.getRouterCallback(), "Unsupport action type=" + chain.getRouterMeta().getType().name());
                break;
        }
        sendHandledCallback(chain.getRouterCallback());
        return result;
    }

    private void sendHandledCallback(RouterCallback callback) {
        if (callback != null) {
            callback.onHandled();
        }
    }

    private void sendErrorCallback(RouterCallback callback, String errorMsg) {
        if (callback != null) {
            callback.onError(errorMsg);
        }
    }

    private static void processActivity(Context context, Intent intent, int requestCode) {
        RLog.i(TAG, "processActivity requestCode=" + requestCode);
        if (requestCode > 0) {
            if (!(context instanceof Activity)) {
                throw new HandlerException("context is not instanceof Activity ,original context is:" + context.getClass().getName());
            }
            Activity activity = (Activity) context;
            activity.startActivityForResult(intent, requestCode);
        } else {
            context.startActivity(intent);
        }
    }

    private static Object processService(Context context, Intent intent, ServiceConnection connection) {
        RLog.i(TAG, "processService");
        if (connection == null) {//startService
            context.startService(intent);
            return intent;
        } else {
            context.bindService(intent, connection, Service.BIND_AUTO_CREATE);
            return connection;
        }
    }

    private static Object processFragment(Class<?> fragmentClz, Bundle extras) throws Exception {
        Object fragment = null;
        fragment = fragmentClz.getConstructor().newInstance();
        if (fragment instanceof Fragment) {
            ((Fragment) fragment).setArguments(extras);
        } else if (fragment instanceof android.support.v4.app.Fragment) {
            ((android.support.v4.app.Fragment) fragment).setArguments(extras);
        }
        return fragment;
    }
}
