package com.spiderman.blogsweb.blogs.entity;

import com.spiderman.blogsweb.base.entity.BaseEntity;
import com.spiderman.blogsweb.blogs.converter.BlogType;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 名言引用实体
 */
@Entity
@Table(name = "blogs_saying")
@SQLDelete(sql = "update blogs_saying set dr = 1 where id = ?")
@Where(clause = "dr = 0")
public class BlogsSayingEntity extends BaseEntity {

    //名言
    @Column(length = 1000)
    private String saying;

    //出处
    @Column(length = 1000)
    private String provenance;

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
