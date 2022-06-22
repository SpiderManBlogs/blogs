package com.spiderman.blogsweb.admin.controller;

import com.spiderman.blogsweb.admin.model.Result;
import com.spiderman.blogsweb.blogs.service.BlogsQueryService;
import com.spiderman.blogsweb.blogs.service.BlogsSaveService;
import com.spiderman.blogsweb.blogs.vo.BlogsSayingVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/blogs/saying")
public class AdminBlogsSayingController {

    private static final Logger log = LogManager.getLogger(AdminBlogsSayingController.class.getName());

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

    @RequestMapping("/save")
    @ResponseBody
    public Result queryPages(BlogsSayingVO blogsSayingVO){
        Result result = Result.instance();
        try {
            if (!StringUtils.hasText(blogsSayingVO.getSaying()) || !StringUtils.hasText(blogsSayingVO.getProvenance())){
                throw new Exception("名言或出处不能为空!");
            }
            BlogsSayingVO back;
            if (StringUtils.hasText(blogsSayingVO.getId())){
                back = save.updateSaying(blogsSayingVO);
            }else {
                back = save.addSaying(blogsSayingVO);
            }
            result.success();
            result.put("data",back);
        }catch (Exception e){
            result.fail();
            result.setErrorMessage("保存失败！" + e.getMessage());
            log.error("名言保存失败！" + e.getMessage());
        }
        return result;
    }

}
