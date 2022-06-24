package com.spiderman.blogsweb.admin.controller;

import com.spiderman.blogsweb.admin.model.Result;
import com.spiderman.blogsweb.file.service.FileSaveService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file")
public class AdminFileController {

    private static final Logger log = LogManager.getLogger(AdminFileController.class.getName());

    private FileSaveService fileSaveService;

    @Autowired
    public void setFileSaveService(FileSaveService fileSaveService) {
        this.fileSaveService = fileSaveService;
    }

    @RequestMapping("/upload")
    @ResponseBody
    public Result insert(@RequestBody MultipartFile file) {
        Result back = Result.instance();
        try {
            log.debug("文件上传日志：" + file.toString());
            String fileId = fileSaveService.insert(file);
            log.debug("文件上传结束日志：" + fileId );
            back.put("data",fileId);
            back.success();
        }catch (Exception e){
            back.fail();
            back.put("msg","文件上传错误：" + e.getMessage());
            log.error("文件上传错误：" + e.getMessage());
        }
        return back;
    }

}
