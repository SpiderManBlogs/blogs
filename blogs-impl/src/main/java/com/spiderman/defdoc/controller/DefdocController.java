package com.spiderman.defdoc.controller;

import com.alibaba.fastjson.JSONObject;
import com.spiderman.defdoc.service.DefdocQueryService;
import com.spiderman.defdoc.service.DefdocService;
import com.spiderman.defdoc.vo.DefdocListVO;
import com.spiderman.defdoc.vo.DefdocVO;
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
@RequestMapping("/defdoc")
public class DefdocController {

    private static final Logger log = LogManager.getLogger(DefdocController.class.getName());

    @Autowired
    private DefdocQueryService defdocQueryService;

    @Autowired
    private DefdocService defdocService;

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

    @RequestMapping("/saveDefdoclist")
    @ResponseBody
    public JSONObject saveDefdoclist(@RequestBody DefdocListVO defdocList) {
        JSONObject back = new JSONObject();
        try {
            if (StringUtils.isEmpty(defdocList.getDefdoclistname()) || StringUtils.isEmpty(defdocList.getDefdoclistcode())){
                throw new Exception("名称或编码不能为空!");
            }
            DefdocListVO backvo = null;
            if(StringUtils.isEmpty(defdocList.getDefdoclistid())){
                //新增
                backvo = defdocService.add(defdocList);
            }else{
                //修改
                backvo = defdocService.edit(defdocList);
            }
            back.put("data",backvo);
            back.put("status",1);
        }catch (Exception e){
            back.put("status",0);
            back.put("msg","保存失败：" + e.getMessage());
            log.error("saveDefdoclist保存失败：" + e.getMessage());
        }
        return back;
    }

    @RequestMapping("/deleteDefdoclist")
    @ResponseBody
    public JSONObject deleteDefdoclist(@RequestBody DefdocListVO defdocList) {
        JSONObject back = new JSONObject();
        try {
            if (StringUtils.isEmpty(defdocList.getDefdoclistid())){
                throw new Exception("id不能为空!");
            }
            defdocService.delete(defdocList);
            back.put("status",1);
        }catch (Exception e){
            back.put("status",0);
            back.put("msg","删除失败：" + e.getMessage());
            log.error("deleteDefdoclist删除失败：" + e.getMessage());
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

    @RequestMapping("/saveDefdoc")
    @ResponseBody
    public JSONObject saveDefdoc(@RequestBody DefdocVO defdoc) {
        JSONObject back = new JSONObject();
        try {
            if (StringUtils.isEmpty(defdoc.getDefdocname()) || StringUtils.isEmpty(defdoc.getDefdoccode())){
                throw new Exception("名称或编码不能为空!");
            }
            if (StringUtils.isEmpty(defdoc.getDefdoclistid())){
                throw new Exception("档案父id不能为空!");
            }
            DefdocVO backvo = null;
            if(StringUtils.isEmpty(defdoc.getDefdocid())){
                //新增
                backvo = defdocService.add(defdoc);
            }else{
                //修改
                backvo = defdocService.edit(defdoc);
            }
            back.put("data",backvo);
            back.put("status",1);
        }catch (Exception e){
            back.put("status",0);
            back.put("msg","保存失败：" + e.getMessage());
            log.error("saveDefdoc保存失败：" + e.getMessage());
        }
        return back;
    }

    @RequestMapping("/deleteDefdoc")
    @ResponseBody
    public JSONObject deleteDefdoc(@RequestBody DefdocVO defdoc) {
        JSONObject back = new JSONObject();
        try {
            if (StringUtils.isEmpty(defdoc.getDefdocid())){
                throw new Exception("id不能为空!");
            }
            defdocService.delete(defdoc);
            back.put("status",1);
        }catch (Exception e){
            back.put("status",0);
            back.put("msg","删除失败：" + e.getMessage());
            log.error("deleteDefdoc删除失败：" + e.getMessage());
        }
        return back;
    }

}
