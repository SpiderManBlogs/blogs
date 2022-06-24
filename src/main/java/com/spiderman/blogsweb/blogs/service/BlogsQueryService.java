package com.spiderman.blogsweb.blogs.service;

import com.spiderman.blogsweb.blogs.converter.BlogType;
import com.spiderman.blogsweb.blogs.entity.BlogsListEntity;
import com.spiderman.blogsweb.blogs.vo.BlogsListVO;
import org.springframework.data.domain.Page;

public interface BlogsQueryService {

    /**
     * 分页查询博客列表
     * @param querySearch 查询条件
     * @return 分页结果
     */
    Page<BlogsListEntity> queryAll(BlogsListVO querySearch);

    Object query(String id, BlogType type) throws Exception;
}
