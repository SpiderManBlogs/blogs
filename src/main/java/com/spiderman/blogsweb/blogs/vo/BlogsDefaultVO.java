package com.spiderman.blogsweb.blogs.vo;

import com.spiderman.blogsweb.base.model.BaseModel;
import com.spiderman.blogsweb.blogs.converter.BlogType;
import com.spiderman.blogsweb.classification.model.ClassificationModel;
import com.spiderman.blogsweb.tag.model.TagLibraryModel;

import java.util.List;

public class BlogsDefaultVO extends BaseModel {

    private List<BlogsDefaultImagesVO> images;
    private String multimedia;
    private String title;
    private String description;
    private ClassificationModel classify;
    private List<TagLibraryModel> tags;
    private String content;
    private String id;
    //类型 image\images\audio\video
    private BlogType blogtype;

    //下一篇
    private String nextblogid;
    //上一篇
    private String prevblogid;

    // 创建人
    private String creator;

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public List<BlogsDefaultImagesVO> getImages() {
        return images;
    }

    public void setImages(List<BlogsDefaultImagesVO> images) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ClassificationModel getClassify() {
        return classify;
    }

    public void setClassify(ClassificationModel classify) {
        this.classify = classify;
    }

    public List<TagLibraryModel> getTags() {
        return tags;
    }

    public void setTags(List<TagLibraryModel> tags) {
        this.tags = tags;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public BlogType getBlogtype() {
        return blogtype;
    }

    public void setBlogtype(BlogType blogtype) {
        this.blogtype = blogtype;
    }

    public String getNextblogid() {
        return nextblogid;
    }

    public void setNextblogid(String nextblogid) {
        this.nextblogid = nextblogid;
    }

    public String getPrevblogid() {
        return prevblogid;
    }

    public void setPrevblogid(String prevblogid) {
        this.prevblogid = prevblogid;
    }
}
