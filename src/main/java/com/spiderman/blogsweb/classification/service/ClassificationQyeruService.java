package com.spiderman.blogsweb.classification.service;

import com.spiderman.blogsweb.classification.entity.ClassificationEntity;
import com.spiderman.blogsweb.classification.model.ClassificationModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClassificationQyeruService {

    /**
     * 分页查询
     * @param querySearch 查询条件
     * @return 分页结果
     */
    Page<ClassificationEntity> queryAll(ClassificationModel querySearch);

    /**
     * 查询总条数
     * @param querySearch 查询条件
     * @return 总条数
     */
    long count(ClassificationModel querySearch);
}
