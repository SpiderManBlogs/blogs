package com.spiderman.blogsweb.blogs.vo;

import com.spiderman.blogsweb.base.model.BaseModel;
import com.spiderman.blogsweb.blogs.converter.BlogType;

public class BlogsSayingVO extends BaseModel {

    //名言
    private String saying;

    //出处
    private String provenance;

    private BlogType blogtype;

    // 创建人
    private String creator;

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getSaying() {
        return saying;
    }

    public void setSaying(String saying) {
        this.saying = saying;
    }

    public String getProvenance() {
        return provenance;
    }

    public void setProvenance(String provenance) {
        this.provenance = provenance;
    }

    public BlogType getBlogtype() {
        return blogtype;
    }

    public void setBlogtype(BlogType blogtype) {
        this.blogtype = blogtype;
    }
}
