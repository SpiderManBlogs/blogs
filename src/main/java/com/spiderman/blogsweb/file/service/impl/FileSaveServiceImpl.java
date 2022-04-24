package com.spiderman.blogsweb.file.service.impl;

import com.spiderman.blogsweb.file.dao.FileDao;
import com.spiderman.blogsweb.file.entity.FileEntity;
import com.spiderman.blogsweb.file.service.FileSaveService;
import com.spiderman.blogsweb.file.util.FfmpegUtil;
import com.spiderman.blogsweb.file.vo.FileVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileSaveServiceImpl implements FileSaveService {

    private static final Logger log = LogManager.getLogger(FileSaveServiceImpl.class.getName());

    @Autowired
    private FileDao fileDao;

    @Value("${video.base}")
    private String BASEPATH;

    @Value("${video.section}")
    private String SECTION;

    @Value("${video.ffmpegExePath}")
    private String FFMPEG;

    @Override
    public String insert(FileVO file) throws IOException {
        CommonsMultipartFile image = file.getFile();
        if(image == null){
            return null;
        }
        String fileId = UUID.randomUUID().toString();
        FileEntity entity = new FileEntity(fileId,image.getFileItem().getName(),image.getFileItem().getSize(),file.getBlogsId(),file.getFieldId());
        String imageName = fileId + "_" + image.getFileItem().getName();
        String imageCode = fileDao.insertFile(image.getInputStream(), imageName);
        entity.setFileCode(imageCode);
        FileEntity backEntity = fileDao.insert(entity);
        return backEntity.getFileCode();
    }

    @Override
    public GridFsResource queryFile(String fileCode){
        return fileDao.findFile(fileCode);
    }


    @Override
    public String downFile(String id) {
        BufferedInputStream inFile=null;
        BufferedOutputStream outFile=null;
        String fileName = "文件存在";
        try {
            File fileId = new File(BASEPATH + id);
            log.debug("完整目录："+BASEPATH + id);
            if (!fileId.exists() && !fileId.isDirectory()) {
                log.debug(id+":文件夹不存在");
                log.debug("开始:创建文件夹");
                boolean ifmkdir = fileId.mkdir();
                if (!ifmkdir){
                    log.error("失败:创建文件夹");
                    return null;
                }
                log.debug("结束:创建文件夹");
                log.debug("开始:下载文件");
                GridFsResource gridFsResource = queryFile(id);
                fileName = gridFsResource.getFilename();
                inFile=new BufferedInputStream(gridFsResource.getInputStream());
                outFile=new BufferedOutputStream(new FileOutputStream(BASEPATH + id+SECTION+fileName));
                int len=-1;
                byte[] b=new byte[1024];
                while((len=inFile.read(b))!=-1){
                    outFile.write(b,0,len);
                }
                log.debug("结束:下载文件");
            }else{
                log.debug(id+":文件夹存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("文件下载失败:" + e.getMessage());
            log.error(e);
        }finally {
            try {
                if (inFile != null) {
                    inFile.close();
                }
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException e) {
                log.error("关闭流错误：" + e.getMessage());
            }
        }
        return fileName;
    }

    @Override
    public boolean sectionFile(String id,String fileName) {
        try {
//            log.debug("开始:转码文件");
//
//            log.debug("结束:转码文件");
            //已测试:mp4、flv格式
            log.debug("开始:切片文件");
            List<String> commands = new ArrayList<>();
            commands.add("ffmpeg");
            commands.add("-i");
            commands.add(BASEPATH + id + SECTION + fileName);
            commands.add("-f");
            commands.add("segment");
            commands.add("-segment_time");
            commands.add("60");
            commands.add("-segment_format");
            commands.add("mpegts");
            commands.add("-segment_list");
            commands.add(BASEPATH + id + SECTION + "video_name.m3u8");
            commands.add("-c");
            commands.add("copy");
            commands.add("-bsf:v");
            commands.add("h264_mp4toannexb");
            commands.add("-map");
            commands.add("0");
            commands.add(BASEPATH + id + SECTION + "course-%04d.ts");
            FfmpegUtil.newRunCommand(commands);
            log.debug("结束:切片文件");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("文件处理失败:" + e.getMessage());
            log.error(e);
        }
        return false;
    }
}
