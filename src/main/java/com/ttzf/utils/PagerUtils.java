package com.ttzf.utils;

import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.Map;

public class PagerUtils {

    public static Map parserPager(String code, String msg, Long count, PageInfo pageInfo) {
        Map map = new HashMap();
        map.put("code", code);
        map.put("msg", msg);
        map.put("count", count);
        map.put("data", pageInfo.getList());
        return map;
    }
}
