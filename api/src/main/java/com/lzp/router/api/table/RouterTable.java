package com.lzp.router.api.table;

import android.content.Context;

import com.lzp.router.api.utils.RLog;
import com.lzp.router.api.utils.RouterUtils;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class RouterTable {
    private static volatile RouterTable sInstance;
    private Map<String, Map<String, RouterMeta>> mRouterTables;

    private RouterTable() {
    }

    public static RouterTable getInstance() {
        if (sInstance == null) {
            synchronized (RouterTable.class) {
                if (sInstance == null) {
                    sInstance = new RouterTable();
                }
            }
        }
        return sInstance;
    }

    public RouterMeta getRouterMeta(String path) {
        String group = RouterUtils.extractGroup(path);
        if (mRouterTables == null || mRouterTables.isEmpty()) {
            return null;
        }
        return mRouterTables.get(group).get(path);
    }

    public void initRouterTable(Context context) throws ClassNotFoundException {
        if (mRouterTables != null) {
            mRouterTables.clear();
        } else {
            mRouterTables = new LinkedHashMap<>();
        }

        try {
            String[] fileLists = context.getAssets().list("router");
            for (String file : fileLists) {
                mRouterTables.putAll(TableFileUtils.parseTableFile(context.getAssets().open("router/" + file)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        RLog.i("RouterTable", "initRouterTable end " + mRouterTables.size() + " group");
    }

    public boolean isInite() {
        return mRouterTables != null && !mRouterTables.isEmpty();
    }
}
