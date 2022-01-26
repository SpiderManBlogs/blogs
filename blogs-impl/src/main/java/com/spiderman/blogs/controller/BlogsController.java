package com.spiderman.blogs.controller;

import com.alibaba.fastjson.JSONObject;
import com.spiderman.blogs.service.BlogsSaveService;
import com.spiderman.blogs.vo.BlogsLinkVO;
import com.spiderman.blogs.vo.BlogsSayingVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/blogs")
public class BlogsController {

    private static final Logger log = LogManager.getLogger(BlogsController.class.getName());

    @Autowired
    private BlogsSaveService blogsSaveService;

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
            log.error("saveSaying保存失败：" + e.getMessage());
        }
        return back;
    }

}