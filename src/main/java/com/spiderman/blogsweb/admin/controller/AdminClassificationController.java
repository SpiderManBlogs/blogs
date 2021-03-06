package com.spiderman.blogsweb.admin.controller;


import com.alibaba.fastjson.JSONObject;
import com.spiderman.blogsweb.admin.model.Result;
import com.spiderman.blogsweb.admin.model.TableListResult;
import com.spiderman.blogsweb.classification.entity.ClassificationEntity;
import com.spiderman.blogsweb.classification.model.ClassificationModel;
import com.spiderman.blogsweb.classification.service.ClassificationQueryService;
import com.spiderman.blogsweb.classification.service.ClassificationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/classification")
public class AdminClassificationController {

    private static final Logger log = LogManager.getLogger(AdminClassificationController.class.getName());

    private ClassificationService service;
    private ClassificationQueryService query;

    @Autowired
    public void setService(ClassificationService service) {
        this.service = service;
    }

    @Autowired
    public void setQuery(ClassificationQueryService query) {
        this.query = query;
    }

    @GetMapping("/queryPages")
    @ResponseBody
    public TableListResult queryPages(ClassificationModel querySearch){
        TableListResult result = TableListResult.instance();
        try {
            Page<ClassificationEntity> classificationEntities = query.queryAll(querySearch);
            result.success();
            result.setData(querySearch.getCurrent(),querySearch.getPageSize(),classificationEntities.getTotalElements(),classificationEntities.getContent());
        }catch (Exception e){
            result.fail();
            result.setErrorMessage("查询失败！" + e.getMessage());
            log.error("分类查询失败！" + e.getMessage());
        }
        return result;
    }

    @GetMapping("/query")
    @ResponseBody
    public Result query(){
        Result result = Result.instance();
        try {
            List<ClassificationModel> classificationModels = query.queryAll();
            List<JSONObject> collect = classificationModels.stream().map(item -> {
                JSONObject object = new JSONObject();
                object.put("name", item.getName());
                object.put("code", item.getCode());
                object.put("id", item.getId());
                return object;
            }).collect(Collectors.toList());
            result.success();
            result.put("data",collect);
        }catch (Exception e){
            result.fail();
            result.setErrorMessage("查询失败！" + e.getMessage());
            log.error("分类查询失败！" + e.getMessage());
        }
        return result;
    }

    @RequestMapping("/save")
    @ResponseBody
    public Result save(@RequestBody ClassificationModel classificationModel){
        Result result = Result.instance();
        try {
            if (!StringUtils.hasText(classificationModel.getCode()) || !StringUtils.hasText(classificationModel.getName())){
                throw new Exception("名称或编码不能为空!");
            }
            ClassificationModel back;
            if (StringUtils.hasText(classificationModel.getId())){
                back = service.update(classificationModel);
            }else {
                back = service.add(classificationModel);
            }
            result.success();
            result.put("data",back);
        }catch (Exception e){
            result.fail();
            result.setErrorMessage("保存失败！" + e.getMessage());
            log.error("分类保存失败！" + e.getMessage());
        }
        return result;
    }

    @RequestMapping("/enable")
    @ResponseBody
    public Result enable(@RequestParam String id){
        Result result = Result.instance();
        try {
            int count = service.updateEnable(id,true);
            if (count == 1){
                result.success();
                result.put("data",true);
            }else{
                throw new Exception("更新数据失败！count!=1");
            }
        }catch (Exception e){
            result.fail();
            result.setErrorMessage("启用失败！" + e.getMessage());
            log.error("分类启用失败！" + e.getMessage());
        }
        return result;
    }

    @RequestMapping("/unenable")
    @ResponseBody
    public Result unenable(@RequestParam String id){
        Result result = Result.instance();
        try {
            int count = service.updateEnable(id,false);
            if (count == 1){
                result.success();
                result.put("data",true);
            }else{
                throw new Exception("更新数据失败！count!=1");
            }
        }catch (Exception e){
            result.fail();
            result.setErrorMessage("停用失败！" + e.getMessage());
            log.error("分类停用失败！" + e.getMessage());
        }
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(@RequestParam String id){
        Result result = Result.instance();
        try {
            service.delete(id);
            result.success();
            result.put("data",true);
        }catch (Exception e){
            result.fail();
            result.setErrorMessage("停用失败！" + e.getMessage());
            log.error("分类停用失败！" + e.getMessage());
        }
        return result;
    }

}
