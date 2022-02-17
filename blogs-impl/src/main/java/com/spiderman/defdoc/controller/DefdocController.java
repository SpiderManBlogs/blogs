package com.spiderman.defdoc.controller;

import com.alibaba.fastjson.JSONObject;
import com.spiderman.defdoc.service.DefdocQueryService;
import com.spiderman.defdoc.vo.DefdocListVO;
import com.spiderman.defdoc.vo.DefdocVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/defdoc")
public class DefdocController {

    private static final Logger log = LogManager.getLogger(DefdocController.class.getName());

    @Autowired
    private DefdocQueryService defdocQueryService;

    @RequestMapping("/queryList")
    @ResponseBody
    public JSONObject queryList() {
        JSONObject back = new JSONObject();
        try {
            List<DefdocListVO> backvo = defdocQueryService.query();
            back.put("data",backvo);
            back.put("status",1);
        }catch (Exception e){
            back.put("status",0);
            back.put("msg","查询失败：" + e.getMessage());
            log.error("queryList查询失败：" + e.getMessage());
        }
        return back;
    }

    @RequestMapping("/query")
    @ResponseBody
    public JSONObject query(@RequestBody JSONObject json) {
        JSONObject back = new JSONObject();
        try {
            String id = json.getString("id");
            List<DefdocVO> backvo = defdocQueryService.query(id);
            back.put("data",backvo);
            back.put("status",1);
        }catch (Exception e){
            back.put("status",0);
            back.put("msg","查询失败：" + e.getMessage());
            log.error("query查询失败：" + e.getMessage());
        }
        return back;
    }

}
