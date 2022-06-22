package com.spiderman.blogsweb.blogs.vo;

import com.spiderman.blogsweb.base.model.BaseModel;

public class BlogsDefaultImagesVO extends BaseModel {

    //父id
    private String blogid;

    //图片id
    private String imageid;

    public String getBlogid() {
        return blogid;
    }

    public void setBlogid(String blogid) {
        this.blogid = blogid;
    }

    public String getImageid() {
        return imageid;
    }

    public void setImageid(String imageid) {
        this.imageid = imageid;
    }
}
