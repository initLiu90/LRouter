package com.lzp.router.api.logistics;

import com.lzp.router.api.RouterCallback;
import com.lzp.router.api.table.RouterMeta;

class InterceptorDispatcher implements Interceptor.Chain {
    private int mIndex;
    private Router mRouter;
    private RouterMeta mMeta;
    private RouterCallback mCallback;

    public InterceptorDispatcher(Router router, RouterMeta meta, int index, RouterCallback callback) {
        mRouter = router;
        mIndex = index;
        mMeta = meta;
        mCallback = callback;
    }

    @Override
    public boolean proceed() {
        if (mIndex >= mRouter.getInterceptors().size())
            throw new IndexOutOfBoundsException("index is out of interceptor's size. Current index is " + mIndex + " interceptors's size is " + mRouter.getInterceptors().size());

        InterceptorDispatcher next = new InterceptorDispatcher(mRouter, mMeta, mIndex + 1, mCallback);
        Interceptor interceptor = mRouter.getInterceptors().get(mIndex);
        boolean result = interceptor.intercept(next);
        return result;
    }

    @Override
    public RouterMeta getRouterMeta() {
        return mMeta;
    }

    @Override
    public Router getRouter() {
        return mRouter;
    }

    @Override
    public RouterCallback getRouterCallback() {
        return mCallback;
    }
}
