package com.spiderman.file.service;

import com.mongodb.client.gridfs.model.GridFSFile;
import com.spiderman.file.entity.FileEntity;
import com.spiderman.file.vo.FileVO;
import org.springframework.data.mongodb.gridfs.GridFsResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface FileSaveService {

    /**
     * 文件保存
     * @param file 文件vo
     * @return 文件id
     */
    String insert(FileVO file) throws IOException;

    /**
     * 文件查询
     * @param fileCode 文件编码
     * @return 文件流
     */
    List<String> queryImage(String... fileCode) throws IOException;


    /**
     * 文件查询
     * @param fileCode 文件编码
     * @return 文件流
     * @throws IOException 异常
     */
    GridFsResource queryFile(String fileCode) throws IOException;

    /**
     * 视频文件切片
     * @param id 文件编码
     */
    boolean sectionFile(String id,String fileName);

    String downFile(String id);
}
