package com.lzp.router.api;

public interface RouterCallback {
    /**
     * 根据path没有找到对应的路由信息时回调
     *
     * @param path
     */
    void onNotFound(String path);

    /**
     * 跳转处理完后调用
     * 回调发生在route()return 之前
     */
    void onHandled();

    /**
     * 处理跳转过程中发生了异常
     * 回调发生在route()return 之前
     */
    void onError(String errorMsg);
}
