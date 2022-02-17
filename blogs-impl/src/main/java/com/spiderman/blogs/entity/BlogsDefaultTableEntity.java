package com.spiderman.blogs.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * 文章内容列表
 */
@Document(collection = "blogs_default_table")
public class BlogsDefaultTableEntity implements Serializable {



}
