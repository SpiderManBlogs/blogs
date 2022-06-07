package com.spiderman.blogsweb.defdoc.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * 档案列表
 */
@Document(collection = "defdoclist")
public class DefdocListEntity implements Serializable {

    @Id
    private String defdoclistid;
    private String defdoclistname;
    private String defdoclistcode;
    //是否启动 0 否 1 是
    private int enablement;
    //是否删除 0 否 1 是
    private int dr;
    //描述
    private String goal;

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getDefdoclistid() {
        return defdoclistid;
    }

    public ObjectId getDefdoclist_id() {
        return new ObjectId(defdoclistid);
    }

    public void setDefdoclistid(String defdoclistid) {
        this.defdoclistid = defdoclistid;
    }

    public String getDefdoclistname() {
        return defdoclistname;
    }

    public void setDefdoclistname(String defdoclistname) {
        this.defdoclistname = defdoclistname;
    }

    public String getDefdoclistcode() {
        return defdoclistcode;
    }

    public void setDefdoclistcode(String defdoclistcode) {
        this.defdoclistcode = defdoclistcode;
    }

    public int getEnablement() {
        return enablement;
    }

    public void setEnablement(int enablement) {
        this.enablement = enablement;
    }

    public int getDr() {
        return dr;
    }

    public void setDr(int dr) {
        this.dr = dr;
    }
}
