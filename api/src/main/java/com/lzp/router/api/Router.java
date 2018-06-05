package com.lzp.router.api;

import android.app.Application;
import android.content.Context;
import android.net.Uri;

import com.lzp.router.api.logistics.RouteBuilder;
import com.lzp.router.api.table.RouterTable;
import com.lzp.router.api.utils.RLog;

/**
 * 对外接口类
 */
public class Router {
    public static void init(Application application) {
        try {
            RouterTable.getInstance().initRouterTable(application.getApplicationContext());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void setDebug(boolean debug) {
        RLog.setDebug(debug);
    }

    public static RouteBuilder build(Context context, String path) {
        return new RouteBuilder(context, path);
    }

    public static RouteBuilder build(Context context, Uri uri) {
        return new RouteBuilder(context, uri);
    }
}
