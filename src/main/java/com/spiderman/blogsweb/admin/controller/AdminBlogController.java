package com.spiderman.blogsweb.admin.controller;

import com.spiderman.blogsweb.admin.model.Result;
import com.spiderman.blogsweb.admin.model.TableListResult;
import com.spiderman.blogsweb.blogs.converter.BlogType;
import com.spiderman.blogsweb.blogs.entity.BlogsListEntity;
import com.spiderman.blogsweb.blogs.service.BlogsQueryService;
import com.spiderman.blogsweb.blogs.service.BlogsSaveService;
import com.spiderman.blogsweb.blogs.vo.BlogsDefaultVO;
import com.spiderman.blogsweb.blogs.vo.BlogsLinkVO;
import com.spiderman.blogsweb.blogs.vo.BlogsListVO;
import com.spiderman.blogsweb.blogs.vo.BlogsSayingVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
public class AdminBlogController {

    private static final Logger log = LogManager.getLogger(AdminBlogController.class.getName());

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



    @RequestMapping("/list/queryPages")
    @ResponseBody
    public TableListResult queryPages(@RequestBody BlogsListVO querySearch){
        TableListResult result = TableListResult.instance();
        try {
            Page<BlogsListEntity> blogslistEntities = query.queryAll(querySearch);
            List<BlogsListVO> blogsListVOS = query.queryAllTitle(blogslistEntities.getContent());
            result.success();
            result.setData(querySearch.getCurrent(),querySearch.getPageSize(),blogslistEntities.getTotalElements(),blogsListVOS);
        }catch (Exception e){
            result.fail();
            result.setErrorMessage("查询失败！" + e.getMessage());
            log.error("博客列表查询失败！" + e.getMessage());
        }
        return result;
    }

    @RequestMapping("/saying/save")
    @ResponseBody
    public Result sayingSave(@RequestBody BlogsSayingVO blogsSayingVO){
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

    @GetMapping("/{type}/{id}")
    @ResponseBody
    public Result blogQuery(@PathVariable("id") String id, @PathVariable("type") String type){
        Result result = Result.instance();
        try {
            if (!StringUtils.hasText(type) || !StringUtils.hasText(id)){
                throw new Exception("查询参数不能为空!");
            }
            BlogType blogType = BlogType.valueOf(type);
            if (!StringUtils.hasText(blogType.value())){
                throw new Exception("查询类型不正确!");
            }
            result.success();
            result.put("data",query.query(id, blogType));
        }catch (Exception e){
            result.fail();
            result.setErrorMessage("查询详情失败！" + e.getMessage());
            log.error("查询详情失败！" + e.getMessage());
        }
        return result;
    }

    @RequestMapping("/link/save")
    @ResponseBody
    public Result linkSave(@RequestBody BlogsLinkVO blogsLinkVO){
        Result result = Result.instance();
        try {
            if (!StringUtils.hasText(blogsLinkVO.getSketch()) || !StringUtils.hasText(blogsLinkVO.getUrl())){
                throw new Exception("描述或链接地址不能为空!");
            }
            BlogsLinkVO back;
            if (StringUtils.hasText(blogsLinkVO.getId())){
                back = save.updateLink(blogsLinkVO);
            }else {
                back = save.addLink(blogsLinkVO);
            }
            result.success();
            result.put("data",back);
        }catch (Exception e){
            result.fail();
            result.setErrorMessage("保存失败！" + e.getMessage());
            log.error("链接保存失败！" + e.getMessage());
        }
        return result;
    }

    @RequestMapping("/default/save")
    @ResponseBody
    public Result defaultSave(@RequestBody BlogsDefaultVO blogsDefaultVO){
        Result result = Result.instance();
        try {
//            if (!StringUtils.hasText(blogsDefaultVO.getTitle())
//                    || !StringUtils.hasText(blogsDefaultVO.get())){
//                throw new Exception("描述或链接地址不能为空!");
//            }
            BlogsDefaultVO back;
            if (StringUtils.hasText(blogsDefaultVO.getId())){
                back = save.updateDefault(blogsDefaultVO);
            }else {
                back = save.addDefault(blogsDefaultVO);
            }
            result.success();
            result.put("data",back);
        }catch (Exception e){
            result.fail();
            result.setErrorMessage("保存失败！" + e.getMessage());
            log.error("博客保存失败！" + e.getMessage());
        }
        return result;
    }

}
