package com.ttzf.enums;

/**
 * Created by zy on 2019/4/27
 */
public enum  RedisEnum {
    LOGIN_USER_INFO_("login_user_info_"),// 登录用的信息
    ;

    private String key;

    RedisEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
