package com.spiderman.blogsweb.admin.model;

import com.alibaba.fastjson.JSONObject;
import com.spiderman.blogsweb.admin.consts.ResultConsts;

public class Result extends JSONObject {

    public Result setData(Object o,String key){
        if (this.containsKey(ResultConsts.DATA)){
            this.getJSONObject(ResultConsts.DATA).put(key,o);
        }else{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(key,o);
            this.put(ResultConsts.DATA,jsonObject);
        }
        return this;
    }

    public static Result instance() {
        return new Result();
    }

    public Result success() {
        return this.setErrorCode(ResultConsts.CODE_SUCCESS);
    }

    public Result fail(){
        return fail(ResultConsts.CODE_SERVER_ERR);
    }

    public Result fail(String code) {
        return this.setErrorCode(code);
    }

    public Result setErrorCode(String code) {
        this.put(ResultConsts.ERROR_CODE, code);
        return this;
    }

    public Result setErrorMessage(String errorMessage) {
        this.put(ResultConsts.ERROR_MSG, errorMessage);
        return this;
    }
}
