package com.spiderman.blogsweb.admin.controller;

import com.spiderman.blogsweb.admin.model.Result;
import com.spiderman.blogsweb.file.service.FileSaveService;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

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

    @GetMapping("/queryImage/{id}")
    @ResponseBody
    public void queryImage(@PathVariable(value = "id") String id, HttpServletResponse response) {
        InputStream inputStream = null;
        try {
            GridFsResource gridFsResource = fileSaveService.queryFile(id);
            inputStream = gridFsResource.getInputStream();
            response.setHeader("content-disposition", "attachment;filename=" + gridFsResource.getFilename());
            response.setContentType("image/jpeg");
            response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
            response.setContentLength((int) gridFsResource.contentLength());
            IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
        } catch (Exception e) {
            log.error("查询错误：" + e.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                log.error("关闭流错误：" + e.getMessage());
            }

        }
    }

}
