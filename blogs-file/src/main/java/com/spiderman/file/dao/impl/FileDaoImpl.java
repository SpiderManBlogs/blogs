package com.spiderman.file.dao.impl;

import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.spiderman.file.dao.FileDao;
import com.spiderman.file.entity.FileEntity;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;

@Repository
public class FileDaoImpl implements FileDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private MongoTemplate mongodbFactory;

    @Override
    public FileEntity insert(FileEntity file) {
        return mongoTemplate.save(file);
    }

    @Override
    public String insertFile(InputStream image, String fileName) {
        ObjectId store = gridFsTemplate.store(image, fileName);
        return store.toString();
    }

    @Override
    public GridFsResource findFile(String fileCode){
        Query query = new Query(Criteria.where("_id").is(fileCode));
        GridFSFile fsFile = gridFsTemplate.findOne(query);
        assert fsFile != null;
        GridFSDownloadStream gridFSDownloadStream = GridFSBuckets.create(mongodbFactory.getDb()).openDownloadStream(fsFile.getObjectId());
        return new GridFsResource(fsFile, gridFSDownloadStream);
    }
}
