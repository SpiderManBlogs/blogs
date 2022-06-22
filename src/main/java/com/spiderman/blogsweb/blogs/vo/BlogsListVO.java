package com.spiderman.blogsweb.blogs.vo;

import com.spiderman.blogsweb.base.model.BaseModel;
import com.spiderman.blogsweb.blogs.converter.BlogType;

public class BlogsListVO extends BaseModel {

    private String blogid;

    private BlogType blogtype;

    private long listorder;

    private int current = 1;
    private int pageSize = 20;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getBlogid() {
        return blogid;
    }

    public void setBlogid(String blogid) {
        this.blogid = blogid;
    }

    public BlogType getBlogtype() {
        return blogtype;
    }

    public void setBlogtype(BlogType blogtype) {
        this.blogtype = blogtype;
    }

    public long getListorder() {
        return listorder;
    }

    public void setListorder(long listorder) {
        this.listorder = listorder;
    }
}
