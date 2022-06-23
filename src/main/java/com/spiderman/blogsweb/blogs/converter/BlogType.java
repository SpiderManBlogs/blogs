package com.spiderman.blogsweb.blogs.converter;

import com.fasterxml.jackson.annotation.JsonValue;

public enum BlogType {
    SAYING("1","名言"),
    LINK("2","链接"),
    IMAGE("3","图片"),
    IMAGES("4","多图片"),
    VIDEO("5","视频"),
    AUDIO("6","音频"),
    LIST("-1","列表");

    private String value;
    private String label;

    BlogType(String value, String label) {
        this.value = value;
        this.label = label;
    }

    @JsonValue
    public String value(){
        return value;
    }
}
