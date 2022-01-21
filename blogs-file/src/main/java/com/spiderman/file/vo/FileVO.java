package com.spiderman.file.vo;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileVO {
    //文件流
    private CommonsMultipartFile file;
    //文章id
    private String blogsId;
    //字段id
    private String fieldId;

    public CommonsMultipartFile getFile() {
        return file;
    }

    public void setFile(CommonsMultipartFile file) {
        this.file = file;
    }

    public String getBlogsId() {
        return blogsId;
    }

    public void setBlogsId(String blogsId) {
        this.blogsId = blogsId;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }
}
