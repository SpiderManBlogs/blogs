package com.spiderman.blogsweb.blogs.entity;

import com.spiderman.blogsweb.base.entity.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "blogs_default_images")
@SQLDelete(sql = "update blogs_default_images set dr = 1 where id = ?")
@Where(clause = "dr = 0")
public class BlogsDefaultImagesEntity extends BaseEntity {

    //父id
    @ManyToOne
    private BlogsDefaultEntity blogid;

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

    public BlogsDefaultEntity getBlogid() {
        return blogid;
    }

    public void setBlogid(BlogsDefaultEntity blogid) {
        this.blogid = blogid;
    }

    public String getImageid() {
        return imageid;
    }

    public void setImageid(String imageid) {
        this.imageid = imageid;
    }
}
