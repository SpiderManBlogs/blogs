package com.spiderman.file.service.impl;

import com.spiderman.file.dao.FileDao;
import com.spiderman.file.entity.FileEntity;
import com.spiderman.file.service.FileSaveService;
import com.spiderman.file.vo.FileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.Date;
import java.util.UUID;

@Service
public class FileSaveServiceImpl implements FileSaveService {

    @Autowired
    private FileDao fileDao;

    @Override
    public String insert(FileVO file) {


        CommonsMultipartFile image = file.getFile();
        if(image == null){
            return null;
        }
        String fileId = UUID.randomUUID().toString();
        FileEntity entity = new FileEntity(fileId,image.getName(),image.getSize(),file.getBlogsId(),file.getFieldId());
        FileEntity backEntity = fileDao.insert(entity);
        return backEntity.getFieldId();
    }
}
