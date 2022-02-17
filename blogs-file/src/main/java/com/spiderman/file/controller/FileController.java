package com.spiderman.file.controller;

import com.alibaba.fastjson.JSONObject;
import com.spiderman.file.service.FileSaveService;
import com.spiderman.file.vo.FileVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/file")
public class FileController {

    private static final Logger log = LogManager.getLogger(FileController.class.getName());

    @Autowired
    private FileSaveService fileSaveService;


    @RequestMapping("/insert")
    @ResponseBody
    public JSONObject insert(FileVO file) {
        JSONObject back = new JSONObject();
        try {
            log.debug("文件上传日志：" + file.toString());
            String fileId = fileSaveService.insert(file);
            log.debug("文件上传结束日志：" + fileId );
            back.put("data",fileId);
            back.put("status",1);
        }catch (Exception e){
            back.put("status",0);
            back.put("msg","文件上传错误：" + e.getMessage());
            log.error("文件上传错误：" + e.getMessage());
        }
        return back;
    }

    @RequestMapping("/query")
    @ResponseBody
    public JSONObject queryImage(@RequestParam(value="fileCode") String fileCode) {
        JSONObject back = new JSONObject();
        try {
            String imageBase64 = fileSaveService.queryImage(fileCode);
            back.put("data","data:image/jpg;base64," + imageBase64);
            back.put("status",1);
        }catch (Exception e){
            back.put("status",0);
            back.put("msg","文件上传错误：" + e.getMessage());
            log.error("文件上传错误：" + e.getMessage());
        }
        return back;
    }

}
