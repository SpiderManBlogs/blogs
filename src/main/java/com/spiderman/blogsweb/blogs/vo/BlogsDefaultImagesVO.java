package com.spiderman.blogsweb.blogs.vo;

import com.spiderman.blogsweb.base.model.BaseModel;

public class BlogsDefaultImagesVO extends BaseModel {

    //父id
    private BlogsDefaultVO blogid;

    //图片id
    private String imageid;

    //图片名称
    private String name;
    //图片大小
    private long size;
    //图片type
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BlogsDefaultVO getBlogid() {
        return blogid;
    }

    public void setBlogid(BlogsDefaultVO blogid) {
        this.blogid = blogid;
    }

    public String getImageid() {
        return imageid;
    }

    public void setImageid(String imageid) {
        this.imageid = imageid;
    }
}
