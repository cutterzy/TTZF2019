package com.ttzf.utils;

import com.alibaba.fastjson.JSONObject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by zy on 2019/3/27
 */
public class CommonUtils {

    public static boolean isNotEmpty(Object object) {
        if (object instanceof String) {
            return object != null && object != "";
        }
        return object != null;
    }

    public static boolean isEmpty(Object object) {
        return !isNotEmpty(object);
    }

    public static String getNowDate() {
        return LocalDate.now().toString();
    }

    public static String getNowDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

    /**
     *  对象转json
     * @param object
     * @return
     */
    public static String ObjectToJson(Object object) {
        return JSONObject.toJSONString(object);
    }

    public static Object JsonToObject(String json, Object object) {
        return JSONObject.parseObject(json, object.getClass());
    }



}
