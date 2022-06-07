package com.spiderman.blogsweb.defdoc.vo;

public class DefdocListVO {
    private String defdoclistid;
    private String defdoclistname;
    private String defdoclistcode;

    //是否启动 0 否 1 是
    private int enablement;

    //描述
    private String goal;

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public int getEnablement() {
        return enablement;
    }

    public void setEnablement(int enablement) {
        this.enablement = enablement;
    }

    public String getDefdoclistid() {
        return defdoclistid;
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
}
