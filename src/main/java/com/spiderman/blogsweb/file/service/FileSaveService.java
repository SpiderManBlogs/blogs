package com.spiderman.blogsweb.file.service;

import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileSaveService {

    /**
     * 文件保存
     * @param file 文件vo
     * @return 文件id
     */
    String insert(MultipartFile file) throws IOException;

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

    /**
     * 文件下载存储
     * @param id 文件编码
     * @return 文件名称
     */
    String downFile(String id);
}
