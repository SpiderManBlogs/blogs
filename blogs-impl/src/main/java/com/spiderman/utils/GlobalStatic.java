package com.spiderman.utils;

import com.spiderman.blogs.entity.BlogsDefaultEntity;
import com.spiderman.blogs.entity.BlogsLinkEntity;
import com.spiderman.blogs.entity.BlogsSayingEntity;

public class GlobalStatic {

    /**
     * 文章类型
     */
    //名言引用类
    public static final String TYPE_SAYING = "saying";
    //单个图片类
    public static final String TYPE_IMAGE = "image";
    //多个图片类
    public static final String TYPE_IMAGES = "images";
    //音频类
    public static final String TYPE_AUDIO = "audio";
    //视频类
    public static final String TYPE_VIDEO = "video";
    //链接跳转类型
    public static final String TYPE_LINK = "link";

    public static boolean isCardType(String type) throws Exception {
        switch (type){
            case GlobalStatic.TYPE_SAYING:
            case GlobalStatic.TYPE_LINK:
                return false;
            case GlobalStatic.TYPE_IMAGE:
            case GlobalStatic.TYPE_IMAGES:
            case GlobalStatic.TYPE_AUDIO:
            case GlobalStatic.TYPE_VIDEO:
                return true;
            default:
                throw new Exception("类型不正确");
        }
    }

    /**
     * 获取类型对应Class
     * @param type 类型
     * @return Class
     * @throws Exception 类型不正确
     */
    public static Class getClass(String type) throws Exception {
        switch (type){
            case GlobalStatic.TYPE_SAYING:
                return BlogsSayingEntity.class;
            case GlobalStatic.TYPE_LINK:
                return BlogsLinkEntity.class;
            case GlobalStatic.TYPE_IMAGE:
            case GlobalStatic.TYPE_IMAGES:
            case GlobalStatic.TYPE_AUDIO:
            case GlobalStatic.TYPE_VIDEO:
                return BlogsDefaultEntity.class;
            default:
                throw new Exception("类型不正确");
        }
    }

}
