package com.spiderman.defdoc.vo;

import org.springframework.data.annotation.Id;

public class DefdocVO {

    private String defdoclistid;
    private String defdocid;
    private String defdocname;
    private String defdoccode;

    public String getDefdoclistid() {
        return defdoclistid;
    }

    public void setDefdoclistid(String defdoclistid) {
        this.defdoclistid = defdoclistid;
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
}