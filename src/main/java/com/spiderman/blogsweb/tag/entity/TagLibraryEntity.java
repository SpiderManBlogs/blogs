package com.spiderman.blogsweb.tag.entity;

import com.spiderman.blogsweb.base.entity.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tag_library")
@SQLDelete(sql = "update tag_library set dr = 1 where id = ?")
@Where(clause = "dr = 0")
public class TagLibraryEntity extends BaseEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
