package com.lzp.router.api.logistics;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.lzp.router.api.exception.HandlerException;
import com.lzp.router.api.table.RouterMeta;
import com.lzp.router.api.utils.RLog;

public class RealCallInterceptor implements Interceptor {
    private static final String TAG = RealCallInterceptor.class.getSimpleName();

    @Override
    public boolean intercept(Chain chain) {
        RLog.i(TAG, "intercept");

        Router router = chain.getRouter();
        RouterMeta meta = chain.getRouterMeta();

        RLog.i(TAG, "startActivity action type=" + chain.getRouterMeta().getType().name());
        switch (chain.getRouterMeta().getType()) {
            case ACTIVITY:
                startActivity(router.getContext(), router.createIntent(meta.getDestination(), true), router.getRequestCode());
                return true;
            case SERVICE:
                return true;
            case FRAGMENT:
                return true;
            case FRAGMENT_V4:
                return true;
            default:
                return false;
        }
    }

    private static void startActivity(Context context, Intent intent, int requestCode) {
        RLog.i(TAG, "startActivity requestCode=" + requestCode);
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
}
