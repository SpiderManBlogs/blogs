package com.spiderman.file.dao;

import com.mongodb.client.gridfs.model.GridFSFile;
import com.spiderman.file.entity.FileEntity;
import org.apache.commons.fileupload.FileItem;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface FileDao {

    /**
     * 插入文件信息数据
     * @param file 文件信息
     * @return 保存后文件信息
     */
    FileEntity insert(FileEntity file);

    /**
     * GridFS 上传保存图片
     * @param image 图片文件
     * @param fileName 图片名称 文件集合id_文章id_字段id
     * @return 保存后图片id 用于查询
     */
    String insertFile(InputStream image, String fileName);

    /**
     * GridFS 查询图片
     * @param fileCode 文件_id
     * @return 文件流
     */
    InputStream findFile(String fileCode) throws IOException;
}
