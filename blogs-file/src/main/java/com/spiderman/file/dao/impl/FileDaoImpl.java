package com.spiderman.file.dao.impl;

import com.spiderman.file.dao.FileDao;
import com.spiderman.file.entity.FileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FileDaoImpl implements FileDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public FileEntity insert(FileEntity file) {
        try {
            return mongoTemplate.save(file);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
