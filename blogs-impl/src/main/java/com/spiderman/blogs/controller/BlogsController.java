package com.spiderman.blogs.controller;

import com.alibaba.fastjson.JSONObject;
import com.spiderman.blogs.service.BlogsQueryService;
import com.spiderman.blogs.service.BlogsSaveService;
import com.spiderman.blogs.vo.BlogsLinkVO;
import com.spiderman.blogs.vo.BlogsListVO;
import com.spiderman.blogs.vo.BlogsSayingVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/blogs")
public class BlogsController {

    private static final Logger log = LogManager.getLogger(BlogsController.class.getName());

    @Autowired
    private BlogsSaveService blogsSaveService;

    @Autowired
    private BlogsQueryService blogsQueryService;

    @RequestMapping("/saveSaying")
    @ResponseBody
    public JSONObject saveSaying(@RequestBody BlogsSayingVO saying) {
        JSONObject back = new JSONObject();
        try {
            if (StringUtils.isEmpty(saying.getSaying()) || StringUtils.isEmpty(saying.getProvenance())){
                throw new Exception("名言或出处不能为空!");
            }
            BlogsSayingVO backvo = blogsSaveService.saveSaying(saying);
            back.put("data",backvo);
            back.put("status",1);
        }catch (Exception e){
            back.put("status",0);
            back.put("msg","保存失败：" + e.getMessage());
            log.error("saveSaying保存失败：" + e.getMessage());
        }
        return back;
    }

    @RequestMapping("/saveLink")
    @ResponseBody
    public JSONObject saveLink(@RequestBody BlogsLinkVO link) {
        JSONObject back = new JSONObject();
        try {
            if (StringUtils.isEmpty(link.getSketch()) || StringUtils.isEmpty(link.getUrl())){
                throw new Exception("简述或链接地址不能为空!");
            }
            BlogsLinkVO backvo = blogsSaveService.saveLink(link);
            back.put("data",backvo);
            back.put("status",1);
        }catch (Exception e){
            back.put("status",0);
            back.put("msg","保存失败：" + e.getMessage());
            log.error("saveLink保存失败：" + e.getMessage());
        }
        return back;
    }

    @RequestMapping("/queryList")
    @ResponseBody
    public JSONObject queryList(@RequestBody JSONObject page) {
        JSONObject back = new JSONObject();
        try {
            int pageSize = page.containsKey("pageSize") ? page.getInteger("pageSize") : 20;
            int pageThis = page.containsKey("pageThis") ? page.getInteger("pageThis") : 0;
            List<BlogsListVO> backvo = blogsQueryService.queryPage(pageSize, pageThis);
            long count = blogsQueryService.queryCount();
            back.put("data",backvo);
            back.put("count",count);
            back.put("pageSize",pageSize);
            back.put("pageThis",pageThis);
            back.put("status",1);
        }catch (Exception e){
            back.put("status",0);
            back.put("msg","查询失败：" + e.getMessage());
            log.error("queryList查询失败：" + e.getMessage());
        }
        return back;
    }

    @RequestMapping("/queryCard")
    @ResponseBody
    public JSONObject queryCard(@RequestBody JSONObject json) {
        JSONObject back = new JSONObject();
        try {
            String id = json.getString("id");
            String type = json.getString("type");
            if (StringUtils.isEmpty(type) || StringUtils.isEmpty(id)){
                throw new Exception("查询参数不能为空!");
            }
            Object backvo = blogsQueryService.queryById(id,type);
            back.put("data",backvo);
            back.put("status",1);
        }catch (Exception e){
            back.put("status",0);
            back.put("msg","保存失败：" + e.getMessage());
            log.error("queryCard查询失败：" + e.getMessage());
        }
        return back;
    }

}
