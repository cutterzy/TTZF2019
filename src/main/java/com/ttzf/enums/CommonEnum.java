package com.ttzf.enums;

/**
 * Created by zy on 2019/3/27
 */
public enum CommonEnum {
    SUCCESS_CODE("success","成功"),
    FAIL_CODE("fail","失败"),
    CONSTANTS_CODE("code"),
    CONSTANTS_MSG("msg"),
    CONSTANTS_TOKEN("access_token"),
    CONSTANTS_DATA("data"),
    INSERT_SUCCESS("插入成功"),
    INSERT_ERRO("插入异常"),
    UPDATE_SUCCESS("更新成功"),
    UPDATE_ERRO("更新异常"),
    DELETE_SUCCESS("删除成功"),
    DELETE_ERRO("删除异常")
    ;
    private String code ;
    private String msg;

    CommonEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    CommonEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }}
