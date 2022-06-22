package com.spiderman.blogsweb.blogs.entity;

import com.spiderman.blogsweb.base.entity.BaseEntity;
import com.spiderman.blogsweb.blogs.converter.BlogType;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 链接引用实体
 */
@Entity
@Table(name = "blogs_link")
@SQLDelete(sql = "update blogs_link set dr = 1 where id = ?")
@Where(clause = "dr = 0")
public class BlogsLinkEntity extends BaseEntity {

    //简述
    @Column(length = 1000)
    private String sketch;

    //链接
    @Column(length = 1000)
    private String url;

    //类型 saying
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
