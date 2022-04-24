package com.spiderman.blogsweb.blogs.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

/**
 * 链接引用实体
 */
@Document(collection = "blogs_link")
public class BlogsLinkEntity implements Serializable {

    public BlogsLinkEntity(String type) {
        this.dr = 0;
        this.createTime = new Date();
        this.type = type;
    }

    //简述
    private String sketch;

    //链接
    private String url;

    @Id
    private String id;

    //类型 saying
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

    public String getSketch() {
        return sketch;
    }

    public void setSketch(String sketch) {
        this.sketch = sketch;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
