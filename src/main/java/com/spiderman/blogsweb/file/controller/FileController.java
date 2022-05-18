package com.spiderman.blogsweb.file.controller;

import com.alibaba.fastjson.JSONObject;
import com.spiderman.blogsweb.file.service.FileSaveService;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
@RequestMapping("/file")
public class FileController {

    private static final Logger log = LogManager.getLogger(FileController.class.getName());

    @Autowired
    private FileSaveService fileSaveService;

    @Value("${video.base}")
    private String BASEPATH;

    @Value("${video.section}")
    private String SECTION;


    @RequestMapping("/insert")
    @ResponseBody
    public JSONObject insert(@RequestBody MultipartFile file) {
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

    @RequestMapping("/queryImage/{id}")
    @ResponseBody
    public void queryImage(@PathVariable(value = "id") String id, HttpServletResponse response) {
        InputStream inputStream = null;
        try {
            GridFsResource gridFsResource = fileSaveService.queryFile(id);
            inputStream = gridFsResource.getInputStream();
            response.setHeader("content-disposition", "attachment;filename=" + gridFsResource.getFilename());
            response.setContentType("video/mpeg4");
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

    @RequestMapping("/queryAudio")
    @ResponseBody
    public void queryAudio(@RequestParam String id, HttpServletResponse response) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            int len = 0;
            byte[] buffer = new byte[1024];
            GridFsResource gridFsResource = fileSaveService.queryFile(id);
            inputStream = gridFsResource.getInputStream();
            response.setHeader("content-disposition", "attachment;filename="+gridFsResource.getFilename());
            while ((len = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, len);
            }
        } catch (Exception e) {
            log.error("查询错误：" + e.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                log.error("关闭流错误：" + e.getMessage());
            }

        }
    }

    @RequestMapping("/queryVideo")
    @ResponseBody
    public void queryVideo(@RequestParam String id, HttpServletResponse response) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            int len = 0;
            byte[] buffer = new byte[1024];
            GridFsResource gridFsResource = fileSaveService.queryFile(id);
            inputStream = gridFsResource.getInputStream();
            response.setHeader("content-disposition", "attachment;filename="+gridFsResource.getFilename());
            response.setContentType("video/mpeg4");
            response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
            response.setContentLength((int) gridFsResource.contentLength());
//            while ((len = inputStream.read(buffer)) > 0) {
//                outputStream.write(buffer, 0, len);
//            }

            IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
        } catch (Exception e) {
            log.error("查询错误：" + e.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                log.error("关闭流错误：" + e.getMessage());
            }

        }
    }

    @RequestMapping("/queryM3U8/{id}/{name}.m3u8")
    @ResponseBody
    public void queryM3U8(@PathVariable(value = "id") String id,@PathVariable(value = "name") String name, HttpServletResponse response) {
        String fileName = fileSaveService.downFile(id);
        if ("文件存在".equals(fileName)){
            queryM3U8Util(id,name,".m3u8",response);
        }else if (!StringUtils.isEmpty(fileName) && fileSaveService.sectionFile(id,fileName)){
            queryM3U8Util(id,name,".m3u8",response);
        }
    }

    @RequestMapping("/queryM3U8/{id}/{name}.ts")
    @ResponseBody
    public void queryTs(@PathVariable(value = "id") String id,@PathVariable(value = "name") String name, HttpServletResponse response) {
        queryM3U8Util(id,name,".ts",response);
    }

    private void queryM3U8Util(String id,String name,String type,HttpServletResponse response){
        String fileUrl = BASEPATH + id + SECTION + name+type;
        File file = new File(fileUrl);
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            response.setHeader("content-disposition", "attachment;filename="+name);
            response.setContentType("video/mpeg4");
            response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
            response.setContentLength((int) file.length());
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
