package com.spiderman.blogsweb.blogs.entity;

import com.spiderman.blogsweb.base.entity.BaseEntity;
import com.spiderman.blogsweb.blogs.converter.BlogType;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 博客列表\顺序
 */
@Entity
@Table(name = "blogs_list")
@SQLDelete(sql = "update blogs_list set dr = 1 where id = ?")
@Where(clause = "dr = 0")
public class BlogsListEntity extends BaseEntity {

    private String blogid;

    private BlogType blogtype;

    private long listorder;

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
