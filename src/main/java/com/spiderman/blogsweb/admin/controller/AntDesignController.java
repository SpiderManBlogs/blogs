package com.spiderman.blogsweb.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Alan Chen
 * @description 拦截Ant Design Pro访问路径
 * @date 2019/5/24
 */
@Controller
public class AntDesignController {
    /**
     * 配置url通配符，拦截多个地址
     * @return
     */
    @RequestMapping(value = {
            "/",
            "/user",
            "/user/**",
            "/console",
            "/console/**"
    })
    public String fowardIndex() {
        return "index";
    }
}