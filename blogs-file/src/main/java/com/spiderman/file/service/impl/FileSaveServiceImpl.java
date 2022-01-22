package com.spiderman.file.service.impl;

import com.spiderman.file.dao.FileDao;
import com.spiderman.file.entity.FileEntity;
import com.spiderman.file.service.FileSaveService;
import com.spiderman.file.vo.FileVO;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.UUID;

@Service
public class FileSaveServiceImpl implements FileSaveService {

    private static final Logger log = LogManager.getLogger(FileSaveServiceImpl.class.getName());

    @Autowired
    private FileDao fileDao;

    @Override
    public String insert(FileVO file) throws IOException {
        CommonsMultipartFile image = file.getFile();
        if(image == null){
            return null;
        }
        String fileId = UUID.randomUUID().toString();
        FileEntity entity = new FileEntity(fileId,image.getName(),image.getSize(),file.getBlogsId(),file.getFieldId());
        String imageName = fileId + "_" + file.getBlogsId() + "_" + file.getFieldId();
        String imageCode = fileDao.insertFile(image.getInputStream(), imageName);
        entity.setFileCode(imageCode);
        FileEntity backEntity = fileDao.insert(entity);
        return backEntity.getFileCode();
    }

    @Override
    public String queryImage(String fileCode) throws IOException {
        try (InputStream gridFSFile = fileDao.findFile(fileCode)) {
            byte[] bytes = IOUtils.toByteArray(gridFSFile);
            return Base64.getEncoder().encodeToString(bytes);
        } catch (IOException ioe) {
            log.error("查询图片流失败:" + ioe.getMessage());
            throw ioe;
        }
    }
}
