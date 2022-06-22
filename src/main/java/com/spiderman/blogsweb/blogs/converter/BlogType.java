package com.spiderman.blogsweb.blogs.converter;

import com.fasterxml.jackson.annotation.JsonValue;

public enum BlogType {
    SAYING(1,"名言"),
    LINK(2,"连接"),
    IMAGE(3,"图片"),
    IMAGES(4,"多图片"),
    VIDEO(5,"视频"),
    AUDIO(6,"音频");

    private Integer code;
    private String name;

    BlogType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @JsonValue
    public String value(){
        return name;
    }
}
