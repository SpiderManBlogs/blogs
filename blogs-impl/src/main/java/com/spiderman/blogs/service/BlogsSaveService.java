package com.spiderman.blogs.service;

import com.spiderman.blogs.vo.BlogsDefaultVO;
import com.spiderman.blogs.vo.BlogsLinkVO;
import com.spiderman.blogs.vo.BlogsSayingVO;

public interface BlogsSaveService {

    /**
     * 名言保存
     * @param saying 名言vo
     * @return 保存后数据
     */
    BlogsSayingVO saveSaying(BlogsSayingVO saying);

    /**
     * 链接保存
     * @param link 链接VO
     * @return 保存后数据
     */
    BlogsLinkVO saveLink(BlogsLinkVO link);

    /**
     * 基本文章保存
     * @param defaultVO 文章VO
     * @return 保存后数据
     */
    BlogsDefaultVO save(BlogsDefaultVO defaultVO);
}
