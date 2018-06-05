package com.lzp.router.api.logistics;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.lzp.router.api.RouterCallback;
import com.lzp.router.api.table.RouterMeta;
import com.lzp.router.api.table.RouterTable;
import com.lzp.router.api.utils.RLog;
import com.lzp.router.api.utils.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class Route {
    private int mRequestCode;
    private String mPath;
    private Uri mUri;
    private Bundle mExtras;
    private int mFlags;
    private List<Interceptor> mInterceptors = new ArrayList<>();
    private Context mContext;

    private static final String RAW_URI = "a08ad5de46a0d4d3";

    private Route() {
    }

    Route(RouteBuilder builder) {
        mRequestCode = builder.requestCode;
        mPath = builder.path;
        mUri = builder.uri;
        mContext = builder.context;
        mExtras = builder.extras;
        mFlags = builder.flags;
        if (builder.interceptors != null) {
            mInterceptors.addAll(builder.interceptors);
        }
    }

    public void route(RouterCallback callback) {
        RouterMeta meta = RouterTable.getInstance().getRouterMeta(mPath);
        RLog.i("Dispatcher", meta == null ? "not found RouterMeta for path=" + mPath : "find RouterMeta:" + meta.toString());

        List<Interceptor> tmp = new ArrayList<>();
        tmp.add(new ParamsCheckInterceptor());
        tmp.addAll(mInterceptors);
        tmp.add(new RealCallInterceptor());

        mInterceptors.clear();
        mInterceptors.addAll(tmp);

        InterceptorDispatcher interceptorDispatcher = new InterceptorDispatcher(this, meta, 0, callback);
        interceptorDispatcher.proceed();

    }

    public void route() {
        route(null);
    }

    protected Intent createIntent(Class<?> destination, boolean isActivity) {
        Intent intent = new Intent(mContext, destination);
        intent.addFlags(mFlags);
        intent.putExtras(mExtras);
        if (mUri != null && !TextUtils.isEmpty(mUri.toString()))
            intent.putExtra(RAW_URI, mUri);

        if (isActivity && !(mContext instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        return intent;
    }

    public int getRequestCode() {
        return mRequestCode;
    }


    public String getPath() {
        return mPath;
    }

    public Uri getUri() {
        return mUri;
    }

    public Bundle getExtras() {
        return mExtras;
    }

    public int getFlags() {
        return mFlags;
    }

    public List<Interceptor> getInterceptors() {
        return mInterceptors;
    }


    public Context getContext() {
        return mContext;
    }

}
