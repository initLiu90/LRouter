package com.lzp.router.api.logistics;

import android.content.Context;
import android.content.Intent;

import com.lzp.router.api.RouterCallback;
import com.lzp.router.api.logistics.Router;
import com.lzp.router.api.table.RouterMeta;

public interface Interceptor {
    boolean intercept(Chain chain);

    interface Chain {
        boolean proceed();
        RouterMeta getRouterMeta();
        Router getRouter();
        RouterCallback getRouterCallback();
    }
}
