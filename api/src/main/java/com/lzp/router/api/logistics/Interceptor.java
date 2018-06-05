package com.lzp.router.api.logistics;

import com.lzp.router.api.RouterCallback;
import com.lzp.router.api.table.RouterMeta;

public interface Interceptor {
    boolean intercept(Chain chain);

    interface Chain {
        boolean proceed();
        RouterMeta getRouterMeta();
        Route getRouter();
        RouterCallback getRouterCallback();
    }
}
