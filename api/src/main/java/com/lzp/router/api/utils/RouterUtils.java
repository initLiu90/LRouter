package com.lzp.router.api.utils;

import android.support.v4.util.ArrayMap;

import com.lzp.router.api.exception.HandlerException;

public final class RouterUtils {
    public static ArrayMap<String, String> extractQuery(String querys) {
        if (TextUtils.isEmpty(querys)) return null;

        ArrayMap<String, String> queryMap = new ArrayMap<>();
        String[] strs = querys.split("&");
        int len = strs.length;
        for (int i = 0; i < len; i++) {
            String query = strs[i];
            int pos = query.indexOf("=");
            queryMap.put(query.substring(0, pos), query.substring(pos + 1, query.length()));
        }
        return queryMap;
    }

    public static String extractGroup(String path) {
        if (TextUtils.isEmpty(path) || !path.startsWith("/")) {
            throw new HandlerException("Extract the default group failed, the path must be start with '/' and contain more than 2 '/'!");
        }

        try {
            String defaultGroup = path.substring(1, path.indexOf("/", 1));
            if (TextUtils.isEmpty(defaultGroup)) {
                throw new HandlerException("Extract the default group failed! There's nothing between 2 '/'!");
            } else {
                return defaultGroup;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
