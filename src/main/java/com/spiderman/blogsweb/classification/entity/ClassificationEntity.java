package com.spiderman.blogsweb.classification.entity;

import com.spiderman.blogsweb.base.entity.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * 博客分类(类别)实体
 */
@Entity
@Table(name = "classification")
@SQLDelete(sql = "update classification set dr = 1 where id = ?")
@Where(clause = "dr = 0")
public class ClassificationEntity extends BaseEntity {

    private String name;
    private boolean enable;
    @Column(length = 1000)
    private String memo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
