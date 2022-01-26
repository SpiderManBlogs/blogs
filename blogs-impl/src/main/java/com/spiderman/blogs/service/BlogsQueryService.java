package com.spiderman.blogs.service;

import com.spiderman.blogs.vo.BlogsListVO;

import java.util.List;

public interface BlogsQueryService {

    /**
     * 分页查询
     * @param pageSize 页大小
     * @param pageThis 当前页
     * @return 列表数据
     */
    List<BlogsListVO> queryPage(int pageSize, int pageThis);

    /**
     * 查询总条数
     * @return 总数
     */
    long queryCount();
}
