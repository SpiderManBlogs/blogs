package com.spiderman.file.service;

import com.spiderman.file.entity.FileEntity;
import com.spiderman.file.vo.FileVO;

public interface FileSaveService {

    /**
     * 文件保存
     * @param file 文件vo
     * @return 文件id
     */
    String insert(FileVO file);
}
