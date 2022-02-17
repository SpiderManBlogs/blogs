package com.spiderman.blogs.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 一般文章实体
 * 包含单图片、多图片、音频、视频
 */
@Document(collection = "blogs_default")
public class BlogsDefaultEntity implements Serializable {

    //首页图片
    private String images;

    //多媒体
    private String multimedia;

    //标题
    private String title;

    //描述
    private String describe;

    //分类
    private String classify;

    //标签
    private List<String> tag;

    //下一篇
    private String next;

    //内容
    private List<BlogsDefaultTableEntity> tables;

    @Id
    private String id;

    //类型 image\images\audio\video
    private String type;

    //创建人
    @Field("create_time")
    private Date createTime;
    private String create;

    //修改人
    @Field("modifier_time")
    private Date modifierTime;
    private String modifier;

    //是否删除 0 否 1 是
    private int dr;

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(String multimedia) {
        this.multimedia = multimedia;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public List<BlogsDefaultTableEntity> getTables() {
        return tables;
    }

    public void setTables(List<BlogsDefaultTableEntity> tables) {
        this.tables = tables;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreate() {
        return create;
    }

    public void setCreate(String create) {
        this.create = create;
    }

    public Date getModifierTime() {
        return modifierTime;
    }

    public void setModifierTime(Date modifierTime) {
        this.modifierTime = modifierTime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public int getDr() {
        return dr;
    }

    public void setDr(int dr) {
        this.dr = dr;
    }
}
