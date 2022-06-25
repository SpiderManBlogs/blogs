package com.spiderman.blogsweb.tag.entity;

import com.spiderman.blogsweb.base.entity.BaseEntity;
import com.spiderman.blogsweb.blogs.entity.BlogsDefaultEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "tag_library")
@SQLDelete(sql = "update tag_library set dr = 1 where id = ?")
@Where(clause = "dr = 0")
public class TagLibraryEntity extends BaseEntity {

    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<BlogsDefaultEntity> blog;

    public List<BlogsDefaultEntity> getBlog() {
        return blog;
    }

    public void setBlog(List<BlogsDefaultEntity> blog) {
        this.blog = blog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
