package com.ttzf.enums.internetCafe;

public enum InternetCafeEnum {
    CAFE_NOTICE_ZANWU("暂无"),
    /**
     *  筛选条件
     * nearest 离我最近
     * cheapest 最便宜
     *
     */
    CAFE_NEAREST("nearest"),

    CAFE_CHEAPEST("cheapest"),

    CAFE_HOTEST("hotest")

    ;
    private String name;



    InternetCafeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
