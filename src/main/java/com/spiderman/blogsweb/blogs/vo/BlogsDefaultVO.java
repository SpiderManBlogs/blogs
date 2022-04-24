package com.spiderman.blogsweb.blogs.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spiderman.blogsweb.defdoc.vo.DefdocVO;

import java.util.Date;
import java.util.List;

public class BlogsDefaultVO {

    private List<String> images;
    private String multimedia;
    private String title;
    private String describe;
    private DefdocVO classify;
    private List<String> tag;
    private String content;
    private String id;
    //类型 image\images\audio\video
    private String type;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    private String create;


    //下一篇
    private String next;
    //上一篇
    private String prev;

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrev() {
        return prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(String multimedia) {
        this.multimedia = multimedia;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public DefdocVO getClassify() {
        return classify;
    }

    public void setClassify(DefdocVO classify) {
        this.classify = classify;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreate() {
        return create;
    }

    public void setCreate(String create) {
        this.create = create;
    }
}
