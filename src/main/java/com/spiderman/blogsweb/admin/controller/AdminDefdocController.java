package com.spiderman.blogsweb.admin.controller;

import com.spiderman.blogsweb.admin.model.DefdocListQueryModel;
import com.spiderman.blogsweb.admin.model.Result;
import com.spiderman.blogsweb.admin.model.TableListResult;
import com.spiderman.blogsweb.defdoc.service.DefdocQueryService;
import com.spiderman.blogsweb.defdoc.service.DefdocService;
import com.spiderman.blogsweb.defdoc.vo.DefdocListVO;
import com.spiderman.blogsweb.defdoc.vo.DefdocVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/defdoc")
public class AdminDefdocController {

    private static final Logger log = LogManager.getLogger(AdminDefdocController.class.getName());

    @Autowired
    private DefdocQueryService defdocQueryService;

    @Autowired
    private DefdocService defdocService;

    @GetMapping("/queryList")
    @ResponseBody
    public TableListResult queryList(DefdocListQueryModel querySearch) {
        TableListResult result = TableListResult.instance();
        try {
            List<ObjectId> list = defdocQueryService.queryAllId(querySearch);
            int total = list.size();
            int current = querySearch.getCurrent();
            int pageSize = querySearch.getPageSize();
            list = list.stream().skip((long) (current - 1) *pageSize).limit(pageSize).collect(Collectors.toList());
            List<DefdocListVO> query;
            if (list.size() > 0){
                query = defdocQueryService.query(list.toArray(new ObjectId[0]));
            }else {
                query = new ArrayList<>();
            }
            result.success();
            result.setData(current,pageSize,total,query);
        }catch (Exception e){
            result.fail();
            result.setErrorCode("查询失败：" + e.getMessage());
            log.error("查询失败：" + e.getMessage());
        }
        return result;
    }

    @RequestMapping("/saveDefdoclist")
    @ResponseBody
    public Result saveDefdoclist(@RequestBody DefdocListVO defdocList) {
        Result result = Result.instance();
        try {
            if (!StringUtils.hasText(defdocList.getDefdoclistname()) || !StringUtils.hasText(defdocList.getDefdoclistcode())){
                throw new Exception("名称或编码不能为空!");
            }
            DefdocListVO backvo = null;
            if(!StringUtils.hasText(defdocList.getDefdoclistid())){
                //新增
                backvo = defdocService.add(defdocList);
            }else{
                //修改
                backvo = defdocService.edit(defdocList);
            }
            result.success();
            result.put("data",backvo);
        }catch (Exception e){
            result.fail();
            result.setErrorMessage("保存失败：" + e.getMessage());
            log.error("saveDefdoclist保存失败：" + e.getMessage());
        }
        return result;
    }

    @RequestMapping("/deleteDefdoclist")
    @ResponseBody
    public Result deleteDefdoclist(@RequestBody DefdocListVO... defdocList) {
        Result result = Result.instance();
        try {
            List<DefdocListVO> collect = Arrays.stream(defdocList)
                    .filter(item -> StringUtils.hasText(item.getDefdoclistid()))
                    .filter(item -> item.getEnablement() == 0)
                    .collect(Collectors.toList());
            for (DefdocListVO item:collect) {
                defdocService.delete(item);
            }
            result.success();
            result.put("data",true);
        }catch (Exception e){
            result.fail();
            result.setErrorMessage("删除失败：" + e.getMessage());
            log.error("deleteDefdoclist删除失败：" + e.getMessage());
        }
        return result;
    }



    @RequestMapping("/saveDefdoc")
    @ResponseBody
    public Result saveDefdoc(@RequestBody DefdocVO... defdoc) {
        Result result = Result.instance();
        try {
            List<DefdocVO> defdocs = Arrays.stream(defdoc)
                    .filter(item -> StringUtils.hasText(item.getDefdocname()) && StringUtils.hasText(item.getDefdoccode()))
                    .collect(Collectors.toList());
            List<DefdocVO> backvo = new ArrayList<>();
            List<DefdocVO> backadd = defdocService.add(defdocs.stream().filter(item -> !StringUtils.hasText(item.getDefdoclistid())).toArray(DefdocVO[]::new));
            List<DefdocVO> backedit = defdocService.edit(defdocs.stream().filter(item -> StringUtils.hasText(item.getDefdoclistid())).toArray(DefdocVO[]::new));
            backvo.addAll(backadd);
            backvo.addAll(backedit);
            result.success();
            result.put("data",backvo);
        }catch (Exception e){
            result.fail();
            result.setErrorMessage("保存失败：" + e.getMessage());
            log.error("saveDefdoclist保存失败：" + e.getMessage());
        }
        return result;
    }

}
