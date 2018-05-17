package com.lzp.router.api.logistics;

import com.lzp.router.api.utils.RLog;

public class ParamsCheckInterceptor implements Interceptor {
    @Override
    public boolean intercept(Chain chain) {
        RLog.i("ParamsCheckInterceptor", "intercept");

        if (chain.getRouterMeta() == null) {
            if (chain.getRouterCallback() != null) {
                chain.getRouterCallback().onNotFound(chain.getRouter().getPath());
            }
            return false;
        }
        return chain.proceed();
    }
}
