package com.spiderman.blogs.service;

import com.spiderman.blogs.vo.BlogsSayingVO;

public interface BlogsSaveService {

    /**
     * 名言保存
     * @param saying 名言vo
     * @return 保存后数据
     */
    BlogsSayingVO saveSaying(BlogsSayingVO saying);
}
