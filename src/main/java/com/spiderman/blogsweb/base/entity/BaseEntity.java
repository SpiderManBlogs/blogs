package com.spiderman.blogsweb.base.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "system_uuid")
    @GenericGenerator(name = "system_uuid",strategy = "uuid")
    @Column(nullable = false,length = 36)
    private String id;
    @Column(nullable = false)
    private int dr = 0;
    @Column(nullable = false)
    private Date ts;
    @Column(nullable = false)
    private String code;
    @Column(updatable = false)
    private Date createTime;
    private Date updateTime;


    @PreUpdate
    protected void update(){
        this.ts = new Date();
        this.updateTime = new Date();
    }

    @PrePersist
    protected void create(){
        this.createTime = new Date();
        this.ts = new Date();
        this.dr = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getDr() {
        return dr;
    }

    public void setDr(int dr) {
        this.dr = dr;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
