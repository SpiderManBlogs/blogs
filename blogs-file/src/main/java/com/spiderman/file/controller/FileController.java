package com.spiderman.file.controller;

import com.alibaba.fastjson.JSONObject;
import com.spiderman.file.entity.FileEntity;
import com.spiderman.file.service.FileSaveService;
import com.spiderman.file.vo.FileVO;
import org.apache.tomcat.util.json.JSONParser;
import org.bson.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileSaveService fileSaveService;


    @RequestMapping("/insert")
    @ResponseBody
    public String insert(FileVO file) {
        String fileId = fileSaveService.insert(file);
        return "添加成功,添加后的用户id为：" + fileId;
    }

}
