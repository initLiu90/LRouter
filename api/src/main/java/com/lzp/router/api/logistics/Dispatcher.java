package com.lzp.router.api.logistics;

import com.lzp.router.api.RouterCallback;
import com.lzp.router.api.table.RouterMeta;
import com.lzp.router.api.table.RouterTable;
import com.lzp.router.api.utils.RLog;

import java.util.ArrayList;
import java.util.List;

class Dispatcher {
    public static void dispatch(Route router, RouterCallback callback) {
        RouterMeta meta = RouterTable.getInstance().getRouterMeta(router.getPath());
        RLog.i("Dispatcher", meta == null ? "not found RouterMeta for path=" + router.getPath() : "find RouterMeta:" + meta.toString());

        List<Interceptor> tmp = new ArrayList<>();
        tmp.add(new ParamsCheckInterceptor());
        tmp.addAll(router.getInterceptors());
        tmp.add(new RealCallInterceptor());

        router.getInterceptors().clear();
        router.getInterceptors().addAll(tmp);

        InterceptorDispatcher interceptorDispatcher = new InterceptorDispatcher(router, meta, 0, callback);
        interceptorDispatcher.proceed();
    }
}
