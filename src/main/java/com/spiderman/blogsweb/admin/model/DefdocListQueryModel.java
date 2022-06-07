package com.spiderman.blogsweb.admin.model;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;


/**
 * 档案列表查询表格 使用model
 */
public class DefdocListQueryModel {

    private String defdoclistid;
    private String defdoclistname;
    private String defdoclistcode;
    private int enablement = -1;
    private int current = 1;
    private int pageSize = 20;
    private int dr =0;

    public Query getQuery(){
        Query query = new Query();
        query.addCriteria(Criteria.where("dr").is(0));
        if (StringUtils.hasText(defdoclistid)){
            query.addCriteria(Criteria.where("_id").is(defdoclistid));
        }
        if (StringUtils.hasText(defdoclistname)){
            query.addCriteria(Criteria.where("defdoclistname").regex(".*?"+defdoclistname+".*"));
        }
        if (StringUtils.hasText(defdoclistcode)){
            query.addCriteria(Criteria.where("defdoclistcode").regex(".*?"+defdoclistcode+".*"));
        }
        if (enablement > -1){
            query.addCriteria(Criteria.where("enablement").is(enablement));
        }
        return query;
    }

    public int getDr() {
        return dr;
    }

    public void setDr(int dr) {
        this.dr = dr;
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

    public int getEnablement() {
        return enablement;
    }

    public void setEnablement(int enablement) {
        this.enablement = enablement;
    }

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
}
