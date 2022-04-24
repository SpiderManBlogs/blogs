package com.spiderman.blogsweb.blogs.vo;

public class BlogsSayingVO {

    private String id;

    //名言
    private String saying;

    //出处
    private String provenance;

    public String getSaying() {
        return saying;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSaying(String saying) {
        this.saying = saying;
    }

    public String getProvenance() {
        return provenance;
    }

    public void setProvenance(String provenance) {
        this.provenance = provenance;
    }
}
