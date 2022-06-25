package com.spiderman.blogsweb.blogs.service;


import com.spiderman.blogsweb.blogs.vo.BlogsDefaultVO;
import com.spiderman.blogsweb.blogs.vo.BlogsLinkVO;
import com.spiderman.blogsweb.blogs.vo.BlogsSayingVO;
import com.spiderman.blogsweb.utils.CheckoutException;

public interface BlogsSaveService {
    BlogsSayingVO addSaying(BlogsSayingVO blogsSayingVO);

    BlogsSayingVO updateSaying(BlogsSayingVO blogsSayingVO) throws CheckoutException;

    BlogsLinkVO addLink(BlogsLinkVO blogsLinkVO);

    BlogsLinkVO updateLink(BlogsLinkVO blogsLinkVO) throws CheckoutException;

    BlogsDefaultVO addDefault(BlogsDefaultVO blogsDefaultVO);

    BlogsDefaultVO updateDefault(BlogsDefaultVO blogsDefaultVO) throws CheckoutException;
}
