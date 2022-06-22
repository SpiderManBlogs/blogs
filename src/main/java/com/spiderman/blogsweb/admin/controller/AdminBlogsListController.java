package com.spiderman.blogsweb.admin.controller;

import com.spiderman.blogsweb.admin.model.TableListResult;
import com.spiderman.blogsweb.blogs.entity.BlogsListEntity;
import com.spiderman.blogsweb.blogs.service.BlogsQueryService;
import com.spiderman.blogsweb.blogs.service.BlogsSaveService;
import com.spiderman.blogsweb.blogs.vo.BlogsListVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/blogs/list")
public class AdminBlogsListController {

    private static final Logger log = LogManager.getLogger(AdminBlogsListController.class.getName());

    private BlogsQueryService query;
    private BlogsSaveService save;

    @Autowired
    public void setQuery(BlogsQueryService query) {
        this.query = query;
    }

    @Autowired
    public void setSave(BlogsSaveService save) {
        this.save = save;
    }

    @GetMapping("/queryPages")
    @ResponseBody
    public TableListResult queryPages(BlogsListVO querySearch){
        TableListResult result = TableListResult.instance();
        try {
            Page<BlogsListEntity> blogslistEntities = query.queryAll(querySearch);
            result.success();
            result.setData(querySearch.getCurrent(),querySearch.getPageSize(),blogslistEntities.getTotalElements(),blogslistEntities.getContent());
        }catch (Exception e){
            result.fail();
            result.setErrorMessage("查询失败！" + e.getMessage());
            log.error("博客列表查询失败！" + e.getMessage());
        }
        return result;
    }

}
