package com.spiderman.blogsweb.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AdminLoginController {

    private static final Logger log = LogManager.getLogger(AdminLoginController.class.getName());

    @Autowired
    private MongoTemplate mongoTemplate;

    @RequestMapping("/login")
    @ResponseBody
    public JSONObject login(HttpServletRequest request, HttpServletResponse response) {
        JSONObject back = new JSONObject();
        try {
            //获取访问ip
            String ip = request.getRemoteAddr();
            //校验访问ip

            back.put("data",ip);
            back.put("status",1);
        }catch (Exception e){
            back.put("status",0);
            back.put("msg","检查失败：" + e.getMessage());
            log.error("检查失败：" + e.getMessage());
        }
        return back;
    }

}
