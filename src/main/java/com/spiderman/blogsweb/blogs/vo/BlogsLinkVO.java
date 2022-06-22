package com.spiderman.blogsweb.blogs.vo;

import com.spiderman.blogsweb.base.model.BaseModel;
import com.spiderman.blogsweb.blogs.converter.BlogType;

public class BlogsLinkVO extends BaseModel {

    //简述
    private String sketch;
    //链接地址
    private String url;

    private BlogType blogtype;

    // 创建人
    private String creator;

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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

    public BlogType getBlogtype() {
        return blogtype;
    }

    public void setBlogtype(BlogType blogtype) {
        this.blogtype = blogtype;
    }
}
