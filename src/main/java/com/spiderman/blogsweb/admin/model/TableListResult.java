package com.spiderman.blogsweb.admin.model;

import com.alibaba.fastjson.JSONObject;
import com.spiderman.blogsweb.admin.consts.ResultConsts;

/**
 * Ant Design Pro 查询表格返回规范
 */
public class TableListResult extends JSONObject {

    public TableListResult setData(int current,int pageSize,int total,Object data){
        this.put("current",current);
        this.put("pageSize",pageSize);
        this.put("total",total);
        this.put("data",data);
        return this;
    }

    public TableListResult success() {
        return this.setErrorCode(ResultConsts.CODE_SUCCESS);
    }

    public static TableListResult instance() {
        return new TableListResult();
    }


    public TableListResult fail(){
        return fail(ResultConsts.CODE_SERVER_ERR);
    }

    public TableListResult fail(String code) {
        return this.setErrorCode(code);
    }

    public TableListResult setErrorCode(String code) {
        this.put(ResultConsts.ERROR_CODE, code);
        return this;
    }

    public TableListResult setErrorMessage(String errorMessage) {
        this.put(ResultConsts.ERROR_MSG, errorMessage);
        return this;
    }

}
