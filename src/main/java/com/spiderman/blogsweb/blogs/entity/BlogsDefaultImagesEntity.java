package com.spiderman.blogsweb.blogs.entity;

import com.spiderman.blogsweb.base.entity.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "blogs_default_images")
@SQLDelete(sql = "update blogs_default_images set dr = 1 where id = ?")
@Where(clause = "dr = 0")
public class BlogsDefaultImagesEntity extends BaseEntity {

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
