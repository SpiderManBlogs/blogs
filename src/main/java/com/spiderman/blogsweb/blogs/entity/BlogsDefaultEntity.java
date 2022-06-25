package com.spiderman.blogsweb.blogs.entity;

import com.spiderman.blogsweb.base.entity.BaseEntity;
import com.spiderman.blogsweb.blogs.converter.BlogType;
import com.spiderman.blogsweb.classification.entity.ClassificationEntity;
import com.spiderman.blogsweb.tag.entity.TagLibraryEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

/**
 * 一般文章实体
 * 包含单图片、多图片、音频、视频
 */
@Entity
@Table(name = "blogs_default")
@SQLDelete(sql = "update blogs_default set dr = 1 where id = ?")
@Where(clause = "dr = 0")
public class BlogsDefaultEntity extends BaseEntity {

    //首页图片
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "blogid_id")
    private List<BlogsDefaultImagesEntity> images;

    //多媒体
    private String multimedia;

    //标题
    private String title;

    //描述
    @Column(length = 1000)
    private String description;

    //分类
    @ManyToOne
    @JoinColumn(name = "classify_id")
    private ClassificationEntity classify;

    //标签
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "tags_id")
    private List<TagLibraryEntity> tags;

    //内容
    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;


    //类型 image\images\audio\video
    private BlogType blogtype;

    //下一篇
    private String nextblogid;

    // 创建人
    private String creator;

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public List<BlogsDefaultImagesEntity> getImages() {
        return images;
    }

    public void setImages(List<BlogsDefaultImagesEntity> images) {
        this.images = images;
    }

    public List<TagLibraryEntity> getTags() {
        return tags;
    }

    public void setTags(List<TagLibraryEntity> tags) {
        this.tags = tags;
    }

    public ClassificationEntity getClassify() {
        return classify;
    }

    public void setClassify(ClassificationEntity classify) {
        this.classify = classify;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
}
