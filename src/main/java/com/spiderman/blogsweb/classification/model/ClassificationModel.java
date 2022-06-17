package com.spiderman.blogsweb.classification.model;

import com.spiderman.blogsweb.base.model.BaseModel;

public class ClassificationModel extends BaseModel {

    private String name;
    private boolean enable = true;
    private String memo;

    private int current = 1;
    private int pageSize = 20;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

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
