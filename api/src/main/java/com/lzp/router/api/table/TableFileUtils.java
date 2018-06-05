package com.lzp.router.api.table;

import com.lzp.router.api.utils.RLog;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 解析路由表
 */
class TableFileUtils {
    public static Map<String, Map<String, RouterMeta>> parseTableFile(InputStream inputStream) {
        return parseTableFileInner(inputStream);
    }

    private static Map<String, Map<String, RouterMeta>> parseTableFileInner(InputStream inputStream) {
        Map<String, Map<String, RouterMeta>> routerTables = new LinkedHashMap<>();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String content = null;
            while ((content = reader.readLine()) != null) {
                parseRouter(content, routerTables);
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return routerTables;
    }

    private static void parseRouter(String content, Map<String, Map<String, RouterMeta>> routerTables) {
        RouterMeta.Builder builder = new RouterMeta.Builder();
        try {
            if (content != null && !content.equals("")) {
                //[path,destination,type]
                if (content.startsWith("[") && content.endsWith("]")) {
                    content = content.substring(1, content.length() - 1);
                    String[] strs = content.split(",");
                    int len = strs.length;
                    for (int i = 0; i < len; i++) {
                        int pos = strs[i].indexOf(":");
                        String key = strs[i].substring(0, pos);
                        String value = strs[i].substring(pos + 1, strs[i].length());
                        builder.setValue(key, value);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        RouterMeta meta = builder.build();

        Map<String, RouterMeta> routers = routerTables.get(meta.getGroup());
        if (routers == null) {
            routers = new HashMap<>();
            routerTables.put(meta.getGroup(), routers);
        }
        routers.put(meta.getPath(), meta);
        RLog.w("TableFileUtils", "find a router info " + meta.toString());
    }
}
