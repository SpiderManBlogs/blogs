package com.spiderman.defdoc.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * 档案内容
 */
@Document(collection = "defdoc")
public class DefdocEntity implements Serializable {

    private ObjectId defdoclistid;
    @Id
    private String defdocid;
    private String defdocname;
    private String defdoccode;
    //是否启动 0 否 1 是
    private int enablement;
    //是否删除 0 否 1 是
    private int dr;

    public String getDefdoclistid() {
        return defdoclistid.toString();
    }

    public void setDefdoclistid(String defdoclistid) {
        this.defdoclistid = new ObjectId(defdoclistid);
    }

    public String getDefdocid() {
        return defdocid;
    }

    public void setDefdocid(String defdocid) {
        this.defdocid = defdocid;
    }

    public String getDefdocname() {
        return defdocname;
    }

    public void setDefdocname(String defdocname) {
        this.defdocname = defdocname;
    }

    public String getDefdoccode() {
        return defdoccode;
    }

    public void setDefdoccode(String defdoccode) {
        this.defdoccode = defdoccode;
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
