package com.ttzf.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

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


    public static void validDate(BindingResult result) throws Exception {
        if (result.hasFieldErrors()) {
            String msg = result.getFieldErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.joining(","));
            throw new Exception(msg);
        }
    }

    public static void writeContent(String content) {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getResponse();
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "text/plain;charset=UTF-8");
        response.setHeader("icop-content-type", "exception");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();

            writer.print((content == null) ? "" : content);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
