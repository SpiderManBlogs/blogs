package com.spiderman.blogs.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * 博客列表\顺序
 */
@Document(collection = "blogs_list")
public class BlogsListEntity implements Serializable {

    public BlogsListEntity(String type) {
        this.type = type;
        this.dr = 0;
    }

    @Id
    private String id;

    private String blogid;

    private String type;

    private long order;

    private int dr;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBlogid() {
        return blogid;
    }

    public void setBlogid(String blogid) {
        this.blogid = blogid;
    }

    public long getOrder() {
        return order;
    }

    public void setOrder(long order) {
        this.order = order;
    }

    public int getDr() {
        return dr;
    }

    public void setDr(int dr) {
        this.dr = dr;
    }
}
