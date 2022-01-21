package com.spiderman.file.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

@Document(collection = "files")
public class FileEntity implements Serializable {

    @Id
    @Field("file_id")
    private String fileId;
    @Field("file_name")
    private String fileName;
    @Field("file_code")
    private String fileCode;
    @Field("file_size")
    private Long fileSize;

    @Field("create_time")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date createTime;

    private CommonsMultipartFile file;
    @Field("blogs_id")
    private String blogsId;
    @Field("field_id")
    private String fieldId;

    public FileEntity(String fileId, String fileName, Long fileSize, String blogsId, String fieldId) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.createTime = new Date();
        this.blogsId = blogsId;
        this.fieldId = fieldId;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileCode() {
        return fileCode;
    }

    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

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

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public String toString() {
        return "FileEntity{" +
                "fileId='" + fileId + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileCode='" + fileCode + '\'' +
                ", createTime=" + createTime +
                ", file=" + file +
                ", blogsId='" + blogsId + '\'' +
                ", fieldId='" + fieldId + '\'' +
                '}';
    }
}
